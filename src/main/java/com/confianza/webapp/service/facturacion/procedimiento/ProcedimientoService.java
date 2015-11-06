package com.confianza.webapp.service.facturacion.procedimiento;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import javax.servlet.http.HttpServletRequest;

public interface ProcedimientoService{	

	public String executeProcess(String conscons, String params, HttpServletRequest request);	
}
