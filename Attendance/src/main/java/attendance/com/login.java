package attendance.com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String username,password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		
		String requestType=request.getParameter("login");
		if(requestType!=null) {
			
			SQLConnection logindata=new SQLConnection();
			String result=logindata.loginverfiycheck(username,password);
			if(result.equals("UserName is empty")) {
				 out.print("<b>Please Enter UserName</b>");
				 
	   				RequestDispatcher rd = request.getRequestDispatcher("index.html");
	   				rd.include(request, response);
				
			} else if(result.equals("Password  is empty")) {
				
				out.print("<b>Please Enter password</b>"); 
   				RequestDispatcher rd = request.getRequestDispatcher("index.html");
   				rd.include(request, response);
			}
			else if(result.equals("Incorrect password")) {
				out.print("<b>Please Enter Correct password</b>"); 
   				RequestDispatcher rd = request.getRequestDispatcher("index.html");
   				rd.include(request, response);
			}
			else  if(result.equals("User not Found")){
				out.print("<b>Please Enter Correct UserName</b>"); 
   				RequestDispatcher rd = request.getRequestDispatcher("index.html");
   				rd.include(request, response);
			}
			
		}
		//HomeAttendancePage homepae=new HomeAttendancePage();
		
		else {
			response.sendRedirect("Register.html");
		}
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
