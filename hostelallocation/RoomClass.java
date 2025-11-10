package hostelallocation;



public class RoomClass {

     int roomNumber;
     String corner;
    int capacity;
    private boolean available;
    private StudentProfile allocatedStudent;
    private int hostelId;

    public RoomClass( int hostelId,int roomNumber, String corner, int capacity) {
        this.roomNumber = roomNumber;
        this.corner = corner;
        this.capacity = capacity;
        this.available = true;
        this.hostelId = hostelId;
    }
    // Automatically allocate room based on student
    public static RoomClass autoAllocateRoom(StudentProfile student, int hostelId) {
        // Management-controlled allocation logic
        if (student.getGender().equalsIgnoreCase("male")) {
            return new RoomClass(hostelId,101, "Left Wing", 4);
        } else {
            return new RoomClass(hostelId,103, "Upstairs", 6);
        }
    }

    // Allocate Room and update database using Database 
    public boolean allocateRoom(StudentProfile student) {
        if (!available) {
            System.out.println("Room " + roomNumber + " is already occupied!");
            return false;
        }
        this.allocatedStudent = student;
        this.available = false;
        //Update room in the database via database
        boolean updated= sqlconnector.updateRoomStatus(roomNumber,student.getHostel().getHostelId(), true);
        if (updated){
        System.out.println("Room " + roomNumber + " successfully allocated to " + student.getName());
        return true;
        }else{
            System.out.println("Room allocation failed to update in database.");
           
        }
         return false;
    }

    // Check Availability
    public boolean isAvailable() {
        return available;
    }
        //  Display Room Details
    public void displayRoomClass() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Corner: " + corner);
        System.out.println("Capacity: " + capacity);
        System.out.println("Availability: " + (available ? "Available" : "Occupied"));
        if (!available && allocatedStudent != null) {
            System.out.println("Allocated To: " + allocatedStudent.getName());
        }
    }
        //getters
    public int getRoomNumber() {
       return roomNumber;
    }

    public String getCorner() {
        return corner;
    }

    public int getCapacity() {
        return capacity;
    }

    public StudentProfile getAllocatedStudent() {
        return allocatedStudent;
    }
    public int getHostelId(){
        return hostelId;
    }
    boolean isOccupied() {
         boolean isOccupied = true;
      return isOccupied; 
    }
    
    }

