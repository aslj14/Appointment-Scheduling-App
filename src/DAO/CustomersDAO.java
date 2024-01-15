package DAO;

import Model.Customers;
import javafx.collections.ObservableList;

/**
 *
 * This is the DAO class for the Customers.
 * This class will function as the interface for the Implement Customers class.
 *
 * @author Ariel Johnson
 *
 */
public interface CustomersDAO {

    /**
     *
     * This is the method that will get all of the customers from the database and add the customers to an
     * observable list.
     *
     * @return A list of all the customers.
     *
     */
    public ObservableList<Customers> getAllCustomers();

    /**
     *
     * This is the method that will get a particular customer in accordance with that particular customer's
     * ID.
     *
     * @param appointmentCustomerID This is the particular customer's ID.
     * @return The particular customer's information is returned.
     *
     */
    public Customers getCustomer(int appointmentCustomerID);

    /**
     *
     * This is the method that will get a list of customers that are tied to a specific country ID.
     *
     * @param appointmentCountryID This is the ID of the country.
     * @return Returns a list of customers that are located in a specific country.
     *
     */
    public ObservableList<Customers> getCustomerCountry(int appointmentCountryID);

    /**
     *
     * This is the Add New Customer method. This method gives the user the ability to add a new customer to the database.
     *
     * @param appointmentCustomerName The new customer's name.
     * @param appointmentCustomerAddress The new customer's address.
     * @param appointmentCustomerPostalCode The new customer's postal code.
     * @param appointmentCustomerPhoneNumber The new customer's phone number.
     * @param appointmentCustomerDivisionID The new customer's division ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int addNewCustomers(String appointmentCustomerName, String appointmentCustomerAddress,
                               String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                               int appointmentCustomerDivisionID);

    /**
     *
     * This is the Modify Existing Customer method. This method permits the user to select a customer and then modify/edit
     * that customer's information.
     *
     * @param appointmentCustomerID The modified customer's ID.
     * @param appointmentCustomerName The modified customer's name.
     * @param appointmentCustomerAddress The modified customer's address.
     * @param appointmentCustomerPostalCode The modified customer's postal code.
     * @param appointmentCustomerPhoneNumber The modified customer's phone number.
     * @param appointmentCustomerDivisionID The modified customer's division ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int modifyCustomer(int appointmentCustomerID, String appointmentCustomerName, String appointmentCustomerAddress,
                              String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                              int appointmentCustomerDivisionID);

    /**
     *
     * This is the Delete an Existing Customer method. This method permits the user to select an existing customer and
     * delete that customer from the database.
     *
     * @param appointmentCustomerID The selected customer's ID.
     * @param appointmentCustomerName The selected customer's name.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int deleteCustomer(int appointmentCustomerID, String appointmentCustomerName);
}
