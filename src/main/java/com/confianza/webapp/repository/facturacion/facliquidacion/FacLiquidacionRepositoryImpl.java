package com.confianza.webapp.repository.facturacion.facliquidacion;

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
public class FacLiquidacionRepositoryImpl implements FacLiquidacionRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacLiquidacion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacLiquidacion = objeto de la case FacLiquidacion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacLiquidacion list(Long id){
		try{
			String sql = "select "+FacLiquidacion.getColumnNames()
					   + "from FAC_LIQUIDACION "
					   + "where liqucons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacLiquidacion.class)					
					     .setParameter("id", id);
			return (FacLiquidacion)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacLiquidacion
	 * @return FacLiquidacion = coleccion de objetos de la case FacLiquidacion que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacLiquidacion> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacLiquidacion.getColumnNames()
					   + "from FAC_LIQUIDACION ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacLiquidacion.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacLiquidacion.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacLiquidacion
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacLiquidacion ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacLiquidacion.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacLiquidacion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacLiquidacion = objeto de la case FacLiquidacion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacLiquidacion update(FacLiquidacion facliquidacion){
		getSession().update(facliquidacion);
		return facliquidacion;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacLiquidacion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacLiquidacion = objeto de la case FacLiquidacion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacLiquidacion facliquidacion){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacLiquidacion
	 * @value liqucons
	 * @value liquusua
	 * @value liqufein
	 * @value liqufefi
	 * @value liqutoco
	 * @value liqutoap
	 * @value liqureaj
	 * @value liqutore
	 * @value liqunuco
	 * @value liqufecr
	 * @value liquesta
	 * @return FacLiquidacion = objeto de la case FacLiquidacion que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacLiquidacion insert(FacLiquidacion facliquidacion){
		getSession().save(facliquidacion);	
		return facliquidacion;
	}
}
