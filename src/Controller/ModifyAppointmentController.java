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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    Appointments apptSelected = null;

    @FXML
    private ComboBox<Contacts> modapptcontact;

    @FXML
    private ComboBox<Customers> modapptcustid;

    @FXML
    private TextField modapptdesc;

    @FXML
    private DatePicker modapptenddate;

    @FXML
    private ComboBox<LocalTime> modapptendtime;

    @FXML
    private TextField modapptid;

    @FXML
    private TextField modapptlocation;

    @FXML
    private DatePicker modapptstartdate;

    @FXML
    private ComboBox<LocalTime> modapptstarttime;

    @FXML
    private TextField modappttitle;

    @FXML
    private TextField modappttype;

    @FXML
    private ComboBox<Users> modapptuserid;

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


    public void setAppointment(Appointments theAppt) {
        JDBC.openConnection();
        ContactsDAO contactsDAO = new ImplementContacts();
        CustomersDAO customersDAO = new ImplementCustomers();
        UsersDAO usersDAO = new ImplementUsers();

        apptSelected = theAppt;

        modapptid.setText(Integer.toString(apptSelected.getAppointmentID()));
        modappttitle.setText(apptSelected.getAppointmentTitle());
        modapptdesc.setText(apptSelected.getAppointmentDesc());
        modapptlocation.setText(apptSelected.getAppointmentLocation());
        modappttype.setText(apptSelected.getAppointmentType());

        Contacts contactSelected = null;
        for (Contacts contacts : contactsDAO.getAllContacts()) {
            if (contacts.getAppointmentContactID() == apptSelected.getAppointmentContactID()) {
                contactSelected = contacts;
                break;
            }
        }
        modapptcontact.getSelectionModel().select(contactSelected);

        Customers customerSelected = null;
        for (Customers customers : customersDAO.getAllCustomers()) {
            if (customers.getCustomerID() == apptSelected.getAppointmentCustomerID()) {
                customerSelected = customers;
                break;
            }
        }
        modapptcustid.getSelectionModel().select(customerSelected);

        Users userSelected = null;
        for (Users users : usersDAO.getAllUsers()) {
            if (users.getUserID() == apptSelected.getAppointmentUserID()) {
                userSelected = users;
                break;
            }
        }
        modapptuserid.getSelectionModel().select(userSelected);

        modapptstartdate.setValue(apptSelected.getAppointmentStartDate());
        modapptstarttime.getSelectionModel().select(apptSelected.getAppointmentStartTime());
        modapptenddate.setValue(apptSelected.getAppointmentEndDate());
        modapptendtime.getSelectionModel().select(apptSelected.getAppointmentEndTime());
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
        try {
            appointmentID = apptSelected.getAppointmentID();
            appointmentTitle = modappttitle.getText();
            appointmentDesc = modapptdesc.getText();
            appointmentLocation = modapptlocation.getText();
            appointmentContactID = modapptcontact.getSelectionModel().getSelectedItem().getAppointmentContactID();
            appointmentType = modappttype.getText();
            appointmentStartDate = modapptstartdate.getValue();
            appointmentStartTime = modapptstarttime.getSelectionModel().getSelectedItem();
            appointmentStartDateTime = LocalDateTime.of(appointmentStartDate.getYear(), appointmentStartDate.getMonth(),
                    appointmentStartDate.getDayOfMonth(), appointmentStartTime.getHour(), appointmentStartTime.getMinute());
            appointmentEndDate = modapptenddate.getValue();
            appointmentEndTime = modapptendtime.getSelectionModel().getSelectedItem();
            appointmentEndDateTime = LocalDateTime.of(appointmentEndDate.getYear(), appointmentEndDate.getMonth(),
                    appointmentEndDate.getDayOfMonth(), appointmentEndTime.getHour(), appointmentEndTime.getMinute());
            appointmentCustomerID = modapptcustid.getSelectionModel().getSelectedItem().getCustomerID();
            appointmentUserID = modapptuserid.getSelectionModel().getSelectedItem().getUserID();

            AppointmentsDAO appointmentsDAO = new ImplementAppointments();
            appointmentsDAO.modifyAppointments(appointmentID, appointmentTitle, appointmentDesc, appointmentLocation,
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ZoneId systemZoneID = ZoneId.systemDefault();
            ZoneId businessZoneID = ZoneId.of("America/New_York");
            LocalTime appointmentStartTime = LocalTime.of(8, 0);
            int businessHours = 13;

            JDBC.openConnection();
            ContactsDAO contactsDAO = new ImplementContacts();
            CustomersDAO customersDAO = new ImplementCustomers();
            UsersDAO usersDAO = new ImplementUsers();

            modapptcontact.setItems(contactsDAO.getAllContacts());
            modapptcustid.setItems(customersDAO.getAllCustomers());
            modapptuserid.setItems(usersDAO.getAllUsers());
            modapptstarttime.setItems(Time.businessHours(systemZoneID, businessZoneID, appointmentStartTime, businessHours));
            modapptendtime.setItems(Time.businessHours(systemZoneID, businessZoneID, LocalTime.of(9, 0), businessHours));
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }
}
