package controller;

import util.AccountManager;
import util.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationController extends HttpServlet
{
	private static String userEmail = "";
	private static String firstName = "";
	private static String lastName = "";
	private static String age = "";
	private static String city = "";
	private static String country = "";
	private static String gender = "";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("RegistrationController Received GET Request...");
		
		HttpSession session = req.getSession();
		String emailAttribute = (String) session.getAttribute(AttributeNames.EMAIL_ATTR);
		
		if (emailAttribute == null) // redirect to auth to prevent directly accessing this page
		{
			resp.sendRedirect(req.getContextPath() + "/auth");
			return;
		}
		
		userEmail = emailAttribute;
		
		System.out.println("Registration opened for :" + emailAttribute);
		
		resp.sendRedirect("register.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		firstName = req.getParameter("firstName");
		lastName = req.getParameter("lastName");
		age = req.getParameter("age");
		city = req.getParameter("city");
		country = req.getParameter("country");
		gender = req.getParameter("gender");
		
		if(userEmail.equals("") || firstName == null || lastName == null || age == null || city == null || country == null || gender == null)
		{
			System.out.println("Can't register. Incomplete data");
			return;
		}
		
		int ageInt = Integer.parseInt(age);
		
		AccountManager.setUserData(userEmail, firstName, lastName, ageInt, city, country, gender);
		
		saveUserToSession(req);
		
		resp.sendRedirect(req.getContextPath() + "/cabinet");
	}
	
	/**
	 * Saves the user info to the session. Should be called upon the submission of the registration form.
	 * @param req
	 */
	private void saveUserToSession(HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		session.setAttribute(AttributeNames.FIRST_NAME_ATTR, firstName);
		session.setAttribute(AttributeNames.LAST_NAME_ATTR, lastName);
		session.setAttribute(AttributeNames.AGE_ATTR, age);
		session.setAttribute(AttributeNames.CITY_ATTR, city);
		session.setAttribute(AttributeNames.COUNTRY_ATTR, country);
		session.setAttribute(AttributeNames.GENDER_ATTR, gender);
	}
}
