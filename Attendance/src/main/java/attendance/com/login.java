package attendance.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public login() {
        // Constructor
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginButton = request.getParameter("login");

        if (loginButton != null && loginButton.equals("Login")) {
            // Check if username and password are provided
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                out.print("<b>Please Enter Username and Password</b>");
                response.sendRedirect("index.html");
                return; // Exit the method
            }

            SQLConnection loginData = new SQLConnection();
            String result = loginData.loginVerifyCheck(username, password);

            if (result != null) {
                if (result.equals("Incorrect password")) {
                    out.print("<b>Please Enter Correct Password</b>");
                } else if (result.equals("User not found")) {
                    out.print("<b>User Not Found. Please Enter Correct Username</b>");
                } else if (result.equals("Login successful")) {
                    // Insert login time into database
                    insertLoginTime(username,password);
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("home.jsp");
                    return; // Exit the method after redirect
                } else {
                    // If result is not one of the above, treat it as an error message
                    request.setAttribute("errorMessage", result);
                    response.sendRedirect("index.html");
                    return; // Exit the method after redirect
                }
            }
        } else {
            // Redirect to register page
            response.sendRedirect("Register.html");
        }
    }

    
    // Method to insert login time into database
     void insertLoginTime(String username,String password) {
        // JDBC connection parameters
        String dbUsername = "sa";
        String dbPassword = "ser@123";
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=login;encrypt=true;trustServerCertificate=true;";
        
        // Get current time
        LocalDateTime loginTime = LocalDateTime.now();
        
        // Format time as per database requirement
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLoginTime = loginTime.format(formatter);
        
        // Database operations
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            // Insert query
            String query = "INSERT INTO SignIn (username, password,login_time) VALUES (?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, formattedLoginTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
