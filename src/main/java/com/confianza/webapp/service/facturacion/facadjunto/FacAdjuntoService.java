package com.confianza.webapp.service.facturacion.facadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
  */                          

import java.util.List;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.repository.facturacion.facadjunto.FacAdjunto;

public interface FacAdjuntoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacAdjunto facadjunto);
	
	public String update(FacAdjunto facadjunto);
	
	public void delete(FacAdjunto facadjunto);	
	
	public int getCount(List<Filter> filters);
	
}
