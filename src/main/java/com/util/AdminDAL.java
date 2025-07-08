package com.util;
import java.sql.*;

/**
 * AdminDAL
 */
public class AdminDAL {

    public static boolean registerAdmin(String firstName, String middleName, String lastName, String email, String password) throws SQLException {
        
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO admins (first_name, middle_name, last_Name, email, password) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, firstName);
            stmt.setString(2, middleName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, password);
            return stmt.executeUpdate() > 0;
        }
    }

    public static String getFullName(String email) throws SQLException {
        
        try (Connection conn = DBUtil.getConnection()) {
            CallableStatement cs = conn.prepareCall("CALL fullName(?)");
            cs.setString(1, email);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
        
    }

    public static boolean loginAdmin(String email, String password) throws SQLException {
        
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM admins WHERE email = ? AND password = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        
    }
}
