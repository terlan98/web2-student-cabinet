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
		else // Editing account details
		{
			req.getSession().setAttribute(AttributeNames.REG_STATE_ATTR, "edit");
			resp.sendRedirect(req.getContextPath() + "/register");
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
}
