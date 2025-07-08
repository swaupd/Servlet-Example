package com.webapp;

import com.util.BookDAL;
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

        try {
            
        String bookName = request.getParameter("name");
        float price = (float) (Float.parseFloat(request.getParameter("price")) * 100.0f) / 100.0f;

        if (BookDAL.addBook(bookName, price)) {
            // On success, redirect to success.html
                HttpSession session = request.getSession();
                session.setAttribute("successTitle", "Book Added Successfully");
                session.setAttribute("successMessage", "Book with Name " + bookName + " has been added.");
                response.sendRedirect("success.jsp");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
