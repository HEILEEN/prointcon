package com.confianza.webapp.service.formatos.fmtvalocamp;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;

public interface FmtValocampService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtValocamp fmtvalocamp);
	
	public String update(FmtValocamp fmtvalocamp);
	
	public void delete(FmtValocamp fmtvalocamp);	
	
	public int getCount();
	
}
