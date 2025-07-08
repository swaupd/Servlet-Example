package com.webapp;

import com.util.AdminDAL;
import com.util.DBUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {

            if (AdminDAL.registerAdmin(firstName, middleName, lastName, email, password)) {
                String fullName = AdminDAL.getFullName(email);
                HttpSession session = req.getSession();
                session.setAttribute("adminEmail", email);
                session.setAttribute("adminName", fullName);
                res.sendRedirect("home.jsp");
            } else {
                res.sendRedirect("index.html"); 
            }


        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(500, "Database error");
        }
    }
}
