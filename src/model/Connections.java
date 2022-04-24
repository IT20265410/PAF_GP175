//To implement the server-model 
package model;

import java.sql.*;
import java.sql.Connection;

public class Connections {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "0752341290");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String register( String conName, String conType, String conDesc ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into connections (`conId`,`conName`,`conType`,`conDesc`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, conName);
			preparedStmt.setString(3, conType);
			preparedStmt.setString(4, conDesc);
			
		
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Connection registered successfully";
		} catch (Exception e) {
			output = "Error while registering the Connection.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String viewConnection(String conId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Connection Name/th><th>Connection Type</th>" + "<th>Connection Description</th>" + "<th>Update</th>";

			String query = "select * from connections where conId= '" + conId +"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String conid = Integer.toString(rs.getInt("conId"));
				String conName = rs.getString("conName");
    			String conType = rs.getString("conType");
				String conDesc = rs.getString("conDesc");
				
				// Add into the html table
				output += "<tr><td>" + conName + "</td>";
				output += "<td>" + conType + "</td>";
				output += "<td>" + conDesc + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>";
			
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the connection details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateConnections(String conid, String cname, String ctype, String cdesc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE connections SET conName=?,conType=?,NIC=?,conDesc=? WHERE conId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cname);
			preparedStmt.setString(2, ctype);
			preparedStmt.setString(3, cdesc);
			preparedStmt.setInt(4, Integer.parseInt(conid));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Connection details updated successfully";
		} catch (Exception e) {
			output = "Error while updating the connection details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String viewAllConnections() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Connection Name</th><th>Connection Type</th>" + "<th>Connection Descriptionb</th>"
			+ "<th>Remove</th></tr>";

			String query = "select * from connections";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String conId = Integer.toString(rs.getInt("conId"));
				String conName = rs.getString("conName");
				String conType = rs.getString("ConType");
				String conDesc = rs.getString("condesc");
				
				
				// Add into the html table
				output += "<tr><td>" + conName + "</td>";
				output += "<td>" + conType + "</td>";
				output += "<td>" + conDesc + "</td>";
			
				// buttons
				output += "<td><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Connection details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteConnection(String conId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from connection where conId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(conId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Connection details deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the connection details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}