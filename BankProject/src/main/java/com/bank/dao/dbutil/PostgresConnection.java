package com.bank.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

	private static Connection connection;
	///disable constructor by makdig it private
	private PostgresConnection() {
		
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		//step 2 open connection
	if(connection == null) {	
Class.forName("org.postgresql.Driver");
System.out.println("Driver loaded succesfully");

String url = "jdbc:postgresql://localhost:5432/Bank";
//jdbc:postgresql://{host}[:{port}]/[{database}]
String username = "postgres";
String password = "Printer@2007";

connection = DriverManager.getConnection(url, username, password);
return connection;
	}else {
		return connection;
	}

}
}