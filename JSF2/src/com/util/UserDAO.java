package com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.model.Users;

public class UserDAO 
{
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Cart");
	private EntityManager em = emfactory.createEntityManager();

	public String getUser(String email, String pwd) {
		  try{  Query q=em.createQuery("SELECT u from Users u where u.emailId = :EMAIL_ID"); 
			q.setParameter("EMAIL_ID", email);
			Users user = (Users) q.getSingleResult();
			if(user.getPassword().equals(pwd))
			{	HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
				
				return "Yes";
			}
			else
				    return "Password incorrect!!";		   
		  
	        }
	catch (NoResultException e) {
		//e.printStackTrace();
		return "No";
	}
  }
}
