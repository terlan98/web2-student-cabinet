package controller;

import model.User;
import util.AccountManager;
import util.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("")
public class AuthController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("AuthController Received GET Request...");
		HttpSession session = req.getSession();
		
		if (session.getAttribute(AttributeNames.USER_ID_ATTR) != null) // User already logged in, redirect to cabinet
		{
			resp.sendRedirect(req.getContextPath() + "/cabinet");
		}
		else
		{
			resp.sendRedirect("auth.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String buttonType = req.getParameter("buttonType");
		
		switch (buttonType)
		{
			// SIGN IN
			case "signIn":
				if (AccountManager.isUserRegistered(email, password))
				{
					System.out.println("User is registered. Signing in...");
					
					User user = AccountManager.getUser(email);
					
					if (user != null) // found user data
					{
						session.setAttribute(AttributeNames.USER_ID_ATTR, user.getId());
						session.setAttribute(AttributeNames.FIRST_NAME_ATTR, user.getFirstName());
						session.setAttribute(AttributeNames.LAST_NAME_ATTR, user.getLastName());
						session.setAttribute(AttributeNames.AGE_ATTR, user.getAge());
						session.setAttribute(AttributeNames.CITY_ATTR, user.getCity());
						session.setAttribute(AttributeNames.COUNTRY_ATTR, user.getCountry());
						session.setAttribute(AttributeNames.GENDER_ATTR, user.getGender());
						session.setAttribute(AttributeNames.EMAIL_ATTR, email);
					}
					else // couldn't find user data
					{
						System.out.println("User data not found");
						session.setAttribute(AttributeNames.ERR_MSG_ATTR, "User not found");
						resp.sendRedirect(req.getContextPath() + "/auth");
						return;
					}
					
					resp.sendRedirect(req.getContextPath() + "/cabinet");
				}
				else
				{
					System.out.println("User not found");
					session.setAttribute(AttributeNames.ERR_MSG_ATTR, "User not found");
					resp.sendRedirect(req.getContextPath() + "/auth");
				}
				break;
			
			// SIGN UP
			case "signUp":
				int userId = -1;
				
				if (AccountManager.isUserRegistered(email)) // If the user is already registered
				{
					System.out.println("Already registered");
					session.setAttribute(AttributeNames.ERR_MSG_ATTR, "The user is already registered");
					resp.sendRedirect(req.getContextPath() + "/auth");
				}
				else if ((userId = AccountManager.createUser(password, email)) != -1) // If the user is created successfully
				{
					session.setAttribute(AttributeNames.EMAIL_ATTR, email);
					session.setAttribute(AttributeNames.REG_STATE_ATTR, "register");
					session.setAttribute(AttributeNames.USER_ID_ATTR, userId);
					
					resp.sendRedirect(req.getContextPath() + "/register");
				}
				else // If the user couldn't be created for some reason
				{
					session.setAttribute(AttributeNames.ERR_MSG_ATTR, "Couldn't create the user");
					resp.sendRedirect(req.getContextPath() + "/auth");
				}
				break;
		}
	}
}
