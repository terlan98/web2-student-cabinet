package controller;

import model.Course;
import util.AttributeNames;
import util.CourseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CabinetController extends HttpServlet
{
	ArrayList<Course> courses = new ArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int userId = (int) req.getSession().getAttribute(AttributeNames.USER_ID_ATTR);
		System.out.println("Opening the cabinet of the user with id: " + userId);
		courses = CourseManager.getCourses(userId);
		
		req.getSession().setAttribute(AttributeNames.COURSE_LIST_ATTR, courses);
		
		resp.sendRedirect("cabinet.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String buttonType = req.getParameter("buttonType");
		
		int userId = (int) req.getSession().getAttribute(AttributeNames.USER_ID_ATTR);
		
		if (buttonType.contains("-")) // Deleting a course
		{
			int courseId = extractInt(buttonType);
			System.out.println("Dropping course with id: " + courseId);
			CourseManager.drop(userId, courseId);
		}
		else if (buttonType.contains("+")) // Taking a course
		{
			int courseId = extractInt(buttonType);
			System.out.println("Taking course with id: " + courseId);
			CourseManager.enroll(userId, courseId);
		}
		else if (buttonType.equals("edit")) // Editing account details
		{
			req.getSession().setAttribute(AttributeNames.REG_STATE_ATTR, "edit");
			resp.sendRedirect(req.getContextPath() + "/register");
			return;
		}
		else // Logging out
		{
			resetAllAttributes(req.getSession());
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		
		resp.sendRedirect(req.getContextPath() + "/cabinet");
	}
	
	/**
	 * Extracts the integer part of the given string and returns it as an int.
	 * @param string
	 * @return
	 */
	private int extractInt(String string)
	{
		if (string.isBlank() || string == null) return -1;
		
		try
		{
			return Integer.parseInt(string.replaceAll("\\D+",""));
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	private void resetAllAttributes(HttpSession session)
	{
		session.removeAttribute(AttributeNames.USER_ID_ATTR);
		session.removeAttribute(AttributeNames.EMAIL_ATTR);
		session.removeAttribute(AttributeNames.FIRST_NAME_ATTR);
		session.removeAttribute(AttributeNames.LAST_NAME_ATTR);
		session.removeAttribute(AttributeNames.AGE_ATTR);
		session.removeAttribute(AttributeNames.CITY_ATTR);
		session.removeAttribute(AttributeNames.COUNTRY_ATTR);
		session.removeAttribute(AttributeNames.GENDER_ATTR);
		session.removeAttribute(AttributeNames.COURSE_LIST_ATTR);
		session.removeAttribute(AttributeNames.REG_STATE_ATTR);
	}
}
