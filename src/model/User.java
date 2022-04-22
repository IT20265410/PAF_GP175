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
	
//	public String readCustomer() {
//		String output = "";
//		try {
//			Connection con = connect();
//			if (con == null) {
//				return "Error while connecting to the database for reading.";
//			}
//			// Prepare the html table to be displayed
//			output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" + "<th>NIC</th>"
//					+ "<th>Address</th>" + "<th>Phone</th>"+ "<th>Email</th>"+ "<th>Update</th>";
//
//			String query = "select * from users where userId=?";
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			// iterate through the rows in the result set
//			while (rs.next()) {
//				String userId = Integer.toString(rs.getInt("userId"));
//				String firstName = rs.getString("firstName");
//				String lastName = rs.getString("lastName");
//				String NIC = rs.getString("NIC");
//				String address = rs.getString("address");
//				String phone = Integer.toString(rs.getInt("phone"));
//				String email = rs.getString("email");
//				
//				// Add into the html table
//				output += "<tr><td>" + firstName + "</td>";
//				output += "<td>" + lastName + "</td>";
//				output += "<td>" + NIC + "</td>";
//				output += "<td>" + address + "</td>";
//				output += "<td>" + phone + "</td>";
//				output += "<td>" + email + "</td>";
//				// buttons
//				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>";
//			}
//			con.close();
//			// Complete the html table
//			output += "</table>";
//		} catch (Exception e) {
//			output = "Error while reading the customer details.";
//			System.err.println(e.getMessage());
//		}
//		return output;
//	}
	
	public String updateCustomer(String userid, String fname, String lname, String nic, String address, String phone, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE users SET firstName=?,lastName=?,NIC=?,address=?,phone=?,email=? WHERE userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, address);
			preparedStmt.setInt(5, Integer.parseInt(phone));
			preparedStmt.setString(6, email);
			preparedStmt.setInt(7, Integer.parseInt(userid));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Customer details updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
