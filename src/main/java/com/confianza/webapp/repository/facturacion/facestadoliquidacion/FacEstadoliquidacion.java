package com.confianza.webapp.repository.facturacion.facestadoliquidacion;

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
@Table(name = "FAC_ESTADOLIQUIDACION")
public class FacEstadoliquidacion {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_ESTADOLIQUIDACION_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_ESTADOLIQUIDACION_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_ESTADOLIQUIDACION_GEN")  
	@Column(name = "ESLICONS")
	protected Long eslicons; 
	 
	@Column(name = "ESLILIQU")
	protected Long esliliqu; 
	 
	@Column(name = "ESLIFECR")
	protected oracle.sql.TIMESTAMP eslifecr; 
	 
	@Column(name = "ESLIUSER")
	protected String esliuser; 
	 
	@Column(name = "ESLIESTA")
	protected String esliesta; 

	public FacEstadoliquidacion(){
	
	}

	public Long getEslicons(){
		return eslicons;
	}
	
	public void setEslicons(Long eslicons){
		this.eslicons = eslicons;
	}

	public Long getEsliliqu(){
		return esliliqu;
	}
	
	public void setEsliliqu(Long esliliqu){
		this.esliliqu = esliliqu;
	}

	public oracle.sql.TIMESTAMP getEslifecr(){
		return eslifecr;
	}
	
	public void setEslifecr(oracle.sql.TIMESTAMP eslifecr){
		this.eslifecr = eslifecr;
	}

	public String getEsliuser(){
		return esliuser;
	}
	
	public void setEsliuser(String esliuser){
		this.esliuser = esliuser;
	}

	public String getEsliesta(){
		return esliesta;
	}
	
	public void setEsliesta(String esliesta){
		this.esliesta = esliesta;
	}


	static public String[] getNames(){
		return new String[]{ "eslicons", "esliliqu", "eslifecr", "esliuser", "esliesta" };
	}		
	
	static public String getColumnNames(){
		return " eslicons, esliliqu, eslifecr, esliuser, esliesta ";
	}
	
	public String toString(){
		return " ESLICONS: "+ this.eslicons 
			+" ESLILIQU: "+ this.esliliqu 
			+" ESLIFECR: "+ this.eslifecr 
			+" ESLIUSER: "+ this.esliuser 
			+" ESLIESTA: "+ this.esliesta ;
	}
}
