package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/consume", "root",""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertService(String month, String pastUnits, String currentUnits, String consumeUnits) 
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
	 String query = "insert into consumption (`id`,`month`,`pastUnits`,`currentUnits`,`consumeUnits`)"
	 + " values (?, ?, ?, ?, ? )"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values 
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, month); 
	 preparedStmt.setString(3, pastUnits); 
	 preparedStmt.setString(4, currentUnits);
	 preparedStmt.setString(5, consumeUnits);
	 // execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String readService() 
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
	 output = "<table border='1'>"+
	 "<tr><th>Id</th>" + 
	 "<th>month</th>"+
     "<th>pastUnits</th>" +
	 "<th>currentUnits</th>" + 
	 "<th>consumeUnits</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from consumption"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id"));	 
	 String month = rs.getString("month"); 
	 String pastUnits = rs.getString("pastUnits");
	 String currentUnits = rs.getString("currentUnits");
	 String consumeUnits = rs.getString("consumeUnits"); 
	 // Add Stringo the html table 
	 output += "<tr><td>" + id + "</td>";
	 output += "<td>" + month + "</td>"; 
	 output += "<td>" + pastUnits + "</td>"; 
	 output += "<td>" + currentUnits + "</td>";
	 output += "<td>" + consumeUnits + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + id 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

	public String updateService(String id, String month, String pastUnits, String currentUnits, String consumeUnits ) 
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
	 String query = "UPDATE consumption SET month=?, pastUnits=?, currentUnits=?, consumeUnits=?  WHERE id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, month); 
	 preparedStmt.setString(2, pastUnits); 
	 preparedStmt.setString(3, currentUnits); 
	 preparedStmt.setString(4, consumeUnits);
	 preparedStmt.setInt(5, Integer.parseInt(id));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } catch (Exception e) { 
	 output = "Error while updating the details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String deleteService(String id) 
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
	 String query = "delete from consumption where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

}
