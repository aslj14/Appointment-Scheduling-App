package DAO;

import Model.Appointments;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static Helper.JDBC.connection;
public class ImplementAppointments implements AppointmentsDAO {

    ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    @Override
    public ObservableList<Appointments> getAllAppointments() {
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStartDateTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDate appointmentsStartDate = appointmentStartDateTime.toLocalDate();
                LocalTime appointmentStartTime = appointmentStartDateTime.toLocalTime();
                LocalDateTime appointmentEndDateTime = rs.getTimestamp("End").toLocalDateTime();
                LocalDate appointmentEndDate = appointmentEndDateTime.toLocalDate();
                LocalTime appointmentEndTime = appointmentEndDateTime.toLocalTime();
                int appointmentsCustomerID = rs.getInt("Customer_ID");
                int appointmentsUserID = rs.getInt("User_ID");
                int appointmentsContactID = rs.getInt("Contact_ID");
                Appointments appointments = new Appointments(appointmentID, appointmentTitle,appointmentDesc,
                                            appointmentLocation, appointmentType, appointmentStartDateTime,
                                            appointmentsStartDate, appointmentStartTime, appointmentEndDateTime,
                                            appointmentEndDate, appointmentEndTime, appointmentsCustomerID,
                                            appointmentsUserID, appointmentsContactID);
                allAppointments.add(appointments);
            }
            return allAppointments;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public Appointments getAppointments(int appointmentsID) {
        return null;
    }

    @Override
    public int modifyAppointments(int appointmentsID, String appointmentTitle, String appointmentDesc, String appointmentLocation, String appointmentType, LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime, int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID) {
        return 0;
    }

    @Override
    public int deleteAppointments(int appointmentsID, int appointmentsCustomerID, String appointmentType) {
        return 0;
    }

    @Override
    public int addNewAppointment(int appointmentsID, String appointmentTitle, String appointmentDesc, String appointmentLocation, String appointmentType, LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime, int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID) {
        return 0;
    }
}
