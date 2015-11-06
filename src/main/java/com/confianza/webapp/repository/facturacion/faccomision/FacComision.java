package com.confianza.webapp.repository.facturacion.faccomision;

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
@Table(name = "FAC_COMISION")
public class FacComision {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_COMISION_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_COMISION_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_COMISION_GEN")  
	@Column(name = "COMICONS")
	protected Long comicons; 
	 
	@Column(name = "COMILIQU")
	protected Long comiliqu; 
	 
	@Column(name = "COMIFERE")
	protected Date comifere; 
	 
	@Column(name = "COMIDORE")
	protected String comidore; 
	 
	@Column(name = "COMIDOCU")
	protected String comidocu; 
	 
	@Column(name = "COMIPOLI")
	protected String comipoli; 
	 
	@Column(name = "COMICERT")
	protected String comicert; 
	 
	@Column(name = "COMITOMA")
	protected String comitoma; 
	 
	@Column(name = "COMIPRIM")
	protected Double comiprim; 
	 
	@Column(name = "COMIPOCO")
	protected Double comipoco; 
	 
	@Column(name = "COMICOMI")
	protected Long comicomi; 
	 
	@Column(name = "COMIIVAD")
	protected Double comiivad; 
	 
	@Column(name = "COMIIVAR")
	protected Double comiivar; 
	 
	@Column(name = "COMIRETE")
	protected Double comirete; 
	 
	@Column(name = "COMICREE")
	protected Double comicree; 
	 
	@Column(name = "COMIVICA")
	protected Double comivica; 
	 
	@Column(name = "COMINEPA")
	protected Double cominepa; 
	 
	@Column(name = "COMIESTA")
	protected String comiesta; 

	public FacComision(){
	
	}

	public Long getComicons(){
		return comicons;
	}
	
	public void setComicons(Long comicons){
		this.comicons = comicons;
	}

	public Long getComiliqu(){
		return comiliqu;
	}
	
	public void setComiliqu(Long comiliqu){
		this.comiliqu = comiliqu;
	}

	public Date getComifere(){
		return comifere;
	}
	
	public void setComifere(Date comifere){
		this.comifere = comifere;
	}

	public String getComidore(){
		return comidore;
	}
	
	public void setComidore(String comidore){
		this.comidore = comidore;
	}

	public String getComidocu(){
		return comidocu;
	}
	
	public void setComidocu(String comidocu){
		this.comidocu = comidocu;
	}

	public String getComipoli(){
		return comipoli;
	}
	
	public void setComipoli(String comipoli){
		this.comipoli = comipoli;
	}

	public String getComicert(){
		return comicert;
	}
	
	public void setComicert(String comicert){
		this.comicert = comicert;
	}

	public String getComitoma(){
		return comitoma;
	}
	
	public void setComitoma(String comitoma){
		this.comitoma = comitoma;
	}

	public Double getComiprim(){
		return comiprim;
	}
	
	public void setComiprim(Double comiprim){
		this.comiprim = comiprim;
	}

	public Double getComipoco(){
		return comipoco;
	}
	
	public void setComipoco(Double comipoco){
		this.comipoco = comipoco;
	}

	public Long getComicomi(){
		return comicomi;
	}
	
	public void setComicomi(Long comicomi){
		this.comicomi = comicomi;
	}

	public Double getComiivad(){
		return comiivad;
	}
	
	public void setComiivad(Double comiivad){
		this.comiivad = comiivad;
	}

	public Double getComiivar(){
		return comiivar;
	}
	
	public void setComiivar(Double comiivar){
		this.comiivar = comiivar;
	}

	public Double getComirete(){
		return comirete;
	}
	
	public void setComirete(Double comirete){
		this.comirete = comirete;
	}

	public Double getComicree(){
		return comicree;
	}
	
	public void setComicree(Double comicree){
		this.comicree = comicree;
	}

	public Double getComivica(){
		return comivica;
	}
	
	public void setComivica(Double comivica){
		this.comivica = comivica;
	}

	public Double getCominepa(){
		return cominepa;
	}
	
	public void setCominepa(Double cominepa){
		this.cominepa = cominepa;
	}

	public String getComiesta(){
		return comiesta;
	}
	
	public void setComiesta(String comiesta){
		this.comiesta = comiesta;
	}


	static public String[] getNames(){
		return new String[]{ "comicons", "comiliqu", "comifere", "comidore", "comidocu", "comipoli", "comicert", "comitoma", "comiprim", "comipoco", "comicomi", "comiivad", "comiivar", "comirete", "comicree", "comivica", "cominepa", "comiesta" };
	}		
	
	static public String getColumnNames(){
		return " comicons, comiliqu, comifere, comidore, comidocu, comipoli, comicert, comitoma, comiprim, comipoco, comicomi, comiivad, comiivar, comirete, comicree, comivica, cominepa, comiesta ";
	}
	
	public String toString(){
		return " COMICONS: "+ this.comicons 
			+" COMILIQU: "+ this.comiliqu 
			+" COMIFERE: "+ this.comifere 
			+" COMIDORE: "+ this.comidore 
			+" COMIDOCU: "+ this.comidocu 
			+" COMIPOLI: "+ this.comipoli 
			+" COMICERT: "+ this.comicert 
			+" COMITOMA: "+ this.comitoma 
			+" COMIPRIM: "+ this.comiprim 
			+" COMIPOCO: "+ this.comipoco 
			+" COMICOMI: "+ this.comicomi 
			+" COMIIVAD: "+ this.comiivad 
			+" COMIIVAR: "+ this.comiivar 
			+" COMIRETE: "+ this.comirete 
			+" COMICREE: "+ this.comicree 
			+" COMIVICA: "+ this.comivica 
			+" COMINEPA: "+ this.cominepa 
			+" COMIESTA: "+ this.comiesta ;
	}
}
