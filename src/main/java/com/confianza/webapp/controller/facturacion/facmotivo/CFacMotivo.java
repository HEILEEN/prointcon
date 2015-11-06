package com.confianza.webapp.controller.facturacion.facmotivo;

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


import com.confianza.webapp.service.facturacion.facmotivo.FacMotivoService;
import com.confianza.webapp.repository.facturacion.facmotivo.FacMotivo;

@Controller
@RequestMapping("/FacMotivo")
public class CFacMotivo {

	@Autowired
	private FacMotivoService facmotivoService;
	
	public CFacMotivo() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "facturacion/facmotivo/FacMotivo";
	}
	
	@RequestMapping(value = "/{devocons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("devocons") Long devocons){
		
		return this.facmotivoService.list(devocons);
	}
	
	@RequestMapping(value = "/listAll.json",  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.facmotivoService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FacMotivo facmotivo, HttpServletRequest request){
	
		return this.facmotivoService.update(facmotivo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FacMotivo facmotivo, HttpServletRequest request){
	
		//facmotivo.setesta("B");
		return this.facmotivoService.update(facmotivo);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FacMotivo facmotivo, HttpServletRequest request){
		
		return this.facmotivoService.insert(facmotivo);		
	}
}
