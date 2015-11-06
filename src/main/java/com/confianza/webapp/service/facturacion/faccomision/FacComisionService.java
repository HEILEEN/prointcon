package com.confianza.webapp.service.facturacion.faccomision;

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
import com.confianza.webapp.repository.facturacion.faccomision.FacComision;

public interface FacComisionService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacComision faccomision);
	
	public String update(FacComision faccomision);
	
	public void delete(FacComision faccomision);	
	
	public int getCount(List<Filter> filters);
	
}
