package com.example.java11project.sample.Controllers.Profesor;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import com.example.java11project.sample.services.UserService;

import java.io.IOException;


public class ProfesorRequestsController {
    @FXML
    private Label instr1;
    @FXML
    private Button check;
    @FXML
    private TextField username1;
    @FXML
    private Label wrongu;
    @FXML
    private ComboBox accept;

    private ObservableList<String> list1= FXCollections.observableArrayList();
    @FXML
    private TextField client;
    @FXML
    private Label wrongu1;
    @FXML
    private Button submit;
    @FXML
    ListView<String> cereri=new ListView<String>();
    @FXML
    private Label succes;
    private String inst;

    @FXML
    public  void checkButtonOnAction(ActionEvent event) throws IOException {
        inst=username1.getText();
        if(UserService.checkUser(inst)==1){
            wrongu.setVisible(false);
            check.setVisible(false);
            username1.setVisible(false);
            instr1.setVisible(false);
            check.setDisable(true);
            client.setVisible(true);
            submit.setVisible(true);
            cereri.setVisible(true);
            accept.setVisible(true);
            list1.add(UserService.returnsProgramare(inst));
            cereri.setItems(list1);
            accept.getItems().addAll("Accept","Decline");



        }
        else{
            wrongu.setVisible(true);
        }
    }
    @FXML
    public void submitButtonOnAction(ActionEvent event) throws IOException{
        String c=client.getText();


        if(UserService.checkUser(c)==1){
            if(accept.getSelectionModel().getSelectedItem().toString().equals("Accept")){
                UserService.deleteProgramare(c);
                UserService.deleteProgramare(inst);
                UserService.setUserStatus(c,2);
                UserService.setUserStatus(inst,-1);
                succes.setVisible(true);
            }
            else
            {
                UserService.deleteProgramare(c);
                UserService.deleteProgramare(inst);
                UserService.setUserStatus(c,1);
                UserService.setUserStatus(inst,-1);
                succes.setVisible(true);
            }
        }else{
            wrongu1.setVisible(true);
        }
    }


}

