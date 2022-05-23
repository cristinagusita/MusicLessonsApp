package com.example.java11project.sample.Controllers;

import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.w3c.dom.Text;

//import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationControllerTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        FileSistemService.APPLICATION_FOLDER = ".MusicLessonsAppDatabase-test";
        FileSistemService.initDirectory();
        FileUtils.cleanDirectory(FileSistemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterAll
    public static void afterAll()  {
        UserService.closeDatabase();
    }

    @BeforeEach
    public void setUp() {
        UserService.resetDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    @Test
    void testRegistrationProfesor(FxRobot robot) throws InterruptedException {
        robot.clickOn("#usernameField");
        robot.write("Angelina");
        robot.clickOn("#passwordField");
        robot.write("Angelina");
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#accesCode");
        robot.write("");
        robot.clickOn("#registerButton");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#accesCode");
        robot.write("012345");
        robot.clickOn("#registerButton");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully !");
        org.assertj.core.api.Assertions.assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(
                String.format("An account with the username %s already exists!", "Angelina")
        );

        robot.clickOn("#usernameField");
        robot.write("1");
        robot.clickOn("#registerButton");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully !");
        org.assertj.core.api.Assertions.assertThat(UserService.getAllUsers()).size().isEqualTo(2);

       TextField f =  (TextField) robot.lookup("#usernameField").query();
        Stage stageExit = (Stage) f.getScene().getWindow();
        Platform.runLater(
                () -> {
                    stageExit.close();
                }
        );
    }

    @Test
    void testRegistrationClient(FxRobot robot) throws InterruptedException {
        robot.clickOn("#usernameField");
        robot.write("Bob");
        robot.clickOn("#passwordField");
        robot.write("Bob");
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#registerButton");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully !");
        org.assertj.core.api.Assertions.assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(
                String.format("An account with the username %s already exists!", "Bob")
        );

        robot.clickOn("#usernameField");
        robot.write("1");
        robot.clickOn("#registerButton");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully !");
        org.assertj.core.api.Assertions.assertThat(UserService.getAllUsers()).size().isEqualTo(2);
        TextField f =  (TextField) robot.lookup("#usernameField").query();
        Stage stageExit = (Stage) f.getScene().getWindow();
        Platform.runLater(
                () -> {
                    stageExit.close();
                }
        );
    }

    @Test
    void closeButtonOnAction(FxRobot robot) {
        robot.clickOn("Close");
    }

    @Test
    void onRoleSelection() {
    }

    @Test
    void goBackButtonOnAction(FxRobot robot) {
        robot.clickOn("Go back");
        robot.clickOn("Cancel");
    }
}