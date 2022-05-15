package com;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transmission {
	
	private Connection connect()
	 {
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/transmissions", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	 } 

public String readTransmissions()
{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table\"><tr>"
					+ "<th>Transmission No</th>"
					+ "<th>Transmission Area</th>"
					+ "<th>Transmission Name</th>"
					+ "<th>Transmission Voltage</th>"
					+ "<th>Transmission Date</th>"
					+ "<th>Transmission Time</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from distribution";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{	
				String transID = Integer.toString(rs.getInt("transID"));
				String no= rs.getString("no");
				String area = rs.getString("area");
				String name = rs.getString("name");
				String voltage = rs.getString("voltage");
				String date = rs.getString("date");
				String time = rs.getString("time");

			// Add into the html table
				output += "<tr><td>" + no + "</td>";
				output += "<td>" + area + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + voltage + "</td>"; 
				output += "<td>" + date + "</td>"; 
				output += "<td>" + time + "</td>";

			// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-transid='" + transID + "'></td>" 
						+ "<td><input name='btnRemove' type='button' value='Remove'"
						+ "class='btnRemove btn btn-danger' data-transid='" + transID + "'></td></tr>"; 
			}
			
			con.close();

			// Complete the html table
			output += "</table>";
		} 
	catch (Exception e)
	{
		output = "Error while reading the Transmissions.";
		System.err.println(e.getMessage());
	}
		
		return output;
}

public String insertTransmissions(String no, String area, String name, String voltage, String date, String time)
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting.";
			 }
			 
		 // create a prepared statement
		 String query = " insert into distribution (`no`,`area`,`name`,`voltage`,`date`,`time`)" + " values (?, ?, ?, ?, ?,?)";
		 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setString(1, no);
				 preparedStmt.setString(2, area);
				 preparedStmt.setString(3, name);
				 preparedStmt.setString(4, voltage);
				 preparedStmt.setString(5, date);
				 preparedStmt.setString(6, time);
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 String newTransmissions = readTransmissions();
				 output = "{\"status\":\"success\", \"data\": \"" + newTransmissions + "\"}";
			}
		 
		catch (Exception e)
		{
				output = "{\"status\":\"error\", \"data\":\"Error while inserting the Transmission.\"}";
				System.err.println(e.getMessage());
		}
				 return output;
	} 

public String updateTransmissions(String transID, String no, String area, String name, String voltage, String date, String time)
		 {
		 String output = "";
		 try
		 {
			 	Connection con = connect();
			 	if (con == null)
			 	{
			 			return "Error while connecting to the database for updating.";
			 	}
			 	
		 // create a prepared statement
		 String query = "UPDATE distribution SET no= ?,area= ?,name= ?,voltage= ?,date= ?,time= ? WHERE transID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, no);
		 preparedStmt.setString(2, area);
		 preparedStmt.setString(3, name);
		 preparedStmt.setString(4, voltage);
		 preparedStmt.setString(5, date);
		 preparedStmt.setString(6, time);
		 preparedStmt.setInt(7, Integer.parseInt(transID));
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newTransmissions = readTransmissions();
		 	output = "{\"status\":\"success\", \"data\": \"" + newTransmissions + "\"}";
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the Transmission.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 	return output;
	}



public String deleteTransmissions(String transID)
	{
	String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for deleting.";
		}
 
		// create a prepared statement
		String query = "delete from distribution where transID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values
		preparedStmt.setInt(1, Integer.parseInt(transID));

		// execute the statement
		preparedStmt.execute();
		con.close();

		String newTransmissions = readTransmissions();
			output = "{\"status\":\"success\", \"data\": \"" + newTransmissions + "\"}";
	}
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\":\"Error while deleting the Transmission.\"}";
		System.err.println(e.getMessage());
	}
	return output;
	}

}
