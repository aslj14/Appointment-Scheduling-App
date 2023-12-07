package Controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.ImplementAppointments;
import DAO.ImplementContacts;
import Helper.JDBC;
import Model.Appointments;
import Model.Contacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ReportsContactController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton contactcustsbycountry;

    @FXML
    private RadioButton maintotalbymonthtype;

    @FXML
    private TableColumn reportsstartdatecol;

    @FXML
    private TableView<Appointments> reportstableview;

    @FXML
    private RadioButton maincontactsched;

    @FXML
    private Label numofapptslabel;

    @FXML
    private TableColumn reportsapptsidcol;

    @FXML
    private TableColumn reportscontactcol;

    @FXML
    private ToggleGroup reportscontactsTG;

    @FXML
    private ComboBox<Contacts> reportscontactscombobox;

    @FXML
    private TableColumn reportscustomeridcol;

    @FXML
    private TableColumn reportsdesccol;

    @FXML
    private TableColumn reportsenddatecol;

    @FXML
    private TableColumn reportsendtimecol;

    @FXML
    private TableColumn reportslocationcol;

    @FXML
    private TableColumn reportsstarttimecol;

    @FXML
    private TableColumn reportstitlecol;

    @FXML
    private TableColumn reportstypecol;

    @FXML
    private TableColumn reportsuseridcol;

    @FXML
    private HBox selectcontact;

    @FXML
    private Label totalapptslabel;

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionExitReportsContact(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionFillTable(ActionEvent event) throws IOException {
        JDBC.openConnection();
        AppointmentsDAO appointmentsDAO = new ImplementAppointments();
        int appointmentContactID = reportscontactscombobox.getSelectionModel().getSelectedItem().getAppointmentContactID();
        reportstableview.setItems(appointmentsDAO.getContactAppts(appointmentContactID));

        numofapptslabel.setText(" " + appointmentsDAO.getContactAppts(appointmentContactID).size());
    }

    @FXML
    void onActionOpenReportsMonthType(ActionEvent event) throws IOException {
            stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsByMonthTypeScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void onActionShowReportsCountry(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsCountryScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reportsapptsidcol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        reportstitlecol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        reportsdesccol.setCellValueFactory(new PropertyValueFactory<>("appointmentDesc"));
        reportslocationcol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        reportstypecol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        reportsstartdatecol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDate"));
        reportsstarttimecol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        reportsenddatecol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDate"));
        reportsendtimecol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        reportscustomeridcol.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
        reportsuseridcol.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));

        JDBC.openConnection();
        ContactsDAO contactsDAO = new ImplementContacts();
        reportscontactscombobox.setItems(contactsDAO.getAllContacts());
    }
}
