package com.confianza.webapp.service.facturacion.facliquidacion;

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
import com.confianza.webapp.repository.facturacion.facliquidacion.FacLiquidacion;

public interface FacLiquidacionService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacLiquidacion facliquidacion);
	
	public String update(FacLiquidacion facliquidacion);
	
	public void delete(FacLiquidacion facliquidacion);	
	
	public int getCount(List<Filter> filters);
	
}
