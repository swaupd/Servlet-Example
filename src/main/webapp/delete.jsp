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
    <title>Library - Delete Book</title>
</head>
<body>
    <h1>Update Book</h1>
    <form action="deleteBook" method="post">
        <label for="id">Book ID:</label>
        <input type="text" id="id" name="id" required><br><br>
        <input type="submit" value="Delete Book">
    </form>
</body>
</html>
