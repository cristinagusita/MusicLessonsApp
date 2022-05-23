package com.example.java11project.sample.Controllers.Profesor;

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

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)

class ProfesorControllerTest {




    @Test
    void modifyButtonOnAction() {
    }

    @Test
    void seeReviewsButtonOnAction() {
    }

    @Test
    void seeLessonRequestsButtonOnAction() {
    }

    @Test
    void goBackButtonOnAction() {
    }

    @Test
    void logOutButtonOnAction() {
    }
}