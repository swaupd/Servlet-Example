package com.webapp;

import com.util.Book;
import com.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Book> bookList = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("name");
                float price = rs.getFloat("price");
                bookList.add(new Book(id, title, price));
            }

        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset=\"UTF-8\"><title>Book List</title></head><body>");
        out.println("<h1>Book List</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Title</th><th>Price</th></tr>");

        for (Book book : bookList) {
            out.println("<tr>");
            out.println("<td>" + book.id + "</td>");
            out.println("<td>" + book.title + "</td>");
            out.println("<td>" + book.price + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br><a href='home.jsp'>Back to Home</a>");
        out.println("</body></html>");
    }
}
