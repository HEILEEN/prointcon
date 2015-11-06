package com.confianza.webapp.repository.facturacion.facdevolucion;

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

public interface FacDevolucionRepository {
	
	public FacDevolucion list(Long id);
	
	public List<FacDevolucion> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacDevolucion update(FacDevolucion facdevolucion);
	
	public void delete(FacDevolucion facdevolucion);
	
	public FacDevolucion insert(FacDevolucion facdevolucion);
	
	public int getCount(List<Filter> filters);
}
