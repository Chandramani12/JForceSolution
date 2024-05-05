package attendance.com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String loginButton = request.getParameter("Login");
        String registerButton = request.getParameter("Register");

        if (registerButton != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String emailid = request.getParameter("emailId");
            String phone = request.getParameter("phone");
            
            
            
            SQLConnection db=new SQLConnection();
           String error_message = db.register(username,password,emailid,phone);
           if(error_message != null) {
           if(error_message.equals("Full Name")) {
        	   pw.print("<b>Please Enter Full Name</b>");
   				RequestDispatcher rd = request.getRequestDispatcher("Register.html");
   				rd.include(request, response);
           }
           if(error_message.equals(password)){
         	  pw.print("<b>Please Enter Password</b>");
  				RequestDispatcher rd = request.getRequestDispatcher("Register.html");
  				rd.include(request, response);
               
             }
           
         if(error_message.equals("Email ID")){
        	  pw.print("<b>Please Enter Emil  ID</b>");
 				RequestDispatcher rd = request.getRequestDispatcher("Register.html");
 				rd.include(request, response);
              
            }
       
         if(error_message.equals(phone)){
        	  pw.print("<b>Please Enter phone</b>");
 				RequestDispatcher rd = request.getRequestDispatcher("Register.html");
 				rd.include(request, response);
              
            }
           
           
        }
        }else if (loginButton != null) {
            // Forward to login page if login button clicked
//            RequestDispatcher rd = request.getRequestDispatcher("index.html");
//            rd.forward(request, response);
        	response.sendRedirect("index.html");
           
        }
    }

	private boolean isValidEmail(String emailid) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return emailid.matches(emailRegex);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
