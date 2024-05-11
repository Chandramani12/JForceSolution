<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="attendance.com.Attendance" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Attendance Report</h1>
        <table>
            <thead>
                <tr>
                	<th>UserName</th>
                    <th>Login Time</th>
                    <th>Logout Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Attendance> attendanceData = (List<Attendance>) request.getAttribute("attendanceData");
                    for (Attendance attendance : attendanceData) {
                %>
                <tr>
                	<td><%= attendance.getUsername() %></td>
                    <td><%= attendance.getLoginTime() %></td>
                    <td><%= attendance.getLogoutTime() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
