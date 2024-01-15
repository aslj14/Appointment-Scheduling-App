package Model;

/**
 *
 * This Reports class holds the necessary information for the reports.
 *
 * This Appointments class will also model the reports needed for the application.
 *
 * @author Ariel Johnson
 *
 */
public class Reports {

    /**
     *
     * String variable for the appointment month.
     *
     */
    private String appointmentMonth;

    /**
     *
     * String variable for the appointment type.
     *
     */
    private String appointmentType;

    /**
     *
     * int variable for the total number of appointments.
     *
     */
    private int total;

    /**
     * This is the constructor for the reports.
     *
     * @param appointmentMonth This is the appointment month.
     * @param appointmentType This is the appointment type.
     * @param total This is the total number of appointments.
     */
    public Reports(String appointmentMonth, String appointmentType, int total) {
        this.appointmentMonth = appointmentMonth;
        this.appointmentType = appointmentType;
        this.total = total;
    }

    /**
     *
     * This is the getter for the month of the appointment for the report.
     *
     * @return getAppointmentMonth This returns the month of the appointment.
     *
     */
    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    /**
     *
     * This is the setter for the month of the appointment for the report.
     *
     * @param appointmentMonth This is the month of the appointment.
     *
     */
    public void setAppointmentMonth(String appointmentMonth) {
        this.appointmentMonth = appointmentMonth;
    }

    /**
     *
     * This is the getter for the type of the appointment for the report.
     *
     * @return getAppointmentType This returns the type of the appointment.
     *
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     *
     * This is the setter for the type of the appointment for the report.
     *
     * @param appointmentType This is the type of the appointment.
     *
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     *
     * This is the getter for the total of the appointments by month and type for the report.
     *
     * @return getTotal This returns the total of the appointments.
     *
     */
    public int getTotal() {
        return total;
    }

    /**
     *
     * This is the setter for the total of the appointments by month and type for the report..
     *
     * @param total This is the type of the appointment.
     *
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     *
     * This method is the month and type reports toString method.
     *
     * This method will supply the syntax for the necessary information for the reports.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {
        return ("Report: " + appointmentMonth + " " + appointmentType + " " + Integer.toString(total));
    }
}
