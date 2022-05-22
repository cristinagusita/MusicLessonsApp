package com.example.java11project.sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import com.example.java11project.sample.services.UserService;
import com.example.java11project.sample.users.User;



public class LoginController {

    @FXML
    private Text entryMessage;
    @FXML
    private Button cancelButton;
    @FXML
    public Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private AnchorPane openregisterpane;
    @FXML
    private AnchorPane openClientInterface;
    @FXML
    private AnchorPane openprofesorInterface;


    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException{
        String username=usernameTextField.getText();
        String password=passwordField.getText();

        loginMessageLabel.setVisible(true);
        if(username!=null && password!=null){
            int k;
            k=UserService.validateLogin(username,password);
            if(k!=0)
            {
                if(k==1){
                    //open interface for profesors
                    Stage stage;
                    Parent root;
                    stage = (Stage) loginButton.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getClassLoader().getResource("profesorhomepage.fxml"));

                    stage.setScene(new Scene(root, 1127, 680));
                    stage.show();
                }
                if(k==2){
                    //open interface for clients
                    Stage stage;
                    Parent root;
                    stage = (Stage) loginButton.getScene().getWindow();
                    root=FXMLLoader.load(getClass().getClassLoader().getResource("clienthomepage.fxml"));

                    stage.setScene(new Scene(root, 1127, 680));
                    stage.show();
                }

            }
            else{
                loginMessageLabel.setText("Please try again!");
            }
        }
        else{

            loginMessageLabel.setText("Please enter your username and password!");

        }
    }

    @FXML
    public void cancelButtonOnAction(ActionEvent event){
        loginMessageLabel.setVisible(true);
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registerButtonOnAction(ActionEvent event) throws IOException {

        AnchorPane regpane= FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        openregisterpane.getChildren().setAll(regpane);
    }
}