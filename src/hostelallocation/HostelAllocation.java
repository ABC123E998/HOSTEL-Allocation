/*mercy
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelallocation;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class HostelAllocation {

    /**
     * @param argsthe command line arguments
     */
        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
         public void start() {
             System.out.println("=== Hostel Allocation System Started");
             
        // 1. Create a student
        StudentProfile student1 = new StudentProfile("Mercy Chibueze", "2025/245126", "Computer Science", "Female", 200, 21, true, true);

        // 2. Create a hostel
        HostelClass hostel1 = new HostelClass("Okpara Hostel", "Female", 40000.0);

        // 3. Payment process (PaymentClass before RoomClass)
        PaymentClass payment1 = new PaymentClass(student1, 40000.0);  // Student pays 50,000
        payment1.generateRemita();
        payment1.makePayment(hostel1.getHostelFee());

        // 4. Now create a room
        RoomClass room101 = new RoomClass(101, "Left Wing", 4);

        // 5. Assign room after successful payment
        payment1.assignRoom(room101);

        // 6. Display all details
        System.out.println("\n--- Student Details ---");
        student1.displayStudentProfile();

        System.out.println("\n--- Hostel Details ---");
        System.out.println("Hostel: " + hostel1.getHostelName() + " (" + hostel1.getGender() + ")");
        System.out.println("Fee: " + hostel1.getHostelFee());

        System.out.println("\n--- Payment Details ---");
        payment1.displayPaymentClass();
        
         System.out.println("\n--- Room Details ---");
        room101.displayRoomClass();

         }
}

    

   
        