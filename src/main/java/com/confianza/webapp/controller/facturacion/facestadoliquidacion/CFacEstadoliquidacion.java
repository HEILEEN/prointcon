package com.confianza.webapp.controller.facturacion.facestadoliquidacion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.confianza.webapp.service.facturacion.facestadoliquidacion.FacEstadoliquidacionService;
import com.confianza.webapp.repository.facturacion.facestadoliquidacion.FacEstadoliquidacion;

@Controller
@RequestMapping("/FacEstadoliquidacion")
public class CFacEstadoliquidacion {

	@Autowired
	private FacEstadoliquidacionService facestadoliquidacionService;
	
	public CFacEstadoliquidacion() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "facturacion/facestadoliquidacion/FacEstadoliquidacion";
	}
	
	@RequestMapping(value = "/{eslicons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("eslicons") Long eslicons){
		
		return this.facestadoliquidacionService.list(eslicons);
	}
	
	@RequestMapping(value = "/listAll.json",  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.facestadoliquidacionService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FacEstadoliquidacion facestadoliquidacion, HttpServletRequest request){
	
		return this.facestadoliquidacionService.update(facestadoliquidacion);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FacEstadoliquidacion facestadoliquidacion, HttpServletRequest request){
	
		//facestadoliquidacion.setesta("B");
		return this.facestadoliquidacionService.update(facestadoliquidacion);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FacEstadoliquidacion facestadoliquidacion, HttpServletRequest request){
		
		return this.facestadoliquidacionService.insert(facestadoliquidacion);		
	}
}
