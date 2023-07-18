package Controller;

import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<?> modapptcontact;

    @FXML
    private ComboBox<?> modapptcustid;

    @FXML
    private TextField modapptdesc;

    @FXML
    private DatePicker modapptenddate;

    @FXML
    private ComboBox<?> modapptendtime;

    @FXML
    private TextField modapptid;

    @FXML
    private TextField modapptlocation;

    @FXML
    private DatePicker modapptstartdate;

    @FXML
    private ComboBox<?> modapptstarttime;

    @FXML
    private TextField modappttitle;

    @FXML
    private TextField modappttype;

    @FXML
    private ComboBox<?> modapptuserid;

    public void modifyAppointment(Appointments apptSelected) {
    }

    @FXML
    void onActionDisplayMainAppt(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionModAppt(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
