package Model;

public class Reports {

    private String appointmentMonth;

    private String appointmentType;

    private int total;

    public Reports(String appointmentMonth, String appointmentType, int total) {
        this.appointmentMonth = appointmentMonth;
        this.appointmentType = appointmentType;
        this.total = total;
    }

    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    public void setAppointmentMonth(String appointmentMonth) {
        this.appointmentMonth = appointmentMonth;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ("Report: " + appointmentMonth + " " + appointmentType + " " + Integer.toString(total));
    }
}
