module main.appointmentschedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.appointmentschedulingsystem to javafx.fxml;
    exports main.appointmentschedulingsystem;
}