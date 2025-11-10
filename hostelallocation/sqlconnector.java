/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hostelallocation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author hp
 */
public class sqlconnector {
    // MySQL connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostel_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mercylambo";

    // ✅ This method connects and returns the connection
    public static Connection connect() {
        Connection conn = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println(" Connected successfully");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("⚠ JDBC Driver not found: " + e.getMessage());
        }

        return conn;
    }

    // ✅ Optional: method to close the connection
    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }


// ✅ Update a room’s status in the database
public static boolean updateRoomStatus(int roomNumber, int hostelId, boolean available) {
    String sql = "UPDATE room SET hostel_id = ?, occupied = ? WHERE room_number = ?";

    try (Connection conn = connect();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, hostelId);
        pstmt.setBoolean(2, !available); // occupied = 1 if not available
        pstmt.setInt(3, roomNumber);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Room " + roomNumber + " updated successfully in database.");
            return true;
        } else {
            System.out.println("⚠ No room found with number " + roomNumber);
            return false;
        }

    } catch (Exception e) {
        System.out.println("❌ Error updating room: " + e.getMessage());
        return false;
}
}
}