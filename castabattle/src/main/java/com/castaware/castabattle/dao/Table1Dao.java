package com.castaware.castabattle.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.castaware.castabattle.domain.Table1;

@Repository // @Component
@SuppressWarnings("deprecation")
public class Table1Dao 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Table1> retrieveAll()
	{
		// 1 - Utilizando HQL (Hibernate Query Language) 
		// Direto no SessionFactory
		/* Session hbsession = sessionFactory.openSession();
		Query query = hbsession.createQuery("from Table1");
		List<Table1> result = (List<Table1>)query.list();
		return result; */
		
		// 2 - Utilizando Criteria API
		/*Session hbsession = sessionFactory.openSession();
		Criteria criteria = hbsession.createCriteria(Table1.class);
		List<Table1> result = criteria.list();
		return result;*/
		
		// 3 - Utilizando HibernateTemplate
		List<Table1> result = hibernateTemplate.loadAll(Table1.class);
		return result;		
	}
	
	@SuppressWarnings("unchecked")
	public Table1 retrieveById(Long id)
	{
		// 1 - Utilizando HQL (Hibernate Query Language) 
		/* Session hbsession = sessionFactory.openSession();
		Query query = hbsession.createQuery("from Table1 where id = :idParam");
		query.setParameter("idParam",id);
		List<Table1> result = (List<Table1>)query.list(); 

		if (result.size() == 0)
			return null;
		else if (result.size() > 1)
			throw new IllegalStateException("More than one result for the provided id");
		else
			return result.get(0); */
		
		// 2 - Utilizando Criteria API
		/* Session hbsession = sessionFactory.openSession();
		Criteria criteria = hbsession.createCriteria(Table1.class);
		criteria.add(Restrictions.eq("id",id));
		List<Table1> result = criteria.list(); 
		
		if (result.size() == 0)
			return null;
		else if (result.size() > 1)
			throw new IllegalStateException("More than one result for the provided id");
		else
			return result.get(0); */
		
		//Table1 result = (Table1)hibernateTemplate.load(Table1.class, id);
        return null;
	}
}









