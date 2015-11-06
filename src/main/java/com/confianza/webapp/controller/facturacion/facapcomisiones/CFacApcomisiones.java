package com.confianza.webapp.controller.facturacion.facapcomisiones;

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


import com.confianza.webapp.service.facturacion.facapcomisiones.FacApcomisionesService;
import com.confianza.webapp.repository.facturacion.facapcomisiones.FacApcomisiones;

@Controller
@RequestMapping("/FacApcomisiones")
public class CFacApcomisiones {

	@Autowired
	private FacApcomisionesService facapcomisionesService;
	
	public CFacApcomisiones() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "facturacion/facapcomisiones/FacApcomisiones";
	}
	
	@RequestMapping(value = "/{apcocons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("apcocons") Long apcocons){
		
		return this.facapcomisionesService.list(apcocons);
	}
	
	@RequestMapping(value = "/listAll.json",  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.facapcomisionesService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FacApcomisiones facapcomisiones, HttpServletRequest request){
	
		return this.facapcomisionesService.update(facapcomisiones);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FacApcomisiones facapcomisiones, HttpServletRequest request){
	
		//facapcomisiones.setesta("B");
		return this.facapcomisionesService.update(facapcomisiones);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FacApcomisiones facapcomisiones, HttpServletRequest request){
		
		return this.facapcomisionesService.insert(facapcomisiones);		
	}
}
