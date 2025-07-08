package com.webapp;

import com.util.Book;
import com.util.BookDAL;
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
        List<Book> bookList;
        try {

            bookList = BookDAL.displayBooks();


        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset=\"UTF-8\"><title>Book List</title><link rel='stylesheet' href='styles/styles.css'></head><body>");
        out.println("<div class='container'>");
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
        out.println("</div>");
        out.println("</body></html>");
    }
}
