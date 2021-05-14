package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class requirement {
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertRequirement(String resId, String reqType, String reqNote)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database."; }
		 // create a prepared statement
		 String query = " insert into requirement(`reqId`,`resId`,`reqType`,`reqNote`)"
				 + " values (?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
					preparedStmt.setInt(1, Integer.parseInt(resId));
					preparedStmt.setString(2, reqType);
					preparedStmt.setString(3, reqNote);
				// execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 String newRequirement = readrequirement();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newRequirement + "\"}" ; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error While Inserting the Requirement.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readrequirement()
		{
			 String output = "";
			try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database.";
			 }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>ReqId</th><th>ResId</th>" + "<th>Req_Type</th>"
						+ "<th>Req_Note</th>"+ "<th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from requirement";
			
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String reqId = Integer.toString(rs.getInt("reqId"));
				 String resId = Integer.toString(rs.getInt("resId"));
				 String reqType = rs.getString("reqType");
				 String reqNote = rs.getString("reqNote");
			 // Add into the html table;
			 
					output += "<tr><td>" + reqId + "</td>";
					output += "<td>" + resId + "</td>";
					output += "<td>" + reqType + "</td>";
					output += "<td>" + reqNote + "</td>";
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'data-productid='" + reqId + "'></td>"
							 + "<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger' data-productid='" + reqId + "'></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 } 
			
			catch (Exception e)
			 {
			 output = "Error while reading the requirement.";
			 System.err.println(e.getMessage());
			 }
			return output;
			}

		
		public String updateRequirement(String reqId, String resId, String reqType, String reqNote)

		
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE requirement SET resId=?,reqType=?,reqNote=?WHERE reqId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(resId)); 
			 preparedStmt.setString(2, reqType); 
			 preparedStmt.setString(3, reqNote); 
			 preparedStmt.setInt(4, Integer.parseInt(reqId)); 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newrequirement = readrequirement();
			 output =  "{\"status\":\"success\", \"data\": \"" + newrequirement + "\"}" ; 
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\": \"Error While Updating the requirement.\"}";
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 }
			 return output;
			 }
		
			public String deleteRequirement(String reqId)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from requirement where reqId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(reqId));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newrequirement = readrequirement();
			 output =  "{\"status\":\"success\", \"data\": \"" + newrequirement + "\"}" ; 
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\": \"Error While Deleting the requirement.\"}";
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 
			 }
			 return output;
			 }

}
