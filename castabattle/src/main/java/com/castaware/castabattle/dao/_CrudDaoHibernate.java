package com.castaware.castabattle.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public abstract class _CrudDaoHibernate<TYPE> extends _AbstractDaoHibernate
{
	/**
     * Gets the persistent class.
     * 
     * @return the persistent class
     */
    public abstract Class<TYPE> getPersistentClass();     
    
    /**
     * Insere ou Atualiza um Objeto.
     * 
     * @param object the object
     */	    
    public void saveUpdate(TYPE object) 
    {
        getHibernateTemplate().saveOrUpdate(object);
    }
    
    /**
     * Insere um Objeto.
     * 
     * @param object the object
     */	    
	public void save(TYPE object) 
	{
		getHibernateTemplate().save(object);
	}
	    
	public TYPE merge(TYPE object) 
	{
    	return getHibernateTemplate().merge(object);
	}
    
	/**
	 * Insere v�rios Objetos.
	 * 
	 * @param collection the objects
	 */    
	public void saveAll(Collection<TYPE> collection) 
	{
		for (TYPE object : collection) 
		{
			save(object);
		}		
	}

	/**
	 * Atualiza um Objeto.
	 * 
	 * @param object the object
	 */	    
	public void update(TYPE object) 
	{
		getHibernateTemplate().update(object);
	}
	
	/**
	 * Atualiza v�rios Objetos.
	 * 
	 * @param collection the objects
	 */	    
	public void updateAll(Collection<TYPE> collection) 
	{
		for (TYPE object : collection) 
		{
			update(object);
		}		
	}
	
    /**
     * Remove um Objeto.
     * 
     * @param object the object
     */		    
	public void remove(TYPE object) 
	{
		getHibernateTemplate().delete(object);
	}
	
	/**
	 * Busca um Objeto por Id.
	 * 
	 * @param id the id
	 * 
	 * @return the TYPE
	 */        
	public TYPE retrieveById(Long id) 
    {
    	TYPE entity = (TYPE)getHibernateTemplate().get(getPersistentClass(), id);
        return entity;
    }
        
	public TYPE retrieveById(Integer id) 
    {
    	TYPE entity = (TYPE)getHibernateTemplate().get(getPersistentClass(), id);
        return entity;
    }
	
	/**
	 * Busca um Objeto por Exemplos.
	 * 
	 * @return the list< typ e>
	 */        
	public List<TYPE> retrieveAll() 
	{
		return (List<TYPE>)getHibernateTemplate().loadAll(getPersistentClass());		
	}
	
	/**
	 * Busca um Objeto por Exemplos.
	 * 
	 * @param exemplo the exemplo
	 * 
	 * @return the list<type>
	 */	 
	public List<TYPE> retrieveByExample(TYPE exemplo) 
	{	
		return (List<TYPE>)getHibernateTemplate().findByExample(exemplo);		
	}
}