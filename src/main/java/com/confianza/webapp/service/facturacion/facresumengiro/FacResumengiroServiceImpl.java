package com.confianza.webapp.service.facturacion.facresumengiro;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		facturacion  
  */                          

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.facturacion.facresumengiro.FacResumengiro;
import com.confianza.webapp.repository.facturacion.facresumengiro.FacResumengiroRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacResumengiroServiceImpl implements FacResumengiroService{
	
	@Autowired
	private FacResumengiroRepository facresumengiroRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facresumengiroRepository
	 */
	public FacResumengiroRepository getFacResumengiroRepository() {
		return facresumengiroRepository;
	}

	/**
	 * @param facresumengiroRepository the facresumengiroRepository to set
	 */
	public void setFacResumengiroRepository(FacResumengiroRepository facresumengiroRepository) {
		this.facresumengiroRepository = facresumengiroRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENGIRO_ALL", "FAC_RESUMENGIRO_READ"})
	public String list(Long id){
		FacResumengiro listAll=facresumengiroRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENGIRO_ALL", "FAC_RESUMENGIRO_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacResumengiro> listAll=facresumengiroRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facresumengiroRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENGIRO_ALL", "FAC_RESUMENGIRO_UPDATE"})
	public String update(FacResumengiro facresumengiro){
		return gson.toJson(facresumengiroRepository.update(facresumengiro));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENGIRO_ALL", "FAC_RESUMENGIRO_DELETE"})
	public void delete(FacResumengiro facresumengiro){
		facresumengiroRepository.delete(facresumengiro);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENGIRO_ALL", "FAC_RESUMENGIRO_CREATE"})
	public String insert(FacResumengiro facresumengiro){
		return gson.toJson(facresumengiroRepository.insert(facresumengiro));
	}
	
}
