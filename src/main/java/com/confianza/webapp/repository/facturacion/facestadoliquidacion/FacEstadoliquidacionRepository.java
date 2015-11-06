package com.confianza.webapp.repository.facturacion.facestadoliquidacion;

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

public interface FacEstadoliquidacionRepository {
	
	public FacEstadoliquidacion list(Long id);
	
	public List<FacEstadoliquidacion> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacEstadoliquidacion update(FacEstadoliquidacion facestadoliquidacion);
	
	public void delete(FacEstadoliquidacion facestadoliquidacion);
	
	public FacEstadoliquidacion insert(FacEstadoliquidacion facestadoliquidacion);
	
	public int getCount(List<Filter> filters);
}
