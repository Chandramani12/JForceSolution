<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Attendance Page</title>
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
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .button {
            padding: 10px 20px;
            margin: 0 10px;
            font-size: 16px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Home Attendance Page</h1>
        <div class="button-container">
            <%-- Check if user is logged in --%>
            <% if(session.getAttribute("username") != null) { %>
                <form action="LogoutServlet" method="post">
                    <button class="button">Sign Out</button>
                </form>
            <% } else { %>
                <form action="index.html" method="get">
                    <button class="button">Sign In</button>
                </form>
            <% } %>
            <!--  <form action="ViewReportServlet" method="get">
           <!--  <a href="attendanceReport.jsp" class="button">View Report</a>
           <button class="button">View Report</button>
            </form> -->
            <a href="attendanceReport" class="button">View Report</a>
            
        </div>
    </div>
</body>
</html>
