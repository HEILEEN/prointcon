package com.confianza.webapp.repository.formatos.fmtauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "FMT_AUDITORIA")
public class FmtAuditoria {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_AUDITORIA_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_AUDITORIA_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_AUDITORIA_GEN")  
	@Column(name = "AUDICONS")
	protected Long audicons; 
	 
	@Column(name = "AUDITRAN")
	protected Long auditran; 
	 
	@Column(name = "AUDITABL")
	protected String auditabl; 
	 
	@Column(name = "AUDICOPK")
	protected Long audicopk; 
	 
	@Column(name = "AUDICAMP")
	protected String audicamp; 
	 
	@Column(name = "AUDIVAAN")
	protected String audivaan; 
	 
	@Column(name = "AUDIVANU")
	protected String audivanu; 

	public FmtAuditoria(){
	
	}

	public Long getAudicons(){
		return audicons;
	}
	
	public void setAudicons(Long audicons){
		this.audicons = audicons;
	}

	public Long getAuditran(){
		return auditran;
	}
	
	public void setAuditran(Long auditran){
		this.auditran = auditran;
	}

	public String getAuditabl(){
		return auditabl;
	}
	
	public void setAuditabl(String auditabl){
		this.auditabl = auditabl;
	}

	public Long getAudicopk(){
		return audicopk;
	}
	
	public void setAudicopk(Long audicopk){
		this.audicopk = audicopk;
	}

	public String getAudicamp(){
		return audicamp;
	}
	
	public void setAudicamp(String audicamp){
		this.audicamp = audicamp;
	}

	public String getAudivaan(){
		return audivaan;
	}
	
	public void setAudivaan(String audivaan){
		this.audivaan = audivaan;
	}

	public String getAudivanu(){
		return audivanu;
	}
	
	public void setAudivanu(String audivanu){
		this.audivanu = audivanu;
	}


	static public String[] getNames(){
		return new String[]{ "audicons", "auditran", "auditabl", "audicopk", "audicamp", "audivaan", "audivanu" };
	}		
	
	static public String getColumnNames(){
		return " AUDICONS, AUDITRAN, AUDITABL, AUDICOPK, AUDICAMP, AUDIVAAN, AUDIVANU ";
	}
	
	public String toString(){
		return " AUDICONS: "+ this.audicons 
			+" AUDITRAN: "+ this.auditran 
			+" AUDITABL: "+ this.auditabl 
			+" AUDICOPK: "+ this.audicopk 
			+" AUDICAMP: "+ this.audicamp 
			+" AUDIVAAN: "+ this.audivaan 
			+" AUDIVANU: "+ this.audivanu ;
	}
}
