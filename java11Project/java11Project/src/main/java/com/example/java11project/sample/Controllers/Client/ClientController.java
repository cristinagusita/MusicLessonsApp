package com.example.java11project.sample.Controllers.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

//import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Objects;

import com.example.java11project.sample.services.UserService;
import com.example.java11project.sample.users.User;

public class ClientController {
    @FXML
    private AnchorPane change;
    @FXML
    private AnchorPane openClientInterface;
    @FXML
    private Button goBack;
    @FXML
    private Button logOut;

    @FXML
    public void goBackButtonOnAction(ActionEvent event) throws IOException{
        AnchorPane pane=FXMLLoader.load(getClass().getClassLoader().getResource("clienthomepage.fxml"));
        openClientInterface.getChildren().setAll(pane);

    }

    @FXML
    public void logOutButtonOnAction(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage) logOut.getScene().getWindow();
        root=FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));

        stage.setScene(new Scene(root, 600, 450));
        stage.show();

    }
}