//To implement the server-model 
package model;

import java.sql.*;
import java.sql.Connection;

public class User {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "thulya123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String register(String fname, String lname, String nic, String address , int phone, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into users (`userId`,`firstName`,`lastName`,`NIC`,`address`, `phone`, `email`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, nic);
			preparedStmt.setString(5, address);
			preparedStmt.setInt(6, phone);
			preparedStmt.setString(7, email);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "User registered successfully";
		} catch (Exception e) {
			output = "Error while registering the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
