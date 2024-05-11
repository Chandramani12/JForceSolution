package attendance.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/attendanceReport")
public class ViewReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve attendance data from the database for the logged-in user
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            List<Attendance> attendanceData = fetchAttendanceData(username);

            // Set attendance data as an attribute in the request
            request.setAttribute("attendanceData", attendanceData);

            // Forward the request to the JSP page
            request.getRequestDispatcher("attendanceReport.jsp").forward(request, response);
        } else {
            // If user is not logged in, redirect to the login page
            response.sendRedirect("index.html");
        }
    }

    private List<Attendance> fetchAttendanceData(String username) {
        List<Attendance> attendanceData = new ArrayList<>();

        // JDBC connection parameters
       
        String dbUsername = "sa";
        String dbPassword = "ser@123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=login;encrypt=true;trustServerCertificate=true;";
      
        // SQL query to fetch attendance data for the given username
        String sql = "SELECT login_time, logout_time FROM SignIn WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String loginTime = rs.getString("login_time");
                    String logoutTime = rs.getString("logout_time");
                    attendanceData.add(new Attendance(username, loginTime, logoutTime));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceData;
    }
}
