package DAO;

import javafx.collections.ObservableList;
import Model.Reports;


/**
 *
 * This is the DAO class for the Reports.
 * This class will function as the interface for the Implement Reports class.
 *
 * @author Ariel Johnson
 *
 */
public interface ReportsDAO {

    /**
     *
     * This is the method that will get all of appointments and then organize them by month and type. The
     * reports will be tallied up as a number as well.
     *
     * @return The list of all the reports.
     *
     */
    public ObservableList<Reports> getAllReports();
}
