package Controller;

import DAO.*;
import Helper.JDBC;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import Utility.Time;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDesc;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStartDateTime;
    private LocalDate appointmentStartDate;
    private LocalTime appointmentStartTime;
    private LocalDateTime appointmentEndDateTime;
    private LocalDate appointmentEndDate;
    private LocalTime appointmentEndTime;
    private int appointmentCustomerID;
    private int appointmentUserID;
    private int appointmentContactID;

    @FXML
    private ComboBox<Contacts> addapptcontact;

    @FXML
    private ComboBox<Customers> addapptcustid;

    @FXML
    private TextField addapptdesc;

    @FXML
    private DatePicker addapptenddate;

    @FXML
    private ComboBox<LocalTime> addapptendtime;

    @FXML
    private TextField addapptid;

    @FXML
    private TextField addapptlocation;

    @FXML
    private DatePicker addapptstartdate;

    @FXML
    private ComboBox<LocalTime> addapptstarttime;

    @FXML
    private TextField addappttitle;

    @FXML
    private TextField addappttype;

    @FXML
    private ComboBox<Users> addapptuserid;

    @FXML
    void onActionAddAppt(ActionEvent event) throws IOException {
        try {
            appointmentTitle = addappttitle.getText();
            appointmentDesc = addapptdesc.getText();
            appointmentLocation = addapptlocation.getText();
            appointmentContactID = addapptcontact.getSelectionModel().getSelectedItem().getAppointmentContactID();
            appointmentType = addappttype.getText();
            appointmentStartDate = addapptstartdate.getValue();
            appointmentStartTime = addapptstarttime.getSelectionModel().getSelectedItem();
            appointmentStartDateTime = LocalDateTime.of(appointmentStartDate.getYear(), appointmentStartDate.getMonth(),
                    appointmentStartDate.getDayOfMonth(), appointmentStartTime.getHour(), appointmentStartTime.getMinute());
            appointmentEndDate = addapptenddate.getValue();
            appointmentEndTime = addapptendtime.getSelectionModel().getSelectedItem();
            appointmentEndDateTime = LocalDateTime.of(appointmentEndDate.getYear(), appointmentEndDate.getMonth(),
                    appointmentEndDate.getDayOfMonth(), appointmentEndTime.getHour(), appointmentEndTime.getMinute());
            appointmentCustomerID = addapptcustid.getSelectionModel().getSelectedItem().getCustomerID();
            appointmentUserID = addapptuserid.getSelectionModel().getSelectedItem().getUserID();

            AppointmentsDAO appointmentsDAO = new ImplementAppointments();
            appointmentsDAO.addNewAppointment(appointmentID, appointmentTitle, appointmentDesc, appointmentLocation,
                    appointmentType, appointmentStartDateTime, appointmentEndDateTime, appointmentCustomerID,
                    appointmentUserID, appointmentContactID);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }


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
        System.out.println("Add Appointment works!");
        try {

            ZoneId systemZoneID = ZoneId.systemDefault();
            ZoneId businessZoneID = ZoneId.of("America/New_York");
            LocalTime appointmentStartTime = LocalTime.of(8,  0);
            int businessHours = 13;

            JDBC.openConnection();
             ContactsDAO contactsDAO = new ImplementContacts();
             CustomersDAO customersDAO = new ImplementCustomers();
             UsersDAO usersDAO = new ImplementUsers();

             addapptcontact.setItems(contactsDAO.getAllContacts());
             addapptcontact.getSelectionModel().selectFirst();
             addapptstartdate.setValue(LocalDate.now());
             addapptstarttime.setItems(Time.businessHours(systemZoneID, businessZoneID, appointmentStartTime,businessHours));
             addapptstarttime.getSelectionModel().selectFirst();
             addapptenddate.setValue(LocalDate.now());
             addapptendtime.setItems(Time.businessHours(systemZoneID, businessZoneID, LocalTime.of(9, 0), businessHours));
             addapptendtime.getSelectionModel().selectFirst();
             addapptcustid.setItems(customersDAO.getAllCustomers());
             addapptcustid.getSelectionModel().selectFirst();
             addapptuserid.setItems(usersDAO.getAllUsers());
             addapptuserid.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }
}
