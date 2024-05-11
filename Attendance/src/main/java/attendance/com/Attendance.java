package attendance.com;

public class Attendance {
    private String username;
    private String loginTime;
    private String logoutTime;

    public Attendance(String username, String loginTime, String logoutTime) {
        this.username = username;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public String getUsername() {
        return username;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }
}
