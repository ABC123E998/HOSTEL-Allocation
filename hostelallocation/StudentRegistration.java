package hostelallocation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentRegistration extends Application {
private StudentProfile registeredStudent;

     @Override
    public void start(Stage stage) {

        // ==========================
        // SCENE 1: STUDENT REGISTRATION
        // ==========================
        Label title = new Label("🧾 Hostel Allocation System");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField regNumberField = new TextField();
        regNumberField.setPromptText("Registration Number (e.g., 2024/245786)");

        TextField departmentField = new TextField();
        departmentField.setPromptText("Department");

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setPromptText("Select Gender");

        TextField levelField = new TextField();
        levelField.setPromptText("Level (100, 200, 300...)");

        TextField ageField = new TextField();
        ageField.setPromptText("Age (16–40)");

        CheckBox feesPaidBox = new CheckBox("School Fees Paid?");
        Label statusLabel = new Label();

        Button registerButton = new Button("Register Student");
        registerButton.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold;");
        registerButton.setPrefWidth(200);

        VBox regLayout = new VBox(10, title, nameField, regNumberField, departmentField,
                genderBox, levelField, ageField, feesPaidBox, registerButton, statusLabel);
        regLayout.setPadding(new Insets(20));
        regLayout.setStyle("-fx-background-color: #f0f8f5;");
        Scene registrationScene = new Scene(regLayout, 420, 500);

        // ==========================
        // SCENE 2: HOSTEL SELECTION
        // ==========================
        Label hostelTitle = new Label("🏠 Select Hostel");
        hostelTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        ComboBox<String> hostelBox = new ComboBox<>();
        Label hostelFeeLabel = new Label();
        Label locationLabel = new Label();
        Label confirmLabel = new Label();
        Button confirmHostelBtn = new Button("Confirm Hostel");
        Button nextToPaymentBtn = new Button("Next ➜ Payment");

        confirmHostelBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold;");
        nextToPaymentBtn.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-weight: bold;");
        confirmHostelBtn.setPrefWidth(200);
        nextToPaymentBtn.setPrefWidth(200);

        VBox hostelLayout = new VBox(10, hostelTitle, hostelBox, hostelFeeLabel, locationLabel,
                confirmHostelBtn, nextToPaymentBtn, confirmLabel);
        hostelLayout.setPadding(new Insets(20));
        hostelLayout.setStyle("-fx-background-color: #f0f8f5;");
        Scene hostelScene = new Scene(hostelLayout, 420, 400);

        // ==========================
        // SCENE 3: ROOM ALLOCATION + PAYMENT
        // ==========================
        Label paymentLabel = new Label("💳 Room Allocation & Payment");
        paymentLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        Label roomLabel = new Label("Room allocated: 103 (Upstairs)");
        Label amountDueLabel = new Label("Amount Due: ₦0");
        TextField paymentField = new TextField();
        paymentField.setPromptText("Enter amount to pay");

        Button payButton = new Button("Make Payment");
        payButton.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-weight: bold;");
        payButton.setPrefWidth(200);

        Label paymentStatus = new Label();

        VBox paymentLayout = new VBox(10, paymentLabel, roomLabel, amountDueLabel, paymentField, payButton, paymentStatus);
        paymentLayout.setPadding(new Insets(20));
        paymentLayout.setStyle("-fx-background-color: #f0f8f5;");
        Scene paymentScene = new Scene(paymentLayout, 420, 400);

        // ==========================
        // EVENT HANDLERS
        // ==========================
        // Register student
        registerButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String regNumber = regNumberField.getText();
                String department = departmentField.getText();
                String gender = genderBox.getValue();
                int level = Integer.parseInt(levelField.getText());
                int age = Integer.parseInt(ageField.getText());
                boolean feesPaid = feesPaidBox.isSelected();

                if (name.isEmpty() || regNumber.isEmpty() || department.isEmpty() || gender == null) {
                    statusLabel.setText("⚠️ Please fill all fields.");
                    return;
                }

                // Create StudentProfile object
                registeredStudent  = new StudentProfile(name, regNumber, department, gender, level, age, feesPaid);
                statusLabel.setText("✅ " + registeredStudent.getName() + " registered successfully!");
                if (!feesPaid){
                    statusLabel.setText("Please pay your school fees first!");
                    return;
                }

                // Move to hostel selection
                stage.setScene(hostelScene);

                // Load hostels based on gender
                hostelBox.getItems().clear();
                if (gender.equalsIgnoreCase("Female")) {
                    hostelBox.getItems().addAll(
                            "Okpara Hostel - ₦40,000 (St.Peters)",
                            "Bello Hostel - ₦45,000 (Pharmacy)",
                            " Eyoita Hostel - ₦25,000 {Pharmacy)",
                            " Akpaio Hostel -  ₦25,000 (studentAffairs)",
                            " Awolowo Hostel -  ₦35,000 (Malima)",
                            "presidential Hostel - ₦45,000 (SUB)",
                            " Mbanefo Hostel -  ₦50,000(Stadium)",
                            " Maryslessor Hostel- ₦25,000(Freedom )",
                            "Okeke Hostel - 35,000(Medical Centre)",
                            " Isiakaita hostel - 35,000(UBA BANK)"
                    );
                } else {
                    hostelBox.getItems().addAll(
                            "Avan Hostel - ₦42,000 (Stadium)",
                            "Enijoku Hostel- ₦38,000 (Stadium)"
                    );
                }
                stage.setScene(hostelScene);
            } catch (NumberFormatException ex) {
                statusLabel.setText("⚠️ Level and Age must be valid numbers.");
            }
        });

        // Handle hostel selection
        hostelBox.setOnAction(event -> {
            String choice = hostelBox.getValue();
            if (choice != null) {
                if (choice.contains("₦")) {
                    hostelFeeLabel.setText("Fee: " + choice.split("₦")[1].split(" ")[0]);
                }
                locationLabel.setText("Location: " + choice.substring(choice.indexOf("(") + 1, choice.indexOf(")")));
            }
        });

        // Confirm hostel
        confirmHostelBtn.setOnAction(e -> {
            String chosenHostel = hostelBox.getValue();
            if (chosenHostel == null) {
                confirmLabel.setText("⚠️ Please select a hostel first.");
            } else {
                confirmLabel.setText("✅ Hostel selected: " + chosenHostel);
            }
        });

        // Go to payment scene
        nextToPaymentBtn.setOnAction(e -> {
            String feeText = hostelFeeLabel.getText().replace("Fee: ₦", "");
            amountDueLabel.setText("Amount Due: ₦" + feeText);
            stage.setScene(paymentScene);
        });

        // Payment handler
        payButton.setOnAction(e -> {
            try {
                String dueText = amountDueLabel.getText().replaceAll("[^0-9.]", "");
        double due = Double.parseDouble(dueText.trim());

        String paidText = paymentField.getText().replaceAll("[^0-9.]", "");
        double amountPaid = Double.parseDouble(paidText.trim());

        PaymentClass pay = new PaymentClass(registeredStudent,due);

        if (pay.makePayment(amountPaid)) {
           // pay.savePayment(regNumberField.getText(), hostelBox.getValue(), 103);
            paymentStatus.setText(" Payment successful  for" + registeredStudent.getName());
        } else {
            paymentStatus.setText("Insufficient amount!");
        }
    } catch (Exception ex) {
        paymentStatus.setText("⚠ Invalid amount entered!");
    }
});



        // ==========================
        // SHOW INITIAL SCENE
        // ==========================
        stage.setScene(registrationScene);
        stage.setTitle("Hostel Allocation System - JavaFX");
        stage.show();
    } 

    public static void main(String[] args) {
        launch(args);
    }
}
 