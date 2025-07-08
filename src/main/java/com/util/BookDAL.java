package com.util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BookDAL
 */
public class BookDAL {

    private static Connection connection;

    static {
        try {
            connection = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addBook(String name, float price) throws SQLException {
        String sql = "INSERT INTO books (name, price) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setFloat(2, price);
            return pstmt.executeUpdate() > 0;
        }
        
    }

    public static List<Book> displayBooks() throws SQLException {
        String sql = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                books.add(new Book(id, name, price));
            }
            return books;
        }
    }

    public static boolean updateBook(int bookId, String name, float price) throws SQLException {

        try(CallableStatement cstmt = connection.prepareCall("{call updateBook(?, ?, ?)}")) {
            cstmt.setInt(1, bookId);
            cstmt.setString(2, name);
            cstmt.setFloat(3, price);
            return cstmt.executeUpdate() > 0;
        }
    }

    public static boolean deleteBook(int bookId) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            return pstmt.executeUpdate() > 0;
        }
    }

}
