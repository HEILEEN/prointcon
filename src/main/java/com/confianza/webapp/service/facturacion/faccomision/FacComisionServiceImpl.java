package com.confianza.webapp.service.facturacion.faccomision;

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
import com.confianza.webapp.repository.facturacion.faccomision.FacComision;
import com.confianza.webapp.repository.facturacion.faccomision.FacComisionRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacComisionServiceImpl implements FacComisionService{
	
	@Autowired
	private FacComisionRepository faccomisionRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the faccomisionRepository
	 */
	public FacComisionRepository getFacComisionRepository() {
		return faccomisionRepository;
	}

	/**
	 * @param faccomisionRepository the faccomisionRepository to set
	 */
	public void setFacComisionRepository(FacComisionRepository faccomisionRepository) {
		this.faccomisionRepository = faccomisionRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_COMISION_ALL", "FAC_COMISION_READ"})
	public String list(Long id){
		FacComision listAll=faccomisionRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_COMISION_ALL", "FAC_COMISION_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacComision> listAll=faccomisionRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return faccomisionRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_COMISION_ALL", "FAC_COMISION_UPDATE"})
	public String update(FacComision faccomision){
		return gson.toJson(faccomisionRepository.update(faccomision));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_COMISION_ALL", "FAC_COMISION_DELETE"})
	public void delete(FacComision faccomision){
		faccomisionRepository.delete(faccomision);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_COMISION_ALL", "FAC_COMISION_CREATE"})
	public String insert(FacComision faccomision){
		return gson.toJson(faccomisionRepository.insert(faccomision));
	}
	
}
