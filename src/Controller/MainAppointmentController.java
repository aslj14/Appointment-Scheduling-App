package Controller;

import DAO.*;
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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * This is the Main Appointments controller.
 *
 * <p>This controller class will supply the control and logic of the main appointments screen.
 * This controller class will permit the user to view all of the appointments in the database. This is the first screen
 * user will see after the initial log in to the application. This main appointments screen/controller will display a
 * list of the current appointments that are saved to the database along with all of the information associated to the
 * appointments. From this screen the user can add a new appointment, modify an existing appointment, and/or delete an
 * existing appointment from the database. This screen also has the functionality to allow a user ot filter all of the
 * approaching appointments by either month or week. The user can maneuver throughout the entire application just from
 * this screen. The user can also logout (exit) the application as well.</p>
 *
 * @author Ariel Johnson
 *
 */
public class MainAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the appointment that is selected.
     *
     */
    private static Appointments apptSelected;

    /**
     *
     * This is the getter for the selected appointment.
     * @return getApptSelected This returns the selected appointment.
     *
     */
    public static Appointments getApptSelected() {
        return apptSelected;
    }

    /**
     *
     * This is the setter for the selected appointment.
     * @param theAppt This is the appointment that is selected.
     *
     */
    public static void setApptSelected(Appointments theAppt) {
        apptSelected = theAppt;
    }

    /**
     *
     * RadioButton for "View All Appointments".
     *
     */
    @FXML
    private RadioButton allapptsview;

    /**
     *
     * RadioButton for "View All Customers".
     *
     */
    @FXML
    private RadioButton allcustomersview;

    /**
     *
     * RadioButton for "View All Appointments by Month".
     *
     */
    @FXML
    private RadioButton apptmonthview;

    /**
     *
     * RadioButton for "View All Appointments by Week".
     *
     */
    @FXML
    private RadioButton apptweekview;

    /**
     *
     * ToggleGroup for all of the radio buttons.
     *
     */
    @FXML
    private ToggleGroup mainApptsTG;

    /**
     *
     * This is the contact column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptscontactcol;

    /**
     *
     * This is the customer ID column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptscustomeridcol;

    /**
     *
     * This is the description column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsdesccol;

    /**
     *
     * This is the end date column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsenddatecol;

    /**
     *
     * This is the end time column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsendtimecol;

    /**
     *
     * This is the appointment ID column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsidcol;

    /**
     *
     * This is the location column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptslocationcol;

    /**
     *
     * This is the start date column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsstartdatecol;

    /**
     *
     * This is the start time column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsstarttimecol;

    /**
     *
     * This is the table for the all of the appointments.
     *
     */
    @FXML
    private TableView<Appointments> mainapptstableview;

    /**
     *
     * This is the title column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptstitlecol;

    /**
     *
     * This is the type column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptstypecol;

    /**
     *
     * This is the user ID column in the main appointments tableview.
     *
     */
    @FXML
    private TableColumn mainapptsuseridcol;

    /**
     *
     * This is the Add New Appointment method.
     *
     * <p>This method gives the Add Appointment button the functionality to switch the screen to the Add Appointment form
     * when the button is clicked.</p>
     *
     * @param event This is the action for the Add Appointment button on the Main Appointments screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionAddAppt(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the Delete Appointment method.
     *
     * <p>The user is supposed to choose an appointment that they would like to delete from the main appointments
     * tableview. After an appointment is selected, the connection to the database is opened. If the button is clicked
     * and no appointment is selected, an alert is displayed that tells the user an appointment must be selected.</p>
     *
     * <p>Once an appointment is selected for deletion, an alert is displayed which asks the user to confirm if they would
     * like to delete the appointment. The user can either select "OK" or "Cancel". If the user selects "OK" the
     * appointment is deleted from the main appointments tableview and the database as well. Once the appointment is
     * deleted the user will see an alert message displayed that confirms the appointment ID and type of appointment that
     * was deleted.</p>
     *
     *
     * @param event This is the action for the Delete Appointment button on the Main Appointments screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDeleteAppt(ActionEvent event) throws IOException {
        try {
            Appointments apptSelected = mainapptstableview.getSelectionModel().getSelectedItem();
            if (apptSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select an appointment to delete! ");
                alert.showAndWait();
            } else {
                JDBC.openConnection();
                AppointmentsDAO appointmentsDAO = new ImplementAppointments();
                int appointmentsID = apptSelected.getAppointmentID();
                int appointmentCustID = apptSelected.getAppointmentCustomerID();
                String appointmentType = apptSelected.getAppointmentType();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Existing Appointment");
                alert.setContentText("Are you sure you want to delete this appointment?");
                Optional<ButtonType> choice = alert.showAndWait();

                if ((choice.isPresent() && choice.get() == ButtonType.OK)) {
                    appointmentsDAO.deleteAppointments(appointmentsID, appointmentCustID, appointmentType);

                        JDBC.openConnection();
                        mainapptstableview.setItems(appointmentsDAO.getAllAppointments());

                Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
                cancelAlert.setTitle("Appointment Deletion");
                cancelAlert.setContentText("Appointment ID: " + apptSelected.getAppointmentID() + ", Type: " +
                        apptSelected.getAppointmentType() + " has been deleted.");
                cancelAlert.showAndWait();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
        }
    }

    /**
     *
     * This is the View All Customers method.
     *
     * <p>When the user clicks on the "View All Customers" radio button, the screen will switch to the Main Customers
     * screen that displays a tableview of all the customers in the database.</p>
     *
     * @param event This is the action for the View All Customers button on the Main Appointments screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayAllCusts(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the View Reports method.
     *
     *<p>When the user clicks on the "Reports" button, the screen will switch to the Reports: Contact Schedule screen.
     * </p>
     *
     * @param event This is the action for the Reports button on the Main Appointments screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This will exit the application.
     *
     * @param event This is the action for the Logout button on the Main Appointments screen.
     *
     */
    @FXML
    void onActionLogoutMainApptScreen(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * This is the Modify Appointment method.
     *
     * <p>The user is supposed to choose an appointment that they would like to modify from the main appointments
     * tableview. After an appointment is selected, the connection to the database is opened. If the button is clicked
     * and no appointment is selected, an alert is displayed that tells the user an appointment must be selected.</p>
     *
     * @param event This is the action for the Modify Appointment button on the Main Appointments screen.
     * @throws IOException From the FXMLLoader.
     *
     */
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
                    controller.setAppointment(apptSelected);
                    stage.setTitle("Appointment Scheduling System");
                    stage.setScene(scene);
                    stage.show();
                }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     *
     * This is the "View All Appointments" method.
     *
     * <p>When clicking on the View All Appointments radio button, the connection to the database is
     * opened. Once the connection is opened, the tableview for all of the appointments is then filled with
     * a list of all appointments from the database.</p>
     *
     * @param event This is the action for the View All Appointments radio button on the Main Appointments screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionViewAllAppts(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the View Appointments by Month method.
     *
     * <p>When clicking on the "View by Month" radio button, the "viewApptsByMonth" method is called
     * and the tableview is then filled with a list of upcoming appointments that are only in the month that is captured
     * from the user's operating system local date.</p>
     *
     * @param event This is the action for the View by Month radio button on the Main Appointments screen.
     *
     */
    @FXML
    void onActionViewAptsByMonth(ActionEvent event) {
            JDBC.openConnection();
            AppointmentsDAO appointmentsDAO = new ImplementAppointments();
            mainapptstableview.setItems(appointmentsDAO.viewApptsByMonth(LocalDate.from(DatabaseLogin.getLoginLocalDateTime())));
    }

    /**
     *
     * This is the View Appointments by Week method.
     *
     * <p>When clicking on the "View by Week" radio button, the "viewApptsByWeek" method is called
     * and the tableview is then filled with a list of appointments that are only in the upcoming week.</p>
     *
     * @param event This is the action for the View by Week radio button on the Main Appointments screen.
     *
     */
    @FXML
    void onActionViewAptsByWeek(ActionEvent event) {
        JDBC.openConnection();
        AppointmentsDAO appointmentsDAO = new ImplementAppointments();
        mainapptstableview.setItems(appointmentsDAO.viewApptsByWeek(LocalDate.from(DatabaseLogin.getLoginLocalDateTime())));
    }

    /**
     *
     * This initializes the Main Appointment controller and populates the tableview with the necessary
     * data for the appointments in the database.
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *
     */
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
        mainapptscontactcol.setCellValueFactory(new PropertyValueFactory<>("appointmentContactID"));
        }
    }

