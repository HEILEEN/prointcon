package com.confianza.webapp.repository.facturacion.facresumenajustes;

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

public interface FacResumenajustesRepository {
	
	public FacResumenajustes list(Long id);
	
	public List<FacResumenajustes> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacResumenajustes update(FacResumenajustes facresumenajustes);
	
	public void delete(FacResumenajustes facresumenajustes);
	
	public FacResumenajustes insert(FacResumenajustes facresumenajustes);
	
	public int getCount(List<Filter> filters);
}
