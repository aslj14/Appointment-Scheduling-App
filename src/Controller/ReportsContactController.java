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

/**
 *
 * This is the Reports: Contact Schedule controller.
 *
 * <p>This controller class will supply the control and logic of the Reports: Contact Schedule screen.
 * This controller class will permit the user to select a contact and see a tableview of that particular
 * contact's schedule. The user will also be ale to see a count of the the total number of appointments that the
 * contact has.</p>
 *
 * @author Ariel Johnson
 *
 */
public class ReportsContactController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * RadioButton for Total Customers by Country.
     *
     */
    @FXML
    private RadioButton contactcustsbycountry;

    /**
     *
     * RadioButton for Reports by Month and Type.
     *
     */
    @FXML
    private RadioButton maintotalbymonthtype;

    /**
     *
     * Start date column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsstartdatecol;

    /**
     *
     * Tableview to show the selected contact's schedule.
     *
     */
    @FXML
    private TableView<Appointments> reportstableview;

    /**
     *
     * RadioButton for Reports: Contact Schedule.
     *
     */
    @FXML
    private RadioButton maincontactsched;

    /**
     *
     * Label that shows the actual number total of the appointments.
     *
     */
    @FXML
    private Label numofapptslabel;

    /**
     *
     * Start date column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsapptsidcol;

    /**
     *
     * ToggleGroup for the contact schedule tableview.
     *
     */
    @FXML
    private ToggleGroup reportscontactsTG;

    /**
     *
     * ComboBox to hold the list of contacts.
     *
     */
    @FXML
    private ComboBox<Contacts> reportscontactscombobox;

    /**
     *
     * Customer ID column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportscustomeridcol;

    /**
     *
     * Description column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsdesccol;

    /**
     *
     * End date column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsenddatecol;

    /**
     *
     * End time column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsendtimecol;

    /**
     *
     * Location column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportslocationcol;

    /**
     *
     * Start time column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsstarttimecol;

    /**
     *
     * Title column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportstitlecol;

    /**
     *
     * Type column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportstypecol;

    /**
     *
     * User ID column in the contact schedule tableview.
     *
     */
    @FXML
    private TableColumn reportsuseridcol;

    /**
     *
     * HBox that prompts the user to select a contact from the combo box.
     *
     */
    @FXML
    private HBox selectcontact;

    /**
     *
     * "Total # of Appointments" label.
     *
     */
    @FXML
    private Label totalapptslabel;

    /**
     *
     * This will load the Main Appointments screen controller and display the main screen
     * that shows the tableview of all the appointments.
     *
     * @param event This is the action for the Cancel button on the Reports: Contact Schedule screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This will exit the application.
     *
     * @param event This is the action for the Logout button on the Reports: Contact Schedule screen.
     *
     */
    @FXML
    void onActionExitReportsContact(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * This is the Fill Table method.
     *
     * <p>When the connection to the database is opened, the tableview is filled with the information from the selected
     * contact. The total number of appointments for this particular contact is counted and shown as a number.</p>
     *
     * @param event This is the action for when the user selects a contact from the combo box.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionFillTable(ActionEvent event) throws IOException {
        JDBC.openConnection();
        AppointmentsDAO appointmentsDAO = new ImplementAppointments();
        int appointmentContactID = reportscontactscombobox.getSelectionModel().getSelectedItem().getAppointmentContactID();
        reportstableview.setItems(appointmentsDAO.getContactAppts(appointmentContactID));

        numofapptslabel.setText(" " + appointmentsDAO.getContactAppts(appointmentContactID).size());
    }

    /**
     *
     * This is the Reports: Total Appointments by Month and Type method.
     *
     * <p>When the user clicks on the Total Appointments by Month and Type radio button, the screen will switch to show the
     * Reports: Total Appointments by Month and Type screen.</p>
     *
     * @param event This is the action for the Total Appointments by Month and Type radio button on the Reports: Contact
     *              Schedule screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionOpenReportsMonthType(ActionEvent event) throws IOException {
            stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsByMonthTypeScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
    }

    /**
     *
     * This is the Reports: Total Customers by Country method.
     *
     * <p>When the user clicks on the Total Customers by Country radio button, the screen will switch to show the
     * Reports: Total Customers by Country screen.</p>
     *
     * @param event This is the action for the Total Customers by Country radio button on the Reports: Contact Schedule
     *              screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionShowReportsCountry(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsCountryScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This initializes the Reports: Contact Schedule controller and populates the tableview with the necessary
     * data for the report that shows the appointments for a selected contact.
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *
     */
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
