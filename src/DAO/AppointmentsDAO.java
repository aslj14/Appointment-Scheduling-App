package DAO;

import Model.Appointments;
import Model.Contacts;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public interface AppointmentsDAO {

    ObservableList<Appointments> getAllAppointments();

    public Appointments getAppointments(int appointmentsID);

    public int addNewAppointment (String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID);

    public int modifyAppointments(int appointmentsID, String appointmentTitle, String appointmentDesc,
                                  String appointmentLocation, String appointmentType,
                                  LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                                  int appointmentsCustomerID, int appointmentsUserID, int appointmentsContactID);

    public int deleteAppointments(int appointmentsID,int appointmentsCustomerID,String appointmentType);

};
