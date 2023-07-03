module main.appointmentschedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports Controller;
    opens Controller to javafx.fxml;
    exports Main;
    opens Main to javafx.fxml;
    exports Model;
    opens Model to javafx.base;
}