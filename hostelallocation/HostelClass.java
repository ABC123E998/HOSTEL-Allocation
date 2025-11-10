/*
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
public class HostelClass {
    private  int hostelId;
    private final String name;
    private final String gender;
    private final String location;
    double hostelfee;

    // Constructor (to create a hostel with a given name, gender, and location)
    public HostelClass( int hostelId,String name, String gender, double hostelfee, String location) {
        this.hostelId = hostelId;
        this.name = name;
        this.gender = gender;
        this.hostelfee = hostelfee;
        this.location = location;
    }

    // Prompt the user to choose hostel based on their gender
    public static HostelClass chooseHostelForGender(String gender) {
        Scanner sc = new Scanner(System.in);
        int choice;

        if (gender.equalsIgnoreCase("Female")) {
            System.out.println("\nAvailable Hostels for Female Students:");
            System.out.println("1. Okpara Hostel - ₦40,000 (Location: St.Peters)");
            System.out.println("2. Bello Hostel - ₦45,000 (Location:Pharmacy)");
            System.out.println("3. Eyoita Hostel - ₦25,000 (Location:{Pharmacy");
            System.out.println("4. Akpaio Hostel -  ₦25,000 (Location:(studentAffairs");
            System.out.println("5. Awolowo Hostel -  ₦35,000 (Location:(Malima ");
            System.out.println("6. presidential Hostel - ₦45,000 (Location:(SUB");
            System.out.println("7. Mbanefo Hostel -  ₦50,000(Location:(Stadium");
            System.out.println("8. Maryslessor Hostel- ₦25,000(Location:(Freedom square");
            System.out.println("9. Okeke Hostel - 35,000(Location:(Medical Centre");
            System.out.println("10. Isiakaita hostel - 35,000(Location:(UBA BANK");

            do {
                System.out.print("Choose any Available Hostels(1-10):");
                while (!sc.hasNextInt()) {
                    System.out.print(" Invalid! Enter number (1-10): ");
                    sc.next();
                }
                choice = sc.nextInt();
            } while (choice < 1 || choice > 10);

            switch (choice) {
                case 1:
                    return new HostelClass(1,"Okpara Hostel", "Female", 40000, "St.Peters");
                case 2:
                    return new HostelClass(2,"Bello Hostel", "Female", 45000, "Pharmacy");
                case 3:
                    return new HostelClass(3,"Eyoita Hostel", "Female", 25000, "Pharmacy");
                case 4:
                    return new HostelClass(4,"Akpaio Hostel ", "Female", 25000, "studentAffairs");
                case 5:
                    return new HostelClass(5,"Awolowo Hostel ", "Female", 35000, "Malima");
                case 6:
                    return new HostelClass(6,"presidential Hostel ", "Female", 45000, "SUB");
                case 7:
                    return new HostelClass(7,"Mbanefo Hostel", "Female", 50000, "Stadium");
                case 8:
                    return new HostelClass(8," Maryslessor Hostel", "Female", 25000, "Freedom square");
                case 9:
                    return new HostelClass(9," Okeke Hostel", "Female", 35000, "Medical Centre");
                case 10:
                    return new HostelClass(10,"Isiakaita hostel", "Female", 35000, "UBA BANK");
                default:
                    return null;
            }
        } else {
            System.out.println("1. Avan Hostel - ₦42,000 (Location: Stadium)");
            System.out.println("2. Enijoku Hostel- ₦38,000 (Location: Stadium)");
            do {
                System.out.print("Choose any available hostels (1 or 2): ");
                while (!sc.hasNextInt()) {
                    System.out.print("Invalid! Enter number (1 or 2): ");
                    sc.next();
                }
                choice = sc.nextInt();
            } while (choice != 1 && choice != 2);

            return (choice == 1)
                    ? new HostelClass(11,"Avan Hostel", "Male", 42000, "Stadium")
                    : new HostelClass(12,"Enijoku", "Male", 38000, "Stadium");
        }
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Getter for hostel name
    public String getHostelName() {
        return name;
    }

    // Getter for hostel fee
    public double getHostelFee() {
        return hostelfee;
    }
    //getter for hostelId
    public int getHostelId(){
        return hostelId;
    }
    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setters
    public void setHostelId(int hostelId){
        this.hostelId = hostelId;
    }
    public void setHostelFee(double hostelfee) {
        if (hostelfee > 0) {
            this.hostelfee = hostelfee;
        } else {
            System.out.println("Invalid fee. Must be greater than 0.");
        }
    }
}
