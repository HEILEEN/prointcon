package com.confianza.webapp.repository.facturacion.facmotivo;

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
public class FacMotivoRepositoryImpl implements FacMotivoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacMotivo = objeto de la case FacMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacMotivo list(Long id){
		try{
			String sql = "select "+FacMotivo.getColumnNames()
					   + "from FAC_MOTIVO "
					   + "where devocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacMotivo.class)					
					     .setParameter("id", id);
			return (FacMotivo)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacMotivo
	 * @return FacMotivo = coleccion de objetos de la case FacMotivo que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacMotivo> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacMotivo.getColumnNames()
					   + "from FAC_MOTIVO ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacMotivo.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacMotivo.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacMotivo
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacMotivo ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacMotivo.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacMotivo = objeto de la case FacMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacMotivo update(FacMotivo facmotivo){
		getSession().update(facmotivo);
		return facmotivo;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacMotivo = objeto de la case FacMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacMotivo facmotivo){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacMotivo
	 * @value devocons
	 * @value devonomb
	 * @value devodesc
	 * @return FacMotivo = objeto de la case FacMotivo que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacMotivo insert(FacMotivo facmotivo){
		getSession().save(facmotivo);	
		return facmotivo;
	}
}
