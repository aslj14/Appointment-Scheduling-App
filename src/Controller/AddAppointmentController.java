package Controller;

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

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<?> addapptcontact;

    @FXML
    private ComboBox<?> addapptcustid;

    @FXML
    private TextField addapptdesc;

    @FXML
    private DatePicker addapptenddate;

    @FXML
    private ComboBox<?> addapptendtime;

    @FXML
    private TextField addapptid;

    @FXML
    private TextField addapptlocation;

    @FXML
    private DatePicker addapptstartdate;

    @FXML
    private ComboBox<?> addapptstarttime;

    @FXML
    private TextField addappttitle;

    @FXML
    private TextField addappttype;

    @FXML
    private ComboBox<?> addapptuserid;

    @FXML
    void onActionAddAppt(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainAppts(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
