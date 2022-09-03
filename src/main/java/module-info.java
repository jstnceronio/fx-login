module com.demo.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens com.management to javafx.fxml;
    exports com.management;
}