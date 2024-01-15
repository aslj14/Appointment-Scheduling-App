package Controller;

import DAO.CountriesDAO;
import DAO.CustomersDAO;
import DAO.ImplementCountries;
import DAO.ImplementCustomers;
import Helper.JDBC;
import Model.Countries;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * This is the Reports: Total Customers by Country controller.
 *
 * <p>This controller class will supply the control and logic of the Reports: Total Customers by Country screen.
 * This controller class will permit the user to select a country from the combo box and then the the tableview will
 * show the customers that are located in that country along with their customer information (name, address, phone
 * number, etc.). Also on this screen is a total count of the number of customers in the selected country.</p>
 *
 * @author Ariel Johnson
 *
 */
public class ReportsCountryController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * RadioButton for Contact Schedule.
     *
     */
    @FXML
    private RadioButton contactsched;

    /**
     *
     * ComboBox that holds the list of countries.
     *
     */
    @FXML
    private ComboBox<Countries> countrycombobox;

    /**
     *
     * Tabbleview that will show the customers in the selected country.
     *
     */
    @FXML
    private TableView<Customers> countrycuststableview;

    /**
     *
     * Address column in the tableview..
     *
     */
    @FXML
    private TableColumn custaddresscol;

    /**
     *
     * Customer ID column in the tableview..
     *
     */
    @FXML
    private TableColumn customeridcol;

    /**
     *
     * Country column in the tableview..
     *
     */
    @FXML
    private TableColumn custscountrycol;

    /**
     *
     * Name column in the tableview..
     *
     */
    @FXML
    private TableColumn custsnamecol;

    /**
     *
     * Postal code column in the tableview..
     *
     */
    @FXML
    private TableColumn custspostalcol;

    /**
     *
     * Label that shows the actual number total of the customers in the selected country.
     *
     */
    @FXML
    private Label numofcustslabel;

    /**
     *
     * ToggleGroup for the total customers by country screen.
     *
     */
    @FXML
    private ToggleGroup totalbycountryTG;

    /**
     *
     * RadioButton for the total appointments by month and type.
     *
     */
    @FXML
    private RadioButton totalbymonthtype;

    /**
     *
     * RadioButton for the total customers by country.
     *
     */
    @FXML
    private RadioButton totalcustsbycountry;

    /**
     *
     * "Total # of Customers" label.
     *
     */
    @FXML
    private Label totalcustslabel;

    /**
     *
     * This is the Reports: Contact Schedule method.
     *
     * <p>When the user clicks on the Contact Schedule radio button, the screen will switch to show the
     * Reports: Contact Schedule screen.</p>
     *
     * @param event This is the action for the Contact Schedule radio button on the Reports: Total Customers by Country
     *              screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayContactSchedule(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();

    }

    /**
     *
     * This will load the Main Appointments screen controller and display the main screen
     * that shows the tableview of all the appointments.
     *
     * @param event This is the action for the Cancel button on the Reports: Total Customers by Country screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
    }

    /**
     *
     * This is the Reports: Total Appointments by Month and Type method.
     *
     * <p>When the user clicks on the Total Appointments by Month and Type radio button, the screen will switch to show the
     * Reports: Total Appointments by Month and Type screen.</p>
     *
     * @param event This is the action for the Total Appointments by Month and Type radio button on the Total Customers
     *              by Country screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMonthType(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsByMonthTypeScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();

    }

    /**
     *
     * This will exit the application.
     *
     * @param event This is the action for the Logout button on the Reports: Total Customers by Country screen.
     *
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * This is the Fill Table method.
     *
     * <p>When the connection to the database is opened, the tableview is filled with the customers that are located in
     * the selected country. The total number of customers for this particular country are counted and shown as a number.</p>
     *
     * @param event This is the action for when the user selects a country from the combo box.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionFillTableview(ActionEvent event) throws IOException {
        JDBC.openConnection();
        CustomersDAO customersDAO = new ImplementCustomers();
        int appointmentCountryID = countrycombobox.getSelectionModel().getSelectedItem().getCountryID();
        countrycuststableview.setItems(customersDAO.getCustomerCountry(appointmentCountryID));

        numofcustslabel.setText(" " + customersDAO.getCustomerCountry(appointmentCountryID).size());
    }

    /**
     *
     * This initializes the Reports: Total Customers by Country controller and fills the tableview with the necessary
     * data for the report that shows the customers for a selected country.
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customeridcol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        custsnamecol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custaddresscol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custspostalcol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        custscountrycol.setCellValueFactory(new PropertyValueFactory<>("customerCountryName"));

        JDBC.openConnection();
        CountriesDAO countriesDAO = new ImplementCountries();
        countrycombobox.setItems(countriesDAO.getAllCountries());
    }
}
