package com.example.java11project.sample.Controllers.Profesor;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
class ProfesorInfoControllerTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        FileSistemService.APPLICATION_FOLDER = ".MusicLessonsAppDatabase-test-2";
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
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Modify Info");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    @Test
    void saveButtonOnAction(FxRobot robot) {
        robot.clickOn("#usernameTextField");
        robot.write("Dumbledore");
        robot.clickOn("#passwordField");
        robot.write("hogwarts");
        robot.clickOn("Login");
        robot.clickOn("Modify Personal Information");
        robot.clickOn("#username");
        robot.write("Dumbledore");
        robot.clickOn("#password");
        robot.write("hogwart");
        robot.clickOn("#pricetext");
        robot.write("123");
        robot.clickOn("#instrumenttext");
        robot.write("pian");
        robot.clickOn("#descriptiontext");
        robot.write("te invat in 1 zi sa canti la pian!");
        robot.clickOn("SAVE");
        robot.clickOn("#password");
        robot.write("s");
        robot.clickOn("SAVE");
        TextField f =  (TextField) robot.lookup("#username").query();
        Stage stageExit = (Stage) f.getScene().getWindow();
        Platform.runLater(
                () -> {
                    stageExit.close();
                }
        );
    }
}