package com.confianza.webapp.repository.facturacion.facmotiform;

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

public interface FacMotiformRepository {
	
	public FacMotiform list(Long id);
	
	public List<FacMotiform> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacMotiform update(FacMotiform facmotiform);
	
	public void delete(FacMotiform facmotiform);
	
	public FacMotiform insert(FacMotiform facmotiform);
	
	public int getCount(List<Filter> filters);
}
