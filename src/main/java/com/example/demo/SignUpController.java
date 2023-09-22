package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.DataBase.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpLastname;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpName;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button singUpButton;

    @FXML
    private RadioButton singUpCheckBoxFamale;

    @FXML
    private RadioButton singUpCheckBoxMale;

    @FXML
    void initialize() {
        singUpButton.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {

        String firstName = signUpName.getText();
        String lastName = signUpLastname.getText();
        String userName = signUpLogin.getText();
        String password = signUpPassword.getText();
        String location = signUpCountry.getText();
        String gender = singUpCheckBoxMale.isSelected()? "Мужской": "Женский";

        User user = new User(firstName,lastName,userName,password,location,gender);
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.signUpUser(user);
    }

}
