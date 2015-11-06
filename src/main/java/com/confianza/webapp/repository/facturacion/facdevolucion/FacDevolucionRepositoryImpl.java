package com.confianza.webapp.repository.facturacion.facdevolucion;

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
public class FacDevolucionRepositoryImpl implements FacDevolucionRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacDevolucion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacDevolucion = objeto de la case FacDevolucion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacDevolucion list(Long id){
		try{
			String sql = "select "+FacDevolucion.getColumnNames()
					   + "from FAC_DEVOLUCION "
					   + "where devocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacDevolucion.class)					
					     .setParameter("id", id);
			return (FacDevolucion)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacDevolucion
	 * @return FacDevolucion = coleccion de objetos de la case FacDevolucion que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacDevolucion> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacDevolucion.getColumnNames()
					   + "from FAC_DEVOLUCION ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacDevolucion.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacDevolucion.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacDevolucion
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacDevolucion ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacDevolucion.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacDevolucion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacDevolucion = objeto de la case FacDevolucion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacDevolucion update(FacDevolucion facdevolucion){
		getSession().update(facdevolucion);
		return facdevolucion;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacDevolucion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacDevolucion = objeto de la case FacDevolucion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacDevolucion facdevolucion){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacDevolucion
	 * @value devocons
	 * @value devoliqu
	 * @value devodesc
	 * @value devofech
	 * @return FacDevolucion = objeto de la case FacDevolucion que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacDevolucion insert(FacDevolucion facdevolucion){
		getSession().save(facdevolucion);	
		return facdevolucion;
	}
}
