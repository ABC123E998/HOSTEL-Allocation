/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelallocation;
//import student.Student;

/**
 *
 * @author hp
 */
    public class RoomClass {
    private int roomNumber;      // Unique number of the room
    private String corner;       // Which side or section (e.g., "Left", "Right")
    int capacity;
    private StudentProfile allocatedStudent;
    private boolean available;
 
 
    // contructor
    public RoomClass(int roomNumber, String corner, int capacity) {
    this.roomNumber = roomNumber;
    this.corner = corner;
    this.capacity = capacity;
    this.allocatedStudent = null;
    this.available = available;
    
    }
   
    // Getters
    public int getRoomNumber() {return roomNumber;}
    public String getCorner() {return corner;}
    public int getcapacity() {return capacity;}
   public StudentProfile getallocatedStudent() {return allocatedStudent;}
   public boolean isAvailable(){return available;} 
    
    // Setters
    public void setRoomNumber(int roomNumber) {this.roomNumber = roomNumber;}
    public void setcapacity (int capacity) {this. capacity = capacity; }
    public void setAvailable(boolean available) {this.available = available;}
    

 public void allocateRoom(StudentProfile student) {
     if (isAvailable()){
         this.allocatedStudent = student;
         System.out.println("Room" + roomNumber + "allocated to student" + student.getName());
     }else{
         System.out.println("Room" + roomNumber + "is already occupied.");
        }
    }

   
    //Display from details
      public void displayRoomClass() { 
System.out.println("Room Number:" + roomNumber);
System.out.println("Corner:" + corner);
System.out.println("capacity;" + capacity);

        }   
    }

    

                                                                   
 