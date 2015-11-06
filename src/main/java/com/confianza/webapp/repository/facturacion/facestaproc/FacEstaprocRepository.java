package com.confianza.webapp.repository.facturacion.facestaproc;

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

public interface FacEstaprocRepository {
	
	public FacEstaproc list(Long id);
	
	public List<FacEstaproc> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public FacEstaproc update(FacEstaproc facestaproc);
	
	public void delete(FacEstaproc facestaproc);
	
	public FacEstaproc insert(FacEstaproc facestaproc);
	
	public int getCount(List<Filter> filters);
}
