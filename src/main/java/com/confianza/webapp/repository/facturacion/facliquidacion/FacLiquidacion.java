package com.confianza.webapp.repository.facturacion.facliquidacion;

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
@Table(name = "FAC_LIQUIDACION")
public class FacLiquidacion {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_LIQUIDACION_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_LIQUIDACION_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_LIQUIDACION_GEN")  
	@Column(name = "LIQUCONS")
	protected Long liqucons; 
	 
	@Column(name = "LIQUUSUA")
	protected Long liquusua; 
	 
	@Column(name = "LIQUFEIN")
	protected Date liqufein; 
	 
	@Column(name = "LIQUFEFI")
	protected Date liqufefi; 
	 
	@Column(name = "LIQUTOCO")
	protected Double liqutoco; 
	 
	@Column(name = "LIQUTOAP")
	protected Double liqutoap; 
	 
	@Column(name = "LIQUREAJ")
	protected Double liqureaj; 
	 
	@Column(name = "LIQUTORE")
	protected Double liqutore; 
	 
	@Column(name = "LIQUNUCO")
	protected Long liqunuco; 
	 
	@Column(name = "LIQUFECR")
	protected oracle.sql.TIMESTAMP liqufecr; 
	 
	@Column(name = "LIQUESTA")
	protected String liquesta; 

	public FacLiquidacion(){
	
	}

	public Long getLiqucons(){
		return liqucons;
	}
	
	public void setLiqucons(Long liqucons){
		this.liqucons = liqucons;
	}

	public Long getLiquusua(){
		return liquusua;
	}
	
	public void setLiquusua(Long liquusua){
		this.liquusua = liquusua;
	}

	public Date getLiqufein(){
		return liqufein;
	}
	
	public void setLiqufein(Date liqufein){
		this.liqufein = liqufein;
	}

	public Date getLiqufefi(){
		return liqufefi;
	}
	
	public void setLiqufefi(Date liqufefi){
		this.liqufefi = liqufefi;
	}

	public Double getLiqutoco(){
		return liqutoco;
	}
	
	public void setLiqutoco(Double liqutoco){
		this.liqutoco = liqutoco;
	}

	public Double getLiqutoap(){
		return liqutoap;
	}
	
	public void setLiqutoap(Double liqutoap){
		this.liqutoap = liqutoap;
	}

	public Double getLiqureaj(){
		return liqureaj;
	}
	
	public void setLiqureaj(Double liqureaj){
		this.liqureaj = liqureaj;
	}

	public Double getLiqutore(){
		return liqutore;
	}
	
	public void setLiqutore(Double liqutore){
		this.liqutore = liqutore;
	}

	public Long getLiqunuco(){
		return liqunuco;
	}
	
	public void setLiqunuco(Long liqunuco){
		this.liqunuco = liqunuco;
	}

	public oracle.sql.TIMESTAMP getLiqufecr(){
		return liqufecr;
	}
	
	public void setLiqufecr(oracle.sql.TIMESTAMP liqufecr){
		this.liqufecr = liqufecr;
	}

	public String getLiquesta(){
		return liquesta;
	}
	
	public void setLiquesta(String liquesta){
		this.liquesta = liquesta;
	}


	static public String[] getNames(){
		return new String[]{ "liqucons", "liquusua", "liqufein", "liqufefi", "liqutoco", "liqutoap", "liqureaj", "liqutore", "liqunuco", "liqufecr", "liquesta" };
	}		
	
	static public String getColumnNames(){
		return " liqucons, liquusua, liqufein, liqufefi, liqutoco, liqutoap, liqureaj, liqutore, liqunuco, liqufecr, liquesta ";
	}
	
	public String toString(){
		return " LIQUCONS: "+ this.liqucons 
			+" LIQUUSUA: "+ this.liquusua 
			+" LIQUFEIN: "+ this.liqufein 
			+" LIQUFEFI: "+ this.liqufefi 
			+" LIQUTOCO: "+ this.liqutoco 
			+" LIQUTOAP: "+ this.liqutoap 
			+" LIQUREAJ: "+ this.liqureaj 
			+" LIQUTORE: "+ this.liqutore 
			+" LIQUNUCO: "+ this.liqunuco 
			+" LIQUFECR: "+ this.liqufecr 
			+" LIQUESTA: "+ this.liquesta ;
	}
}
