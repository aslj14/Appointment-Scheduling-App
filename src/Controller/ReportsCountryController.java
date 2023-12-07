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

public class ReportsCountryController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton contactsched;

    @FXML
    private ComboBox<Countries> countrycombobox;

    @FXML
    private TableView<Customers> countrycuststableview;

    @FXML
    private TableColumn custaddresscol;

    @FXML
    private TableColumn customeridcol;

    @FXML
    private TableColumn custscountrycol;

    @FXML
    private TableColumn custsnamecol;

    @FXML
    private TableColumn custspostalcol;

    @FXML
    private TableColumn custsstateprovincecol;

    @FXML
    private Label numofcustslabel;

    @FXML
    private ToggleGroup totalbycountryTG;

    @FXML
    private RadioButton totalbymonthtype;

    @FXML
    private RadioButton totalcustsbycountry;

    @FXML
    private Label totalcustslabel;

    @FXML
    void onActionDisplayContactSchedule(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Appointment Scheduling System");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void onActionDisplayMonthType(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsByMonthTypeScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionFillTableview(ActionEvent event) throws IOException {
        JDBC.openConnection();
        CustomersDAO customersDAO = new ImplementCustomers();
        int appointmentCountryID = countrycombobox.getSelectionModel().getSelectedItem().getCountryID();
        countrycuststableview.setItems(customersDAO.getCustomerCountry(appointmentCountryID));

        numofcustslabel.setText(" " + customersDAO.getCustomerCountry(appointmentCountryID).size());
    }

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
