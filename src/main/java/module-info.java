

module com.example.attandenceproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.attandenceproject to javafx.fxml;
    exports com.example.attandenceproject;
    exports Models;
    opens Models to javafx.fxml;
}