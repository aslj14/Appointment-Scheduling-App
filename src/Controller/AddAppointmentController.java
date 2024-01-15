package Controller;

import DAO.*;
import Helper.JDBC;
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
import java.time.*;
import java.util.ResourceBundle;

/**
 *
 * This is the Add Appointments controller.
 *
 * <p>This controller class will supply the control and logic of the add appointment form.
 * This controller class will permit the user to add a new appointment to the database. The user
 * has to input the necessary information into the form and in turn the appointment is saved to the
 * database.</p>
 *
 * @author Ariel Johnson
 *
 */
public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * String variable for the appointment title.
     *
     */
    private String appointmentTitle;

    /**
     *
     * String variable for the appointment description.
     *
     */
    private String appointmentDesc;

    /**
     *
     * String variable for the appointment location.
     *
     */
    private String appointmentLocation;

    /**
     *
     * String variable for the appointment type.
     *
     */
    private String appointmentType;

    /**
     *
     * LocalDateTime variable for the appointment start date and start time..
     *
     */
    private LocalDateTime appointmentStartDateTime;

    /**
     *
     * LocalDate variable for the appointment start date.
     *
     */
    private LocalDate appointmentStartDate;

    /**
     *
     * LocalTime variable for the appointment start time.
     *
     */
    private LocalTime appointmentStartTime;

    /**
     *
     * LocalDateTime variable for the appointment end date and end time.
     *
     */
    private LocalDateTime appointmentEndDateTime;

    /**
     *
     * LocalDate variable for the appointment end date.
     *
     */
    private LocalDate appointmentEndDate;

    /**
     *
     * LocalTime variable for the appointment end time.
     *
     */
    private LocalTime appointmentEndTime;

    /**
     *
     * Int variable for the customer ID of the appointment.
     *
     */
    private int appointmentCustomerID;

    /**
     *
     * Int variable for the user ID of the appointment.
     *
     */
    private int appointmentUserID;

    /**
     *
     * Int variable for the contact ID of the appointment.
     *
     */
    private int appointmentContactID;

    /**
     *
     * ComboBox for the contacts.
     *
     */
    @FXML
    private ComboBox<Contacts> addapptcontact;

    /**
     *
     * ComboBox for the customers.
     *
     */
    @FXML
    private ComboBox<Customers> addapptcustid;

    /**
     *
     * Textfield for the appointment description.
     *
     */
    @FXML
    private TextField addapptdesc;

    /**
     *
     * Date picker for the appointment end date.
     *
     */
    @FXML
    private DatePicker addapptenddate;

    /**
     *
     * ComboBox for the appointment end time.
     *
     */
    @FXML
    private ComboBox<LocalTime> addapptendtime;

    /**
     *
     * Textfield for the appointment location.
     *
     */
    @FXML
    private TextField addapptlocation;

    /**
     *
     * Date picker for the appointment start date.
     *
     */
    @FXML
    private DatePicker addapptstartdate;

    /**
     *
     * ComboBox for the appointment start time.
     *
     */
    @FXML
    private ComboBox<LocalTime> addapptstarttime;

    /**
     *
     * Textfield for the appointment title.
     *
     */
    @FXML
    private TextField addappttitle;

    /**
     *
     * Textfield for the appointment type.
     *
     */
    @FXML
    private TextField addappttype;

    /**
     *
     * ComboBox for the users in the database..
     *
     */
    @FXML
    private ComboBox<Users> addapptuserid;

    /**
     *
     * This is the Add New Appointment method.
     *
     * <p>The user will input the required information into the textfields and select the required information from
     * the combo boxes and date pickers as well. The method will then check if any of the textfields are blank. If the
     * user does not input any information into the textfields, the "alertMessages" method will be called. When the
     * "alertMessages" method is called it will display an alert letting the user know which textfield is empty and
     * it will ask the user to enter the required information.</p>
     *
     * <p>If the user enters all of the required information and none of the textfields are empty, the start date and
     * time that is selected will then be checked to verify it is within the company's business hours. If the selected
     * start date and time falls within company business hours, the selected end date and end time is then checked against
     * the same criteria. If either check fails, the "alertMessages" method will be called again and will display a message
     * that lets the user know to select a different time.</p>
     *
     *<p>If the selected start and end time are both within the company's business hours, the application will then check
     * if the selected start time is before the selected end time. IF the selected start time is not before the end time,
     * the "alertMessages" method will be called again and display an alert that lets the user know to select an
     * appointment start time that is before the appointment's end time.</p>
     *
     * <p>If the appointment's selected start time is before the selected end time, the "inspectNewOverlap" method is
     * called to inspect if there may be any appointments related to the customer ID that is selected and that may coincide
     * with the new appointment that the user is trying to save. If there is any overlap between the new appointment that the
     * user wants to save and an existing appointment, the "alertMessages" method is called and an alert is displayed that
     * lets the user know that there are overlapping appointments.</p>
     *
     * <p>If the required information for the new appointment that was input by the user does not coincide with any existing
     * appointments, the "addNewAppointment" method is called, and the new appointment will then be added to the database
     * and the main appointments tableview that holds all of the appointments.</p>
     *
     * @param event This is the action for the Save button on the Add Appointment form.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionAddAppt(ActionEvent event) throws IOException {
        try {
            boolean inputError = false;
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

            if(appointmentTitle.isBlank()) {
                alertMessages(1);
                inputError = true;
            } else if(appointmentDesc.isBlank()) {
                alertMessages(2);
                inputError = true;
            } else if(appointmentLocation.isBlank()) {
                alertMessages(3);
                inputError = true;
            } else if(appointmentType.isBlank()) {
                alertMessages(4);
                inputError = true;
            }

            if(!inputError) {
                AppointmentsDAO appointmentDAO = new ImplementAppointments();
                if(appointmentDAO.getAppointmentStartTime(appointmentStartDateTime) && appointmentDAO.getAppointmentEndTime(appointmentEndDateTime)) {
                   if(appointmentStartDateTime.toLocalTime().isBefore(appointmentEndDateTime.toLocalTime())) {
                       if(!appointmentDAO.inspectNewApptOverlap(appointmentCustomerID, appointmentStartDate, appointmentEndDate,
                               appointmentStartTime, appointmentEndTime)) {
                           appointmentDAO.addNewAppointment(appointmentTitle, appointmentDesc, appointmentLocation,
                                   appointmentType, appointmentStartDateTime, appointmentEndDateTime, appointmentCustomerID,
                                   appointmentUserID, appointmentContactID);

                           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                           FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
                           Scene scene = new Scene(loader.load());
                           stage.setTitle("Appointment Scheduling System");
                           stage.setScene(scene);
                           stage.show();
                           JDBC.closeConnection();
                       } else {
                           alertMessages(7);
                       }
                   } else {
                       alertMessages(6);
                   }
                } else {
                    alertMessages(5);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

    /**
     *
     * This will load the Main Appointments screen controller and display the main screen
     * that shows the tableview of all the appointments.
     *
     * @param event This is the action for the Cancel button on the Add Appointment controller.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMainAppts(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the "alertMessages" method. This is the method that holds all of the alert dialog
     * boxes that will display if there are any errors when trying to save/add a new appointment.
     *
     * <p>LAMBDA EXPRESSION (1): When the "alertMessages" method is called, it will display the
     * appropriate error message in an alert dialog box.</p>
     * <p>MY REASONING FOR CHOOSING A LAMBDA HERE: I chose to use a lambda expression here because
     * it was a straightforward, yet very helpful way to produce individual alerts for each particular case.
     * The lambda expression also helped to use less code for the method since the number for each
     * specific case related to the "->" returned the appropriate alert.</p>
     *
     * @param alertChoice case number associated with the appropriate alert message
     *
     */
    public void alertMessages(int alertChoice) {
        switch (alertChoice) {
            case 1 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Error");
                alert.setContentText("Please input an appointment title!");
                alert.showAndWait();
            }
            case 2 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add New Appointment Error");
                alert.setContentText("Please input an appointment description!");
                alert.showAndWait();
            }
            case 3 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add New Appointment Error");
                alert.setContentText("Please input an appointment location!");
                alert.showAndWait();
            }
            case 4 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add New Appointment Error");
                alert.setContentText("Please input an appointment type!");
                alert.showAndWait();
            }
            case 5 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Appointment Error");
                alert.setContentText("The appointment time selected is outside of regular business hours. Please select a " +
                        "valid time for your appointment that is between 08:00 AM and 22:00 PM EST!");
                alert.showAndWait();
            }
            case 6 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add New Appointment Error");
                alert.setContentText("Start Date/Time of appointment must be before End Date/Time of appointment. " +
                        "Please select a valid start time/date for your appointment!");
                alert.showAndWait();
            }
            case 7 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add New Appointment Error");
                alert.setContentText("The customer has appointments that overlap. Please select \n" +
                        "another time!");
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * This initializes the Add Appointment controller.
     *
     * This method is the first method that is called when the screen/form
     * is displayed.
     *
     * <p>This method will assign the appropriate values to their respective
     * variables. These value and variables will be used as parameters for the
     * business hours method.</p>
     *
     * <p>The connection to the database is opened to getAllContacts to populate
     * the contacts combo box. The getAllCustomers method is also called to populate the
     * customer combo box. Then, the getAllUsers method is called to populate the user combo box.</p>
     *
     * <p>The combo box for the start times is populated with the business hours. The business
     * hours are converted from Eastern Standard Time to reflect the user's local time on their
     * operating system. The same procedure was done to the combo box for the end time as well.</p>
     *
     * <p>The date picker for both the start and end dates are coordinated to reflect the user's local date. The weekend
     * days have been disabled on the start and end date pickers to make sure the user does not choose a weekend day since
     * weekends are days that the company does not operate on.</p>
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            ZoneId systemZoneID = ZoneId.systemDefault();
            ZoneId businessZoneID = ZoneId.of("America/New_York");
            LocalTime appointmentStartTime = LocalTime.of(8,  0);
            int businessHours = 15;

             JDBC.openConnection();
             ContactsDAO contactsDAO = new ImplementContacts();
             CustomersDAO customersDAO = new ImplementCustomers();
             UsersDAO usersDAO = new ImplementUsers();

            addapptstartdate.setDayCellFactory(apptDate -> new DateCell() {
                        @Override
                        public void updateItem(LocalDate date, boolean none) {
                            super.updateItem(date, none);
                            setDisable(none || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
                        }
                    });

            addapptenddate.setDayCellFactory(apptDate -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean none) {
                    super.updateItem(date, none);
                    setDisable(none || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
                }
            });


             addapptcontact.setItems(contactsDAO.getAllContacts());
             addapptcontact.getSelectionModel().selectFirst();
             addapptstartdate.setValue(LocalDate.now());
             addapptstarttime.setItems(Time.businessHours(systemZoneID, businessZoneID, appointmentStartTime,
                     businessHours));
             addapptstarttime.getSelectionModel().selectFirst();
             addapptenddate.setValue(LocalDate.now());
             addapptendtime.setItems(Time.businessHours(systemZoneID, businessZoneID, LocalTime.of(9, 0),
                     businessHours));
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
