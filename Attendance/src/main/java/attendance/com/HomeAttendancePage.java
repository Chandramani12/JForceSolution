//package attendance.com;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebServlet("/HomeAttendancePage")
//public class HomeAttendancePage extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//        	
//            response.sendRedirect("index.html"); // Redirect to login page if not logged in
//            return;
//        }
//
//        // Check if user has already signed in for today
//        boolean hasSignedInToday = hasSignedInToday(username);
//
//        // Forward to the JSP page
//        request.setAttribute("hasSignedInToday", hasSignedInToday);
//        request.getRequestDispatcher("home.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            response.sendRedirect("index.html"); // Redirect to login page if not logged in
//            return;
//        }
//
//        if ("signin".equals(action)) {
//            // Sign in user for today
//            signIn(username);
//        } else if ("signout".equals(action)) {
//            // Sign out user for today
//            signOut(username);
//        }
//
//        // Redirect back to the Home Attendance Page
//        response.sendRedirect(request.getContextPath() + "/HomeAttendancePage");
//    }
//
//    private boolean hasSignedInToday(String username) {
//        // Check if the user has signed in for today
//        LocalDate today = LocalDate.now();
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_db", "username", "password");
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM attendance_records WHERE username=? AND date=? AND signed_in=?")) {
//            stmt.setString(1, username);
//            stmt.setDate(2, java.sql.Date.valueOf(today));
//            stmt.setBoolean(3, true);
//            try (ResultSet rs = stmt.executeQuery()) {
//                return rs.next();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private void signIn(String username) {
//        // Sign in user for today
//        LocalDate today = LocalDate.now();
//        try {
//        	
//        	//String u=conect
//        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_db", "username", "password");
//             PreparedStatement stmt = conn.prepareStatement("INSERT INTO attendance_records (username, date, signed_in) VALUES (?, ?, ?)");
//            stmt.setString(1, username);
//            stmt.setDate(2, java.sql.Date.valueOf(today));
//            stmt.setBoolean(3, true);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void signOut(String username) {
//        // Sign out user for today
//        LocalDate today = LocalDate.now();
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_db", "username", "password");
//             PreparedStatement stmt = conn.prepareStatement("UPDATE attendance_records SET signed_in=? WHERE username=? AND date=?")) {
//            stmt.setBoolean(1, false);
//            stmt.setString(2, username);
//            stmt.setDate(3, java.sql.Date.valueOf(today));
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
