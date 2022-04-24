//To implement the server-model 
package model;

import java.sql.*;
import java.sql.Connection;
         
public class Bill {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String addBill(String issueDate, int unit, float balance, float amountToPay , float totalAmount, int customerId ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into bill(`billId`,`issueDate`,`unit`,`balance`,`amountToPay`, `totalAmount`, `customerId`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, issueDate);
			preparedStmt.setInt(3, unit);
			preparedStmt.setFloat(4, balance);
			preparedStmt.setFloat(5, amountToPay);
			preparedStmt.setFloat(6, totalAmount);
			preparedStmt.setInt(7, customerId);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Bill added successfully";
		} catch (Exception e) {
			output = "Error while inserting the bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String viewBill(String billId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Issue Date</th><th>Unit</th>" + "<th>Balance</th>"
					+ "<th>Amount To Pay</th>" + "<th>Total Amount</th>"+ "<th>Customer Id</th>"+ "<th>Update</th>";

			String query = "select * from users where userId= '" + billId +"' ";
			Statement stmt = con.createStatement();
			ResultSet totalAmount = stmt.executeQuery(query);
			
			ResultSet rs = null;
			// iterate through the rows in the result set
			while (rs.next()) {
				String billid = Integer.toString(rs.getInt("billId"));
				String issueDate = rs.getString("issueDate");
				String unit = Integer.toString(rs.getInt("unit"));
				String balance = Float.toString(rs.getFloat("balance"));
				String amountToPay = Float.toString(rs.getFloat("amountToPay"));
				String totalAmount1 = Float.toString(rs.getFloat("totalAmount"));
				String customerId = Integer.toString(rs.getInt("customerId"));
			
				// Add into the html table
				output += "<tr><td>" + issueDate + "</td>";
				output += "<td>" + unit + "</td>";
				output += "<td>" + balance + "</td>";
				output += "<td>" + amountToPay + "</td>";
				output += "<td>" + totalAmount1 +"</td>";
				output += "<td>" + customerId + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>";
			
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the bill details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateBill(String issueDate, String unit, String balance, String amountToPay , String totalAmount, String customerId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE bill SET issueDate=?,unit=?,balance=?,amountToPay=?,totalAmount=?,customerId=? WHERE billId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, issueDate);
			preparedStmt.setString(3, unit);
			preparedStmt.setString(4, balance);
			preparedStmt.setString(5, amountToPay);
			preparedStmt.setString(6, totalAmount);
			preparedStmt.setString(7, customerId); 
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Bill details updated successfully";
		} catch (Exception e) {
			output = "Error while updating the bill details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String viewAllBill() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Issue Date</th><th>Unit</th>" + "<th>Balance</th>"
					+ "<th>Amount To Pay</th>" + "<th>Total Amount</th>"+ "<th>Customer Id</th>"+ "<th>Update</th>";

			String query = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String billid = Integer.toString(rs.getInt("billId"));
				String issueDate = rs.getString("issueDate");
				String unit = Integer.toString(rs.getInt("unit"));
				String balance = Float.toString(rs.getFloat("balance"));
				String amountToPay = Float.toString(rs.getFloat("amountToPay"));
				String totalAmount = Float.toString(rs.getFloat("totalAmount"));
				String customerId = Integer.toString(rs.getInt("customerId"));
			
				// Add into the html table
				output += "<tr><td>" + issueDate + "</td>";
				output += "<td>" + unit + "</td>";
				output += "<td>" + balance + "</td>";
				output += "<td>" + amountToPay + "</td>";
				output += "<td>" + totalAmount +"</td>";
				output += "<td>" + customerId + "</td>";
				// buttons
				output += "<td><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the bill details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteBill(String billId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from users where userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Bill details deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the bill details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
