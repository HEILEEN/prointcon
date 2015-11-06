package com.confianza.webapp.repository.facturacion.facresumengiro;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		facturacion  
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
@Table(name = "FAC_RESUMENGIRO")
public class FacResumengiro {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_RESUMENGIRO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_RESUMENGIRO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_RESUMENGIRO_GEN")  
	@Column(name = "REGICONS")
	protected Long regicons; 
	 
	@Column(name = "REGILIQU")
	protected Long regiliqu; 
	 
	@Column(name = "REGIDESC")
	protected String regidesc; 
	 
	@Column(name = "REGITOTA")
	protected Double regitota; 
	 
	@Column(name = "REGIESTA")
	protected String regiesta; 

	public FacResumengiro(){
	
	}

	public Long getRegicons(){
		return regicons;
	}
	
	public void setRegicons(Long regicons){
		this.regicons = regicons;
	}

	public Long getRegiliqu(){
		return regiliqu;
	}
	
	public void setRegiliqu(Long regiliqu){
		this.regiliqu = regiliqu;
	}

	public String getRegidesc(){
		return regidesc;
	}
	
	public void setRegidesc(String regidesc){
		this.regidesc = regidesc;
	}

	public Double getRegitota(){
		return regitota;
	}
	
	public void setRegitota(Double regitota){
		this.regitota = regitota;
	}

	public String getRegiesta(){
		return regiesta;
	}
	
	public void setRegiesta(String regiesta){
		this.regiesta = regiesta;
	}


	static public String[] getNames(){
		return new String[]{ "regicons", "regiliqu", "regidesc", "regitota", "regiesta" };
	}		
	
	static public String getColumnNames(){
		return " regicons, regiliqu, regidesc, regitota, regiesta ";
	}
	
	public String toString(){
		return " REGICONS: "+ this.regicons 
			+" REGILIQU: "+ this.regiliqu 
			+" REGIDESC: "+ this.regidesc 
			+" REGITOTA: "+ this.regitota 
			+" REGIESTA: "+ this.regiesta ;
	}
}
