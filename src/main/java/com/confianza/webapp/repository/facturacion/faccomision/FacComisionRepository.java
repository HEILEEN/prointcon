package com.confianza.webapp.repository.facturacion.faccomision;

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

public interface FacComisionRepository {
	
	public FacComision list(Long id);
	
	public List<FacComision> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacComision update(FacComision faccomision);
	
	public void delete(FacComision faccomision);
	
	public FacComision insert(FacComision faccomision);
	
	public int getCount(List<Filter> filters);
}
