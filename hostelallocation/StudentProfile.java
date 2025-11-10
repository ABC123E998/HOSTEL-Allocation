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
public class StudentProfile {

    String name;
    String regNumber;
    String department;
    String gender;
    int level;
    final int age;
    boolean feesPaid;
    boolean registered;
    private HostelClass hostel;

    //Parameterized constructor
    public  StudentProfile(String name, String regNumber, String department,
            String gender, int level, int age, boolean feePaid) {
        this.name = name;
        this.regNumber = regNumber;
        this.department = department;
        this.gender = gender;
        this.level = level;
        this.age = age;
        this.feesPaid = feePaid;
        this.registered = false;
    }

   
    //  Prompt user for valid details
    public static StudentProfile createStudent() {
        Scanner sc = new Scanner(System.in);
        String name, regNumber, department, gender, paid;
        int level = 0, age = 0;
        boolean feesPaid;

        System.out.println("STUDENT REGISTRATION");

        //  Name
        do {
            System.out.print("Enter Full Name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(" Name cannot be empty!");
            }
        } while (name.isEmpty());

        //  Registration Number 
        do {
            System.out.print("Enter Registration Number (e.g., 2022/123456): ");
            regNumber = sc.nextLine().trim();
            if (!regNumber.matches("\\d{4}/\\d{6}")) {
                System.out.println(" Invalid format! Use this pattern: 2022/123456");
            }
        } while (!regNumber.matches("\\d{4}/\\d{6}"));

        // Department
        do {
            System.out.print("Enter Department: ");
            department = sc.nextLine().trim();
            if (department.isEmpty()) {
                System.out.println(" Department cannot be empty!");
            }
        } while (department.isEmpty());

        //  Gender
        do {
            System.out.print("Enter Gender (Male/Female): ");
            gender = sc.nextLine().trim();
            if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
                System.out.println(" Please enter 'Male' or 'Female' only.");
            }
        } while (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")));

        //  Level (100–500)
        do {
            System.out.print("Enter Level (100, 200, 300, 400, 500): ");
            while (!sc.hasNextInt()) {
                System.out.print(" Invalid input! Please enter a number: ");
                sc.next(); // clear invalid input
            }
            level = sc.nextInt();
            if (level != 100 && level != 200 && level != 300 && level != 400 && level != 500) {
                System.out.println(" Level must be 100, 200, 300, 400, or 500.");
            }
        } while (level != 100 && level != 200 && level != 300 && level != 400 && level != 500);

        // Age
        do {
            System.out.print("Enter Age (16–40): ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid! Enter a valid age number: ");
                sc.next(); // clear invalid input
            }
            age = sc.nextInt();
            if (age < 16 || age > 40) {
                System.out.println(" Age must be between 16 and 40.");
            }
        } while (age < 16 || age > 40);

        sc.nextLine(); // clear leftover newline

        // Fees Paid
        do {
            System.out.print("Have you paid school fees? (yes/no): ");
            paid = sc.nextLine().trim();
            if (!(paid.equalsIgnoreCase("yes") || paid.equalsIgnoreCase("no"))) {
                System.out.println(" Please answer 'yes' or 'no'.");
            }
        } while (!(paid.equalsIgnoreCase("yes") || paid.equalsIgnoreCase("no")));

        if (paid.equalsIgnoreCase("no")) {
            System.out.println("\n⚠ You must pay your school fees before applying for hostel allocation.");
            System.out.println("Returning to main menu...\n");
            return null;  // exits this method and returns control to main class
        }

        feesPaid = true; // only runs if student says "yes"
                
        
    return new StudentProfile(name, regNumber, department, gender, level, age, feesPaid);
    
    }
    


    //Getters ( to read values)
    public String getName() {
        return name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getGender() {
        return gender;
    }

    public int getLevel() {
        return level;
    }

    public int getAge() {
        return age;
    }
    
    public String getDepartment(){
       return department;
    }

    public boolean isFeesPaid() {
        return feesPaid;
    }
    public HostelClass getHostel(){
        return hostel;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
    
    public void setFeesPaid(boolean feesPaid){
        this.feesPaid = feesPaid;
    }
    public void setHostel(HostelClass hostel){
        this.hostel = hostel;
    }
    public void displayStudentProfile() {
        System.out.println("Name: " + name);
        System.out.println("Reg Number: " + regNumber);
        System.out.println("Department: " + department);
        System.out.println("Age: " + age);
        System.out.println("Level: " + level + " Level");
        System.out.println("Fees Paid: " + feesPaid);
        System.out.println("Registered: " + registered);
  //show hostel if set
  if (hostel !=null){
      System.out.println("Hostel:" + hostel.getHostelName());
  }else{
      System.out.println("Hostel: Not yet assigned");
  }
    }

}
