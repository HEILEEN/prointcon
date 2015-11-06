package com.confianza.webapp.repository.facturacion.facmotivo;

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
@Table(name = "FAC_MOTIVO")
public class FacMotivo {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_MOTIVO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_MOTIVO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_MOTIVO_GEN")  
	@Column(name = "DEVOCONS")
	protected Long devocons; 
	 
	@Column(name = "DEVONOMB")
	protected String devonomb; 
	 
	@Column(name = "DEVODESC")
	protected String devodesc; 

	public FacMotivo(){
	
	}

	public Long getDevocons(){
		return devocons;
	}
	
	public void setDevocons(Long devocons){
		this.devocons = devocons;
	}

	public String getDevonomb(){
		return devonomb;
	}
	
	public void setDevonomb(String devonomb){
		this.devonomb = devonomb;
	}

	public String getDevodesc(){
		return devodesc;
	}
	
	public void setDevodesc(String devodesc){
		this.devodesc = devodesc;
	}


	static public String[] getNames(){
		return new String[]{ "devocons", "devonomb", "devodesc" };
	}		
	
	static public String getColumnNames(){
		return " devocons, devonomb, devodesc ";
	}
	
	public String toString(){
		return " DEVOCONS: "+ this.devocons 
			+" DEVONOMB: "+ this.devonomb 
			+" DEVODESC: "+ this.devodesc ;
	}
}
