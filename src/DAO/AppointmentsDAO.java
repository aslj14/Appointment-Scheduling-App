package DAO;

import Model.Appointments;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * This is the DAO class for the Appointments.
 * This class will function as the interface for the Implement Appointments class.
 *
 * @author Ariel Johnson
 *
 */
public interface AppointmentsDAO {

    /**
     *
     * This is the method that will get all of the appointments from the database and add the appointments to an
     * observable list.
     *
     * @return A list of all the appointments.
     *
     */
    public ObservableList<Appointments> getAllAppointments();

    /**
     *
     * This is the method that will get a particular appointment in accordance with that particular appointment's
     * ID.
     *
     * @param appointmentsID This is the particular appointment's ID.
     * @return The particular appointment's information is returned.
     *
     */
    public Appointments getAppointments(int appointmentsID);

    /**
     *
     * This is the method to get an appointment by the contact's ID.
     *
     * <p>This method reaches the database to decipher through the list of all of the appointments based on the
     * ID of the contact and then add that appointment to a list.</p>
     *
     * @param appointmentContactID The contact's ID for that particular appointment.
     * @return The list of appointments by a particular contact.
     *
     */
    public ObservableList<Appointments> getContactAppts(int appointmentContactID);

    /**
     *
     * This is the method tp get an appointment by the customer's ID.
     *
     * <p>This method reaches the database to decipher through the list of all of the appointments based on the customer's
     * ID and then add that appointment to a list.</p>
     *
     * @param appointmentCustomerID The customer's ID for that particular appointment.
     * @return The list of appointments by a particular customer.
     */
    public ObservableList<Appointments> getCustomerAppts(int appointmentCustomerID);

    /**
     *
     * This is the add appointment method for the Appointments DAO class.
     *
     * @param appointmentTitle The new appointment's title.
     * @param appointmentDesc The new appointment's description.
     * @param appointmentLocation The new appointment's location.
     * @param appointmentType The new appointment's type.
     * @param appointmentStartDateTime The new appointment's start date and time.
     * @param appointmentEndDateTime The new appointment's end date and time.
     * @param appointmentsCustomerID The new appointment's customer ID.
     * @param appointmentsUserID The new appointment's user ID.
     * @param appointmentsContactID The new appointment's contact ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int addNewAppointment(String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID);

    /**
     *
     * This is the Modify Appointment method in the Appointments DAO class.
     *
     * @param appointmentID The modified appointment's ID.
     * @param appointmentTitle The modified appointment's title.
     * @param appointmentDesc The modified appointment's description.
     * @param appointmentLocation The modified appointment's location.
     * @param appointmentType The modified appointment's type.
     * @param appointmentStartDateTime The modified appointment's start date and time.
     * @param appointmentEndDateTime The modified appointment's end date and time.
     * @param appointmentsCustomerID The modified appointment's customer ID.
     * @param appointmentsUserID The modified appointment's user ID.
     * @param appointmentsContactID The modified appointment's contact ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int modifyAppointments(int appointmentID, String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID);

    /**
     *
     * This is the Delete Appointment method in the Appointments DAO class.
     *
     * @param appointmentsID The deleted appointment's ID.
     * @param appointmentsCustomerID The deleted appointment's customer ID.
     * @param appointmentType The deleted appointment's type.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int deleteAppointments(int appointmentsID,int appointmentsCustomerID,String appointmentType);

    /**
     *
     * This is the Find Appointments method.
     *
     * @param date The local start date of the appointment.
     * @return List of appointments with that specific start date.
     *
     */
    public ObservableList<Appointments> findAppts(LocalDate date);

    /**
     *
     * This is the method that searches for the start time of the appointment.
     *
     * @param appointmentST The start time of the appointment.
     * @return True, only if the start time matches the requirements.
     *
     */
    public boolean getAppointmentStartTime(LocalDateTime appointmentST);

