package com.castaware.castabattle.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class _AbstractDaoHibernate 
{
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;	
		
	public HibernateTemplate getHibernateTemplate() 
	{
		return hibernateTemplate;
	}		
}