package br.com.java.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection conn=null;
	public static Connection getConnection()
	{
		if(conn !=null) //if there is a connection already return it, otherwise establish one 
			return conn;
		else
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/db_controle_tarefa?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
		       
				String driver = "com.mysql.cj.jdbc.Driver";
				String username= "root";
				String password= "root";
				Class.forName(driver);
				conn = DriverManager.getConnection(url,username,password);
			}
			catch (ClassNotFoundException e) 
			{
                e.printStackTrace();
            }
			catch (SQLException e) 
			{
                e.printStackTrace();
		
			}
			return conn;
		}
		
	}
}
