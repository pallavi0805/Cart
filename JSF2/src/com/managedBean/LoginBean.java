package com.managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.model.Users;
import com.util.SessionUtils;
import com.util.UserDAO;

/**
 * 
 * @author Pallavi Jaiswal
 * 18/09/2016
 *
 */
@ManagedBean(name="lb")
@SessionScoped
public class LoginBean 
{
	private UserDAO userDAO;
	private Users user=new Users();

	public String check() {
		userDAO = new UserDAO();		
		String result = userDAO.getUser(user.getEmailId(), user.getPassword());

		if (result.equals("No")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email not found", "Login Error!!"));
			return null;
		}
		else if(result.equals("Password incorrect!!"))	
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password incorrect!!", "Login Error!!"));
			return null;
		} else 
			return "Success";
	}

	public String logOut() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login";
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {				
		this.user = user;
	}
}