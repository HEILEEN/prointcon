package com.confianza.webapp.repository.facturacion.faccomision;

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
public class FacComisionRepositoryImpl implements FacComisionRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FacComision por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacComision = objeto de la case FacComision que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacComision list(Long id){
		try{
			String sql = "select "+FacComision.getColumnNames()
					   + "from FAC_COMISION "
					   + "where comicons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacComision.class)					
					     .setParameter("id", id);
			return (FacComision)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FacComision
	 * @return FacComision = coleccion de objetos de la case FacComision que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FacComision> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FacComision.getColumnNames()
					   + "from FAC_COMISION ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FacComision.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FacComision.class);
				
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
	 * Metodo de consulta para el conteo de los registros de la tabla FacComision
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FacComision ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, FacComision.getColumnNames());
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FacComision por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacComision = objeto de la case FacComision que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacComision update(FacComision faccomision){
		getSession().update(faccomision);
		return faccomision;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FacComision por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FacComision = objeto de la case FacComision que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FacComision faccomision){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FacComision
	 * @value comicons
	 * @value comiliqu
	 * @value comifere
	 * @value comidore
	 * @value comidocu
	 * @value comipoli
	 * @value comicert
	 * @value comitoma
	 * @value comiprim
	 * @value comipoco
	 * @value comicomi
	 * @value comiivad
	 * @value comiivar
	 * @value comirete
	 * @value comicree
	 * @value comivica
	 * @value cominepa
	 * @value comiesta
	 * @return FacComision = objeto de la case FacComision que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FacComision insert(FacComision faccomision){
		getSession().save(faccomision);	
		return faccomision;
	}
}
