package com.example.java11project.sample.Controllers.Profesor;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

//import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ProfesorReviewsControllerTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        FileSistemService.APPLICATION_FOLDER = ".MusicLessonsAppDatabase-test-3";
        FileSistemService.initDirectory();
        FileUtils.cleanDirectory(FileSistemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterAll
    public static void afterAll()  {
        UserService.closeDatabase();
    }

    @BeforeEach
    public void setUp() throws UsernameAlreadyExistsException {
        UserService.resetDatabase();
        UserService.addUser("Dumbledore", "hogwarts", "Profesor");
        UserService.modifyUserReview("Dumbledore","10");
        UserService.modifyUserReview("Dumbledore", "9.5");
        UserService.modifyUserReview("Dumbledore", "10");
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Modify Info");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    @Test
    void checkButtonOnAction(FxRobot robot) {
        robot.clickOn("#usernameTextField");
        robot.write("Dumbledore");
        robot.clickOn("#passwordField");
        robot.write("hogwarts");
        robot.clickOn("Login");
        robot.clickOn("See Reviews");
        robot.clickOn("#nume");
        robot.write("Dumbledore");
        robot.clickOn("Check");
        robot.clickOn("Go Back To Homepage");
        Button f = robot.lookup("#modify").queryButton();
        Stage stageExit = (Stage) f.getScene().getWindow();
        Platform.runLater(
                () -> {
                    stageExit.close();
                }
        );
    }
}