    /**
     *
     * This is the method that searches for the end time of the appointment,
     *
     * @param appointmentET The end time of the appointment
     * @return True, only if the end time matches the requirements.
     *
     */
    public boolean getAppointmentEndTime(LocalDateTime appointmentET);

    /**
     *
     * This is the method that searches if there are any upcoming appointments in the next 15 minutes.
     * If there are, an alert message will be displayed that tells the user that the appointment is nearing.
     * If there are no upcoming appointments an alert will be displayed that tells the user there are no upcoming
     * upcoming appointments. These alerts will be displayed after the initial login into the application.
     *
     * @param loginLocalDateTime The local date and time from the user's operating system.
     *
     */
    public void nearingAppointmentsAlert(LocalDateTime loginLocalDateTime);

    /**
     *
     * This is the view upcoming appointments by week method.
     *
     * <p>LAMBDA EXPRESSION (2): This method will check all of the appointments for a list of appointments that
     * will occur over the next 7 days. This also includes the date when the user initially logs in to the application.
     * If there is an appointment in the database that matches the requirements, the lambda expression will add the appointment
     * to a list that is filtered.</p>
     *
     * <p>MY REASONING FOR CHOOSING A LAMBDA EXPRESSION HERE: Utilizing a lambda expression here was more straightforward
     * when compared to adding each appointment to a filtered list individually.</p>
     *
     * @param loginLocalDate The local date from the user's operating system.
     * @return The filtered list of appointments for the upcoming week.
     *
     */
    public ObservableList<Appointments> viewApptsByWeek(LocalDate loginLocalDate);

    /**
     *
     * This is the method that lets the user view the upcoming appointments for the month.
     *
     * <p>LAMBDA EXPRESSION (2): This method will check all of the appointments for a list of appointments that are upcoming
     * in the present month. This also includes the date when the user initially logs in to the application.
     * If there is an appointment in the database that matches the requirements, the lambda expression will add the appointment
     * to a list that is filtered.</p>
     *
     * <p>MY REASONING FOR CHOOSING A LAMBDA EXPRESSION HERE: Utilizing a lambda expression here was more straightforward
     * when compared to adding each appointment to a filtered list individually.</p>
     *
     * @param loginLocalDate The local date from the user's operating system.
     * @return The filtered list of appointments that are upcoming in the current month.
     *
     */
    public ObservableList<Appointments> viewApptsByMonth(LocalDate loginLocalDate);

    /**
     *
     * This is the method that will inspect the new appointments for overlap with any existing appointments.
     *
     * @param appointmentCustomerID The appointment's customer ID.
     * @param startDateSelected The start date that was selected for the new appointment.
     * @param endDateSelected The end date that was selected for the new appointment.
     * @param startTimeSelected The start time that was selected for the new appointment.
     * @param endTimeSelected The end time that was selected for the new appointment.
     *
     * @return True, if there are any existing appointments that overlap with the new appointment trying to be added.
     *
     */
    public boolean inspectNewApptOverlap(int appointmentCustomerID, LocalDate startDateSelected, LocalDate endDateSelected,
                                         LocalTime startTimeSelected, LocalTime endTimeSelected);

    /**
     *
     * This is the method that will inspect the modified appointments and test if there will be any overlap with an existing
     * appointment.
     *
     * @param appointmentCustomerID The appointment's customer ID.
     * @param apppointmentID The appointment's ID.
     * @param startDateSelected The start date that was selected for the new appointment.
     * @param endDateSelected The end date that was selected for the new appointment.
     * @param startTimeSelected The start time that was selected for the new appointment.
     * @param endTimeSelected The end time that was selected for the new appointment.
     * @return True, if there are any existing appointments that overlap with the modified appointment trying to be added.
     */
    public boolean inspectModifiedApptOverlap(int appointmentCustomerID, int apppointmentID, LocalDate startDateSelected,
                                              LocalDate endDateSelected, LocalTime startTimeSelected, LocalTime endTimeSelected);

};
