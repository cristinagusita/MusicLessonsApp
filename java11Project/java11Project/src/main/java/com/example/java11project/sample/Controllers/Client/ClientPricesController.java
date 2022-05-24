package com.example.java11project.sample.Controllers.Client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import com.example.java11project.sample.services.UserService;

import java.io.IOException;

public class ClientPricesController {
    @FXML
    private Button check;
    @FXML
    ListView<String> list1=new ListView<String>();
    @FXML
    ListView<String> list2=new ListView<String>();
    private ObservableList<String> items_list1 = FXCollections.observableArrayList();
    private ObservableList<String> items_list2 = FXCollections.observableArrayList();

    @FXML
    public void checkButtonOnAction(ActionEvent event)throws IOException{
        UserService.getprofesors(items_list1);
        UserService.getPrices(items_list2);
        list1.setItems(items_list1);
        list2.setItems(items_list2);
    }

}
