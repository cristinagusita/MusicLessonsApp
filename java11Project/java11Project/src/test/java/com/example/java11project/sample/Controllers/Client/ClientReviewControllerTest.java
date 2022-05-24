package com.example.java11project.sample.Controllers.Client;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
import org.testfx.framework.junit5.ApplicationExtension;
import java.io.IOException;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)

class ClientReviewControllerTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        FileSistemService.APPLICATION_FOLDER = ".MusicLessonsAppDatabase-test-14";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("review_client.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 1127, 675));
        primaryStage.show();
    }

    @Test
    @DisplayName("Check the review function")
    void reviewTest(FxRobot robot) throws IOException
    {
        try{
            UserService.addUser("test1", "test1", "Profesor");
        }catch(UsernameAlreadyExistsException e)
        {
            fail();
        }

        assertEquals(1, UserService.modifyUserInfo("test1", "test1", "100", "My Description", "My Instrument"));

        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#search");
        robot.clickOn("#cb1");
        robot.clickOn("test1");
        robot.clickOn("#cb2");
        robot.clickOn("1");
        robot.clickOn("#review");

        assertEquals("1", (UserService.returnsReviews("test1")).trim());
    }
}