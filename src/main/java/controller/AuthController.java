package controller;

import util.AccountManager;
import util.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("AuthController Received GET Request...");
		resp.sendRedirect("auth.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String buttonType = req.getParameter("buttonType");
		
		System.out.println(email);
		System.out.println(password);
		System.out.println(buttonType);
		
		switch (buttonType)
		{
			// SIGN IN
			case "signIn":
				if (AccountManager.isUserRegistered(email, password))
				{
					System.out.println("User is registered. Signing in...");
					
					ResultSet userData = AccountManager.getUserInfo(email);
					if (userData != null) // found user data
					{
						try
						{
							userData.next();
							System.out.println();
							
							String firstName = userData.getString("first_name");
							String lastName = userData.getString("last_name");
							String age = userData.getString("age");
							String city = userData.getString("city");
							String country = userData.getString("country");
							String gender = userData.getString("gender");
							
							if(firstName == null || lastName == null || age == null || city == null || country == null || gender == null)
							{
								// TODO: Send the user back to /register to complete registration
								return;
							}
							
							HttpSession session = req.getSession();
							session.setAttribute(AttributeNames.FIRST_NAME_ATTR, firstName);
							session.setAttribute(AttributeNames.LAST_NAME_ATTR, lastName);
							session.setAttribute(AttributeNames.AGE_ATTR, age);
							session.setAttribute(AttributeNames.CITY_ATTR, city);
							session.setAttribute(AttributeNames.COUNTRY_ATTR, country);
							session.setAttribute(AttributeNames.GENDER_ATTR, gender);
							
						} catch (SQLException e)
						{
							e.printStackTrace();
						}
					}
					else // couldn't find user data
					{
						System.out.println("User data not found");
						resp.sendRedirect(req.getContextPath() + "/auth");
						return;
					}
					
					resp.sendRedirect(req.getContextPath() + "/cabinet");
				}
				else
				{
					System.out.println("User not found");
					resp.sendRedirect(req.getContextPath() + "/auth");
				}
				break;
			
			// SIGN UP
			case "signUp":
				if (AccountManager.isUserRegistered(email))
				{
					System.out.println("Already registered");
					resp.sendRedirect(req.getContextPath() + "/auth");
				}
				else
				{
					AccountManager.createUser(password, email);
					
					HttpSession session = req.getSession();
					session.setAttribute(AttributeNames.EMAIL_ATTR, email);
					
					resp.sendRedirect(req.getContextPath() + "/register");
				}
				break;
		}
	}
	
}
