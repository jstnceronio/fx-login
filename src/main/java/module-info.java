module com.demo.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires lombok;

    opens com.management to javafx.fxml;
    exports com.management;
}