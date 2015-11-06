package com.confianza.webapp.service.facturacion.facresumenajustes;

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
import com.confianza.webapp.repository.facturacion.facresumenajustes.FacResumenajustes;

public interface FacResumenajustesService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacResumenajustes facresumenajustes);
	
	public String update(FacResumenajustes facresumenajustes);
	
	public void delete(FacResumenajustes facresumenajustes);	
	
	public int getCount(List<Filter> filters);
	
}
