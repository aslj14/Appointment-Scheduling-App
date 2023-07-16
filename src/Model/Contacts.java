package Model;

public class Contacts {

    private int appointmentContactID;

    private String appointmentContactName;

    private String contactEmail;

    public Contacts(int appointmentContactID, String appointmentContactName) {
        this.appointmentContactID = appointmentContactID;
        this.appointmentContactName = appointmentContactName;
        this.contactEmail = contactEmail;
    }

    public int getAppointmentContactID() {
        return appointmentContactID;
    }

    public void setAppointmentContactID(int appointmentContactID) {
        this.appointmentContactID = appointmentContactID;
    }

    public String getAppointmentContactName() {
        return appointmentContactName;
    }

    public void setAppointmentContactName(String appointmentContactName) {
        this.appointmentContactName = appointmentContactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
