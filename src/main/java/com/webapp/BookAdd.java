package com.webapp;

import com.util.DBUtil;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBook")
public class BookAdd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String bookName = request.getParameter("name");
        String price = request.getParameter("price");

        try {
            Statement stmt = DBUtil.getConnection().createStatement();
            String sql = "INSERT INTO books (name, price) VALUES ('" + bookName + "', " + price + ")";
            stmt.executeUpdate(sql);
            stmt.close();

            // On success, redirect to success.html
            response.sendRedirect("success.html");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding book: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
