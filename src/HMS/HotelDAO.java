package HMS;

import HMS.DBConnection;
import java.sql.*;
import java.util.*;

public class HotelDAO {

	public void insertGuest(int id, String name, String email) {
	    try (Connection conn = DBConnection.getConnection()) {

	        // Step 1: Check number of current guests
	        String countSql = "SELECT COUNT(*) AS total FROM guests";
	        Statement countStmt = conn.createStatement();
	        ResultSet countRs = countStmt.executeQuery(countSql);
	        countRs.next();
	        int currentGuests = countRs.getInt("total");

	        if (currentGuests >= 10) {
	            System.out.println("No vacancy available!");
	            return;
	        }

	        // Step 2: Find available room number (1 to 10)
	        Set<Integer> occupiedRooms = new HashSet<>();
	        String roomsSql = "SELECT room_number FROM guests";
	        ResultSet roomsRs = countStmt.executeQuery(roomsSql);
	        while (roomsRs.next()) {
	            occupiedRooms.add(roomsRs.getInt("room_number"));
	        }

	        int assignedRoom = -1;
	        for (int i = 1; i <= 10; i++) {
	            if (!occupiedRooms.contains(i)) {
	                assignedRoom = i;
	                break;
	            }
	        }

	        if (assignedRoom == -1) {
	            System.out.println("No room available to assign.");
	            return;
	        }

	        // Step 3: Insert guest with assigned room
	        String sql = "INSERT INTO guests (id, name, email, room_number) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, id);
	        stmt.setString(2, name);
	        stmt.setString(3, email);
	        stmt.setInt(4, assignedRoom);
	        stmt.executeUpdate();

	        System.out.println("Guest added successfully! Assigned Room: " + assignedRoom);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    public void deleteGuestById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM guests WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Guest deleted.");
            else
                System.out.println("Guest not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGuestById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM guests WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("Guest not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllGuests() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM guests";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("ID | Name           | Email                | Room No");
            System.out.println("------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int roomNumber = rs.getInt("room_number");

                System.out.printf("%-3d| %-15s| %-20s| %-8d\n", id, name, email, roomNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
