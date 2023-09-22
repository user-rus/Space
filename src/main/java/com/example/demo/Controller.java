package com.example.demo;

import animations.Shake;
import com.example.demo.DataBase.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Button authSigInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        authSigInButton.setOnAction(event -> {
            System.out.println("Авторизация");
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            }
            else
                System.out.println("Login and password is empty");
        });

        loginSignUpButton.setOnAction(event -> {
            System.out.println("вы нажали на кнопку регистрации");
            loginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Parking");
            stage.setScene(new Scene(root));
            InputStream iconStream = getClass().getResourceAsStream("car.png");
            Image image = new Image(iconStream);
            stage.getIcons().add(image);
            stage.showAndWait();
        });
    }

    private void new_scene(String fxml ) {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource(fxml));
        try {
            loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader1.getRoot();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        InputStream iconStream = getClass().getResourceAsStream("car.png");
        Image image = new Image(iconStream);
        stage1.getIcons().add(image);
        stage1.showAndWait();
    }

    private void loginUser(String loginText, String loginPassword) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!result.next()) {
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if (counter >= 1) {
            System.out.println("Success");
            new_scene("main_win.fxml");
        }
        else {
            Shake shake = new Shake(login_field);
            Shake shake1 = new Shake(password_field);
            shake.playAnim();
            shake1.playAnim();
        }
    }
}
