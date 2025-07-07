<%@ page session="true" %>
<%
    String email = (String) session.getAttribute("adminEmail");
    String name = (String) session.getAttribute("adminName");
    if (email == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Added</title>
</head>
<body>
    <h1>✅ Book added successfully!</h1>
    <a href="home.jsp">Go home</a>
</body>
</html>
