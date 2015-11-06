package com.confianza.webapp.repository.facturacion.facapcomisiones;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		facturacion  
  */                          

import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.utils.SqlFunctions;
import com.confianza.webapp.utils.Filter;

@Repository
public class FacApcomisionesRepositoryImpl implements FacApcomisionesRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private SqlFunctions sqlFunctions;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacApcomisiones por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacApcomisiones = objeto de la case FacApcomisiones que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacApcomisiones list(Long id){
		try{
			String sql = "select "+FacApcomisiones.getColumnNames()
					   + "from FAC_APCOMISIONES "
					   + "where apcocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacApcomisiones.class)					
					     .setParameter("id", id);
			return (FacApcomisiones)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacApcomisiones
	 * @return FacApcomisiones = coleccion de objetos de la case FacApcomisiones que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacApcomisiones> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacApcomisiones.getColumnNames()
					   + "from FAC_APCOMISIONES ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacApcomisiones.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacApcomisiones.class);
				
			query=sqlFunctions.setParameters(filters, query);
						 
			if(limit!=0){
				query.setFirstResult(init);			
				query.setMaxResults(limit);
			}
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo de consulta para el conteo de los registros de la tabla FacApcomisiones
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacApcomisiones ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacApcomisiones.getColumnNames());
						
			Query query = getSession().createQuery(sql);
	        
	        query=sqlFunctions.setParameters(filters, query);
	        
			Iterator it = query.list().iterator();
	        Long ret = new Long(0);
	        
	        if (it != null)
		        if (it.hasNext()){
		        	ret = (Long) it.next();
		        }
	        
			return ret.intValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Metodo para actualizar los datos de un registro de la tabla FacApcomisiones por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacApcomisiones = objeto de la case FacApcomisiones que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacApcomisiones update(FacApcomisiones facapcomisiones){
		getSession().update(facapcomisiones);
		return facapcomisiones;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacApcomisiones por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacApcomisiones = objeto de la case FacApcomisiones que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacApcomisiones facapcomisiones){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacApcomisiones
	 * @value apcocons
	 * @value apcoliqu
	 * @value apcodesc
	 * @value apcopoli
	 * @value apcocert
	 * @value apcotpba
	 * @value apcobase
	 * @value apcotpid
	 * @value apcoivde
	 * @value apcotpir
	 * @value apcoivre
	 * @value apcotpre
	 * @value apcorete
	 * @value apcotprc
	 * @value apcortcr
	 * @value apcotiic
	 * @value apcovica
	 * @value apcotota
	 * @value apcoesta
	 * @return FacApcomisiones = objeto de la case FacApcomisiones que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacApcomisiones insert(FacApcomisiones facapcomisiones){
		getSession().save(facapcomisiones);	
		return facapcomisiones;
	}
}
