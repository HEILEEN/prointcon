package com.confianza.webapp.service.facturacion.facdevolucion;

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
import com.confianza.webapp.repository.facturacion.facdevolucion.FacDevolucion;

public interface FacDevolucionService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacDevolucion facdevolucion);
	
	public String update(FacDevolucion facdevolucion);
	
	public void delete(FacDevolucion facdevolucion);	
	
	public int getCount(List<Filter> filters);
	
}
