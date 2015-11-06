package com.confianza.webapp.repository.facturacion.facresumenajustes;

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
public class FacResumenajustesRepositoryImpl implements FacResumenajustesRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacResumenajustes por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacResumenajustes = objeto de la case FacResumenajustes que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacResumenajustes list(Long id){
		try{
			String sql = "select "+FacResumenajustes.getColumnNames()
					   + "from FAC_RESUMENAJUSTES "
					   + "where reajcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacResumenajustes.class)					
					     .setParameter("id", id);
			return (FacResumenajustes)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacResumenajustes
	 * @return FacResumenajustes = coleccion de objetos de la case FacResumenajustes que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacResumenajustes> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacResumenajustes.getColumnNames()
					   + "from FAC_RESUMENAJUSTES ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacResumenajustes.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacResumenajustes.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacResumenajustes
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacResumenajustes ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacResumenajustes.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacResumenajustes por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacResumenajustes = objeto de la case FacResumenajustes que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacResumenajustes update(FacResumenajustes facresumenajustes){
		getSession().update(facresumenajustes);
		return facresumenajustes;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacResumenajustes por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacResumenajustes = objeto de la case FacResumenajustes que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacResumenajustes facresumenajustes){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacResumenajustes
	 * @value reajcons
	 * @value reajliqu
	 * @value reajtiva
	 * @value reajtota
	 * @value reajesta
	 * @return FacResumenajustes = objeto de la case FacResumenajustes que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacResumenajustes insert(FacResumenajustes facresumenajustes){
		getSession().save(facresumenajustes);	
		return facresumenajustes;
	}
}
