package com.confianza.webapp.service.facturacion.facresumenajustes;

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
import com.confianza.webapp.repository.facturacion.facresumenajustes.FacResumenajustes;
import com.confianza.webapp.repository.facturacion.facresumenajustes.FacResumenajustesRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacResumenajustesServiceImpl implements FacResumenajustesService{
	
	@Autowired
	private FacResumenajustesRepository facresumenajustesRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facresumenajustesRepository
	 */
	public FacResumenajustesRepository getFacResumenajustesRepository() {
		return facresumenajustesRepository;
	}

	/**
	 * @param facresumenajustesRepository the facresumenajustesRepository to set
	 */
	public void setFacResumenajustesRepository(FacResumenajustesRepository facresumenajustesRepository) {
		this.facresumenajustesRepository = facresumenajustesRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENAJUSTES_ALL", "FAC_RESUMENAJUSTES_READ"})
	public String list(Long id){
		FacResumenajustes listAll=facresumenajustesRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENAJUSTES_ALL", "FAC_RESUMENAJUSTES_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacResumenajustes> listAll=facresumenajustesRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facresumenajustesRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENAJUSTES_ALL", "FAC_RESUMENAJUSTES_UPDATE"})
	public String update(FacResumenajustes facresumenajustes){
		return gson.toJson(facresumenajustesRepository.update(facresumenajustes));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENAJUSTES_ALL", "FAC_RESUMENAJUSTES_DELETE"})
	public void delete(FacResumenajustes facresumenajustes){
		facresumenajustesRepository.delete(facresumenajustes);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_RESUMENAJUSTES_ALL", "FAC_RESUMENAJUSTES_CREATE"})
	public String insert(FacResumenajustes facresumenajustes){
		return gson.toJson(facresumenajustesRepository.insert(facresumenajustes));
	}
	
}
