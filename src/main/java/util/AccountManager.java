package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Communicates with the DBUtils to handle account-related data
 */
public class AccountManager
{
	private AccountManager()
	{
	}
	
	public static void createUser(String pass, String email)
	{
		String query = "insert into users (password, email) values (?, ?)";
		try
		{
			PreparedStatement preparedStatement = DBUtils.dataSource.getConnection().prepareStatement(query);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
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
		try
		{
			PreparedStatement preparedStatement = DBUtils.dataSource.getConnection().prepareStatement(query);
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
		}
	}
	
	
	public static ResultSet getUserInfo(String email)
	{
		String query = "select * from users where email = ?";
		
		try
		{
			PreparedStatement preparedStatement = DBUtils.dataSource.getConnection().prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
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
		String query = "select * from users where password = ? and email = ?";
		
		try
		{
			PreparedStatement preparedStatement = DBUtils.dataSource.getConnection().prepareStatement(query);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet.next();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
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
		
		try
		{
			PreparedStatement preparedStatement = DBUtils.dataSource.getConnection().prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet.next();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
