package com.webapp;

import com.util.DBUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM admins WHERE email = ? AND password = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            CallableStatement cs = conn.prepareCall("CALL fullName(?)");
            cs.setString(1, email);
            ResultSet rsName = cs.executeQuery();
            String fullName = null;
            if (rsName.next()) {
                fullName = rsName.getString(1);
            }

            if (rs.next()) {
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
