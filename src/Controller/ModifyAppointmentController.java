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

    public void setAppointment(Appointments theAppt) {
        JDBC.openConnection();
        ContactsDAO contactsDAO = new ImplementContacts();
        CustomersDAO customersDAO = new ImplementCustomers();
        UsersDAO usersDAO = new ImplementUsers();

        apptSelected = theAppt;

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
        modapptcontact.getSelectionModel().getSelectedItem();

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


    public void modifyAppointment(Appointments apptSelected) {
        JDBC.openConnection();
        ContactsDAO contactsDAO = new ImplementContacts();
        CustomersDAO customersDAO = new ImplementCustomers();
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
