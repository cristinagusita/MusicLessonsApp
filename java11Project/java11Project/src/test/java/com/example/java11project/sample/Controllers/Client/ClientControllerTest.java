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

class ClientControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clienthomepage.fxml"));
        primaryStage.setTitle("CLIENT HOMEPAGE TEST");
        primaryStage.setScene(new Scene(root, 1127, 680));
        primaryStage.show();
    }

    @Test
    @DisplayName("Check \" See teachers\" button")
    void testSeeProfesors(FxRobot robot) throws InterruptedException {

        robot.clickOn("#SeeTeachers");
        Text SeeTeachers_Text=robot.lookup("#OK_Text").query();

        assertEquals("If you want to see our music teachers press \"OK\"", SeeTeachers_Text.getText());

        robot.clickOn("#goBack");

        Text text =robot.lookup("#ThanksMessage").queryText();
        assertEquals("Dear user, we thank you for using our Music Lessons App!", text.getText());
    }
    @Test
    @DisplayName("Check \" See prices\" button")
    void testSeePrices(FxRobot robot)
    {
        robot.clickOn("#SeePrices");
        Button check=robot.lookup("#check").query();
        assertEquals("CHECK", check.getText());

        robot.clickOn("#goBack");

        Text text =robot.lookup("#ThanksMessage").queryText();
        assertEquals("Dear user, we thank you for using our Music Lessons App!", text.getText());
    }
    @Test
    @DisplayName("Check \"Review profesor\" button")
    void testReviewTeacher(FxRobot robot)
    {
        robot.clickOn("#ReviewProfesor");
        Text choice= robot.lookup("#choose").query();
        assertEquals("Now choose a grade: ", choice.getText());

        robot.clickOn("#goBack");

        Text text =robot.lookup("#ThanksMessage").queryText();
        assertEquals("Dear user, we thank you for using our Music Lessons App!", text.getText());
    }
    @Test
    @DisplayName("Check \" Book a lesson\" button")
    void testBookLessons(FxRobot robot)
    {
        robot.clickOn("#BookLesson");
        Label text=robot.lookup("#text").query();
        assertEquals("Press OK and start search an profesor ", text.getText());

        robot.clickOn("#goBack");

        Text text1 =robot.lookup("#ThanksMessage").queryText();
        assertEquals("Dear user, we thank you for using our Music Lessons App!", text1.getText());
    }
    @Test
    @DisplayName("Check \" Log out\" button")
    void testLogOut(FxRobot robot)
    {
        robot.clickOn("#logOut");
        assertThat(robot.lookup("#entryMessage").queryText()).hasText("You don't have an account? Register now!");
    }
}