package Controller;

import DAO.AppointmentsDAO;
import DAO.ImplementAppointments;
import Helper.JDBC;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton allapptsview;

    @FXML
    private RadioButton allcustomersview;

    @FXML
    private RadioButton apptmonthview;

    @FXML
    private RadioButton apptweekview;

    @FXML
    private ToggleGroup mainApptsTG;

    @FXML
    private TableColumn mainapptscontactcol;

    @FXML
    private TableColumn mainapptscustomeridcol;

    @FXML
    private TableColumn mainapptsdesccol;

    @FXML
    private TableColumn mainapptsenddatecol;

    @FXML
    private TableColumn mainapptsendtimecol;

    @FXML
    private TableColumn mainapptsidcol;

    @FXML
    private TableColumn mainapptslocationcol;

    @FXML
    private TableColumn mainapptsstartdatecol;

    @FXML
    private TableColumn mainapptsstarttimecol;

    @FXML
    private TableView<Appointments> mainapptstableview;

    @FXML
    private TableColumn mainapptstitlecol;

    @FXML
    private TableColumn mainapptstypecol;

    @FXML
    private TableColumn mainapptsuseridcol;


    @FXML
    void onActionAddAppt(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionDeleteAppt(ActionEvent event) {

    }

    @FXML
    void onActionDisplayAllCusts(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionLogoutMainApptScreen(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void onActionModifyAppt(ActionEvent event) throws IOException {
        try {
                Appointments apptSelected = mainapptstableview.getSelectionModel().getSelectedItem();
                if (apptSelected == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please select an appointment to modify! ");
                    alert.showAndWait();
                } else {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyAppointmentScreen.fxml"));
                    Scene scene = new Scene(loader.load());
                    ModifyAppointmentController controller = loader.getController();
                    controller.modifyAppointment(apptSelected);
                    stage.setTitle("Appointment Scheduling System");
                    stage.setScene(scene);
                    stage.show();
                }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        JDBC.openConnection();
        AppointmentsDAO appointmentsDAO = new ImplementAppointments();
        mainapptstableview.setItems(appointmentsDAO.getAllAppointments());

        mainapptsidcol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        mainapptstitlecol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        mainapptsdesccol.setCellValueFactory(new PropertyValueFactory<>("appointmentDesc"));
        mainapptslocationcol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        mainapptstypecol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        mainapptsstartdatecol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDate"));
        mainapptsstarttimecol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        mainapptsenddatecol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDate"));
        mainapptsendtimecol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        mainapptscustomeridcol.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
        mainapptsuseridcol.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));



    }
}

