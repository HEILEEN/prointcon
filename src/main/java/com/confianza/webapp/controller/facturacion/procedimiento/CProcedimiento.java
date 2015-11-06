package com.confianza.webapp.controller.facturacion.procedimiento;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.confianza.webapp.service.facturacion.procedimiento.ProcedimientoService;

@Controller
@RequestMapping("/Facturacion")
public class CProcedimiento {
	
	@Autowired
	private ProcedimientoService ProcedimientoService;
	
	public CProcedimiento() {
		super();
	}
	
	@RequestMapping("/Procedimiento/")
	public String soporteCierreCarteraCuadre() {
		return "facturacion/procedimiento/Procedimiento";
	}
	
	@RequestMapping(value = "/ExecuteProcedimientoLiquidacion.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcess(@RequestParam("conscons") String conscons, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		return this.ProcedimientoService.executeProcess(conscons, params, request);  
	}
		
}
