package com.confianza.webapp.service.facturacion.facmotivo;

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
import com.confianza.webapp.repository.facturacion.facmotivo.FacMotivo;

public interface FacMotivoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacMotivo facmotivo);
	
	public String update(FacMotivo facmotivo);
	
	public void delete(FacMotivo facmotivo);	
	
	public int getCount(List<Filter> filters);
	
}
