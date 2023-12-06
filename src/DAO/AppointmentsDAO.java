package DAO;

import Model.Appointments;
import Model.Contacts;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentsDAO {

    ObservableList<Appointments> getAllAppointments();

    public Appointments getAppointments(int appointmentsID);

    public ObservableList<Appointments> getContactAppts(int appointmentContactID);

    public int addNewAppointment(String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID);

    public int modifyAppointments(int appointmentID, String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID);

    public int deleteAppointments(int appointmentsID,int appointmentsCustomerID,String appointmentType);

    public ObservableList<Appointments> findAppts(LocalDate date);

    public void nearingAppointmentsAlert(LocalDateTime loginLocalDateTime);

    public boolean getAppointmentStartTime(LocalDateTime appointmentST);

    public boolean getAppointmentEndTime(LocalDateTime appointmentET);

    public ObservableList<Appointments> viewApptsByWeek(LocalDate loginLocalDate);

    public ObservableList<Appointments> viewApptsByMonth(LocalDate loginLocalDate);

};
