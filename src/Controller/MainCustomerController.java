package Controller;

import DAO.CustomersDAO;
import DAO.ImplementCustomers;
import Helper.JDBC;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    private static Customers customerSelected;

    public static Customers getCustomerSelected() {
        return customerSelected;
    }

    public static void setCustomerSelected(Customers theCustomer) {
        customerSelected = theCustomer;
    }

    @FXML
    private TableColumn maincustaddresscol;

    @FXML
    private TableColumn maincustcountrycol;

    @FXML
    private TableColumn maincustidcol;

    @FXML
    private TableColumn maincustnamecol;

    @FXML
    private TableColumn maincustphonenumbercol;

    @FXML
    private TableColumn maincustpostalcodecol;

    @FXML
    private ToggleGroup maincustsTG;

    @FXML
    private TableColumn maincuststatecol;

    @FXML
    private TableView<Customers> maincusttableview;

    @FXML
    private RadioButton maincustviewallappts;

    @FXML
    private RadioButton maincustviewallcusts;

    @FXML
    private RadioButton maincustviewbymonth;

    @FXML
    private RadioButton maincustviewbywk;

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        JDBC.openConnection();
        CustomersDAO customersDAO = new ImplementCustomers();
        Customers chosenCustomer = maincusttableview.getSelectionModel().getSelectedItem();
        int customerID = chosenCustomer.getCustomerID();
        String customerName = chosenCustomer.getCustomerName();

        customersDAO.deleteCustomer(customerID,customerName);
        maincusttableview.setItems(customersDAO.getAllCustomers());
    }

    @FXML
    void onActionDisplayMainAppts(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionLogout(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyCustomer(ActionEvent event) throws IOException {
                Customers customerSelected = maincusttableview.getSelectionModel().getSelectedItem();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyCustomerScreen.fxml"));
                Scene scene = new Scene(loader.load());
                ModifyCustomerController controller = loader.getController();
                controller.setCustomer(customerSelected);
                stage.setTitle("Appointment Scheduling System");
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    void onActionViewByMonth(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        JDBC.openConnection();
        CustomersDAO customersDAO = new ImplementCustomers();
        maincusttableview.setItems(customersDAO.getAllCustomers());

        maincustidcol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        maincustnamecol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        maincustaddresscol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        maincuststatecol.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
        maincustcountrycol.setCellValueFactory(new PropertyValueFactory<>("customerCountryName"));
        maincustpostalcodecol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        maincustphonenumbercol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
    }
}