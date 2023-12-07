package Controller;

import Model.Appointments;
import Model.Contacts;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsByMonthTypeController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<Contacts> contactmonthtypebox;

    @FXML
    private RadioButton monthtypescheduleradio;

    @FXML
    private TableView<Appointments> monthtypetableview;

    @FXML
    private TableColumn monthtypetotalcol;

    @FXML
    private TableColumn reportmonthcol;

    @FXML
    private TableColumn reporttypecol;

    @FXML
    private Label selectcontactlabel;

    @FXML
    private Label totalapptslabel;

    @FXML
    private RadioButton totalbycountryradio;

    @FXML
    private ToggleGroup totalbymonthtypeTG;

    @FXML
    private RadioButton totalmonthtyperadio;

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionGetContact(ActionEvent event) {

    }

    @FXML
    void onActionReturnToMainScreen(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void onActionShowContactSchedule(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionShowMonthType(ActionEvent event) {

    }

    @FXML
    void onActionShowTotalByCountry(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsCountryScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
