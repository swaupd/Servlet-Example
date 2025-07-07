package com.webapp;

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
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO admins (first_name, middle_name, last_Name, email, password) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, firstName);
            stmt.setString(2, middleName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, password);
            int rowsAffected = stmt.executeUpdate();
            CallableStatement cs = conn.prepareCall("CALL fullName(?)");
            cs.setString(1, email);
            ResultSet rsName = cs.executeQuery();
            String fullName = null;
            if (rsName.next()) {
                fullName = rsName.getString(1);
            }

            if (rowsAffected > 0 && fullName != null) {
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
