package com.confianza.webapp.service.facturacion.facliquidacion;

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
import com.confianza.webapp.repository.facturacion.facliquidacion.FacLiquidacion;
import com.confianza.webapp.repository.facturacion.facliquidacion.FacLiquidacionRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class FacLiquidacionServiceImpl implements FacLiquidacionService{
	
	@Autowired
	private FacLiquidacionRepository facliquidacionRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the facliquidacionRepository
	 */
	public FacLiquidacionRepository getFacLiquidacionRepository() {
		return facliquidacionRepository;
	}

	/**
	 * @param facliquidacionRepository the facliquidacionRepository to set
	 */
	public void setFacLiquidacionRepository(FacLiquidacionRepository facliquidacionRepository) {
		this.facliquidacionRepository = facliquidacionRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_LIQUIDACION_ALL", "FAC_LIQUIDACION_READ"})
	public String list(Long id){
		FacLiquidacion listAll=facliquidacionRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_LIQUIDACION_ALL", "FAC_LIQUIDACION_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FacLiquidacion> listAll=facliquidacionRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return facliquidacionRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_LIQUIDACION_ALL", "FAC_LIQUIDACION_UPDATE"})
	public String update(FacLiquidacion facliquidacion){
		return gson.toJson(facliquidacionRepository.update(facliquidacion));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_LIQUIDACION_ALL", "FAC_LIQUIDACION_DELETE"})
	public void delete(FacLiquidacion facliquidacion){
		facliquidacionRepository.delete(facliquidacion);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FAC_LIQUIDACION_ALL", "FAC_LIQUIDACION_CREATE"})
	public String insert(FacLiquidacion facliquidacion){
		return gson.toJson(facliquidacionRepository.insert(facliquidacion));
	}
	
}
