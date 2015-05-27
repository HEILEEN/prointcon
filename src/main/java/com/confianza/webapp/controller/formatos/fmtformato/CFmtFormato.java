package com.confianza.webapp.controller.formatos.fmtformato;

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



import com.confianza.webapp.service.formatos.fmtformato.FmtFormatoService;
import com.confianza.webapp.repository.formatos.fmtformato.FmtFormato;

@Controller
@RequestMapping("/FmtFormato")
public class CFmtFormato {

	@Autowired
	private FmtFormatoService fmtformatoService;
	
	public CFmtFormato() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtformato/FmtFormato";
	}
	
	@RequestMapping(value = "/{formcons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("formcons") Long formcons){
		
		return this.fmtformatoService.list(formcons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtformatoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtFormato fmtformato, HttpServletRequest request){
	
		return this.fmtformatoService.update(fmtformato);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtFormato fmtformato, HttpServletRequest request){
	
		//fmtformato.setesta("B");
		return this.fmtformatoService.update(fmtformato);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtFormato fmtformato, HttpServletRequest request){
		
		return this.fmtformatoService.insert(fmtformato);		
	}
	
	@RequestMapping(value = "/loadData.json", params = {"formcons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String loadData(@RequestParam("formcons") String formcons) throws Throwable{
			
		return this.fmtformatoService.loadData(formcons);
	}
}
