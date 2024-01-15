package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * This Appointments class holds the necessary information for the appointments.
 *
 * This Appointments class will also model the customer appointments.
 *
 * @author Ariel Johnson
 *
 */
public class Appointments {

    /**
     *
     * int variable for the appointment ID.
     *
     */
    private int appointmentID;

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
     * The appointment's local start date and start time.
     *
     */
    private LocalDateTime appointmentStartDateTime;

    /**
     *
     * The appointment's local start date.
     *
     */
    private LocalDate appointmentStartDate;

    /**
     *
     * The appointment's local start time.
     *
     */
    private LocalTime appointmentStartTime;

    /**
     *
     * The appointment's local end date and end time.
     *
     */
    private LocalDateTime appointmentEndDateTime;

    /**
     *
     * The appointment's local end date.
     *
     */
    private LocalDate appointmentEndDate;

    /**
     *
     * The appointment's local end time.
     *
     */
    private LocalTime appointmentEndTime;

    /**
     *
     * Integer variable for the customer ID associated with the appointment.
     *
     */
    private int appointmentCustomerID;

    /**
     *
     * Integer variable for the user ID associated with the appointment.
     *
     */
    private int appointmentUserID;

    /**
     *
     * Integer variable for the contact ID associated with the appointment.
     *
     */
    private int appointmentContactID;

    /**
     *
     * This is the constructor for the appointments.
     *
     * @param appointmentID This is the ID for the appointment.
     * @param appointmentTitle This is the title for the appointment.
     * @param appointmentDesc This is the description for the appointment.
     * @param appointmentLocation This is the location for the appointment.
     * @param appointmentType This is the type for the appointment.
     * @param appointmentStartDateTime This is the start date and start time for the appointment.
     * @param appointmentStartDate This is the start date for the appointment.
     * @param appointmentStartTime This is the start time for the appointment.
     * @param appointmentEndDateTime This is the end date and start time for the appointment.
     * @param appointmentEndDate This is the end date for the appointment.
     * @param appointmentEndTime This is the end time for the appointment.
     * @param appointmentCustomerID This is the customer ID for the appointment.
     * @param appointmentUserID This is the user ID for the appointment.
     * @param appointmentContactID This is the contact ID for the appointment.
     *
     */

    public Appointments(int appointmentID, String appointmentTitle, String appointmentDesc, String appointmentLocation,
                        String appointmentType, LocalDateTime appointmentStartDateTime, LocalDate appointmentStartDate,
                        LocalTime appointmentStartTime, LocalDateTime appointmentEndDateTime,
                        LocalDate appointmentEndDate, LocalTime appointmentEndTime, int appointmentCustomerID,
                        int appointmentUserID, int appointmentContactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDesc = appointmentDesc;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStartDateTime = appointmentStartDateTime;
        this.appointmentStartDate = appointmentStartDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndDateTime = appointmentEndDateTime;
        this.appointmentEndDate = appointmentEndDate;
        this.appointmentEndTime = appointmentEndTime;
        this.appointmentCustomerID = appointmentCustomerID;
        this.appointmentUserID = appointmentUserID;
        this.appointmentContactID = appointmentContactID;
    }

    /**
     *
     * This is the getter for the ID of the appointment.
     *
     * @return getAppointmentID This returns the ID of the appointment.
     *
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     *
     * This is the setter for the ID of the appointment.
     *
     * @param appointmentID This is the ID of the appointment.
     *
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     *
     * This is the getter for the title of the appointment.
     *
     * @return getAppointmentTitle This returns the title of the appointment.
     *
     */
    public String getAppointmentTitle() {

        return appointmentTitle;
    }

    /**
     *
     * This is the setter for the title of the appointment.
     *
     * @param appointmentTitle This is the title of the appointment.
     *
     */
    public void setAppointmentTitle(String appointmentTitle) {

        this.appointmentTitle = appointmentTitle;
    }

    /**
     *
     * This is the getter for the description of the appointment.
     *
     * @return getAppointmentDesc This returns the description of the appointment.
     *
     */
    public String getAppointmentDesc() {

        return appointmentDesc;
    }

    /**
     *
     * This is the setter for the description of the appointment.
     *
     * @param appointmentDesc This is the description of the appointment.
     *
     */
    public void setAppointmentDesc(String appointmentDesc) {

        this.appointmentDesc = appointmentDesc;
    }

    /**
     *
     * This is the getter for the location of the appointment.
     *
     * @return getAppointmentLocation This returns the location of the appointment.
     *
     */
    public String getAppointmentLocation() {

        return appointmentLocation;
    }

    /**
     *
     * This is the setter for the location of the appointment.
     *
     * @param appointmentLocation This is the location of the appointment.
     *
     */
    public void setAppointmentLocation(String appointmentLocation) {

        this.appointmentLocation = appointmentLocation;
    }

    /**
     *
     * This is the getter for the type of the appointment.
     *
     * @return getAppointmentType This returns the type of the appointment.
     *
     */
    public String getAppointmentType() {

        return appointmentType;
    }

    /**
     *
     * This is the setter for the type of the appointment.
     *
     * @param appointmentType This is the type of the appointment.
     *
     */
    public void setAppointmentType(String appointmentType) {

        this.appointmentType = appointmentType;
    }

