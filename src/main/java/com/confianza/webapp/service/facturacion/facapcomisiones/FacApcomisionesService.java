package com.confianza.webapp.service.facturacion.facapcomisiones;

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
import com.confianza.webapp.repository.facturacion.facapcomisiones.FacApcomisiones;

public interface FacApcomisionesService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacApcomisiones facapcomisiones);
	
	public String update(FacApcomisiones facapcomisiones);
	
	public void delete(FacApcomisiones facapcomisiones);	
	
	public int getCount(List<Filter> filters);
	
}
