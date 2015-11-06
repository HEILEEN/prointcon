package com.confianza.webapp.repository.facturacion.facapcomisiones;

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
@Table(name = "FAC_APCOMISIONES")
public class FacApcomisiones {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FAC_APCOMISIONES_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FAC_APCOMISIONES_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FAC_APCOMISIONES_GEN")  
	@Column(name = "APCOCONS")
	protected Long apcocons; 
	 
	@Column(name = "APCOLIQU")
	protected Long apcoliqu; 
	 
	@Column(name = "APCODESC")
	protected String apcodesc; 
	 
	@Column(name = "APCOPOLI")
	protected String apcopoli; 
	 
	@Column(name = "APCOCERT")
	protected String apcocert; 
	 
	@Column(name = "APCOTPBA")
	protected String apcotpba; 
	 
	@Column(name = "APCOBASE")
	protected Double apcobase; 
	 
	@Column(name = "APCOTPID")
	protected String apcotpid; 
	 
	@Column(name = "APCOIVDE")
	protected Double apcoivde; 
	 
	@Column(name = "APCOTPIR")
	protected String apcotpir; 
	 
	@Column(name = "APCOIVRE")
	protected Long apcoivre; 
	 
	@Column(name = "APCOTPRE")
	protected String apcotpre; 
	 
	@Column(name = "APCORETE")
	protected Double apcorete; 
	 
	@Column(name = "APCOTPRC")
	protected String apcotprc; 
	 
	@Column(name = "APCORTCR")
	protected Double apcortcr; 
	 
	@Column(name = "APCOTIIC")
	protected String apcotiic; 
	 
	@Column(name = "APCOVICA")
	protected Double apcovica; 
	 
	@Column(name = "APCOTOTA")
	protected Double apcotota; 
	 
	@Column(name = "APCOESTA")
	protected String apcoesta; 

	public FacApcomisiones(){
	
	}

	public Long getApcocons(){
		return apcocons;
	}
	
	public void setApcocons(Long apcocons){
		this.apcocons = apcocons;
	}

	public Long getApcoliqu(){
		return apcoliqu;
	}
	
	public void setApcoliqu(Long apcoliqu){
		this.apcoliqu = apcoliqu;
	}

	public String getApcodesc(){
		return apcodesc;
	}
	
	public void setApcodesc(String apcodesc){
		this.apcodesc = apcodesc;
	}

	public String getApcopoli(){
		return apcopoli;
	}
	
	public void setApcopoli(String apcopoli){
		this.apcopoli = apcopoli;
	}

	public String getApcocert(){
		return apcocert;
	}
	
	public void setApcocert(String apcocert){
		this.apcocert = apcocert;
	}

	public String getApcotpba(){
		return apcotpba;
	}
	
	public void setApcotpba(String apcotpba){
		this.apcotpba = apcotpba;
	}

	public Double getApcobase(){
		return apcobase;
	}
	
	public void setApcobase(Double apcobase){
		this.apcobase = apcobase;
	}

	public String getApcotpid(){
		return apcotpid;
	}
	
	public void setApcotpid(String apcotpid){
		this.apcotpid = apcotpid;
	}

	public Double getApcoivde(){
		return apcoivde;
	}
	
	public void setApcoivde(Double apcoivde){
		this.apcoivde = apcoivde;
	}

	public String getApcotpir(){
		return apcotpir;
	}
	
	public void setApcotpir(String apcotpir){
		this.apcotpir = apcotpir;
	}

	public Long getApcoivre(){
		return apcoivre;
	}
	
	public void setApcoivre(Long apcoivre){
		this.apcoivre = apcoivre;
	}

	public String getApcotpre(){
		return apcotpre;
	}
	
	public void setApcotpre(String apcotpre){
		this.apcotpre = apcotpre;
	}

	public Double getApcorete(){
		return apcorete;
	}
	
	public void setApcorete(Double apcorete){
		this.apcorete = apcorete;
	}

	public String getApcotprc(){
		return apcotprc;
	}
	
	public void setApcotprc(String apcotprc){
		this.apcotprc = apcotprc;
	}

	public Double getApcortcr(){
		return apcortcr;
	}
	
	public void setApcortcr(Double apcortcr){
		this.apcortcr = apcortcr;
	}

	public String getApcotiic(){
		return apcotiic;
	}
	
	public void setApcotiic(String apcotiic){
		this.apcotiic = apcotiic;
	}

	public Double getApcovica(){
		return apcovica;
	}
	
	public void setApcovica(Double apcovica){
		this.apcovica = apcovica;
	}

	public Double getApcotota(){
		return apcotota;
	}
	
	public void setApcotota(Double apcotota){
		this.apcotota = apcotota;
	}

	public String getApcoesta(){
		return apcoesta;
	}
	
	public void setApcoesta(String apcoesta){
		this.apcoesta = apcoesta;
	}


	static public String[] getNames(){
		return new String[]{ "apcocons", "apcoliqu", "apcodesc", "apcopoli", "apcocert", "apcotpba", "apcobase", "apcotpid", "apcoivde", "apcotpir", "apcoivre", "apcotpre", "apcorete", "apcotprc", "apcortcr", "apcotiic", "apcovica", "apcotota", "apcoesta" };
	}		
	
	static public String getColumnNames(){
		return " apcocons, apcoliqu, apcodesc, apcopoli, apcocert, apcotpba, apcobase, apcotpid, apcoivde, apcotpir, apcoivre, apcotpre, apcorete, apcotprc, apcortcr, apcotiic, apcovica, apcotota, apcoesta ";
	}
	
	public String toString(){
		return " APCOCONS: "+ this.apcocons 
			+" APCOLIQU: "+ this.apcoliqu 
			+" APCODESC: "+ this.apcodesc 
			+" APCOPOLI: "+ this.apcopoli 
			+" APCOCERT: "+ this.apcocert 
			+" APCOTPBA: "+ this.apcotpba 
			+" APCOBASE: "+ this.apcobase 
			+" APCOTPID: "+ this.apcotpid 
			+" APCOIVDE: "+ this.apcoivde 
			+" APCOTPIR: "+ this.apcotpir 
			+" APCOIVRE: "+ this.apcoivre 
			+" APCOTPRE: "+ this.apcotpre 
			+" APCORETE: "+ this.apcorete 
			+" APCOTPRC: "+ this.apcotprc 
			+" APCORTCR: "+ this.apcortcr 
			+" APCOTIIC: "+ this.apcotiic 
			+" APCOVICA: "+ this.apcovica 
			+" APCOTOTA: "+ this.apcotota 
			+" APCOESTA: "+ this.apcoesta ;
	}
}
