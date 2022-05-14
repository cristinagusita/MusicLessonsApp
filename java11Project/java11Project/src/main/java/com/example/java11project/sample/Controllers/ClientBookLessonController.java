package com.example.java11project.sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import com.example.java11project.sample.services.UserService;
import javax.validation.constraints.Null;
import java.io.IOException;
import javafx.scene.image.ImageView ;
import com.example.java11project.sample.users.User;


public class ClientBookLessonController {
    public AnchorPane anchorpane;
    @FXML
    private ComboBox cb1;
    private ObservableList<String> list1= FXCollections.observableArrayList();
    @FXML
    private ComboBox cb2;
    private ObservableList<String> list2= FXCollections.observableArrayList();
    @FXML
    private ComboBox cb3;
    private ObservableList<String> list3= FXCollections.observableArrayList();
    @FXML
    private Button ok;
    @FXML
    private Button save;
    @FXML
    private TextField username;
    @FXML
    private TextArea message;
    @FXML
    private Label eusername;
    @FXML
    private Label eusername1;
    @FXML
    private Label text;
    @FXML
    private Label rezervare;
    @FXML
    private ImageView image;
    @FXML
    private Label status0;
    @FXML
    private Label status01;
    @FXML
    private Label status1;
    @FXML
    private Label status2;
    @FXML
    public void okButtonOnAction(ActionEvent event)throws IOException{
        UserService.getprofesors(list1);
        cb1.getItems().addAll(list1);

        for(int i=0;i<=30;i++)
            cb2.getItems().add(i);
        cb3.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        ok.setDisable(true);
        ok.setVisible(false);
        text.setVisible(false);
        save.setVisible(true);
        message.setVisible(true);
        username.setVisible(true);
        cb1.setVisible(true);
        cb2.setVisible(true);
        cb3.setVisible(true);
        image.setVisible(true);



    }
    @FXML
    public void saveButtonOnAction(ActionEvent event) throws IOException{
        String ni=cb1.getSelectionModel().getSelectedItem().toString();
        String z= cb2.getSelectionModel().getSelectedItem().toString();
        String l=cb3.getSelectionModel().getSelectedItem().toString();
        String nu=username.getText();
        String mes=message.getText();
        String progC="Nume profesor:"+ni +"\n"+"Data:"+z+"/"+l;
        String progI="Nume utilizator:"+nu +"\n"+"Data:"+z+"/"+l+"\n"+"Detalii:"+mes;


        if(nu!=null) {
            if (UserService.checkUser(nu)==1 ) {
                if (UserService.checkStatus(nu) == 0 || UserService.checkStatus(ni) == 0) {
                    status0.setVisible(true);
                    status01.setVisible(true);
                    eusername.setVisible(false);
                    status2.setVisible(false);
                    status1.setVisible(false);
                    eusername1.setVisible(false);

                }
                if (UserService.checkStatus(nu) == -1 && UserService.checkStatus(ni) == -1) {
                    UserService.addProgramare(nu, progC);
                    UserService.addProgramare(ni, progI);
                    rezervare.setVisible(true);
                    save.setVisible(false);
                    message.setVisible(false);
                    username.setVisible(false);
                    cb1.setVisible(false);
                    cb2.setVisible(false);
                    cb3.setVisible(false);
                    status0.setVisible(false);
                    status01.setVisible(false);
                    eusername.setVisible(false);
                    status2.setVisible(false);
                    status1.setVisible(false);
                    eusername1.setVisible(false);
                }
                if (UserService.checkStatus(nu) == 1) {
                    status1.setVisible(true);
                    status0.setVisible(false);
                    status01.setVisible(false);
                    eusername.setVisible(false);
                    status2.setVisible(false);
                    eusername1.setVisible(false);
                    UserService.setUserStatus(nu, -1);
                }
                if (UserService.checkStatus(nu) == 2) {
                    status2.setVisible(true);
                    status1.setVisible(false);
                    status0.setVisible(false);
                    status01.setVisible(false);
                    eusername.setVisible(false);
                    eusername1.setVisible(false);
                    UserService.setUserStatus(nu, -1);
                }
            }
            else {
                eusername.setVisible(true);
                status2.setVisible(false);
                status1.setVisible(false);
                status0.setVisible(false);
                status01.setVisible(false);
                eusername1.setVisible(false);
            }
        }
        else{
            eusername1.setVisible(true);
            eusername.setVisible(false);
            status2.setVisible(false);
            status1.setVisible(false);
            status0.setVisible(false);
            status01.setVisible(false);
        }
    }
}
