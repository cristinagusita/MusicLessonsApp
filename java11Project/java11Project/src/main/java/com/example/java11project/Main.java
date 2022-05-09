package com.example.java11project;

import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        initDirectory();
        UserService.initDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setTitle("Music Lessons Application");
        stage.setScene(scene);
        stage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSistemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
