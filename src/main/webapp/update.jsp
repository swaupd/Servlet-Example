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
    <title>Library - Update Book</title>
</head>
<body>
    <h1>Update Book</h1>
    <form action="/mywebapp/updateBook" method="post">
        <label for="id">Book ID:</label>
        <input type="text" id="id" name="id" required><br><br>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price"><br><br>

        <input type="submit" value="Update Book">
    </form>
</body>
</html>
