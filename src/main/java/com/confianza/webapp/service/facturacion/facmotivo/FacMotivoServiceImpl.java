package com.confianza.webapp.service.facturacion.facmotivo;

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
import com.confianza.webapp.repository.facturacion.facmotivo.FacMotivo;
import com.confianza.webapp.repository.facturacion.facmotivo.FacMotivoRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacMotivoServiceImpl implements FacMotivoService{
	
	@Autowired
	private FacMotivoRepository facmotivoRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facmotivoRepository
	 */
	public FacMotivoRepository getFacMotivoRepository() {
		return facmotivoRepository;
	}

	/**
	 * @param facmotivoRepository the facmotivoRepository to set
	 */
	public void setFacMotivoRepository(FacMotivoRepository facmotivoRepository) {
		this.facmotivoRepository = facmotivoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_MOTIVO_ALL", "FAC_MOTIVO_READ"})
	public String list(Long id){
		FacMotivo listAll=facmotivoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_MOTIVO_ALL", "FAC_MOTIVO_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacMotivo> listAll=facmotivoRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facmotivoRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_MOTIVO_ALL", "FAC_MOTIVO_UPDATE"})
	public String update(FacMotivo facmotivo){
		return gson.toJson(facmotivoRepository.update(facmotivo));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_MOTIVO_ALL", "FAC_MOTIVO_DELETE"})
	public void delete(FacMotivo facmotivo){
		facmotivoRepository.delete(facmotivo);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_MOTIVO_ALL", "FAC_MOTIVO_CREATE"})
	public String insert(FacMotivo facmotivo){
		return gson.toJson(facmotivoRepository.insert(facmotivo));
	}
	
}
