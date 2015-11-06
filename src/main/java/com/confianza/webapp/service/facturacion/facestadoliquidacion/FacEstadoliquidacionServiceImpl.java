package com.confianza.webapp.service.facturacion.facestadoliquidacion;

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
import com.confianza.webapp.repository.facturacion.facestadoliquidacion.FacEstadoliquidacion;
import com.confianza.webapp.repository.facturacion.facestadoliquidacion.FacEstadoliquidacionRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacEstadoliquidacionServiceImpl implements FacEstadoliquidacionService{
	
	@Autowired
	private FacEstadoliquidacionRepository facestadoliquidacionRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facestadoliquidacionRepository
	 */
	public FacEstadoliquidacionRepository getFacEstadoliquidacionRepository() {
		return facestadoliquidacionRepository;
	}

	/**
	 * @param facestadoliquidacionRepository the facestadoliquidacionRepository to set
	 */
	public void setFacEstadoliquidacionRepository(FacEstadoliquidacionRepository facestadoliquidacionRepository) {
		this.facestadoliquidacionRepository = facestadoliquidacionRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTADOLIQUIDACION_ALL", "FAC_ESTADOLIQUIDACION_READ"})
	public String list(Long id){
		FacEstadoliquidacion listAll=facestadoliquidacionRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTADOLIQUIDACION_ALL", "FAC_ESTADOLIQUIDACION_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacEstadoliquidacion> listAll=facestadoliquidacionRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facestadoliquidacionRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTADOLIQUIDACION_ALL", "FAC_ESTADOLIQUIDACION_UPDATE"})
	public String update(FacEstadoliquidacion facestadoliquidacion){
		return gson.toJson(facestadoliquidacionRepository.update(facestadoliquidacion));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTADOLIQUIDACION_ALL", "FAC_ESTADOLIQUIDACION_DELETE"})
	public void delete(FacEstadoliquidacion facestadoliquidacion){
		facestadoliquidacionRepository.delete(facestadoliquidacion);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ESTADOLIQUIDACION_ALL", "FAC_ESTADOLIQUIDACION_CREATE"})
	public String insert(FacEstadoliquidacion facestadoliquidacion){
		return gson.toJson(facestadoliquidacionRepository.insert(facestadoliquidacion));
	}
	
}
