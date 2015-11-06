package com.confianza.webapp.service.facturacion.facapcomisiones;

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
import com.confianza.webapp.repository.facturacion.facapcomisiones.FacApcomisiones;
import com.confianza.webapp.repository.facturacion.facapcomisiones.FacApcomisionesRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacApcomisionesServiceImpl implements FacApcomisionesService{
	
	@Autowired
	private FacApcomisionesRepository facapcomisionesRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facapcomisionesRepository
	 */
	public FacApcomisionesRepository getFacApcomisionesRepository() {
		return facapcomisionesRepository;
	}

	/**
	 * @param facapcomisionesRepository the facapcomisionesRepository to set
	 */
	public void setFacApcomisionesRepository(FacApcomisionesRepository facapcomisionesRepository) {
		this.facapcomisionesRepository = facapcomisionesRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_APCOMISIONES_ALL", "FAC_APCOMISIONES_READ"})
	public String list(Long id){
		FacApcomisiones listAll=facapcomisionesRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_APCOMISIONES_ALL", "FAC_APCOMISIONES_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacApcomisiones> listAll=facapcomisionesRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facapcomisionesRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_APCOMISIONES_ALL", "FAC_APCOMISIONES_UPDATE"})
	public String update(FacApcomisiones facapcomisiones){
		return gson.toJson(facapcomisionesRepository.update(facapcomisiones));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_APCOMISIONES_ALL", "FAC_APCOMISIONES_DELETE"})
	public void delete(FacApcomisiones facapcomisiones){
		facapcomisionesRepository.delete(facapcomisiones);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_APCOMISIONES_ALL", "FAC_APCOMISIONES_CREATE"})
	public String insert(FacApcomisiones facapcomisiones){
		return gson.toJson(facapcomisionesRepository.insert(facapcomisiones));
	}
	
}
