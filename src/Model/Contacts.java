package Model;

/**
 *
 * This Contacts class holds the necessary information for the contacts.
 *
 * This Contacts class will also model the contacts.
 *
 * @author Ariel Johnson
 *
 */
public class Contacts {

    /**
     *
     * int variable for the contact ID.
     *
     */
    private int appointmentContactID;

    /**
     *
     * String variable for the contact name.
     *
     */
    private String appointmentContactName;

    /**
     *
     * This is the constructor for the contacts.
     *
     * @param appointmentContactID This is the contact ID.
     * @param appointmentContactName this is the contact name.
     *
     */
    public Contacts(int appointmentContactID, String appointmentContactName) {
        this.appointmentContactID = appointmentContactID;
        this.appointmentContactName = appointmentContactName;
    }

    /**
     *
     * This is the getter for the ID of the contact.
     *
     * @return getAppointmentContactID This returns the ID of the contact.
     *
     */
    public int getAppointmentContactID() {
        return appointmentContactID;
    }

    /**
     *
     * This is the setter for the ID of the contact.
     *
     * @param appointmentContactID This is the ID of the contact.
     *
     */
    public void setAppointmentContactID(int appointmentContactID) {
        this.appointmentContactID = appointmentContactID;
    }

    /**
     *
     * This is the getter for the name of the contact.
     *
     * @return getAppointmentContactName This returns the name of the contact.
     *
     */
    public String getAppointmentContactName() {
        return appointmentContactName;
    }

    /**
     *
     * This is the setter for the name of the contact.
     *
     * @param appointmentContactName This is the name of the contact.
     *
     */
    public void setAppointmentContactName(String appointmentContactName) {
        this.appointmentContactName = appointmentContactName;
    }

    /**
     *
     * This method is the contacts toString method.
     *
     * This method will supply the syntax for the necessary information for the contacts.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {
        return ("[" + Integer.toString(appointmentContactID) + "] " + appointmentContactName);
    }
}
