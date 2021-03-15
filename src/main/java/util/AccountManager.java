package util;

import model.Course;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Communicates with the DBUtils to handle account-related data
 */
public class AccountManager
{
	/**
	 * Creates a new user with the given parameters and returns the ID of the user.
	 * Returns -1 if a problem occurs.
	 *
	 * @param pass
	 * @param email
	 * @return
	 */
	public static int createUser(String pass, String email)
	{
		pass = HashManager.hash(pass);
		
		String query = "insert into users (password, email) values (?, ?)";
		
		Connection connection = null;
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			closeConnection(connection);
		}
		
		return getUserId(email);
	}
	
	/**
	 * Returns the id of the user based on email.
	 *
	 * @param email email of the user
	 * @return
	 */
	private static int getUserId(String email)
	{
		String query = "select user_id from users where email = ?";
		int id = -1;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			id = resultSet.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
			try
			{
				if (resultSet != null) resultSet.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	/**
	 * Uses the given email parameter to find and update the user data in the database.
	 *
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param city
	 * @param country
	 * @param gender
	 */
	public static void setUserData(String email, String firstName, String lastName, int age, String city, String country, String gender)
	{
		String query = "update users set first_name = ?, last_name = ?, age = ?, city = ?, country = ?, gender = ? where email = ?";
		
		Connection connection = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setInt(3, age);
			preparedStatement.setString(4, city);
			preparedStatement.setString(5, country);
			preparedStatement.setString(6, gender);
			preparedStatement.setString(7, email);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
		}
	}
	
	
	public static User getUser(String email)
	{
		String query = "select * from users where email = ?";
		Connection connection = null;
		User user = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			user = parseUser(resultSet);
			resultSet.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
		}
		
		return user;
	}
	
	/**
	 * Returns true if a user with the specified email and password exists in the database.
	 *
	 * @param email
	 * @param pass
	 * @return
	 */
	public static boolean isUserRegistered(String email, String pass)
	{
		pass = HashManager.hash(pass);
		
		String query = "select * from users where password = ? and email = ?";
		
		Connection connection = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			boolean result = resultSet.next();
			resultSet.close();
			
			return result;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
		}
		
		return false;
	}
	
	/**
	 * Returns true if a user with the specified email exists in the database.
	 *
	 * @param email
	 * @return
	 */
	public static boolean isUserRegistered(String email)
	{
		String query = "select * from users where email = ?";
		
		Connection connection = null;
		
		try
		{
			connection = DBUtils.dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			boolean result = resultSet.next();
			resultSet.close();
			
			return result;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(connection);
		}
		
		return false;
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
	
	private static User parseUser(ResultSet resultSet)
	{
		try
		{
			if (resultSet.next())
			{
				int userId = resultSet.getInt(1);
				String firstName = resultSet.getString(5);
				String lastName = resultSet.getString(6);
				int age = resultSet.getInt(7);
				String city = resultSet.getString(8);
				String country = resultSet.getString(9);
				String gender = resultSet.getString(10);
				
				return new User(userId, firstName, lastName, age, city, country, gender);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private AccountManager()
	{
	}
}
