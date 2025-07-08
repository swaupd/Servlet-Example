package com.webapp;

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

@WebServlet("/deleteBook")
public class DeleteBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


        try { 

            int bookId = Integer.parseInt(request.getParameter("id"));

            if (BookDAL.deleteBook(bookId)) {
                HttpSession session = request.getSession();
                session.setAttribute("successTitle", "Book Deleted Successfully");
                session.setAttribute("successMessage", "Book with ID " + bookId + " has been deleted.");
                response.sendRedirect("success.jsp");
            }

        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }
}
