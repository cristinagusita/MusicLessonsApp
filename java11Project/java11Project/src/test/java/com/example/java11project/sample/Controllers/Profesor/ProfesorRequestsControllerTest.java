package com.example.java11project.sample.Controllers.Profesor;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

//import static org.junit.jupiter.api.Assertions.*;
import javax.swing.text.TableView;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ProfesorRequestsControllerTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        FileSistemService.APPLICATION_FOLDER = ".MusicLessonsAppDatabase-test-4";
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
        UserService.addUser("Voldemort", "nimic", "Client");
        UserService.addProgramare("Dumbledore", "Nume utilizator:Voldemort \nData:10/10 \nDetalii: reau sa invat sa cant la pian");
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Modify Info");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Test
    void checkButtonOnActionDecline(FxRobot robot) {
        robot.clickOn("#usernameTextField");
        robot.write("Dumbledore");
        robot.clickOn("#passwordField");
        robot.write("hogwarts");
        robot.clickOn("Login");
        robot.clickOn("See Lesson Requests");
        robot.clickOn("#username1");
        robot.write("Dumbledore");
        robot.clickOn("CHECK");
        ListView requests = robot.lookup("#cereri").queryListView();
        Platform.runLater(
                () -> {
                    requests.getSelectionModel().select(0);
                }
        );
        robot.clickOn("#client");
        robot.write("Voldemort");
        robot.clickOn("Accept/Decline");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("SUBMIT");
    }

    @Test
    void checkButtonOnActionAccept(FxRobot robot) {
        robot.clickOn("#usernameTextField");
        robot.write("Dumbledore");
        robot.clickOn("#passwordField");
        robot.write("hogwarts");
        robot.clickOn("Login");
        robot.clickOn("See Lesson Requests");
        robot.clickOn("#username1");
        robot.write("");
        robot.clickOn("CHECK");
        robot.clickOn("#username1");
        robot.write("Dumbledore");
        robot.clickOn("CHECK");
        ListView requests = robot.lookup("#cereri").queryListView();
        Platform.runLater(
                () -> {
                    requests.getSelectionModel().select(0);
                }
        );
        robot.clickOn("SUBMIT");
        robot.clickOn("#client");
        robot.write("Voldemort");
        robot.clickOn("Accept/Decline");
        robot.clickOn("Accept");
        robot.type(KeyCode.ENTER);
        robot.clickOn("SUBMIT");
        robot.clickOn("Log Out");
    }
}