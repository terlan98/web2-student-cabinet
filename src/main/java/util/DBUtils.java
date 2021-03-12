package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

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
	
	public static ResultSet execQuery(String sql)
	{
		try
		{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			boolean result = statement.execute(sql);
			
			if (result)
			{
				ResultSet resultSet = statement.getResultSet();
				return resultSet;
			}
			else
			{
				System.out.println("There is no result set for this query");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
