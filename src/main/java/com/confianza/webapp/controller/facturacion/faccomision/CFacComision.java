package com.confianza.webapp.controller.facturacion.faccomision;

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


import com.confianza.webapp.service.facturacion.faccomision.FacComisionService;
import com.confianza.webapp.repository.facturacion.faccomision.FacComision;

@Controller
@RequestMapping("/FacComision")
public class CFacComision {

	@Autowired
	private FacComisionService faccomisionService;
	
	public CFacComision() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "facturacion/faccomision/FacComision";
	}
	
	@RequestMapping(value = "/{comicons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("comicons") Long comicons){
		
		return this.faccomisionService.list(comicons);
	}
	
	@RequestMapping(value = "/listAll.json",  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.faccomisionService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FacComision faccomision, HttpServletRequest request){
	
		return this.faccomisionService.update(faccomision);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FacComision faccomision, HttpServletRequest request){
	
		//faccomision.setesta("B");
		return this.faccomisionService.update(faccomision);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FacComision faccomision, HttpServletRequest request){
		
		return this.faccomisionService.insert(faccomision);		
	}
}
