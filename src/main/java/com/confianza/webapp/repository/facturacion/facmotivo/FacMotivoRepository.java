package com.confianza.webapp.repository.facturacion.facmotivo;

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

public interface FacMotivoRepository {
	
	public FacMotivo list(Long id);
	
	public List<FacMotivo> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacMotivo update(FacMotivo facmotivo);
	
	public void delete(FacMotivo facmotivo);
	
	public FacMotivo insert(FacMotivo facmotivo);
	
	public int getCount(List<Filter> filters);
}
