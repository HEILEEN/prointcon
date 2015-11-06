package com.confianza.webapp.repository.facturacion.facresumengiro;

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

public interface FacResumengiroRepository {
	
	public FacResumengiro list(Long id);
	
	public List<FacResumengiro> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacResumengiro update(FacResumengiro facresumengiro);
	
	public void delete(FacResumengiro facresumengiro);
	
	public FacResumengiro insert(FacResumengiro facresumengiro);
	
	public int getCount(List<Filter> filters);
}
