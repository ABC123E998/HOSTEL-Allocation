 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package hostelallocation;
import java.util.ArrayList;
import java.util.List;


/**
 *  
 * @author hp
 */
public class HostelClass {
    // Fields (properties of the hostel)
    private final String name;           // Name of the hostel (e.g., "Hostel A")
    private String gender;// male or female
    private double hostelfee;
    private final List <RoomClass> rooms;      // A list of all rooms in this hostel

    // Constructor (to create a hostel with a given name)
    public HostelClass(String name,  String gender,double hostelfee) {
        this.name = name;
        this.gender = gender;
        this.hostelfee = hostelfee;
        this.rooms = new ArrayList<>(); // Start with an empty list of rooms
    }
   // getter for gender
    public String getGender() {
        return gender;
    }
    //getter for hostel name
    public String getHostelName() {
        return name;
    }
    //getter for hostelfee
    public double getHostelFee(){
        return hostelfee;
    }
    //setter for hostel fee
    public void setHostelfee(double HostelFee) {
        if (hostelfee > 0) {
            this. hostelfee = HostelFee;
        }else {
            System.out.println("Invalid fee. Must be greater than 0.");
        }
    }

    // Method to add a new room to this hostel
    public void addRoom(RoomClass room) {
        rooms.add(room);
    }
    //method to get the first available room
    public RoomClass getAvailableRoom(){
        for (RoomClass room :rooms ){
            if(room.isAvailable()){
                return room;
            }
        }
        return null;
    }
     
                        
                        
            }
        

   