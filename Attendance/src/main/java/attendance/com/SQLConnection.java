package attendance.com;


import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import jakarta.servlet.http.HttpServletResponse;

public class SQLConnection {
		static Connection con;
		boolean connect(){
			String user ="sa",pass="ser@123";
			String url = "jdbc:sqlserver://localhost:1433;databaseName=login;encrypt=true;trustServerCertificate=true;" + "user=" + user + ";password="
					+ pass;
				try {
					
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
					con=DriverManager.getConnection(url);
					System.out.println("find driver");
					System.out.println("Connection succefully");
					
				}	
				catch(Exception e) {
					e.printStackTrace();
				}
		
		return true;
		}
		public String  register(String username, String password,String emailid,String phone  ){
			SQLConnection sqlcon=new SQLConnection();
			String error_message = "";

			if (username.isEmpty()) {
				error_message = "Full Name";
				return error_message;
			}
			if (password.equals("")) {
				error_message = "password";
				return error_message;
			}

			if (emailid.equals("")) {
				error_message = "Email ID";
				return error_message;
			}
		
			if (phone.equals("")) {
				error_message = "phone";
				return error_message;
			}
			
			int result=0;
			boolean requested=connect();
			if(requested) {
				try {
					PreparedStatement pst=con.prepareStatement("insert into SignUp values(?,?,?,?)");
					pst.setString(1, username);
					pst.setString(2, password);
					pst.setString(3, emailid);
					pst.setString(4, phone);
					int i =pst.executeUpdate();
					if(i>0) {
						System.out.println("Insert succefully");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			return null;
		
		}
		//login validation check
		public String loginverfiycheck(String username, String password) {
			String err_message="";
			if(username.isEmpty()) {
				err_message="UserName is empty";
				return err_message;
			}
			if(password.isEmpty()) {
				err_message="Password  is empty";
				return err_message;
			}
			SQLConnection logindata=new SQLConnection();
			boolean result=connect();
		
			if(result) {
				try {
					String query="select * from SignUp where username=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, username);
					ResultSet rs=pst.executeQuery();
					if(rs.next()) {
						String spass=rs.getString("password");
						if(password.equals(spass)) {
							System.out.println("Login succefull");
							try {
			                    PreparedStatement insertStmt = con.prepareStatement("INSERT INTO SignIn (username,password) VALUES (?,?)");
			                    insertStmt.setString(1, username);
			                    insertStmt.setString(2, password);
			                    insertStmt.executeUpdate();
			                    System.out.println("Login data inserted successfully for user" );
			                    logLoginTime(username);
			                } catch (SQLException e) {
			                    e.printStackTrace();
			                    
						}
						}
						else {
							err_message = "Incorrect password";
						}
						
					}
					else {
						err_message="User not Found";
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			return err_message
					;
		}
		
		private void logLoginTime(String username) {
		    String sql = "UPDATE SignIn SET login_time = CURRENT_TIMESTAMP WHERE username = ?";
		    
		    try {
		    	SQLConnection timecon=new SQLConnection();
		    	boolean time=timecon.connect();
		    	if(time) {
		    		 PreparedStatement statement = con.prepareStatement(sql);
		 		        statement.setString(1, username);
		 		        statement.executeUpdate();
		 		        System.out.println("updated");
		    	}
		    	//con=DriverManager.getConnection(url);
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		public void logLogoutTime(String username) {
			
		
			SQLConnection timelog=new SQLConnection();
	    	boolean logtime=timelog.connect();
	            String query = "UPDATE SignUp SET logout_time = ? WHERE username = ?";
	            if(logtime) {
	            try 
	            {	
	            	PreparedStatement statement = con.prepareStatement(query);
	            
	                statement.setTimestamp(1, new Timestamp(new Date(0).getTime()));
	                statement.setString(2, username);
	                statement.executeUpdate();
	                System.out.println("Logout time recorded for user: " + username);
	            }catch (SQLException e) {
		            e.printStackTrace();
		        }
	            }
	           
	         
		}

	 
}
