package com.confianza.webapp.service.facturacion.facmotiform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		facturacion  
  */                          

import java.util.List;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.repository.facturacion.facmotiform.FacMotiform;

public interface FacMotiformService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacMotiform facmotiform);
	
	public String update(FacMotiform facmotiform);
	
	public void delete(FacMotiform facmotiform);	
	
	public int getCount(List<Filter> filters);
	
}
