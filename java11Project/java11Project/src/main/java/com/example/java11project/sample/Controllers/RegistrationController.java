package com.example.java11project.sample.Controllers;

import com.example.java11project.AlertBox;
import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistrationController {
    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Button closeButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button goBackButton;
    @FXML
    private AnchorPane gobacktologin;
    @FXML
    private TextField accesCode;


    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Profesor");
    }

    @FXML
    public void registerButtonOnAction() {
        if (role.getValue() == "Profesor") {
            if (accesCode.getText().equals("012345")) {
                try {
                    UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
                    registrationMessage.setText("Account created successfully !");
                } catch (UsernameAlreadyExistsException e) {
                    registrationMessage.setText(e.getMessage());
                }
            }
            else {
                AlertBox.display("Error", "The introduced code is incorrect");
            }
        } else {
            if (role.getValue() == "Client") {

                try {
                    UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
                    registrationMessage.setText("Account created successfully !");
                } catch (UsernameAlreadyExistsException e) {
                    registrationMessage.setText(e.getMessage());
                }
            }
        }

    }

    @FXML
    public void closeButtonOnAction(ActionEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void onRoleSelection(MouseEvent event)
    {
        if(role.getValue()=="Profesor"){
            accesCode.setDisable(false);
            accesCode.setPromptText("acces code for teachers");
        }
        else if(role.getValue()=="Client")
        {
            accesCode.setDisable(true);
            accesCode.clear();
            accesCode.setPromptText("Not for clients");
        }
    }
}
