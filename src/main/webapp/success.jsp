<%@ page session="true" %>
<%
String email = (String) session.getAttribute("adminEmail");
String name = (String) session.getAttribute("adminName");
if (email == null) {
response.sendRedirect("login.html");
return;
}
String successTitle = (String) session.getAttribute("successTitle");
String successMessage = (String) session.getAttribute("successMessage");
if (successTitle == null) {
successTitle = "Success";
successMessage = "Operation completed successfully.";
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><%= successTitle %></title>
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <div class="container">
            <h1><%= successMessage %></h1>
            <a href="home.jsp">Go home</a>
        </div>
    </body>
</html>
