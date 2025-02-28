module com.example.studentsoftware {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.studentsoftware to javafx.fxml;
    exports com.example.studentsoftware;
}