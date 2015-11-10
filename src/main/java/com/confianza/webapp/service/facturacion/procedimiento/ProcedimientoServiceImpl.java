package com.confianza.webapp.service.facturacion.procedimiento;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.facturacion.faccomision.FacComision;
import com.confianza.webapp.repository.facturacion.facestaproc.FacEstaproc;
import com.confianza.webapp.repository.facturacion.facliquidacion.FacLiquidacion;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.repository.framework.frmtablas.FrmTablas;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.service.email.sendEmail.SendEmail;
import com.confianza.webapp.service.facturacion.facestaproc.FacEstaprocService;
import com.confianza.webapp.service.facturacion.facliquidacion.FacLiquidacionService;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.pila.pilusua.PilUsuaService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProcedimientoServiceImpl implements ProcedimientoService{
	
	SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-YY");
	
	@Autowired
	Gson gson;	

	@Autowired
	private userDetails userDetails;
	
	@Autowired
	private FrmTablasService frmTablasService;
	
	@Autowired
	private FrmConsultaService frmConsultaService;
	
	@Autowired
	private FrmParametroService frmParametroService;
	
	@Autowired
	private FacEstaprocService facEstaprocService;
	
	@Autowired
	private FacLiquidacionService facLiquidacionService;
	
	@Autowired
	private PilUsuaService pilUsuaService;
	
	@Autowired
	private SendEmail sendEmail;
	
	@Override
	public String executeProcess(String conscons, String params, HttpServletRequest request){
		
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters = gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)   								
		
		FrmConsulta procedureOfQuery = frmConsultaService.listProcedureChild(conscons);
		
		List<FrmParametro> parametersProcedure = this.frmParametroService.listParamsCosuType(procedureOfQuery.getConscons());
		Map<String, Object> parametersEnterProcedure = createParametersQueryChild(parameters, parametersProcedure);
		
		List<FacEstaproc> activeEstaproc=facEstaprocService.listAll(0, 0, null, createFilters(procedureOfQuery.getConsnomb()));
		Map<String, Object> resultProcess = validateForStartProcess(request, procedureOfQuery, parametersProcedure, parametersEnterProcedure, activeEstaproc);
		
		return gson.toJson(resultProcess);		
	}

	private Map<String, Object> validateForStartProcess(HttpServletRequest request, FrmConsulta procedureOfQuery, List<FrmParametro> parametersProcedure, Map<String, Object> parametersEnterProcedure, List<FacEstaproc> activeEstaproc) {
		Map<String, Object> resultProcess = new HashMap<String, Object>();
				
		if(activeEstaproc.size()==0)
			resultProcess = doProcess(request, procedureOfQuery, parametersProcedure, parametersEnterProcedure, 0);		
		else
			resultProcess.put("Eror", generateDescriptionProcessActives(activeEstaproc));
		
		return resultProcess;
	}	

	private String generateDescriptionProcessActives( List<FacEstaproc> activeEstaproc) {
		String error="";
		for(FacEstaproc process:activeEstaproc)
			error+="El proceso: "+process.getEsprnomb()+", identificado con el ID: "+process.getEsprcons()+", por el usuario:"+process.getEspruser()+" Se encuentra en estado: "+process.getEspresta()+"<br>";
		return error;
	}
	
	private Map<String, Object> doProcess(HttpServletRequest request, FrmConsulta procedureOfQuery, List<FrmParametro> parametersProcedure, Map<String, Object> parametersEnterProcedure, long porcentaje) {
		
		FacEstaproc facEstaproc = facEstaprocService.insert(procedureOfQuery.getConsnomb(), "Inicio del procedimiento: "+procedureOfQuery.getConsnomb(), userDetails.getUser(), "I");
		Map<String, Object> resultProcedure = frmConsultaService.loadProcedure(procedureOfQuery, parametersProcedure, parametersEnterProcedure, null);
		facEstaproc=modifyEstaProc(facEstaproc, porcentaje, "\nFinalizo la ejecucion del procedimiento: "+procedureOfQuery.getConsnomb(), null);
		
		facEstaproc = startComisiones(procedureOfQuery, facEstaproc, parametersEnterProcedure);
		
		facEstaprocService.closeFinal(facEstaproc);
		
		notifyByEmailEnd(request, procedureOfQuery);
		
		return resultProcedure;
	}

	private FacEstaproc startComisiones(FrmConsulta procedureOfQuery, FacEstaproc facEstaproc, Map<String, Object> parameters) {
		facEstaproc=modifyEstaProc(facEstaproc, 50, "\nInicio del llenado de comisiones: "+procedureOfQuery.getConsnomb(), null);
		processComisiones(procedureOfQuery, parameters);
		facEstaproc=modifyEstaProc(facEstaproc, 50, "\nFinalizo la ejecucion del del llenado de comisiones: "+procedureOfQuery.getConsnomb(), null);
		return facEstaproc;
	}

	private void processComisiones(FrmConsulta procedureOfQuery, Map<String, Object> parameters) {
		FrmConsulta queryComisiones=frmConsultaService.listChild(procedureOfQuery.getConscons().toString());
		List<Object[]> comisiones=frmConsultaService.loadListData(queryComisiones, null, null);
		String[] headersComision = queryComisiones.getConscolu().split(",");
		String numeroCorte="";
		FacLiquidacion facLiquidacion=new FacLiquidacion();
		double netoPagar=0;
		
		for(Object[] comision:comisiones){
			if(!numeroCorte.equals(comision[getIndexof(headersComision, "NUMCORTE")])){
				netoPagar=updateTotalComision(facLiquidacion, netoPagar);
				numeroCorte=(String) comision[getIndexof(headersComision, "NUMCORTE")];
				facLiquidacion=newFacLiquidacion(comision, headersComision, parameters, numeroCorte);	
			}
			netoPagar+=createComision(facLiquidacion, comision, headersComision);
		}
	}

	private double updateTotalComision(FacLiquidacion facLiquidacion, double netoPagar) {
		if(facLiquidacion.getLiqunuco()!=null){
			facLiquidacion.setLiqutoco(netoPagar);
			facLiquidacionService.update(facLiquidacion);
			
			processAjustePagoComisiones();
			return 0;
		}
		return netoPagar;
	}
	
	private void processAjustePagoComisiones(Map<String, Object> parameters) {
		FrmConsulta queryComisiones=frmConsultaService.listName("CONSULTA DE LOS APC");
				
		
	}
	
	private FacLiquidacion newFacLiquidacion(Object[] comision, String[] headersComision, Map<String, Object> parameters, String numeroCorte){
		PilUsua pilUsua=pilUsuaService.listByNit(comision[getIndexof(headersComision, "NIT")].toString());
		return createFacLiquidacion(parameters, numeroCorte, pilUsua);	
	}
	
	private FacLiquidacion createFacLiquidacion(Map<String, Object> parameters, String numeroCorte, PilUsua pilUsua) {
		FacLiquidacion facLiquidacion=new FacLiquidacion();
		facLiquidacion.setLiquusua(pilUsua.getUsuacons());
		try {
			facLiquidacion.setLiqufein(formatDate.parse(parameters.get("FECINI").toString()));
			facLiquidacion.setLiqufefi(formatDate.parse(parameters.get("FECINI").toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		facLiquidacion.setLiqunuco(Long.parseLong(numeroCorte));
		facLiquidacion.setLiqufecr(new Date());
		facLiquidacion.setLiquesta("A");
		facLiquidacionService.insert(facLiquidacion);
		return facLiquidacion;
	}

	private double createComision(FacLiquidacion facLiquidacion, Object[] comision, String[] headersComision){
		FacComision facComision=new FacComision();
		facComision.setComiliqu(facLiquidacion.getLiqucons());
		try {
			facComision.setComifere(formatDate.parse(comision[getIndexof(headersComision, "FEC_RC")].toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		facComision.setComidore(comision[getIndexof(headersComision, "NRO_RC")].toString());
		facComision.setComidocu(comision[getIndexof(headersComision, "NUMDOC")].toString());
		facComision.setComipoli(comision[getIndexof(headersComision, "POLIZA")].toString());		
		facComision.setComicert(comision[getIndexof(headersComision, "CERTIFBOLETA")].toString());
		facComision.setComitoma(comision[getIndexof(headersComision, "CLIENTE")].toString());
		facComision.setComiprim(Double.parseDouble(comision[getIndexof(headersComision, "PRIMA")].toString()));
		facComision.setComipoco(Double.parseDouble(comision[getIndexof(headersComision, "PORCENTAJECOMISION")].toString()));
		facComision.setComicomi(Long.parseLong(comision[getIndexof(headersComision, "COMISION")].toString()));
		facComision.setComiivad(Double.parseDouble(comision[getIndexof(headersComision, "IVADESC")].toString()));
		facComision.setComiivar(Double.parseDouble(comision[getIndexof(headersComision, "IVARETE")].toString()));
		facComision.setComirete(Double.parseDouble(comision[getIndexof(headersComision, "RETEFUENTE")].toString()));
		facComision.setComicree(Double.parseDouble(comision[getIndexof(headersComision, "RETCREE")].toString()));
		facComision.setComivica(Double.parseDouble(comision[getIndexof(headersComision, "ICA")].toString()));
		facComision.setCominepa(Double.parseDouble(comision[getIndexof(headersComision, "NETO")].toString()));
		facComision.setComiesta("A");
		return facComision.getCominepa();
	}
	
	private int getIndexof(String[] headers, String header){
		for(int i=0;i<headers.length;i++)
			if(headers[i].equals(header))
				return i;
		return -1;
	}
	
	private void notifyByEmailEnd(HttpServletRequest request, FrmConsulta procedureOfQuery) {
		List<FrmTablas> listCorreos=frmTablasService.listByCodi("correo"+procedureOfQuery.getConscons());
		String to=null;
		String[] cc=new String[listCorreos.size()-1];
		int i=0;
		for(FrmTablas registro:listCorreos){
			if(to==null)
				to=registro.getTablcodi();
			else
				cc[i++]=registro.getTablcodi();
		}
		sendEmail.sendMessage("Facturacion", "Proceso Finalizado", "El procedimiento: "+procedureOfQuery.getConsdesc()+", ha finalizado", to, cc, request);
	}

	private List<Filter> createFilters(String nameProcess) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter=new Filter();
		filter.setCampo("espresta");
		filter.setTipo("=");
		filter.setTipodato("String");
		filter.setVal1("I");
		filters.add(filter);
		filter=new Filter();
		filter.setCampo("esprnomb");
		filter.setTipo("=");
		filter.setTipodato("String");
		filter.setVal1(nameProcess);
		filters.add(filter);
		return filters;
	}

	private FacEstaproc modifyEstaProc(FacEstaproc facEstaproc, long porcentaje, String descripcion, String error) {
				
		if(porcentaje!=0)
			facEstaproc.setEsprporc(facEstaproc.getEsprporc()+porcentaje);
		if(descripcion!=null)
			facEstaproc.setEsprdesc(facEstaproc.getEsprdesc()+descripcion);
		if(error!=null)
			facEstaproc.setEspreror(facEstaproc.getEspreror()+error);
		facEstaprocService.update(facEstaproc);
		return facEstaproc;
	}		
	

	private Map<String, Object> createParametersQueryChild( Map<String, Object> parameters, List<FrmParametro> parametros) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		for(FrmParametro parameter:parametros)
			parametersChild.put(parameter.getParanomb(), parameters.get(parameter.getParanomb()));
		return parametersChild;
	}

}
