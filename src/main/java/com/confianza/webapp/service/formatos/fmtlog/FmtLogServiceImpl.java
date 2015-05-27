package com.confianza.webapp.service.formatos.fmtlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.formatos.fmtlog.FmtLog;
import com.confianza.webapp.repository.formatos.fmtlog.FmtLogRepository;

@Service
public class FmtLogServiceImpl implements FmtLogService{
	
	@Autowired
	private FmtLogRepository fmtlogRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtlogRepository
	 */
	public FmtLogRepository getFmtLogRepository() {
		return fmtlogRepository;
	}

	/**
	 * @param fmtlogRepository the fmtlogRepository to set
	 */
	public void setFmtLogRepository(FmtLogRepository fmtlogRepository) {
		this.fmtlogRepository = fmtlogRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_READ"})
	public String list(Long id){
		FmtLog listAll=fmtlogRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtLog> listAll=fmtlogRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtlogRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_UPDATE"})
	public String update(FmtLog fmtlog){
		return gson.toJson(fmtlogRepository.update(fmtlog));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_DELETE"})
	public void delete(FmtLog fmtlog){
		fmtlogRepository.delete(fmtlog);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_CREATE"})
	public String insert(FmtLog fmtlog){
		return gson.toJson(fmtlogRepository.insert(fmtlog));
	}
	
}
