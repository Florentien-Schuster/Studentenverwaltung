package com.example.studentsoftware;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.studentsoftware.HelloApplication.openOverviewWindow;

public class HelloController {

    private static final String correctUsername = "admin"; // Beispiel Username
    private static final String correctPassword = "1234"; // Beispiel Passwort

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    /** Wird bei Klicken des Login buttons aufgerufen prüft ob, die eingegebenen Logindaten (Username & Password) korrekt sind,
     * wenn ja öffnet sich das Dashboard Fenster
     */
    @FXML
    private void loginHandler(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals(correctUsername)&& password.equals(correctPassword)){
            messageLabel.setText("Login successful");
            HelloApplication.openOverviewWindow();
        }else{
            messageLabel.setText("Username or password wrong!");
        }

        // Felder leeren nach Eingabe
        usernameField.clear();
        passwordField.clear();

    }
}