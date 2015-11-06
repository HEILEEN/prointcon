package com.confianza.webapp.repository.facturacion.facdevolucion;

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
@Table(name = "FAC_DEVOLUCION")
public class FacDevolucion {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_DEVOLUCION_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_DEVOLUCION_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_DEVOLUCION_GEN")  
	@Column(name = "DEVOCONS")
	protected Long devocons; 
	 
	@Column(name = "DEVOLIQU")
	protected Long devoliqu; 
	 
	@Column(name = "DEVODESC")
	protected String devodesc; 
	 
	@Column(name = "DEVOFECH")
	protected oracle.sql.TIMESTAMP devofech; 

	public FacDevolucion(){
	
	}

	public Long getDevocons(){
		return devocons;
	}
	
	public void setDevocons(Long devocons){
		this.devocons = devocons;
	}

	public Long getDevoliqu(){
		return devoliqu;
	}
	
	public void setDevoliqu(Long devoliqu){
		this.devoliqu = devoliqu;
	}

	public String getDevodesc(){
		return devodesc;
	}
	
	public void setDevodesc(String devodesc){
		this.devodesc = devodesc;
	}

	public oracle.sql.TIMESTAMP getDevofech(){
		return devofech;
	}
	
	public void setDevofech(oracle.sql.TIMESTAMP devofech){
		this.devofech = devofech;
	}


	static public String[] getNames(){
		return new String[]{ "devocons", "devoliqu", "devodesc", "devofech" };
	}		
	
	static public String getColumnNames(){
		return " devocons, devoliqu, devodesc, devofech ";
	}
	
	public String toString(){
		return " DEVOCONS: "+ this.devocons 
			+" DEVOLIQU: "+ this.devoliqu 
			+" DEVODESC: "+ this.devodesc 
			+" DEVOFECH: "+ this.devofech ;
	}
}
