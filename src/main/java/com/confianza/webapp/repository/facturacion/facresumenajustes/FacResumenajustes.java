package com.confianza.webapp.repository.facturacion.facresumenajustes;

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
@Table(name = "FAC_RESUMENAJUSTES")
public class FacResumenajustes {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_RESUMENAJUSTES_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_RESUMENAJUSTES_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_RESUMENAJUSTES_GEN")  
	@Column(name = "REAJCONS")
	protected Long reajcons; 
	 
	@Column(name = "REAJLIQU")
	protected Long reajliqu; 
	 
	@Column(name = "REAJTIVA")
	protected String reajtiva; 
	 
	@Column(name = "REAJTOTA")
	protected Double reajtota; 
	 
	@Column(name = "REAJESTA")
	protected String reajesta; 

	public FacResumenajustes(){
	
	}

	public Long getReajcons(){
		return reajcons;
	}
	
	public void setReajcons(Long reajcons){
		this.reajcons = reajcons;
	}

	public Long getReajliqu(){
		return reajliqu;
	}
	
	public void setReajliqu(Long reajliqu){
		this.reajliqu = reajliqu;
	}

	public String getReajtiva(){
		return reajtiva;
	}
	
	public void setReajtiva(String reajtiva){
		this.reajtiva = reajtiva;
	}

	public Double getReajtota(){
		return reajtota;
	}
	
	public void setReajtota(Double reajtota){
		this.reajtota = reajtota;
	}

	public String getReajesta(){
		return reajesta;
	}
	
	public void setReajesta(String reajesta){
		this.reajesta = reajesta;
	}


	static public String[] getNames(){
		return new String[]{ "reajcons", "reajliqu", "reajtiva", "reajtota", "reajesta" };
	}		
	
	static public String getColumnNames(){
		return " reajcons, reajliqu, reajtiva, reajtota, reajesta ";
	}
	
	public String toString(){
		return " REAJCONS: "+ this.reajcons 
			+" REAJLIQU: "+ this.reajliqu 
			+" REAJTIVA: "+ this.reajtiva 
			+" REAJTOTA: "+ this.reajtota 
			+" REAJESTA: "+ this.reajesta ;
	}
}
