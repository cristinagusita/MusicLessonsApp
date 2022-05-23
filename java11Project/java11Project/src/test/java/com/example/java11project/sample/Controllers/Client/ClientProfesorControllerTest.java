package com.example.java11project.sample.Controllers.Client;

import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.services.FileSistemService;
import com.example.java11project.sample.services.UserService;
import com.example.java11project.sample.users.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(ApplicationExtension.class)

class ClientProfesorControllerTest {

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("profesor_client.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 680, 675));
        primaryStage.show();
    }

    @Test
    @DisplayName("Check the dispayed areas")
    void SeeTeacherInfosTest(FxRobot robot)
    {
        try{
            UserService.addUser("test1", "test1", "Profesor");
            UserService.addUser("test2", "test2", "Profesor");

        }catch(UsernameAlreadyExistsException e)
        {
            fail();
        }

        assertEquals(1, UserService.modifyUserInfo("test1", "test1", "100", "My Description", "My Instrument"));
        assertEquals(0, UserService.modifyUserInfo("test2", "test2", "-100", "My Description1", "My Instrument1"));

        assertThat(UserService.getAllUsers()).size().isEqualTo(2);

        robot.clickOn("#OK");
        robot.clickOn("#cb");
        robot.clickOn("test1");
        robot.clickOn("#SeeInfo");

        TextArea t1=robot.lookup("#textd").query();
        assertEquals("My Description", t1.getText());

        TextArea t2=robot.lookup("#textm").query();
        assertEquals("My Instrument", t2.getText());

        TextArea t3=robot.lookup("#textp").query();
        assertEquals("100 ron", t3.getText());

        robot.clickOn("#cb");
        robot.clickOn("test2");
        robot.clickOn("#SeeInfo");

        assertEquals("", t1.getText());
        assertEquals("", t2.getText());
        assertEquals(" ron", t3.getText());
    }

}