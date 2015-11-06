package com.confianza.webapp.repository.facturacion.facestadoliquidacion;

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
public class FacEstadoliquidacionRepositoryImpl implements FacEstadoliquidacionRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacEstadoliquidacion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacEstadoliquidacion = objeto de la case FacEstadoliquidacion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacEstadoliquidacion list(Long id){
		try{
			String sql = "select "+FacEstadoliquidacion.getColumnNames()
					   + "from FAC_ESTADOLIQUIDACION "
					   + "where eslicons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacEstadoliquidacion.class)					
					     .setParameter("id", id);
			return (FacEstadoliquidacion)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacEstadoliquidacion
	 * @return FacEstadoliquidacion = coleccion de objetos de la case FacEstadoliquidacion que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacEstadoliquidacion> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacEstadoliquidacion.getColumnNames()
					   + "from FAC_ESTADOLIQUIDACION ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacEstadoliquidacion.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacEstadoliquidacion.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacEstadoliquidacion
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacEstadoliquidacion ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacEstadoliquidacion.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacEstadoliquidacion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacEstadoliquidacion = objeto de la case FacEstadoliquidacion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacEstadoliquidacion update(FacEstadoliquidacion facestadoliquidacion){
		getSession().update(facestadoliquidacion);
		return facestadoliquidacion;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacEstadoliquidacion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacEstadoliquidacion = objeto de la case FacEstadoliquidacion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacEstadoliquidacion facestadoliquidacion){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacEstadoliquidacion
	 * @value eslicons
	 * @value esliliqu
	 * @value eslifecr
	 * @value esliuser
	 * @value esliesta
	 * @return FacEstadoliquidacion = objeto de la case FacEstadoliquidacion que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacEstadoliquidacion insert(FacEstadoliquidacion facestadoliquidacion){
		getSession().save(facestadoliquidacion);	
		return facestadoliquidacion;
	}
}
