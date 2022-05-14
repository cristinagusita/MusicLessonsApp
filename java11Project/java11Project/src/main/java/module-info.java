module com.example.java11project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires nitrite;

    opens com.example.java11project to javafx.fxml;
    opens com.example.java11project.sample.Controllers to javafx.fxml;
    opens com.example.java11project.sample.exceptions to javafx.fxml;
    opens com.example.java11project.sample.services to javafx.fxml;
    opens com.example.java11project.sample.users to javafx.fxml, nitrite;

    exports com.example.java11project to javafx.graphics;
    exports com.example.java11project.sample.Controllers to javafx.fxml;
    exports com.example.java11project.sample.exceptions to javafx.fxml;
    exports com.example.java11project.sample.services to javafx.fxml;
    exports com.example.java11project.sample.users to javafx.fxml, nitrite;
    exports com.example.java11project.sample.Controllers.Client to javafx.fxml;
    opens com.example.java11project.sample.Controllers.Client to javafx.fxml;
    exports com.example.java11project.sample.Profesor to javafx.fxml;
    opens com.example.java11project.sample.Profesor to javafx.fxml;

}