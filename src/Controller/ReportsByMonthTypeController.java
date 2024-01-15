package Controller;

import DAO.ImplementReports;
import DAO.ReportsDAO;
import Helper.JDBC;
import Model.Reports;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * This is the Reports by Month and Type controller.
 *
 * <p>This controller class will supply the control and logic of the Reports: Total Appointments by Month and Type screen.
 * This controller class will permit the user to view a total count of the appointments in the database by the month the
 * appointment is scheduled in. The user will also be able to see a total count of the different types of appointments.</p>
 *
 * @author Ariel Johnson
 *
 */
public class ReportsByMonthTypeController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * RadioButton for "Contact Schedule".
     *
     */
    @FXML
    private RadioButton monthtypescheduleradio;

    /**
     *
     * Tableview to show the total count of appointments by month and type.
     *
     */
    @FXML
    private TableView<Reports> monthtypetableview;

    /**
     *
     * Total column in the reports by month and type tableview.
     *
     */
    @FXML
    private TableColumn monthtypetotalcol;

    /**
     *
     * Month column in the reports by month and type tableview.
     *
     */
    @FXML
    private TableColumn reportmonthcol;

    /**
     *
     * Type column in the reports by month and type tableview.
     *
     */
    @FXML
    private TableColumn reporttypecol;

    /**
     *
     * RadioButton for "Total Customers by Country".
     *
     */
    @FXML
    private RadioButton totalbycountryradio;

    /**
     *
     * ToggleGroup for the reports by month and type screen.
     */
    @FXML
    private ToggleGroup totalbymonthtypeTG;

    /**
     *
     * This will exit the application.
     *
     * @param event This is the action for the Logout button on the Reports: Total Appointments by Month and Type screen.
     *
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * This will load the Main Appointments screen controller and display the main screen
     * that shows the tableview of all the appointments.
     *
     * @param event This is the action for the Cancel button on the Reports: Total Appointments by Month and Type screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionReturnToMainScreen(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
    }

    /**
     *
     * This is the Reports: Contact Schedule method.
     *
     * <p>When the user clicks on the Contact Schedule radio button, the screen will switch to show the
     * Reports: Contact Schedule screen.</p>
     *
     * @param event This is the action for the Contact Schedule radio button on the Reports: Total Appointments by Month
     *              and Type screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionShowContactSchedule(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the Reports: Total Customers by Country method.
     *
     * <p>When the user clicks on the Total Customers by Country radio button, the screen will switch to show the
     * Reports: Total Customers by Country screen.</p>
     *
     * @param event This is the action for the Total Customers by Country radio button on the Reports: Total Appointments by Month
     *              and Type screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionShowTotalByCountry(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsCountryScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This initializes the Reports by Month and Type controller.
     *
     * <p>Once the connection the database is opened, the reports method is called and the
     * tableview is then filled with the necessary data.</p>
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reportmonthcol.setCellValueFactory(new PropertyValueFactory<>("appointmentMonth"));
        reporttypecol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        monthtypetotalcol.setCellValueFactory(new PropertyValueFactory<>("total"));

        JDBC.openConnection();
        ReportsDAO reportsDAO = new ImplementReports();
        monthtypetableview.setItems(reportsDAO.getAllReports());
    }
}
