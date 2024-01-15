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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * This is the Main Customers controller.
 *
 * <p>This controller class will supply the control and logic of the main customers screen.
 * This controller class will permit the user to view all of the customers in the database. This main customers
 * screen/controller will display a list of the current customers that are saved to the database along with all of the
 * information associated with the customers. From this screen the user can add a new customer, modify an existing
 * customer, and/or delete an existing customer from the database.</p>
 *
 * @author Ariel Johnson
 *
 */
public class MainCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * This is the customer that is selected.
     *
     */
    private static Customers customerSelected;

    /**
     *
     * This is the getter for the selected customer.
     * @return getCustomerSelected This returns the selected customer.
     *
     */
    public static Customers getCustomerSelected() {

        return customerSelected;
    }

    /**
     *
     * This is the setter for the selected customer.
     * @param theCustomer This is the customer that is selected.
     *
     */
    public static void setCustomerSelected(Customers theCustomer) {

        customerSelected = theCustomer;
    }

    /**
     *
     * This is the address column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincustaddresscol;

    /**
     *
     * This is the country column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincustcountrycol;

    /**
     *
     * This is the ID column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincustidcol;

    /**
     *
     * This is the name column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincustnamecol;

    /**
     *
     * This is the phone number column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincustphonenumbercol;

    /**
     *
     * This is the postal code column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincustpostalcodecol;

    /**
     *
     * ToggleGroup for the radio buttons in the main customer tableview.
     *
     */
    @FXML
    private ToggleGroup maincustsTG;

    /**
     *
     * This is the state column in the main customer tableview.
     *
     */
    @FXML
    private TableColumn maincuststatecol;

    /**
     *
     * The tableview for all of the customers in the database.
     *
     */
    @FXML
    private TableView<Customers> maincusttableview;

    /**
     *
     * RadioButton for "View All Appointments".
     *
     */
    @FXML
    private RadioButton maincustviewallappts;

    /**
     *
     * RadioButton for "View All Customers".
     *
     */
    @FXML
    private RadioButton maincustviewallcusts;

    /**
     *
     * RadioButton for "View All Appointments by Month".
     *
     */
    @FXML
    private RadioButton maincustviewbymonth;

    /**
     *
     * RadioButton for "View All Appointments by Week".
     *
     */
    @FXML
    private RadioButton maincustviewbywk;

    /**
     *
     * This is the Add a New Customer method.
     *
     * <p>This method gives the Add Customer button the functionality to switch the screen to the Add Customer form
     * when the button is clicked.</p>
     *
     * @param event This is the action for the Add Customer button on the Main Customers screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddCustomerScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the Delete Customer  method.
     *
     * <p>The user is supposed to choose a customer that they would like to delete from the main customers
     * tableview. After a customer is selected, the connection to the database is opened. If the button is clicked
     * and no customer is selected, an alert is displayed that tells the user a customer must be selected.</p>
     *
     * <p>Once a customer is selected for deletion, an alert is displayed which asks the user to confirm if they would
     * like to delete the customer. The alert also lets the user know that deleting the customer will also delete all
     * of the customer's appointments. The user can either select "OK" or "Cancel". If the user selects "OK" the
     * customer is deleted from the main customers tableview and the database as well. Once the customer is
     * deleted the user will see an alert message displayed that confirms the customer ID and name of the customer that
     * was deleted.</p>
     *
     *
     * @param event This is the action for the Delete Customer button on the Main Customers screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        try {
            Customers customerSelected = maincusttableview.getSelectionModel().getSelectedItem();
            if (customerSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a customer to delete! ");
                alert.showAndWait();
            } else {
                JDBC.openConnection();
                CustomersDAO customersDAO = new ImplementCustomers();
                Customers chosenCustomer = maincusttableview.getSelectionModel().getSelectedItem();
                int customerID = chosenCustomer.getCustomerID();
                String customerName = chosenCustomer.getCustomerName();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Existing Customer");
                alert.setContentText("Deleting this customer will delete all of the customer's corresponding appointments.\n" +
                        "Are you sure you want to delete this customer?");
                Optional<ButtonType> choice = alert.showAndWait();

                if ((choice.isPresent() && choice.get() == ButtonType.OK)) {
                    customersDAO.deleteCustomer(customerID, customerName);
                    maincusttableview.setItems(customersDAO.getAllCustomers());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
        }
    }

    /**
     *
     * This is the "View All Appointments" method.
     *
     * <p>When clicking on the View All Appointments radio button, the connection to the database is
     * opened. Once the connection is opened, the tableview for all of the appointments is then filled with
     * a list of all appointments from the database.</p>
     *
     * @param event This is the action for the View All Appointments radio button on the Main Customers screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayMainAppts(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the View Reports method.
     *
     *<p>When the user clicks on the "Reports" button, the screen will switch to the Reports: Contact Schedule screen.
     * </p>
     *
     * @param event This is the action for the Reports button on the Main Customers screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ReportsContactScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This will exit the application.
     *
     * @param event This is the action for the Logout button on the Main Appointments scree.
     *
     */
    @FXML
    void onActionLogout(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * This is the Modify Customer method.
     *
     * <p>The user is supposed to choose a customer that they would like to modify from the main customers
     * tableview. After a customer is selected, the connection to the database is opened. If the button is clicked
     * and no appointment is selected, an alert is displayed that tells the user a customer must be selected.</p>
     *
     * @param event This is the action for the Modify Customer button on the Main Customer screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionModifyCustomer(ActionEvent event) throws IOException {
        try {
            Customers customerSelected = maincusttableview.getSelectionModel().getSelectedItem();
            if (customerSelected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a customer to modify! ");
                alert.showAndWait();
            } else {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyCustomerScreen.fxml"));
                Scene scene = new Scene(loader.load());
                ModifyCustomerController controller = loader.getController();
                controller.setCustomer(customerSelected);
                stage.setTitle("Appointment Scheduling System");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            System.out.println("Error: ");
        }
    }

    /**
     *
     * This is the View Appointments by Month method.
     *
     * <p>When clicking on the "View by Month" radio button from the Main Customers screen, the screen switches
     * to the Main Appointments screen and from that screen the user can then click the View By Month radio button
     * and view the upcoming appointments for the month.</p>
     *
     * @param event This is the action for the View by Month radio button on the Main Customers screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionViewByMonth(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This is the View Appointments by Week method.
     *
     * <p>When clicking on the "View by Week" radio button from the Main Customers screen, the screen switches
     * to the Main Appointments screen and from that screen the user can then click the View By Week radio button
     * and view the upcoming appointments for the week.</p>
     *
     * @param event This is the action for the View by Month radio button on the Main Customers screen.
     * @throws IOException From the FXMLLoader.
     *
     */
    @FXML
    void onActionViewByWeek(ActionEvent event) throws IOException {
        stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainAppointmentScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * This initializes the Main Customers controller and populates the tableview with the necessary
     * data for the customers in the database.
     *
     * @param url The location that controls the root object's path that is relative.
     * @param resourceBundle The resources that are utilized to accommodate the root object.
     *
     */
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