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
        <title>Home</title>
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Welcome <%= name %></h1>

            <button class="btn-add" onclick="location.href='add.jsp'">Add Book</button>
            <button class="btn-update" onclick="location.href='update.jsp'">Update Book</button>
            <button class="btn-delete" onclick="location.href='delete.jsp'">Delete Book</button>
            <button class="btn-view" onclick="location.href='books'">View Books</button>

            <form method="post" action="/mywebapp/logout">
                <button class="btn-logout" type="submit">Logout</button>
            </form>
        </div>
    </body>
</html>
