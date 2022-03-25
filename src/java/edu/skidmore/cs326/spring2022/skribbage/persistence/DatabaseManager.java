package edu.skidmore.cs326.spring2022.skribbage.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

		   static final String DB_URL = "jdbc:mysql://bits.monead.com:3306/skribbage";
		   static final String USER = "skribbage_adm";
		   static final String PASS = "cFT8Ef^MmRHP";
		   static final String QUERY = "SELECT * FROM prototype_table";
		   	
		   
		   private static Connection dbConnection;
		   
		   
		   //Query Function
		   // static final Stry{
//		   Connection conn  = DriverManager.getConnection(DB_URL, USER, PASS);
//		   return conn;
//	   } catch (SQLException e) {
//		   System.out.println("Failed to establish connection with database");
//		   e.printStackTrace();	   
//	   }
//	   tring QUERY = "SELECT * FROM prototype_table";
		   // function to assert that username and password belongs to an existing user and that they match
		   
		   
		   
		   /*
		    * This is a function to Authenticate the user password
		    * @param password : a placeholder to be replaced by hash password
		    * 					*note that the SQL player_account table must also be updated to facilitate this change
		    * @param username : the username of the user to authenticate. 
		    * 					*note that this will only work for previously created users as is
		    *
		    */
		   public static boolean userAuthenticate(String username,  String password) {
	
			
			   String tempQuery = "SELECT * FROM player_account WHERE username='" + username + "'";
			 //  Connection conn = dbConnect(); 
			   String storedPassword = "";
			   
			 try {
				 Connection conn  = getDB();
			     Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery(tempQuery); 
			    
			     
			     	 rs.next();
			    	 storedPassword = rs.getString("Password");	
			     
			     	   
				 
			 } catch(SQLException e) {
				 System.out.println("Account not found");
				 e.printStackTrace();	
				 
			 }
			   
		 
			 if(storedPassword.compareTo(password) == 0){
				 System.out.println("Password Accepted");
				   return true;
			   } else {
				   System.out.println("Incorrect Password");
				   return false;
			   }
			 
			 	  	   
		   }
		   
		   
		   
		   /*
		    * This is a function to access the connection singleton
		    *
		    */
		   private static Connection getDB() {
			    
			   if(dbConnection == null) {
				   try{
					   dbConnection   = DriverManager.getConnection(DB_URL, USER, PASS);
					 
				   } catch (SQLException e) {
					   System.out.println("Failed to establish connection with database");
					   e.printStackTrace();	   
				   }
				  return dbConnection;
			   } else {
				   return dbConnection;
			   }
  
			   
		   }
		   
		   /*
		    * This is a function to disconnect the connection passed into the "theConnection" parameter
		    * 
		    * @param theConnection: the connection passed in to be terminated
		    */
		   private void dbDisconnect(Connection theConnection) {
			   		   
			   try {   
				   theConnection.close();		   
			   } catch (SQLException e) {			   
				   System.out.println("Failed to close connection to database");		
				   e.printStackTrace(); 		   
			   }
			   
		   }
		   
		   
		   
		   /*
		    * Just Kidding, you know what this does
		    * 
		    */
		   public static void main(String[] args) {
			    
			      // Open a connection
			      //try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 	       
//			         Statement stmt = conn.createStatement();
//			         ResultSet rs = stmt.executeQuery(QUERY);
//			    		  
//			    		  ){		      
//			    		         while(rs.next()){
//			    		            //Display values
//			    		            System.out.print(" ID: " + rs.getInt("person_id"));
//			    		            System.out.print(", Name: " + rs.getString("person_name"));
//			    		            test
//			    		         }
//			    		         conn.close();
//			         
//			         
//			      } catch (SQLException e) {
//			         e.printStackTrace();
//			      }
//
//			      
//			      
//			    
			      
			      
			     
			     userAuthenticate("nchantzi","ILoveSQL");
			     
			   }

			      
			      

}
