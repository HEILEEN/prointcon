package com.confianza.webapp.service.facturacion.facestadoliquidacion;

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
import com.confianza.webapp.repository.facturacion.facestadoliquidacion.FacEstadoliquidacion;

public interface FacEstadoliquidacionService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacEstadoliquidacion facestadoliquidacion);
	
	public String update(FacEstadoliquidacion facestadoliquidacion);
	
	public void delete(FacEstadoliquidacion facestadoliquidacion);	
	
	public int getCount(List<Filter> filters);
	
}
