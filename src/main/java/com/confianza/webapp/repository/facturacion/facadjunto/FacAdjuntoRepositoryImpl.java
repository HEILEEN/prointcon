package com.confianza.webapp.repository.facturacion.facadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
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
public class FacAdjuntoRepositoryImpl implements FacAdjuntoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacAdjunto = objeto de la case FacAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacAdjunto list(Long id){
		try{
			String sql = "select "+FacAdjunto.getColumnNames()
					   + "from FAC_ADJUNTO "
					   + "where adjucons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacAdjunto.class)					
					     .setParameter("id", id);
			return (FacAdjunto)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacAdjunto
	 * @return FacAdjunto = coleccion de objetos de la case FacAdjunto que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacAdjunto> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacAdjunto.getColumnNames()
					   + "from FAC_ADJUNTO ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacAdjunto.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacAdjunto.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacAdjunto
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacAdjunto ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacAdjunto.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacAdjunto = objeto de la case FacAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacAdjunto update(FacAdjunto facadjunto){
		getSession().update(facadjunto);
		return facadjunto;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacAdjunto = objeto de la case FacAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacAdjunto facadjunto){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacAdjunto
	 * @value adjucons
	 * @value adjudevo
	 * @value adjuarch
	 * @value adjunomb
	 * @value adjuuser
	 * @value adjufech
	 * @value adjuesta
	 * @return FacAdjunto = objeto de la case FacAdjunto que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacAdjunto insert(FacAdjunto facadjunto){
		getSession().save(facadjunto);	
		return facadjunto;
	}
}
