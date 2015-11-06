package com.confianza.webapp.service.facturacion.facadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
  */                          

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.facturacion.facadjunto.FacAdjunto;
import com.confianza.webapp.repository.facturacion.facadjunto.FacAdjuntoRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacAdjuntoServiceImpl implements FacAdjuntoService{
	
	@Autowired
	private FacAdjuntoRepository facadjuntoRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facadjuntoRepository
	 */
	public FacAdjuntoRepository getFacAdjuntoRepository() {
		return facadjuntoRepository;
	}

	/**
	 * @param facadjuntoRepository the facadjuntoRepository to set
	 */
	public void setFacAdjuntoRepository(FacAdjuntoRepository facadjuntoRepository) {
		this.facadjuntoRepository = facadjuntoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ADJUNTO_ALL", "FAC_ADJUNTO_READ"})
	public String list(Long id){
		FacAdjunto listAll=facadjuntoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ADJUNTO_ALL", "FAC_ADJUNTO_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacAdjunto> listAll=facadjuntoRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facadjuntoRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ADJUNTO_ALL", "FAC_ADJUNTO_UPDATE"})
	public String update(FacAdjunto facadjunto){
		return gson.toJson(facadjuntoRepository.update(facadjunto));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ADJUNTO_ALL", "FAC_ADJUNTO_DELETE"})
	public void delete(FacAdjunto facadjunto){
		facadjuntoRepository.delete(facadjunto);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_ADJUNTO_ALL", "FAC_ADJUNTO_CREATE"})
	public String insert(FacAdjunto facadjunto){
		return gson.toJson(facadjuntoRepository.insert(facadjunto));
	}
	
}
