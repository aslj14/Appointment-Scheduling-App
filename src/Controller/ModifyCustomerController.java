package Controller;

import DAO.CountriesDAO;

import DAO.CustomersDAO;
import DAO.ImplementCountries;
import DAO.ImplementCustomers;
import Helper.JDBC;
import Model.Countries;
import Model.Customers;
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
 * This is the Modify Customer controller.
 *
 * <p>This controller class will supply the control and logic of the modify customer form.
 * This controller class will permit the user to modify the details of an existing customer that the user selects.
 * The user can modify the information that is already in the textfields and combo boxes and then save that information
 * and modify the existing customer.</p>
 *
 * @author Ariel Johnson
 *
 */
public class ModifyCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the selected customer that is being modified.
     *
     */
    Customers customerSelected = null;

    /**
     *
     * Int variable for the modified country ID.
     *
     */
    private int modCountryID;

    /**
     *
     * Int variable for the modified division ID.
     *
     */
    private int modDivisionID;

    /**
     *
     * Textfield for the modified customer address.
     *
     */
    @FXML
    private TextField modcustaddress;

    /**
     *
     * ComboBox to hold the list of countries.
     *
     */
    @FXML
    private ComboBox<Countries> modcustcountry;

    /**
     *
     * Textfield for the modified customer ID.
     *
     */
    @FXML
    private TextField modcustid;

    /**
     *
     * Textfield for the modified customer name.
     *
     */
    @FXML
    private TextField modcustname;

    /**
     *
     * Textfield for the modified customer phone number.
     *
     */
    @FXML
    private TextField modcustphonenumber;

    /**
     *
     * Textfield for the modified customer postal code.
     *
     */
    @FXML
    private TextField modcustpostalcode;

    /**
     *
     * ComboBox to hold the list of first level divisions.
     *
     */
    @FXML
    private ComboBox<Divisions> modcuststateprovince;

    /**
     *
     * This is the "setCustomer" method.
     *
     * <p>This method will populate the Modify Customer form's combo boxes and textfields with the
     * information from the existing customer that is selected.</p>
     *
     * @param theCustomer The customer that is selected.
     *
     */
    public void setCustomer(Customers theCustomer) {
        JDBC.openConnection();
        CountriesDAO countriesDAO = new ImplementCountries();

        customerSelected = theCustomer;

        modcustid.setText(Integer.toString(customerSelected.getCustomerID()));
        modcustname.setText(customerSelected.getCustomerName());
        modcustaddress.setText(customerSelected.getCustomerAddress());
        modcustpostalcode.setText(customerSelected.getCustomerPostalCode());
        modcustphonenumber.setText(customerSelected.getCustomerPhoneNumber());

        Countries countrySelected = null;
        for(Countries countries : countriesDAO.getAllCountries()) {
            if(countries.getCountryID() == customerSelected.getCustomerCountryID()) {
                countrySelected = countries;
                break;
            }
        }
        modcustcountry.getSelectionModel().select(countrySelected);
        modCountryID = countrySelected.getCountryID();

        modcuststateprovince.setItems(Lists.getProcessedDivisions(modCountryID));
        Divisions divisionsSelected = null;
        for(Divisions divisions : Lists.getProcessedDivisions(modCountryID)) {
            if(divisions.getDivisionID() == customerSelected.getCustomerDivisionID()) {
                divisionsSelected = divisions;
                break;
            }
        }
        modcuststateprovince.getSelectionModel().select(divisionsSelected);
        modDivisionID = divisionsSelected.getDivisionID();
    }

    /**
     *
     * This is the Modify Customer method.
     *
     * <p>The user will select an existing customer to modify. The user will then modify the information in the
     * textfields and modify the information in the combo boxes if they wish. The method will then check if any of the
     * textfields are blank. If the user does not have any information into the textfields, the "alertMessages" method
     * will be called. When the "alertMessages" method is called it will display an alert letting the user know which
     * textfield is empty and it will ask the user to enter the required information.</p>
     *
     * <p>If all of the required information for the modified customer is entered by the user and none of the textfields are
     * empty, the "modifyCustomer" method is called, and the modified customer will then be added to the database
     * and the main customers tableview that holds all of the customers.</p>
     *
     * @param event This is the action for the Save button on the Modify Customer form.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionModifyCustomer(ActionEvent event) throws IOException {
        try {
            boolean inputError = false;
            int customerID = customerSelected.getCustomerID();
            String customerName = modcustname.getText();
            String customerAddress = modcustaddress.getText();
            String customerPostalCode = modcustpostalcode.getText();
            String customerPhoneNumber = modcustphonenumber.getText();
            int divisionCountryID = modcuststateprovince.getSelectionModel().getSelectedItem().getDivisionID();

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
                customersDAO.modifyCustomer(customerID, customerName, customerAddress, customerPostalCode, customerPhoneNumber,
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
     * @param event This is the action for selecting the user's desired country for the modified customer.
     *
     */
    @FXML
    void onActionSelectModCountry(ActionEvent event) {
        modCountryID = modcustcountry.getValue().getCountryID();
        modcuststateprovince.setItems(Lists.getProcessedDivisions(modCountryID));
        modcuststateprovince.getSelectionModel().selectFirst();
    }

    /**
     *
     * This will load the Main Customers screen controller and display the main screen
     * that shows the tableview of all the customers.
     *
     * @param event This is the action for the Cancel button on the Modify Customer controller.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionShowMainCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
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
                alert.setTitle("Modify Existing Customer Error");
                alert.setContentText("Please input a name!");
                alert.showAndWait();
            }
            case 2 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Customer Error");
                alert.setContentText("Please input an address!");
                alert.showAndWait();
            }
            case 3 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Customer Error");
                alert.setContentText("Please input a postal code!");
                alert.showAndWait();
            }
            case 4 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modify Existing Customer Error");
                alert.setContentText("Please input a phone number!");
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * This initializes the Modify Customer controller.
     *
     * This method is the first method that is called when the screen/form
     * is displayed.
     *
     * The Country combo box is then filled with the list of countries.
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JDBC.openConnection();
        CountriesDAO countriesDAO = new ImplementCountries();

        modcustcountry.setItems(countriesDAO.getAllCountries());
    }
}
