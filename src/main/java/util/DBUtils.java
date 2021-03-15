package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DBUtils
{
	static DataSource dataSource;
	
	private DBUtils()
	{
	}
	
	static
	{
		try
		{
			Context initContext = new InitialContext();
			Context webContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) webContext.lookup("jdbc/postgresql");
		} catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes the given query and returns 2 objects: ResultSet and Connection.
	 * These objects must be closed by the callee.
	 * @param sql query to be executed
	 * @return An arraylist containing a ResultSet and Connection.
	 */
	public static ArrayList<Object> execQuery(String sql)
	{
		ArrayList<Object> resultList = new ArrayList<>();
		
		try
		{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			boolean result = statement.execute(sql);
			
			if (result)
			{
				resultList.add(statement.getResultSet());
				resultList.add(connection);
			}
			else
			{
				System.out.println("There is no result set for this query");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	
}
