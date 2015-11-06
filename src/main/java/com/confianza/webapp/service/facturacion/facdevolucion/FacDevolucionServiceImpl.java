package com.confianza.webapp.service.facturacion.facdevolucion;

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
import com.confianza.webapp.repository.facturacion.facdevolucion.FacDevolucion;
import com.confianza.webapp.repository.facturacion.facdevolucion.FacDevolucionRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacDevolucionServiceImpl implements FacDevolucionService{
	
	@Autowired
	private FacDevolucionRepository facdevolucionRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facdevolucionRepository
	 */
	public FacDevolucionRepository getFacDevolucionRepository() {
		return facdevolucionRepository;
	}

	/**
	 * @param facdevolucionRepository the facdevolucionRepository to set
	 */
	public void setFacDevolucionRepository(FacDevolucionRepository facdevolucionRepository) {
		this.facdevolucionRepository = facdevolucionRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_DEVOLUCION_ALL", "FAC_DEVOLUCION_READ"})
	public String list(Long id){
		FacDevolucion listAll=facdevolucionRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_DEVOLUCION_ALL", "FAC_DEVOLUCION_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacDevolucion> listAll=facdevolucionRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facdevolucionRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_DEVOLUCION_ALL", "FAC_DEVOLUCION_UPDATE"})
	public String update(FacDevolucion facdevolucion){
		return gson.toJson(facdevolucionRepository.update(facdevolucion));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_DEVOLUCION_ALL", "FAC_DEVOLUCION_DELETE"})
	public void delete(FacDevolucion facdevolucion){
		facdevolucionRepository.delete(facdevolucion);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_DEVOLUCION_ALL", "FAC_DEVOLUCION_CREATE"})
	public String insert(FacDevolucion facdevolucion){
		return gson.toJson(facdevolucionRepository.insert(facdevolucion));
	}
	
}
