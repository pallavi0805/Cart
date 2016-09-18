package com.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.Category;

public class CategoryDAO {
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Cart");
	private EntityManager em = emfactory.createEntityManager();
    public List <Category> getCategoryList()
	{
    try {
    	Query q = em.createQuery("SELECT c FROM Category c");
	    @SuppressWarnings("unchecked")
		List<Category> cat= (List<Category>) q.getResultList();
		return cat;
	} catch (NoResultException e) {
		//e.printStackTrace();
		return null;
	}
	}
}
