package com.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.Category;
import com.model.Product;

public class ProductDAO 
{
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Cart");
	private EntityManager em = emfactory.createEntityManager();

	public List <Product> getProductList()
	{
		try 
		{
			Query q = em.createQuery("SELECT p FROM Product p order by p.productId");
			List<Product> prod= (List<Product>) q.getResultList(); 
			return prod;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addProductToDb(Product p)
	{   
		try{
			em.getTransaction().begin();    	
			em.persist(p);
			//em.merge(p);
			
			em.flush();
			
			// em.clear();
	    	// em.close();
			// emfactory.close();
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	public void updateProductToDb(Product p)
	{	
		try{
			em.getTransaction().begin();    	
			//   Category cat;
			//	em.persist(p);	
			//    for (Product p : products) {
			//cat=em.find(Category.class, p.getCategory().getCategoryId());
			//p.setCategory(cat);
			//em.contains(arg0)
			em.merge(p);
			em.flush();
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public void deleteProductFromDb(Product p)
	{	
		try{
			em.getTransaction().begin();
			em.remove(p);
			em.flush();
			em.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}