    /**
     *
     * This is the getter for the start date and start time of the appointment.
     *
     * @return getAppointmentStartDateTime This returns the start date and start time of the appointment.
     *
     */
    public LocalDateTime getAppointmentStartDateTime() {

        return appointmentStartDateTime;
    }

    /**
     *
     * This is the setter for the start date and start time of the appointment.
     *
     * @param appointmentStartDateTime This is the start date and start time of the appointment.
     *
     */
    public void setAppointmentStartDateTime(LocalDateTime appointmentStartDateTime) {
        this.appointmentStartDateTime = appointmentStartDateTime;
    }

    /**
     *
     * This is the getter for the start date of the appointment.
     *
     * @return getAppointmentStartDate This returns the start date and start time of the appointment.
     *
     */
    public LocalDate getAppointmentStartDate() {
        return appointmentStartDate;
    }

    /**
     *
     * This is the setter for the start date of the appointment.
     *
     * @param appointmentStartDate This is the start date and start time of the appointment.
     *
     */
    public void setAppointmentStartDate(LocalDate appointmentStartDate) {
        this.appointmentStartDate = appointmentStartDate;
    }

    /**
     *
     * This is the getter for the start time of the appointment.
     *
     * @return getAppointmentStartDate This returns the start time of the appointment.
     *
     */
    public LocalTime getAppointmentStartTime() {

        return appointmentStartTime;
    }

    /**
     *
     * This is the setter for the start time of the appointment.
     *
     * @param appointmentStartTime This is the start time of the appointment.
     *
     */
    public void setAppointmentStartTime(LocalTime appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    /**
     *
     * This is the getter for the end date and end time of the appointment.
     *
     * @return getAppointmentEndDateTime This returns the end date and end time of the appointment.
     *
     */
    public LocalDateTime getAppointmentEndDateTime() {

        return appointmentEndDateTime;
    }

    /**
     *
     * This is the setter for the end date and end time of the appointment.
     *
     * @param appointmentEndDateTime This is the end date and start time of the appointment.
     *
     */
    public void setAppointmentEndDateTime(LocalDateTime appointmentEndDateTime) {
        this.appointmentEndDateTime = appointmentEndDateTime;
    }

    /**
     *
     * This is the getter for the end date of the appointment.
     *
     * @return getAppointmentEndDate This returns the end date of the appointment.
     *
     */
    public LocalDate getAppointmentEndDate() {

        return appointmentEndDate;
    }

    /**
     *
     * This is the setter for the end date of the appointment.
     *
     * @param appointmentEndDate This is the end date of the appointment.
     *
     */
    public void setAppointmentEndDate(LocalDate appointmentEndDate) {

        this.appointmentEndDate = appointmentEndDate;
    }

    /**
     *
     * This is the getter for the end time of the appointment.
     *
     * @return getAppointmentEndTime This returns the end time of the appointment.
     *
     */
    public LocalTime getAppointmentEndTime() {

        return appointmentEndTime;
    }

    /**
     *
     * This is the setter for the end time of the appointment.
     *
     * @param appointmentEndTime This is the end time of the appointment.
     *
     */
    public void setAppointmentEndTime(LocalTime appointmentEndTime) {

        this.appointmentEndTime = appointmentEndTime;
    }

    /**
     *
     * This is the getter for the customer ID of the appointment.
     *
     * @return getAppointmentCustomerID This returns the customer ID of the appointment.
     *
     */
    public int getAppointmentCustomerID() {

        return appointmentCustomerID;
    }

    /**
     *
     * This is the setter for the customer ID of the appointment.
     *
     * @param appointmentCustomerID This is the customer ID of the appointment.
     *
     */
    public void setAppointmentCustomerID(int appointmentCustomerID) {
        this.appointmentCustomerID = appointmentCustomerID;
    }

    /**
     *
     * This is the getter for the user ID of the appointment.
     *
     * @return getAppointmentUserID This returns the user ID of the appointment.
     *
     */
    public int getAppointmentUserID() {

        return appointmentUserID;
    }

    /**
     *
     * This is the setter for the user ID of the appointment.
     *
     * @param appointmentUserID This is the user ID of the appointment.
     *
     */
    public void setAppointmentUserID(int appointmentUserID) {

        this.appointmentUserID = appointmentCustomerID;
    }

    /**
     *
     * This is the getter for the contact ID of the appointment.
     *
     * @return getAppointmentContactID This returns the contact ID of the appointment.
     *
     */
    public int getAppointmentContactID() {

        return appointmentContactID;
    }

    /**
     *
     * This is the setter for the contact ID of the appointment.
     *
     * @param appointmentContactID This is the contact ID of the appointment.
     *
     */
    public void setAppointmentContactID(int appointmentContactID) {

        this.appointmentContactID = appointmentContactID;
    }

    /**
     *
     * This method is the appointment toString method.
     *
     * This method will supply the syntax for the necessary information for the appointments.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {

        return ("Appointment: [" + Integer.toString(appointmentID) + "] | Customer: [" + Integer.toString(appointmentCustomerID) +
                "] " + "| Contact: [" + Integer.toString(appointmentContactID) + "] | Type: " + appointmentType +
                "| Start: " + appointmentStartDateTime + " | End: " + appointmentEndDateTime);
    }
}
