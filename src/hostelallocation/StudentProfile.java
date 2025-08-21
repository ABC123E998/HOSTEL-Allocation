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
   private String name;
   private String regNumber;
   private String department;
   private String gender;
   private int level;
   private final int age;
   private boolean feesPaid;
   private boolean registered;
   
   //Parameterized constructor
    public StudentProfile(String name, String regNumber, String department,
                          String gender, int level, int age, boolean feePaid, boolean registered) {
        this.name = name;
        this.regNumber = regNumber;
        this.department = department;
        this.gender = gender;
        this.level = level;
        this.age = age;
        this.feesPaid= feePaid;
        this.registered = registered;
    }
 
    // Allowed levels
    private static final int[] VALID_LEVELS = {100, 200, 300, 400, 500, 600};

    // Allowed age range
    private static final int MIN_AGE = 16;
    private static final int MAX_AGE = 30;

    // Constructor with Scanner input
    public StudentProfile() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        this.name = sc.nextLine();

        System.out.print("Enter Registration Number: ");
        this.regNumber = sc.nextLine();

        System.out.print("Enter Department: ");
        this.department = sc.nextLine();

        // Validate Age
        while (true) {
            System.out.print("Enter Age: ");
            int inputAge = sc.nextInt();
            if (inputAge >= MIN_AGE && inputAge <= MAX_AGE) {
                this.age = inputAge;
                break;
            } else {
                System.out.println("ERROR 102: Invalid Age! Age must be between " + MIN_AGE + " and " + MAX_AGE);
            }
        }

        // Validate Level
        while (true) {
            System.out.print("Enter Level (100, 200, 300, 400, 500, 600): ");
            int inputLevel = sc.nextInt();
            if (Arrays.stream(VALID_LEVELS).anyMatch(l -> l == inputLevel)) {
                this.level = inputLevel;
                break;
            } else {
                System.out.println("ERROR 101: Invalid Level! Must be one of 100, 200, 300, 400, 500, 600.");
            }
        }

        // Fees Paid?
        System.out.print("Have you paid your school fees? (yes/no): ");
        String feesInput = sc.next();
        this.feesPaid = feesInput.equalsIgnoreCase("yes");

        // Registration depends on fees
        this.registered = this.feesPaid;
    }

     // Getters ( to read values)
    public String getName() { return name;}
    public String getRegNumber() {return regNumber;}
     public String getGender() {return gender;}
    public int getlevel() {return age;}
    public int getAge() {return level;}
    public boolean isEligible() {return feesPaid && registered;}
    
    //setters
    public void setlevel(){this.level =level;}
    public void setFeesPaid(double newfeespaid){this.feesPaid = feesPaid;}
    public void setRegistered (boolean registered) {this.registered = registered;}
   
    public void displayStudentProfile() {
        System.out.println("=== Student Profile ===");
        System.out.println("Name: " + name);
        System.out.println("Reg Number: " + regNumber);
        System.out.println("Department: " + department);
        System.out.println("Age: " + age);
        System.out.println("Level: " + level + " Level");
        System.out.println("Fees Paid: " + feesPaid);
        System.out.println("Registered: " + registered);
        

} 
        
        
            
}


  
      