package com.example.java11project.sample.Controllers.Profesor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
//import org.w3c.dom.Text;
import java.io.IOException;

import com.example.java11project.sample.services.UserService;

public class ProfesorInfoController {

    @FXML
    private Button save;
    @FXML
    private Label price;
    @FXML
    private Label instrument;
    @FXML
    private Label description;
    @FXML
    private TextField pricetext;
    @FXML
    private TextField instrumenttext;
    @FXML
    private TextField descriptiontext;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text savedf;
    @FXML
    public void saveButtonOnAction(ActionEvent event) throws IOException{
        String p=pricetext.getText();
        String m=instrumenttext.getText();
        String d=descriptiontext.getText();
        String name=username.getText();
        String pass=password.getText();
        if(UserService.modifyUserInfo(name,pass,p,d,m)==1)
        {
            savedf.setText("INFO SAVED");
        }
        else{
            savedf.setText("Something went wrong!");
        }


    }
}