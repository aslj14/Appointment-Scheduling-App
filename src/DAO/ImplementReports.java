package DAO;

import Model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the Reports DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementReports implements ReportsDAO{

    /**
     *
     * This is the list of all reports.
     *
     */
    ObservableList<Reports> allAppointmentReports = FXCollections.observableArrayList();

    /**
     *
     * This is the method that will get all of appointments and then organize them by month and type. The
     * reports will be tallied up as a number as well.
     *
     * @return The list of all the reports.
     *
     */
    @Override
    public ObservableList<Reports> getAllReports() {
        try {
            String sql = "SELECT monthname(start), type, count(*) as ttl FROM appointments GROUP BY " +
                    "monthname(start), type";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String appointmentMonth = rs.getString("monthname(start)");
                String appointmentType = rs.getString("type");
                int total = rs.getInt("ttl");
                Reports reports = new Reports(appointmentMonth, appointmentType, total);
                allAppointmentReports.add(reports);
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
        return allAppointmentReports;
    }
}
