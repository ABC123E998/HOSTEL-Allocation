/*mercy
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelallocation;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author hp
 */
public class HostelAllocation {

    Scanner sc = new Scanner(System.in);
    RoomClass selectedRoom;
    PaymentClass payment;

    public void start() {
        System.out.println("=== HOSTEL ALLOCATION SYSTEM STARTED ===");

        // --- Create Student ---
        StudentProfile student = StudentProfile.createStudent();
        if (student == null) {
            System.out.println("Returning to main menu or exiting progam.");
            return;
        }

        System.out.println("Gender: " + student.getGender());

            // --- 2. Choose hostel based on gender ---
        HostelClass selectedHostel = HostelClass.chooseHostelForGender(student.getGender());
        student.setHostel(selectedHostel);
        System.out.println("\nYou selected: " + selectedHostel.getHostelName()
                + " — Fee: ₦" + selectedHostel.getHostelFee()
                + " — Location: " + selectedHostel.getLocation());

            // --- 3. Allocate room ---
        RoomClass selectedRoom = RoomClass.autoAllocateRoom(student,selectedHostel.getHostelId());
        System.out.println("\nRoom selected: " + selectedRoom.getRoomNumber()
                + " (" + selectedRoom.getCorner() + ")");

            // --- 4. Handle payment ---
        PaymentClass payment = new PaymentClass(student, selectedHostel.getHostelFee());
        payment.generateRemita();

        System.out.println("\nYou are required to pay ₦" + selectedHostel.getHostelFee());

            // Payment loop
        boolean paidOk = false;
        Scanner sc = new Scanner(System.in);

        while (!paidOk) {
            System.out.print("Enter amount you are paying: ₦");
            double amount;

            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                sc.nextLine(); // consume newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            if (payment.makePayment(amount)) { // ✅ fixed to use the actual amount entered
                paidOk = true;
            
            } else {
                System.out.println("❌ Insufficient payment. Please pay at least ₦" + selectedHostel.getHostelFee());
            }
        }

            // --- 5. Assign room and save to database ---
        if (payment.assignRoom(selectedRoom)) {
            student.setRegistered(true);
            System.out.println("\nRoom assigned successfully to " + student.getName());

            // ✅ Save all data to the database
            insertStudentToDatabase(student);
            insertHostelToDatabase(selectedHostel);
            insertRoomToDatabase(selectedRoom);
            insertPaymentToDatabase(payment,student);
        
}


        // 6. Display all details
        System.out.println("\n--- STUDENT DETAILS ---");
        student.displayStudentProfile();

        System.out.println("\n--- HOSTEL DETAILS ---");
        System.out.println("Hostel: " + selectedHostel.getHostelName()
                + " (" + selectedHostel.getGender() + ")");
        System.out.println("Fee: ₦" + selectedHostel.getHostelFee());

        System.out.println("\n--- PAYMENT DETAILS ---");
        payment.displayPaymentClass();

        System.out.println("\n--- ROOM DETAILS ---");
        selectedRoom.displayRoomClass();

        System.out.println("\n Hostel Allocation Complete!");

    }
    //  Method to insert a hostel record into the hostel table

    public void insertHostelToDatabase(HostelClass hostel) {
        String sql = "INSERT INTO hostel (hostel_name, gender, hostel_fee, location) VALUES (?, ?, ?, ?)";

        try (Connection conn = sqlconnector.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hostel.getHostelName());
            stmt.setString(2, hostel.getGender());
            stmt.setDouble(3, hostel.getHostelFee());
            stmt.setString(4, hostel.getLocation());
            stmt.executeUpdate();


            System.out.println(" Hostel inserted: " + hostel.getHostelName());

        } catch (SQLException e) {
            System.out.println(" Error inserting hostel: " + e.getMessage());
        }
    }

    // ✅ Insert Room record into database
    private void insertRoomToDatabase(RoomClass room) {
        String sql = "INSERT INTO room (hostel_id, room_number,capacity,occupied,corner) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = sqlconnector.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, room.getHostelId());
            stmt.setInt(2,  room.getRoomNumber());
            stmt.setInt(3, room.getCapacity());
            stmt.setBoolean(4, room.isOccupied());
            stmt.setString(5, room.getCorner());
            
            stmt.executeUpdate();
            System.out.println(" Room record saved successfully!");

        } catch (SQLException e) {
            System.out.println("Database Error (Room): " + e.getMessage());
        }
    }

    //  Insert Payment record into database
    public void insertPaymentToDatabase(PaymentClass payment, StudentProfile student) {
        String sql = "INSERT INTO payment (student_reg_number, amount_paid) VALUES (?, ?)";
        try (Connection conn = sqlconnector.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getRegNumber());
            stmt.setDouble(2, payment.getAmountPaid());
            

            stmt.executeUpdate();
            System.out.println(" Payment record saved successfully!");

        } catch (SQLException e) {
            System.out.println(" Database Error (Payment): " + e.getMessage());
        }
    }

    //  Method to insert student details into database
    private void insertStudentToDatabase(StudentProfile student) {
        String sql = "INSERT INTO studentprofile (name, reg_number, department, gender, level, age, fees_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = sqlconnector.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getRegNumber());
            stmt.setString(3, student.getDepartment());
            stmt.setString(4, student.getGender());
            stmt.setInt(5, student.getLevel());
            stmt.setInt(6, student.getAge());
            stmt.setBoolean(7, student.isFeesPaid());

            stmt.executeUpdate();
            System.out.println("Student record saved to database successfully!");

        } catch (SQLException e) {
            System.out.println(" Database Error: " + e.getMessage());
        }

    }
}
