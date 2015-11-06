package com.confianza.webapp.service.facturacion.facestaproc;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		facturacion  
  */                          

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.confianza.webapp.repository.facturacion.facestaproc.FacEstaproc;
import com.confianza.webapp.repository.facturacion.facestaproc.FacEstaprocRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacEstaprocServiceImpl implements FacEstaprocService{
	
	@Autowired
	private FacEstaprocRepository facestaprocRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facestaprocRepository
	 */
	public FacEstaprocRepository getFacEstaprocRepository() {
		return facestaprocRepository;
	}

	/**
	 * @param facestaprocRepository the facestaprocRepository to set
	 */
	public void setFacEstaprocRepository(FacEstaprocRepository facestaprocRepository) {
		this.facestaprocRepository = facestaprocRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTAPROC_ALL", "FAC_ESTAPROC_READ"})
	public String list(Long id){
		FacEstaproc listAll=facestaprocRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTAPROC_ALL", "FAC_ESTAPROC_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacEstaproc> listAll=facestaprocRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public List<FacEstaproc> listAll(int pageSize, int page, String order, List<Filter> filters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FacEstaproc> listAll=facestaprocRepository.listAll(init, limit, order, filters);				
		
		return listAll;	
	}
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facestaprocRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTAPROC_ALL", "FAC_ESTAPROC_UPDATE"})
	public String update(FacEstaproc facestaproc){
		return gson.toJson(facestaprocRepository.update(facestaproc));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTAPROC_ALL", "FAC_ESTAPROC_DELETE"})
	public void delete(FacEstaproc facestaproc){
		facestaprocRepository.delete(facestaproc);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTAPROC_ALL", "FAC_ESTAPROC_CREATE"})
	public String insert(FacEstaproc facestaproc){
		return gson.toJson(facestaprocRepository.insert(facestaproc));
	}
	
	@Override	
	public FacEstaproc insert(String esprnomb, String esprdesc, String espruser, String espresta){
		FacEstaproc facEstaproc=createFacEstaproc(esprnomb, esprdesc, espruser, espresta);
		
		return facestaprocRepository.insert(facEstaproc);
	}
	
	@Override	
	public FacEstaproc closeFinal(FacEstaproc facEstaproc){
		facEstaproc.setEsprporc((long) 100);
		facEstaproc.setEsprdesc(facEstaproc.getEsprdesc()+"\nEl proceso finalizo");
		facEstaproc.setEspresta("F");
		facEstaproc.setEsprfefi(new Date());
		facEstaproc.setEsprduho(calculateHours(facEstaproc.getEsprfefi(), facEstaproc.getEsprfein()));		
		return facestaprocRepository.update(facEstaproc);
	}
	
	private double calculateHours(Date fefi, Date feini){
		double diferenciaEn_ms = fefi.getTime() - feini.getTime();
		double hours = diferenciaEn_ms / (1000 * 60 * 60);
		return hours;
	}
	
	private FacEstaproc createFacEstaproc(String esprnomb, String esprdesc, String espruser, String espresta) {
		FacEstaproc facEstaproc=new FacEstaproc();
		facEstaproc.setEsprnomb(esprnomb);
		facEstaproc.setEsprdesc(esprdesc);
		facEstaproc.setEspresta(espresta);
		facEstaproc.setEspruser(espruser);
		
		facEstaproc.setEsprduho(0.0);
		facEstaproc.setEspreror("");		
		facEstaproc.setEsprfein(new Date());
		facEstaproc.setEsprporc(new Long(0));
		return facEstaproc;
	}
}
