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
import java.time.*;
import java.util.ResourceBundle;

/**
 *
 * This is the Modify Appointments controller.
 *
 * <p>This controller class will supply the control and logic of the modify appointment form.
 * This controller class will permit the user to modify the details of an existing appointment that the user selects.
 * The user can modify the information that is already in the textfields and combo boxes and then save that information
 * and modify the existing appointments.</p>
 *
 * @author Ariel Johnson
 *
 */
public class ModifyAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the selected appointment that is being modified.
     *
     */
    Appointments apptSelected = null;

    /**
     *
     * ComboBox for the modified appointment contacts.
     *
     */
    @FXML
    private ComboBox<Contacts> modapptcontact;

    /**
     *
     * ComboBox for the modified appointment customers.
     *
     */
    @FXML
    private ComboBox<Customers> modapptcustid;

    /**
     *
     * Textfield for the modified appointment description.
     *
     */
    @FXML
    private TextField modapptdesc;

    /**
     *
     * DatePicker for the modified appointment end date.
     *
     */
    @FXML
    private DatePicker modapptenddate;

    /**
     *
     * ComboBox for the modified appointment end time.
     *
     */
    @FXML
    private ComboBox<LocalTime> modapptendtime;

    /**
     *
     * Textfield for the modified appointment ID.
     *
     */
    @FXML
    private TextField modapptid;

    /**
     *
     * Textfield for the modified appointment location.
     *
     */
    @FXML
    private TextField modapptlocation;

    /**
     *
     * DatePicker for the modified appointment start date.
     *
     */
    @FXML
    private DatePicker modapptstartdate;

    /**
     *
     * ComboBox for the modified appointment start time.
     *
     */
    @FXML
    private ComboBox<LocalTime> modapptstarttime;

    /**
     *
     * Textfield for the modified appointment title.
     *
     */
    @FXML
    private TextField modappttitle;

    /**
     *
     * Textfield for the modified appointment type.
     *
     */
    @FXML
    private TextField modappttype;

    /**
     *
     * ComboBox for the modified appointment users.
     *
     */
    @FXML
    private ComboBox<Users> modapptuserid;

    /**
     *
     * Int variable for the modified appointment ID.
     *
     */
    private int appointmentID;

    /**
     *
     * String variable for the modified appointment title.
     *
     */
    private String appointmentTitle;

    /**
     *
     * String variable for the modified appointment description.
     *
     */
    private String appointmentDesc;

    /**
     *
     * String variable for the modified appointment location.
     *
     */
    private String appointmentLocation;

    /**
     *
     * String variable for the modified appointment type.
     *
     */
    private String appointmentType;

    /**
     *
     * LocalDateTime variable for the modified appointment start date and start time..
     *
     */
    private LocalDateTime appointmentStartDateTime;

    /**
     *
     * LocalDate variable for the modified appointment start date.
     *
     */
    private LocalDate appointmentStartDate;

    /**
     *
     * LocalTime variable for the modified appointment start time.
     *
     */
    private LocalTime appointmentStartTime;

    /**
     *
     * LocalDateTime variable for the modified appointment end date and end time.
     *
     */
    private LocalDateTime appointmentEndDateTime;

    /**
     *
     * LocalDate variable for the modified appointment end date.
     *
     */
    private LocalDate appointmentEndDate;

    /**
     *
     * LocalTime variable for the modified appointment end time.
     *
     */
    private LocalTime appointmentEndTime;

    /**
     *
     * Int variable for the customer ID of the modified appointment.
     *
     */
    private int appointmentCustomerID;

    /**
     *
     * Int variable for the user ID of the modified appointment.
     *
     */
    private int appointmentUserID;

    /**
     *
     * Int variable for the contact ID of the modified appointment.
     *
     */
    private int appointmentContactID;

    /**
     *
     * This is the "setAppointment" method.
     *
     * <p>This method will populate the Modify Appointment form's combo boxes, date pickers, and textfields with the
     * information from the existing appointment that is selected.</p>
     *
     * @param theAppt The appointment that is selected.
     *
     */
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

    /**
     *
     * This will load the Main Appointments screen controller and display the main screen
     * that shows the tableview of all the appointments.
     *
     * @param event This is the action for the Cancel button on the Modify Appointment controller.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMainAppt(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the Modify Appointment method.
     *
     * <p>The user will select an existing appointment to modify. The user will then modify the information in the
     * textfields and modify the information in the combo boxes and date pickers as well if they wish. The method will
     * then check if any of the textfields are blank. If the user does not have any information into the textfields,
     * the "alertMessages" method will be called. When the "alertMessages" method is called it will display an alert
     * letting the user know which textfield is empty and it will ask the user to enter the required information.</p>
     *
     * <p>If the user has all of the required information and none of the textfields are empty, the start date and
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
     * @param event This is the action for the Save button on the Modify Appointment form.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionModAppt(ActionEvent event) throws IOException {
        try {
            boolean inputError = false;
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
                            appointmentDAO.modifyAppointments(appointmentID, appointmentTitle, appointmentDesc,
                                    appointmentLocation, appointmentType, appointmentStartDateTime, appointmentEndDateTime,
                                    appointmentCustomerID, appointmentUserID, appointmentContactID);

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
     * This is the "alertMessages" method. This is the method that holds all of the alert dialog
     * boxes that will display if there are any errors when trying to save/add a new appointment.
     *
     * <p>LAMBDA EXPRESSION (2): When the "alertMessages" method is called, it will display the
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
                alert.setTitle("Appointment Error");
                alert.setContentText("Please input an appointment description!");
                alert.showAndWait();
            }
            case 3 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Appointment Error");
                alert.setContentText("Please input an appointment location!");
                alert.showAndWait();
            }
            case 4 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Appointment Error");
                alert.setContentText("Please input an appointment type!");
                alert.showAndWait();
            }
            case 5 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing  Appointment Error");
                alert.setContentText("The appointment time selected is outside of regular business hours. Please select a " +
                        "valid time for your appointment that is between 08:00 AM and 22:00 PM EST!");
                alert.showAndWait();
            }
            case 6 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Appointment Error");
                alert.setContentText("Start Date/Time of appointment must be before End Date/Time of appointment. " +
                        "Please select a valid start time/date for your appointment!");
                alert.showAndWait();
            }
            case 7 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Appointment Error");
                alert.setContentText("The customer has appointments that overlap. Please select \n" +
                        "another time!");
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * This initializes the Modify Appointment controller.
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
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ZoneId systemZoneID = ZoneId.systemDefault();
            ZoneId businessZoneID = ZoneId.of("America/New_York");
            LocalTime appointmentStartTime = LocalTime.of(8, 0);
            int businessHours = 15;

            JDBC.openConnection();
            ContactsDAO contactsDAO = new ImplementContacts();
            CustomersDAO customersDAO = new ImplementCustomers();
            UsersDAO usersDAO = new ImplementUsers();

            modapptstartdate.setDayCellFactory(apptDate -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean none) {
                    super.updateItem(date, none);
                    setDisable(none || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
                }
            });

            modapptenddate.setDayCellFactory(apptDate -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean none) {
                    super.updateItem(date, none);
                    setDisable(none || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
                }
            });

            modapptcontact.setItems(contactsDAO.getAllContacts());
            modapptcustid.setItems(customersDAO.getAllCustomers());
            modapptuserid.setItems(usersDAO.getAllUsers());
            modapptstarttime.setItems(Time.businessHours(systemZoneID, businessZoneID, appointmentStartTime, businessHours));
            modapptendtime.setItems(Time.businessHours(systemZoneID, businessZoneID, LocalTime.of(9, 0),
                    businessHours));
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }
}
