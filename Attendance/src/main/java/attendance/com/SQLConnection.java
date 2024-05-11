package attendance.com;

import java.sql.*;

public class SQLConnection {
    private Connection con;

    public SQLConnection() {
        connect(); // Establish database connection upon object creation
    }

    private boolean connect() {
        String user = "sa";
        String pass = "ser@123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=login;encrypt=true;trustServerCertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the database");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String register(String username, String password, String emailid, String phone) {
        String errorMessage = "";
        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
            emailid == null || emailid.isEmpty() || phone == null || phone.isEmpty()) {
            errorMessage = "All fields are required";
            return errorMessage;
        }

        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO SignUp (username, password, emailid, phoneNumber) VALUES (?, ?, ?, ?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, emailid);
            pst.setString(4, phone);
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("Registration successful for user: " + username);
                errorMessage="Succefull Registered";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "Error occurred while registering user";
        }

        return errorMessage;
    }

    public String loginVerifyCheck(String username, String password) {
        String errorMessage = "";
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            errorMessage = "Username and password are required";
            return errorMessage;
        }

        try {
            String query = "SELECT * FROM SignUp WHERE username=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // User exists, check password
                String spass = rs.getString("password");
                if (password.equals(spass)) {
                    errorMessage = "Login successful";
//                    login obj=new login();
//                   obj. insertLoginTime(username,password);
                } else {
                    errorMessage = "Incorrect password";
                }
            } else {
                // User doesn't exist
                errorMessage = "User not found";
            }
        } catch (SQLException e) {
            errorMessage = "Error occurred during login verification: " + e.getMessage();
            e.printStackTrace();
        }
        return errorMessage;
    }

//    private void logLoginTime(String username) {
//        try {
//            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO SignIn (username, login_time) VALUES (?, GETDATE())");
//            insertStmt.setString(1, username);
//            insertStmt.executeUpdate();
//            System.out.println("Login time logged successfully for user: " + username);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void logLogoutTime(String username) {
//        try {
//            PreparedStatement statement = con.prepareStatement("UPDATE SignIn SET logout_time = GETDATE() WHERE username = ? AND logout_time IS NULL");
//            statement.setString(1, username);
//            statement.executeUpdate();
//            System.out.println("Logout time recorded for user: " + username);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void logLogoutTime(String username) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE SignIn SET logout_time = GETDATE() WHERE username = ? AND logout_time IS NULL");
            statement.setString(1, username);
            statement.executeUpdate();
            System.out.println("Logout time recorded for user: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
