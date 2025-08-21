/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelallocation;
import hostelallocation.RoomClass;
//import hostelallocation.Student;

/**
 *
 * @author hp
 */
public class PaymentClass {
     private StudentProfile student;
     private double amount;
     private boolean paid;
     private  RoomClass assignedRoom;
     

    public PaymentClass(StudentProfile student, double amount) {
        this.student = student;
        this.amount = amount;
        this.paid = false; // default: not paid
        this.assignedRoom = null; // no room assigned yet
    }
    

    public void generateRemita() {
        System.out.println("Generated Remita for amount: " + amount);
    
        }
    //process payment
    public void makePayment(double hostelFee){
        if (amount>= hostelFee){
        paid = true;
        System.out.println("Payment successful for" + student.getName());
}else{
            System.out.println("insufficient payment for" + student.getName());
        }
    }

    //Assign room after payment
    public void assignRoom(RoomClass room){
        if (paid && room.isAvailable()){
            this.assignedRoom = room;
            room.setAvailable(false);//Mark the room as taken
            System.out.println("Room" + room.getRoomNumber() + "assigned to" + student.getName());
        }else if (!paid){
            System.out.println("Cannot assign room.Payment not completed.");
        }else{
            System.out.println("Room is not available.");
            
        }
    }
        //Getter
    public boolean isPaid(){
        return paid;
    }
    public RoomClass getAssignedRoom(){
        return assignedRoom;
    }
    //DisplayPaymentClass
    public void displayPaymentClass(){
        System.out.println("Student:" + student.getName());
        System.out.println("Amount Paid:" + amount);
        System.out.println("Payment Status:" + (paid? "paid": "Not paid"));
        if (assignedRoom != null){
            System.out.println("Assigned Room:" + assignedRoom.getRoomNumber()+ "(" + assignedRoom.getCorner() + ")");
        }else {
            System.out.println("No room assigned.");
        }
    }
    }

