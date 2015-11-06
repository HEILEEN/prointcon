package com.confianza.webapp.controller.facturacion.facestaproc;

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


import com.confianza.webapp.service.facturacion.facestaproc.FacEstaprocService;
import com.confianza.webapp.repository.facturacion.facestaproc.FacEstaproc;

@Controller
@RequestMapping("/FacEstaproc")
public class CFacEstaproc {

	@Autowired
	private FacEstaprocService facestaprocService;
	
	public CFacEstaproc() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "facturacion/facestaproc/FacEstaproc";
	}
	
	@RequestMapping(value = "/{esprcons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("esprcons") Long esprcons){
		
		return this.facestaprocService.list(esprcons);
	}
	
	@RequestMapping(value = "/listAll.json",  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.facestaprocService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FacEstaproc facestaproc, HttpServletRequest request){
	
		return this.facestaprocService.update(facestaproc);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FacEstaproc facestaproc, HttpServletRequest request){
	
		//facestaproc.setesta("B");
		return this.facestaprocService.update(facestaproc);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FacEstaproc facestaproc, HttpServletRequest request){
		
		return this.facestaprocService.insert(facestaproc);		
	}
}
