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
<head><title>Home</title></head>
<body>
    <h1>Welcome <%= name %></h1>
    <a href="add.jsp">Add Book</a>
    <br>
    <a href="update.jsp">Update Book</a>
    <br>
    <a href="/mywebapp/books">View Books</a>
    <br>
    <form method="post" action="/mywebapp/logout">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
