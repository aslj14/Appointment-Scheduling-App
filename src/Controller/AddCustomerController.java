package Controller;

import DAO.CountriesDAO;
import DAO.CustomersDAO;
import DAO.ImplementCountries;
import DAO.ImplementCustomers;
import Helper.JDBC;
import Model.Countries;
import Model.Divisions;
import Utility.Lists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * This is the Add Customers controller.
 *
 * <p>This controller class will supply the control and logic of the add customer form.
 * This controller class will permit the user to add a new customer to the database. The user
 * has to input the necessary information into the form and in turn the customer is saved to the
 * database.</p>
 *
 * @author Ariel Johnson
 *
 */
public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * Private int variable for country ID.
     *
     */
    private int countryID;

    /**
     *
     * Textfield for the customer address.
     *
     */
    @FXML
    private TextField addcustaddress;

    /**
     *
     * ComboBox for the list of countries.
     *
     */
    @FXML
    private ComboBox<Countries> addcustcountry;

    /**
     *
     * Textfield for the customer ID.
     *
     */
    @FXML
    private TextField addcustid;

    /**
     *
     * Textfield for the customer name.
     *
     */
    @FXML
    private TextField addcustname;

    /**
     *
     * Textfield for the customer phone number.
     *
     */
    @FXML
    private TextField addcustphonenumber;

    /**
     *
     * Textfield for the customer postal code.
     *
     */
    @FXML
    private TextField addcustpotalcode;

    /**
     *
     * ComboBox for the list of first level divisions.
     *
     */
    @FXML
    private ComboBox<Divisions> addcuststateprovince;

    /**
     *
     * This will load the Main Customers screen controller and display the main screen
     * that shows the tableview of all the customers.
     *
     * @param event This is the action for the Cancel button on the Add Customer controller.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMainCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the Add New Customer method.
     *
     * <p>The user will input the required information into the textfields and select the required information from
     * the combo boxes. The method will then check if any of the textfields are blank. If the
     * user does not input any information into the textfields, the "alertMessages" method will be called. When the
     * "alertMessages" method is called it will display an alert letting the user know which textfield is empty and
     * it will ask the user to enter the required information.</p>
     *
     * <p>If all of the required information for the new customer is entered by the user and none of the textfields are
     * empty, the "addNewCustomers" method is called, and the new customer will then be added to the database
     * and the main customers tableview that holds all of the customers.</p>
     *
     * @param event This is the action for the Save button on the Add Customer form.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionSaveNewCustomer(ActionEvent event) throws IOException {
        try {
            boolean inputError = false;
            String customerName = addcustname.getText();
            addcustname.setText(customerName);
            String customerAddress = addcustaddress.getText();
            String customerPostalCode = addcustpotalcode.getText();
            String customerPhoneNumber = addcustphonenumber.getText();
            int divisionCountryID = addcuststateprovince.getSelectionModel().getSelectedItem().getDivisionID();

            if(customerName.isBlank()) {
                alertMessages(1);
                inputError = true;
            } else if(customerAddress.isBlank()) {
                alertMessages(2);
                inputError = true;
            } else if(customerPostalCode.isBlank()) {
                alertMessages(3);
                inputError = true;
            } else if(customerPhoneNumber.isBlank()) {
                alertMessages(4);
                inputError = true;
            }

            if(!inputError) {
                CustomersDAO customersDAO = new ImplementCustomers();
                customersDAO.addNewCustomers(customerName, customerAddress, customerPostalCode, customerPhoneNumber,
                        divisionCountryID);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setTitle("Appointment Scheduling System");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

    /**
     *
     * This is the method that selects a country.
     *
     * <p>This method takes the country that is selected from the Country combo box, and then populates the
     * State/Province combo box with the states or provinces for that country.</p>
     *
     * @param actionEvent This is the action for selecting the user's desired country for the new customer.
     *
     */
    @FXML
    void onActionSelectACountry(ActionEvent actionEvent) {
        countryID = addcustcountry.getValue().getCountryID();
        addcuststateprovince.setItems(Lists.getProcessedDivisions(countryID));
        addcuststateprovince.getSelectionModel().selectFirst();
    }

    /**
     *
     * This is the "alertMessages" method. This is the method that holds all of the alert dialog
     * boxes that will display if there are any errors when trying to save/add a new appointment.
     *
     * <p>LAMBDA EXPRESSION (1): When the "alertMessages" method is called, it will display the
     * appropriate error message in an alert dialog box.</p>
     * <p>MY REASONING FOR CHOOSING A LAMBDA HERE: I chose to use a lambda expression here because
     * it was a straightforward, yet very helpful way to produce individual alerts for each particular case.
     * The lambda expression also helped to use less code for the method since the number for each
     * specific case related to the "->" returned the appropriate alert.</p>
     *
     * @param alertChoice case number associated with the appropriate alert message
     *
     */
    public void alertMessages(int alertChoice) {
        switch (alertChoice) {
            case 1 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Customer Error");
                alert.setContentText("Please input a name!");
                alert.showAndWait();
            }
            case 2 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Customer Error");
                alert.setContentText("Please input an address!");
                alert.showAndWait();
            }
            case 3 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Customer Error");
                alert.setContentText("Please input a postal code!");
                alert.showAndWait();
            }
            case 4 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Customer Error");
                alert.setContentText("Please input a phone number!");
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * This initializes the Add Customer controller.
     *
     * This method is the first method that is called when the screen/form
     * is displayed.
     *
     * <p>The connection to the database is opened to fill
     * the country combo box. When the user selects a country for the customer, the
     * state/province combo box is then filled with the correct states or provinces for that
     * country.</p>
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *                       
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.openConnection();
            CountriesDAO countriesDAO = new ImplementCountries();

            addcustcountry.setItems(countriesDAO.getAllCountries());
            addcustcountry.getSelectionModel().selectFirst();
            countryID = addcustcountry.getValue().getCountryID();
            addcuststateprovince.setItems(Lists.getProcessedDivisions(countryID));
            addcuststateprovince.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println("Error: ");
        }
    }

}
