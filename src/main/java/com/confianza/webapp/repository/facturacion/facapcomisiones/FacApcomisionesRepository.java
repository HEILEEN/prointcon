package com.confianza.webapp.repository.facturacion.facapcomisiones;

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

public interface FacApcomisionesRepository {
	
	public FacApcomisiones list(Long id);
	
	public List<FacApcomisiones> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacApcomisiones update(FacApcomisiones facapcomisiones);
	
	public void delete(FacApcomisiones facapcomisiones);
	
	public FacApcomisiones insert(FacApcomisiones facapcomisiones);
	
	public int getCount(List<Filter> filters);
}
