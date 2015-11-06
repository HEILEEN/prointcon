package com.confianza.webapp.repository.facturacion.facmotiform;

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
@Table(name = "FAC_MOTIFORM")
public class FacMotiform {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_MOTIFORM_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_MOTIFORM_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_MOTIFORM_GEN")  
	@Column(name = "DEVOCONS")
	protected Long devocons; 
	 
	@Column(name = "MOFOFORE")
	protected Long mofofore; 
	 
	@Column(name = "MOFODEVO")
	protected Long mofodevo; 
	 
	@Column(name = "DEVODESC")
	protected String devodesc; 
	 
	@Column(name = "DEVOFECH")
	protected oracle.sql.TIMESTAMP devofech; 
	 
	@Column(name = "DEVOUSER")
	protected String devouser; 

	public FacMotiform(){
	
	}

	public Long getDevocons(){
		return devocons;
	}
	
	public void setDevocons(Long devocons){
		this.devocons = devocons;
	}

	public Long getMofofore(){
		return mofofore;
	}
	
	public void setMofofore(Long mofofore){
		this.mofofore = mofofore;
	}

	public Long getMofodevo(){
		return mofodevo;
	}
	
	public void setMofodevo(Long mofodevo){
		this.mofodevo = mofodevo;
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

	public String getDevouser(){
		return devouser;
	}
	
	public void setDevouser(String devouser){
		this.devouser = devouser;
	}


	static public String[] getNames(){
		return new String[]{ "devocons", "mofofore", "mofodevo", "devodesc", "devofech", "devouser" };
	}		
	
	static public String getColumnNames(){
		return " devocons, mofofore, mofodevo, devodesc, devofech, devouser ";
	}
	
	public String toString(){
		return " DEVOCONS: "+ this.devocons 
			+" MOFOFORE: "+ this.mofofore 
			+" MOFODEVO: "+ this.mofodevo 
			+" DEVODESC: "+ this.devodesc 
			+" DEVOFECH: "+ this.devofech 
			+" DEVOUSER: "+ this.devouser ;
	}
}
