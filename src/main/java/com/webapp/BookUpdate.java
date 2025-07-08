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

@WebServlet("/updateBook")
public class BookUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


        try {
            int bookId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            if (name == null || name.isEmpty()) {
                name = "";
            }
            float price = 0;
            if (!request.getParameter("price").isEmpty()) {
                price = (float)Math.round(Float.parseFloat(request.getParameter("price")) * 100.0f) / 100.0f;
            }

            if (BookDAL.updateBook(bookId, name, price)) {
                HttpSession session = request.getSession();
                session.setAttribute("successTitle", "Book Updated Successfully");
                session.setAttribute("successMessage", "Book with ID " + bookId + " has been updated.");
                response.sendRedirect("success.jsp");
            }

        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }
}
