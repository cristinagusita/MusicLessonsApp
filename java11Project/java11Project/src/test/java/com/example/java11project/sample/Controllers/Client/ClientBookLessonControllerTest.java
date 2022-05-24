package com.example.java11project.sample.Controllers.Client;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)

class ClientBookLessonControllerTest {
    @BeforeAll
    public static void beforeAll() throws IOException {
        FileSistemService.APPLICATION_FOLDER = ".MusicLessonsAppDatabase-test-11";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("booklesson_client.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 1127, 675));
        primaryStage.show();
    }

    @Test
    @DisplayName("Check the \"Book a lesson\" feature")
    void bookLessonTest(FxRobot robot) {
        try {
            UserService.addUser("test1", "test1", "Profesor");

        } catch (UsernameAlreadyExistsException e) {
            fail();
        }

        assertEquals(1, UserService.modifyUserInfo("test1", "test1", "100", "My Description", "My Instrument"));

        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#ok");
        robot.clickOn("Teacher");
        robot.clickOn("test1");
        robot.clickOn("Ziua");
        robot.clickOn("1");
        robot.clickOn("Luna");
        robot.clickOn("2");
        robot.clickOn("#save");

        Label wrong = robot.lookup("#eusername").query();
        assertEquals("Wrong username", wrong.getText());

        robot.clickOn("#username");
        robot.write("test11");
        robot.clickOn("#save");
        assertEquals("Wrong username", wrong.getText());
        robot.clickOn("#username");
        robot.eraseText(1);
        robot.clickOn("#save");
        assertEquals("Reservation sent successfully", ((Label) robot.lookup("#rezervare").query()).getText());
    }
}