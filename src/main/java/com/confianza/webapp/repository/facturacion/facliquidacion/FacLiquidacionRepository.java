package com.confianza.webapp.repository.facturacion.facliquidacion;

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

public interface FacLiquidacionRepository {
	
	public FacLiquidacion list(Long id);
	
	public List<FacLiquidacion> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacLiquidacion update(FacLiquidacion facliquidacion);
	
	public void delete(FacLiquidacion facliquidacion);
	
	public FacLiquidacion insert(FacLiquidacion facliquidacion);
	
	public int getCount(List<Filter> filters);
}
