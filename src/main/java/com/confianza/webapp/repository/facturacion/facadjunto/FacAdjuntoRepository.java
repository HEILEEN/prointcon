package com.confianza.webapp.repository.facturacion.facadjunto;

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

public interface FacAdjuntoRepository {
	
	public FacAdjunto list(Long id);
	
	public List<FacAdjunto> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacAdjunto update(FacAdjunto facadjunto);
	
	public void delete(FacAdjunto facadjunto);
	
	public FacAdjunto insert(FacAdjunto facadjunto);
	
	public int getCount(List<Filter> filters);
}
