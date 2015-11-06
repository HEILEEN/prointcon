package com.confianza.webapp.repository.facturacion.facestaproc;

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
public class FacEstaprocRepositoryImpl implements FacEstaprocRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacEstaproc por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacEstaproc = objeto de la case FacEstaproc que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacEstaproc list(Long id){
		try{
			String sql = "select "+FacEstaproc.getColumnNames()
					   + "from FAC_ESTAPROC "
					   + "where esprcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacEstaproc.class)					
					     .setParameter("id", id);
			return (FacEstaproc)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacEstaproc
	 * @return FacEstaproc = coleccion de objetos de la case FacEstaproc que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacEstaproc> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacEstaproc.getColumnNames()
					   + "from FAC_ESTAPROC ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacEstaproc.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacEstaproc.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacEstaproc
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacEstaproc ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacEstaproc.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacEstaproc por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacEstaproc = objeto de la case FacEstaproc que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacEstaproc update(FacEstaproc facestaproc){
		getSession().update(facestaproc);
		return facestaproc;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacEstaproc por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacEstaproc = objeto de la case FacEstaproc que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacEstaproc facestaproc){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacEstaproc
	 * @value esprcons
	 * @value esprnomb
	 * @value esprporc
	 * @value esprdesc
	 * @value espreror
	 * @value espruser
	 * @value espresta
	 * @value esprduho
	 * @value esprfein
	 * @value esprfefi
	 * @return FacEstaproc = objeto de la case FacEstaproc que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacEstaproc insert(FacEstaproc facestaproc){
		getSession().save(facestaproc);	
		return facestaproc;
	}
}
