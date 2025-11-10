/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelallocation;

/**
 *
 * @author hp
 */
public class PaymentClass {

     StudentProfile student;
     double hostelFee;
    private boolean paid;
    private double amountPaid;
    private RoomClass assignedRoom;
    private String status;

    public PaymentClass(StudentProfile student, double hostelFee) {
        this.student = student;
        this.hostelFee = hostelFee;
        this.paid = false;
        this.amountPaid = 0;
    }

    // === Generate Remita Code (simulated) ===
    public void generateRemita() {
        System.out.println("\n- PAYMENT INITIALIZED -");
        System.out.println("Remita Code: RRR" + (int) (Math.random() * 1000000000));
        System.out.println("Student: " + student.getName());
    }

    //  Handle Payment 
    public boolean makePayment(double amount) {
        if (amount > hostelFee) {
            System.out.println("You cannot pay more than the hostel fee of ₦" + hostelFee);
            this.paid = true;
            return false;
        } else if (amount < hostelFee) {
            System.out.println(" Payment incomplete!You must pay the full hostelFee of ₦" + hostelFee);
            this.paid = false;
           return false;
        } else {
            this.amountPaid = amount;
            this.paid = true;
            System.out.println("Payment of ₦" + amount + " sucessfull");
            return true;

        }
    }

    //  Assign Room After Successful Payment
    public boolean assignRoom(RoomClass room) {
        if (!paid) {
            System.out.println("You must complete your payment before room assignment.");
            return false;
        }

        if (!room.isAvailable()) {
            System.out.println("Room " + room.getRoomNumber() + " is already occupied!");
            return false;
        }

        boolean allocated = room.allocateRoom(student);
        if (allocated) {
            this.assignedRoom = room;
            System.out.println(" Room " + room.getRoomNumber() + " successfully assigned to " + student.getName());
            return true;
        } else {
            System.out.println(" Room assignment failed.");
            return false;
        }
    }
    //Getter

    public boolean isPaid() {
        return paid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
    public String getStatus() {
        return status;
    }
    
    public RoomClass getassignedRoom() {
        return assignedRoom;
    }

    //DisplayPaymentClass
    public void displayPaymentClass() {
        System.out.println("Student:" + student.getName());
        System.out.println("Amount Paid:" + getAmountPaid());
        System.out.println("Payment Status:" + (paid ? "paid" : "Not paid"));
       
    }

    
}
