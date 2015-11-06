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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.facturacion.facestaproc.FacEstaproc;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.repository.framework.frmtablas.FrmTablas;
import com.confianza.webapp.service.facturacion.facestaproc.FacEstaprocService;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ProcedimientoServiceImpl implements ProcedimientoService{
	
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
	
	@Override
	public String executeProcess(String conscons, String params, HttpServletRequest request){
		
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters = gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)   								
		
		FrmConsulta procedureOfQuery = frmConsultaService.listProcedureChild(conscons);
		
		List<FrmParametro> parametersProcedure = this.frmParametroService.listParamsCosuType(procedureOfQuery.getConscons());
		Map<String, Object> parametersEnterProcedure = createParametersQueryChild(parameters, parametersProcedure);
		
		List<FacEstaproc> activeEstaproc=facEstaprocService.listAll(0, 0, null, createFilters(procedureOfQuery.getConsnomb()));
		Map<String, Object> resultProcess = validateForStartProcess(request, parameters, procedureOfQuery, parametersProcedure, parametersEnterProcedure, activeEstaproc);
		
		return gson.toJson(resultProcess);		
	}

	private Map<String, Object> validateForStartProcess(HttpServletRequest request, Map<String, Object> parameters, FrmConsulta procedureOfQuery, List<FrmParametro> parametersProcedure, Map<String, Object> parametersEnterProcedure, List<FacEstaproc> activeEstaproc) {
		Map<String, Object> resultProcess = new HashMap<String, Object>();
				
		if(activeEstaproc.size()==0)
			resultProcess = doProcess(request, parameters, procedureOfQuery, parametersProcedure, parametersEnterProcedure, 0);		
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
	
	private Map<String, Object> doProcess(HttpServletRequest request, Map<String, Object> parameters, FrmConsulta procedureOfQuery, List<FrmParametro> parametersProcedure, Map<String, Object> parametersEnterProcedure, long porcentaje) {
		
		FacEstaproc facEstaproc = facEstaprocService.insert(procedureOfQuery.getConsnomb(), "Inicio del procedimiento: "+procedureOfQuery.getConsnomb(), userDetails.getUser(), "I");
		Map<String, Object> resultProcedure = frmConsultaService.loadProcedure(procedureOfQuery, parametersProcedure, parametersEnterProcedure, null);
		facEstaproc=modifyEstaProc(facEstaproc, porcentaje, "\nFinalizo la ejecucion del procedimiento: "+procedureOfQuery.getConsnomb(), null);
				
		facEstaprocService.closeFinal(facEstaproc);
		
		return resultProcedure;
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
