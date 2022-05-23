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

class ClientPricesControllerTest {

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("prices_client.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    @Test
    void testSeePrices(FxRobot robot)
    {
        try{
            UserService.addUser("test1", "test1", "Profesor");
            UserService.addUser("test2", "test2", "Profesor");
            UserService.addUser("test3", "test3", "Profesor");
            UserService.addUser("test4", "test4", "Profesor");

        }catch(UsernameAlreadyExistsException e)
        {
            fail();
        }

        UserService.modifyUserInfo("test1", "test1", "100", "My Description", "My instrument");
        UserService.modifyUserInfo("test2", "test2", "-100", "My Description", "My instrument");
        UserService.modifyUserInfo("test3", "test3", "0", "My Description", "My instrument");
        UserService.modifyUserInfo("test4", "test4", "0a", "My Description", "My instrument");
        assertThat(UserService.getAllUsers()).size().isEqualTo(4);

        robot.clickOn("#check");

        ListView <String> l1=robot.lookup("#list1").query();
        ListView <String> l2=robot.lookup("#list2").query();

        assertEquals("test1" ,l1.getItems().get(0));
        assertEquals("100 ron" ,l2.getItems().get(0));

        assertEquals("test2" ,l1.getItems().get(1));
        assertEquals(" ron" ,l2.getItems().get(1));

        assertEquals("test3" ,l1.getItems().get(2));
        assertEquals(" ron" ,l2.getItems().get(2));

        assertEquals("test4" ,l1.getItems().get(3));
        assertEquals(" ron" ,l2.getItems().get(3));
    }
}