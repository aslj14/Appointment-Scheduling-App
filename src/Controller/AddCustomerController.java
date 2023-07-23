package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerController {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addcustaddress;

    @FXML
    private ComboBox<?> addcustcountry;

    @FXML
    private TextField addcustid;

    @FXML
    private TextField addcustname;

    @FXML
    private TextField addcustphonenumber;

    @FXML
    private TextField addcustpotalcode;

    @FXML
    private ComboBox<?> addcuststateprovince;

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

}
