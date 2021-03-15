package util;

import model.Course;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseManager
{
	public static boolean enroll(int studentId, int courseId)
	{
		String query = "insert into enrollment values (?, ?)";
		return modifyCourse(query, studentId, courseId);
	}
	
	public static boolean drop(int studentId, int courseId)
	{
		String query = "delete from enrollment where user_id = ? and course_id = ?";
		return modifyCourse(query, studentId, courseId);
	}
	
	private static boolean modifyCourse(String query, int studentId, int courseId)
	{
		Connection connection = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
		}
		return false;
	}
	
	private static ArrayList<Course> getEnrolledCourses(int studentId)
	{
		String query = "select * from enrollment e, courses c where e.user_id = ? and e.course_id = c.course_id";
		ArrayList<Course> courses = new ArrayList<>();
		
		Connection connection = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, studentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet == null) return new ArrayList<>();
			
			while (resultSet.next())
			{
				int courseId = resultSet.getInt(3);
				String courseName = resultSet.getString(4);
				
				courses.add(new Course(courseName, courseId));
			}
			
			resultSet.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
		}
		
		return courses;
	}
	
	public static ArrayList<Course> getCourses(int studentId)
	{
		String query = "select * from courses";
		
		ArrayList<Object> queryResult = DBUtils.execQuery(query);
		ResultSet resultSet = (ResultSet) queryResult.get(0);
		Connection connection = (Connection) queryResult.get(1);
		
		if (resultSet == null) return new ArrayList<>();
		
		ArrayList<Course> courses = new ArrayList<>();
		ArrayList<Course> enrolledCourses = getEnrolledCourses(studentId);
		
		try
		{
			while (resultSet.next())
			{
				int courseId = resultSet.getInt(1);
				String courseName = resultSet.getString(2);
				
				courses.add(new Course(courseName, courseId));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				resultSet.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			closeConnection(connection);
		}
		
		for (Course course : courses)
		{
			Course foundCourse = findCourseById(enrolledCourses, course.getId());
			
			if (foundCourse != null)
			{
				course.setEnrolled(true);
			}
		}
		
		return courses;
	}
	
	public static Course findCourseById(ArrayList<Course> courses, int id)
	{
		for (Course course : courses)
		{
			if (course.getId() == id)
			{
				return course;
			}
		}
		return null;
	}
	
	private static void closeConnection(Connection connection)
	{
		try
		{
			if (connection != null) connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
