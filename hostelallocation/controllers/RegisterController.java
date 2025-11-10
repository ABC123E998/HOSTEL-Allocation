/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hostelallocation.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author hp
 */
public class RegisterController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField regNumberField;

    @FXML
    private TextField departmentField;

    @FXML
    private void handleRegister() {
        System.out.println("Student Registered:");
        System.out.println("Name: " + nameField.getText());
        System.out.println("Reg Number: " + regNumberField.getText());
        System.out.println("Department: " + departmentField.getText());
    }
}

