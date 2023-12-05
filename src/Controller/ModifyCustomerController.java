package Controller;

import DAO.CountriesDAO;

import DAO.ImplementCountries;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {

    Stage stage;
    Parent scene;


    Customers customerSelected;

    private int modCountryID;

    private int modDivisionID;

    @FXML
    private TextField modcustaddress;

    @FXML
    private ComboBox<Countries> modcustcountry;

    @FXML
    private TextField modcustid;

    @FXML
    private TextField modcustname;

    @FXML
    private TextField modcustphonenumber;

    @FXML
    private TextField modcustpostalcode;

    @FXML
    private ComboBox<Divisions> modcuststateprovince;

    @FXML
    void onActionDisplayModStateProvince(ActionEvent event) {
        modCountryID = modcustcountry.getValue().getCountryID();
        modcuststateprovince.setItems(Lists.getProcessedDivisions(modCountryID));
        modcuststateprovince.getSelectionModel().selectFirst();
    }

    public void setCustomer(Customers customerSelected) {
        JDBC.openConnection();
        CountriesDAO countriesDAO = new ImplementCountries();

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

    @FXML
    void onActionModifyCustomer(ActionEvent event) throws IOException {


    }

    @FXML
    void onActionShowMainCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JDBC.openConnection();
        CountriesDAO countriesDAO = new ImplementCountries();

        modcustcountry.setItems(countriesDAO.getAllCountries());
    }
}
