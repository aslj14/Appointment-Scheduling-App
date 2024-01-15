package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.*;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the Appointments DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementAppointments implements AppointmentsDAO {

    /**
     *
     * The ObservableList of all of the appointments in the database.
     *
     */
    ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     *
     * Boolean variable and object used for the findAppts method.
     *
     */
    public boolean foundAppointment;

    /**
     *
     * This method is the method that gets all of the appointments from the database and then adds those appointments to
     * a list.
     *
     * @return This method returns the list of all appointments.
     *
     */
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
                Appointments appointments = new Appointments(appointmentID, appointmentTitle, appointmentDesc,
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

    /**
     *
     * This is the method that will get a particular appointment based on the appointment's ID.
     *
     * @param appointmentsID This is the particular appointment's ID.
     * @return This method returns the particular appointment.
     *
     */
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
                Appointments appointments = new Appointments(appointmentID, appointmentTitle, appointmentDesc,
                        appointmentLocation, appointmentType, appointmentStartDateTime,
                        appointmentsStartDate, appointmentStartTime, appointmentEndDateTime,
                        appointmentEndDate, appointmentEndTime, appointmentsCustomerID, appointmentsUserID, appointmentsContactID);
            }
            return apptsResults;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * This method gets a particular appointment based on the contact associated with that appointment.
     *
     * @param appointmentContactID The contact's ID for that particular appointment.
     * @return This method returns the list of appointments by a contact.
     *
     */
    @Override
    public ObservableList<Appointments> getContactAppts(int appointmentContactID) {
        ObservableList<Appointments> contactAppts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentContactID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                appointmentContactID = rs.getInt("Contact_ID");
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
                Appointments appointments = new Appointments(appointmentID,appointmentTitle, appointmentDesc,
                                            appointmentLocation, appointmentType, appointmentStartDateTime, appointmentsStartDate,
                                            appointmentStartTime, appointmentEndDateTime, appointmentEndDate, appointmentEndTime,
                                            appointmentsCustomerID, appointmentsUserID, appointmentContactID);
                contactAppts.add(appointments);
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
        return contactAppts;
    }

    /**
     *
     * This is the method that will get an appointment based on the customer ID associated with that appointment.
     *
     * @param appointmentCustomerID The customer's ID for that particular appointment.
     * @return This method returns the list of appointments by a customer.
     *
     */
    @Override
    public ObservableList<Appointments> getCustomerAppts(int appointmentCustomerID) {
        ObservableList<Appointments> apptsByCustomer = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentCustomerID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                int appointmentContactID = rs.getInt("Contact_ID");
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
                appointmentCustomerID = rs.getInt("Customer_ID");
                int appointmentsUserID = rs.getInt("User_ID");
                Appointments appointments = new Appointments(appointmentID,appointmentTitle, appointmentDesc,
                        appointmentLocation, appointmentType, appointmentStartDateTime, appointmentsStartDate,
                        appointmentStartTime, appointmentEndDateTime, appointmentEndDate, appointmentEndTime,
                        appointmentCustomerID, appointmentsUserID, appointmentContactID);
                apptsByCustomer.add(appointments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apptsByCustomer;
    }

    /**
     *
     * This is the Add New Appointment method.
     *
     * <p>This method opens the connection to the database and then adds the new appointment and its information to the
     * database.</p>
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
     * @return Returns the rows in the database that were affected.
     *
     */
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

    /**
     *
     * This is the Modify Existing Appointment method.
     *
     * <p>This method will allow the user to select an existing appointment and then modify/edit the information for that
     * appointment and then save it to the database.</p>
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
     * @return Returns the rows in the database that were affected.
     */
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

    /**
     *
     * This is the Delete Existing Appointment method.
     * <p>This method permits the user to select an existing appointment and then delete the appointment and all
     * information associated with the appointment, from the database.</p>
     *
     * @param appointmentsID The deleted appointment's ID.
     * @param appointmentsCustomerID The deleted appointment's customer ID.
     * @param appointmentsType The deleted appointment's type.
     * @return Returns the rows in the database that were affected.
     *
     */
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

    /**
     *
     * This is the Find Appointment method.
     * <p>This particular method will check the list of all appointments and look for a filtered list of appintments
     * that have a particular start date.</p>
     *
     * @param dateSelected The local start date of the appointment.
     * @return The list of filtered appointments that have the start date.
     *
     */
    @Override
    public ObservableList<Appointments> findAppts(LocalDate dateSelected) {
        ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();
        foundAppointment = false;

        for (Appointments appointments : allAppointments) {
            if (appointments.getAppointmentStartDate().equals(dateSelected)) {
                filteredAppointments.add(appointments);
            }
        }
        if (filteredAppointments.isEmpty()) {
            return allAppointments;
        }
        foundAppointment = true;
        return filteredAppointments;
    }

    /**
     *
     * This is the Nearing Appointments Alert method.
     *
     *<p>This method will display an alert message after the initial login. There will be an alert displayed
     * if there are no upcoming appointments in the next 15 minutes. If there are appointments that are scheduled
     * to occur in the next 15 minutes, an alert will be displayed in this scenario as well.</p>
     *
     * @param localDT The local date and time from the user's operating system.
     *
     */
    @Override
    public void nearingAppointmentsAlert(LocalDateTime localDT) {
        try {
            ObservableList<Appointments> nearingAppointments = FXCollections.observableArrayList();
            ObservableList<Appointments> allTheAppointments = FXCollections.observableArrayList();
            AppointmentsDAO appointmentsDAO = new ImplementAppointments();
            allTheAppointments = appointmentsDAO.getAllAppointments();

            ZoneId zoneID = ZoneId.systemDefault();
            ZonedDateTime loginZoneDT = localDT.atZone(zoneID);
            LocalDateTime appointmentStartDT = localDT.plusMinutes(15);

            for (Appointments appointment : allTheAppointments) {
                ZonedDateTime zonedAppointment = ZonedDateTime.from(appointment.getAppointmentStartDateTime().atZone(zoneID));
                if (zonedAppointment.isAfter(loginZoneDT) && zonedAppointment.isBefore(appointmentStartDT.atZone(zoneID))) {
                    nearingAppointments.add(appointment);
                    System.out.println("There is an upcoming appointment at: " + appointment);
                }
            }
            if (nearingAppointments.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointments");
                alert.setContentText("There are no upcoming appointments in the next 15 minutes!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upcoming Appointments");
                alert.setHeaderText("Appointments beginning in the next 15 minutes: ");
                String textForAlert = "";

                for (Appointments nearAppointment : nearingAppointments) {
                    textForAlert = ("Appointment ID: " + nearAppointment.getAppointmentID() + " at " + nearAppointment.getAppointmentStartTime() +
                            " on " + nearAppointment.getAppointmentStartDate() + "\n") + textForAlert;
                }
                alert.setContentText(textForAlert);
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

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
     * @param loginByDate The local date from the user's operating system when they login.
     * @return The filtered list of appointments for the upcoming week.
     *
     */
        @Override
        public FilteredList<Appointments> viewApptsByWeek(LocalDate loginByDate) {
        try {
            ObservableList<Appointments> allTheAppointments = FXCollections.observableArrayList();
            allTheAppointments = getAllAppointments();
            FilteredList<Appointments> filteredAppointments = new FilteredList<>(allTheAppointments);

            filteredAppointments.setPredicate(appointment -> {
                LocalDate dateOfAppt = appointment.getAppointmentStartDate();

                return ((dateOfAppt.isEqual(loginByDate) || dateOfAppt.isAfter(loginByDate)) &&
                        dateOfAppt.isBefore(loginByDate.plusDays(7)));
            });
            return filteredAppointments;
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
        return null;
    }

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
     * @param loginByDate The local date from the user's operating system when they log in.
     * @return The filtered list of appointments that are upcoming in the current month.
     *
     */
    @Override
    public FilteredList<Appointments> viewApptsByMonth(LocalDate loginByDate) {
        ObservableList<Appointments> allTheAppointments = FXCollections.observableArrayList();
        allTheAppointments = getAllAppointments();
        FilteredList<Appointments> filteredAppointments = new FilteredList<>(allTheAppointments);

        filteredAppointments.setPredicate(appointment -> {
            LocalDate dateOfAppt = appointment.getAppointmentStartDate();

            return (dateOfAppt.isEqual(loginByDate) || dateOfAppt.isAfter(loginByDate)) &&
                    dateOfAppt.getMonth().equals(loginByDate.getMonth());
        });
        return filteredAppointments;
    }

    /**
     *
     * This is the method that searches for the start time of the appointment.
     *
     * @param appointmentST The start time of the appointment.
     * @return True, only if the start time matches the requirements.
     *
     */
    @Override
    public boolean getAppointmentStartTime(LocalDateTime appointmentST) {
        ZonedDateTime zoneForAppt = appointmentST.atZone(ZoneId.systemDefault());
        zoneForAppt = zoneForAppt.withZoneSameInstant(ZoneId.of("US/Eastern"));
        appointmentST = zoneForAppt.toLocalDateTime();

        LocalTime businessOpenTime = LocalTime.of(8, 0);
        LocalTime businessCloseTime = LocalTime.of(22, 0);
        return ((appointmentST.toLocalTime().isAfter(businessOpenTime) || appointmentST.toLocalTime().equals(businessOpenTime))
                && (appointmentST.toLocalTime().isBefore(businessCloseTime)));
    }

    /**
     *
     * This is the method that searches for the end time of the appointment,
     *
     * @param appointmentET The end time of the appointment
     * @return True, only if the end time matches the requirements.
     *
     */
    @Override
    public boolean getAppointmentEndTime(LocalDateTime appointmentET) {
        ZonedDateTime zoneForAppt = appointmentET.atZone(ZoneId.systemDefault());
        zoneForAppt = zoneForAppt.withZoneSameInstant(ZoneId.of("US/Eastern"));
        appointmentET = zoneForAppt.toLocalDateTime();

        LocalTime businessOpenTime = LocalTime.of(8, 0);
        LocalTime businessCloseTime = LocalTime.of(22, 0);
        return ((appointmentET.toLocalTime().isAfter(businessOpenTime)) &&
                (appointmentET.toLocalTime().isBefore(businessCloseTime) || appointmentET.toLocalTime().equals(businessCloseTime)));
    }

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
    @Override
    public boolean inspectNewApptOverlap(int appointmentCustomerID, LocalDate startDateSelected, LocalDate endDateSelected,
                                         LocalTime startTimeSelected, LocalTime endTimeSelected) {
        AppointmentsDAO appointmentDAO = new ImplementAppointments();
        ObservableList<Appointments> customerAppointments = appointmentDAO.getCustomerAppts(appointmentCustomerID);
        boolean anyOverlap = false;

        for (Appointments appointment : customerAppointments) {
            if ((appointment.getAppointmentStartDate().isEqual(startDateSelected)) || (appointment.getAppointmentEndDate().isEqual(endDateSelected))) {
                if (appointment.getAppointmentStartTime().equals(startTimeSelected)) {
                    anyOverlap = true;
                    break;
                } else if(appointment.getAppointmentStartTime().isAfter(startTimeSelected) && appointment.getAppointmentStartTime().isBefore(endTimeSelected)) {
                    anyOverlap = true;
                    break;
                } else if(startTimeSelected.isBefore(appointment.getAppointmentStartTime()) && (endTimeSelected.isAfter(appointment.getAppointmentStartTime()))) {
                    anyOverlap = true;
                    break;
                }
            }
        }
        return anyOverlap;
    }

    /**
     *
     * This is the method that will inspect the modified appointments and test if there will be any overlap with an existing
     * appointment.
     *
     * @param appointmentCustomerID The appointment's customer ID.
     * @param appointmentID The appointment's ID.
     * @param startDateSelected The start date that was selected for the new appointment.
     * @param endDateSelected The end date that was selected for the new appointment.
     * @param startTimeSelected The start time that was selected for the new appointment.
     * @param endTimeSelected The end time that was selected for the new appointment.
     * @return True, if there are any existing appointments that overlap with the modified appointment trying to be added.
     */
    @Override
    public boolean inspectModifiedApptOverlap(int appointmentCustomerID, int appointmentID, LocalDate startDateSelected,
                                              LocalDate endDateSelected, LocalTime startTimeSelected,
                                              LocalTime endTimeSelected) {
        AppointmentsDAO appointmentsDAO = new ImplementAppointments();
        ObservableList<Appointments> customerAppointments = appointmentsDAO.getCustomerAppts(appointmentCustomerID);
        boolean anyOverlap = false;

        for(Appointments appointment : customerAppointments) {
            if((appointment.getAppointmentID() == appointmentID) && (startTimeSelected.equals(appointment.getAppointmentStartTime())
            && (endTimeSelected.equals(appointment.getAppointmentEndTime())))) {
                break;
            } else {
                inspectNewApptOverlap(appointmentCustomerID, startDateSelected, endDateSelected, startTimeSelected, endTimeSelected);
            }
        }
        return anyOverlap;
    }
}
