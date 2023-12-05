package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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

        try {
            String sql = "SELECT * FROM appointments where Appointment_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentsID);

            ResultSet rs = ps.executeQuery();
            Appointments apptsResults = null;
            if (rs.next()) {
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
            }
            return apptsResults;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public int addNewAppointment(String appointmentTitle, String appointmentDesc,
                                 String appointmentLocation, String appointmentType,
                                 LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                 int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID) {
        int affectedRows = 0;
        try {
            String sql = "INSERT INTO appointments (Title, Description, Location," +
                    "Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDesc);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, Timestamp.valueOf(appointmentStartDateTime));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentEndDateTime));
            ps.setInt(7, appointmentsCustomerID);
            ps.setInt(8, appointmentsUserID);
            ps.setInt(9, appointmentsContactID);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return 0;
    }

    @Override
    public int modifyAppointments(int appointmentID, String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, " +
                    "Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDesc);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, Timestamp.valueOf(appointmentStartDateTime));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentEndDateTime));
            ps.setInt(7, appointmentsCustomerID);
            ps.setInt(8, appointmentsUserID);
            ps.setInt(9, appointmentsContactID);
            ps.setInt(10, appointmentID);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return affectedRows;
    }

    @Override
    public int deleteAppointments(int appointmentsID, int appointmentsCustomerID, String appointmentsType) {
        int affectedRows = 0;
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ? AND Customer_ID = ? AND Type = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentsID);
            ps.setInt(2, appointmentsCustomerID);
            ps.setString(3, appointmentsType);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return affectedRows;
    }
}
