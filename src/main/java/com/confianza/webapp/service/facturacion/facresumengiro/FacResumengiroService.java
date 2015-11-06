package com.confianza.webapp.service.facturacion.facresumengiro;

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
import com.confianza.webapp.repository.facturacion.facresumengiro.FacResumengiro;

public interface FacResumengiroService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacResumengiro facresumengiro);
	
	public String update(FacResumengiro facresumengiro);
	
	public void delete(FacResumengiro facresumengiro);	
	
	public int getCount(List<Filter> filters);
	
}
