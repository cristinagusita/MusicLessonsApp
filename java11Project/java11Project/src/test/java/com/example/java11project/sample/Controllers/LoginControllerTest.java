package com.example.java11project.sample.Controllers;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import org.testfx.framework.junit5.ApplicationExtension;
import java.io.IOException;

import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.osgi.service.TestFx;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.matcher.control.TextMatchers.hasText;



@ExtendWith(ApplicationExtension.class)

class LoginControllerTest {

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("LOGIN TEST");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    @Test
    void testLoginClient(FxRobot robot) throws InterruptedException
    {
        assertThat(robot.lookup("#entryMessage").queryText()).hasText("You don't have an account? Register now!");
        try{
            UserService.addUser( "test", "test", "Client");
        }catch(UsernameAlreadyExistsException e)
        {
            fail();
        }

        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

       robot.clickOn("#usernameTextField");
       robot.write("test");
       robot.clickOn("#passwordField");
        robot.write("test1");
        robot.clickOn("#loginButton");

        TextField user_name1= robot.lookup("#usernameTextField").query();
        TextField pass1=robot.lookup("#passwordField").query();

        assertEquals(0, UserService.validateLogin(user_name1.getText(), pass1.getText() ));

        Label l=robot.lookup("#loginMessageLabel").query();
        assertEquals("Please try again!", l.getText());


        robot.clickOn("#passwordField");
        robot.eraseText(1);
        robot.clickOn("#loginButton");

        assertEquals(2, UserService.validateLogin(user_name1.getText(), pass1.getText() ));

        Text text =robot.lookup("#ThanksMessage").queryText();
        assertEquals("Dear user, we thank you for using our Music Lessons App!", text.getText());
    }

    @Test
    void testLoginProfesor(FxRobot robot) throws InterruptedException
    {
        assertThat(robot.lookup("#entryMessage").queryText()).hasText("You don't have an account? Register now!");
        try{
            UserService.addUser( "test", "test", "Profesor");
        }catch(UsernameAlreadyExistsException e)
        {
            fail();
        }

        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#usernameTextField");
        robot.write("test");
        robot.clickOn("#passwordField");
        robot.write("test1");
        robot.clickOn("#loginButton");

        TextField user_name1= robot.lookup("#usernameTextField").query();
        TextField pass1=robot.lookup("#passwordField").query();

        assertEquals(0, UserService.validateLogin(user_name1.getText(), pass1.getText() ));

        Label l=robot.lookup("#loginMessageLabel").query();
        assertEquals("Please try again!", l.getText());


        robot.clickOn("#passwordField");
        robot.eraseText(1);
        robot.clickOn("#loginButton");

        assertEquals(1, UserService.validateLogin(user_name1.getText(), pass1.getText() ));

        Text text =robot.lookup("#ThanksMessage").queryText();
        assertEquals("Dear user, we thank you for using our Music Lessons App!", text.getText());
    }
}