//To implement the server-model 
package model;

import java.sql.*;


public class Payment {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "thulya123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String payment( String cardnumber, String cvv, String expdate, float amounts, int paycustomerid ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into payment (`paymentId`,`cardNumber`,`CVV`,`expDate`,`amount`,`payCustomerId`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cardnumber);
			preparedStmt.setString(3, cvv);
			preparedStmt.setString(4, expdate);
			preparedStmt.setFloat(5, amounts);
			preparedStmt.setInt(6, paycustomerid);
			
		
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment added successfully";
		} catch (Exception e) {
			output = "Error while adding the payment..";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	

	
	public String updatePayment(String paymentid, String cardnumber, String cvv, String expdate, String amounts, String paycustomerid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payment SET cardNumber=?,CVV=?,expDate=?,amount=?,payCustomerId=? WHERE paymentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cardnumber);
			preparedStmt.setString(2, cvv);
			preparedStmt.setString(3, expdate);
			preparedStmt.setFloat(4, Float.parseFloat(amounts));
			preparedStmt.setInt(5, Integer.parseInt(paycustomerid));
			preparedStmt.setInt(6, Integer.parseInt(paymentid));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment details updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String viewAllPayments() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Card No</th><th>CVV</th>" + "<th>Expire Date</th>" + "<th>Amount</th>"
			+ "<th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentId = Integer.toString(rs.getInt("paymentId"));
				String cardNumber = rs.getString("cardNumber");
				String CVV = rs.getString("CVV");
				String expDate = rs.getString("expDate");
				String amount = Float.toString(rs.getFloat("amount"));
				String payCustomerId = Integer.toString(rs.getInt("payCustomerId"));
				
				
				// Add into the html table
				output += "<tr><td>" + cardNumber + "</td>";
				output += "<td>" + CVV + "</td>";
				output += "<td>" + expDate + "</td>";
				output += "<td>" + amount + "</td>";
			
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
	
	public String deletePayment(String paymentId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from payment where paymentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment details deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}