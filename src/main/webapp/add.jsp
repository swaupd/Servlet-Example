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
        <title>Library - Add Book</title>
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Add Book</h1>
            <form action="addBook" method="post">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required><br><br>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" required><br><br>

                <input type="submit" value="Add Book">
            </form>
        </div>
    </body>
</html>
