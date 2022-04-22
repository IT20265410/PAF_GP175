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


}
