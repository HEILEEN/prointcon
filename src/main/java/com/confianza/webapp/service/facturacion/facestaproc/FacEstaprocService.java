package com.confianza.webapp.service.facturacion.facestaproc;

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
import com.confianza.webapp.repository.facturacion.facestaproc.FacEstaproc;

public interface FacEstaprocService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(FacEstaproc facestaproc);
	
	public String update(FacEstaproc facestaproc);
	
	public void delete(FacEstaproc facestaproc);	
	
	public int getCount(List<Filter> filters);

	public FacEstaproc closeFinal(FacEstaproc facEstaproc);

	public List<FacEstaproc> listAll(int pageSize, int page, String order, List<Filter> filters);

	public FacEstaproc insert(String esprnomb, String esprdesc, String espruser, String espresta);
	
}
