package Controller;

import DAO.CountriesDAO;
import DAO.ImplementCountries;
import Helper.JDBC;
import Model.Countries;
import Model.Divisions;
import Utility.Lists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addcustaddress;

    @FXML
    private ComboBox<Countries> addcustcountry;

    @FXML
    private TextField addcustid;

    @FXML
    private TextField addcustname;

    @FXML
    private TextField addcustphonenumber;

    @FXML
    private TextField addcustpotalcode;

    @FXML
    private ComboBox<Divisions> addcuststateprovince;

    private int countryID;

    @FXML
    void onActionDisplayMainCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionSaveNewCustomer(ActionEvent event) {


    }

    @FXML
    void onActionSelectACountry(ActionEvent event) {
        countryID = addcustcountry.getValue().getCountryID();
        addcuststateprovince.setItems(Lists.getProcessedDivisions(countryID));
        addcuststateprovince.getSelectionModel().selectFirst();
    }

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
            e.printStackTrace();
        }
    }

}
