package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the Customers DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementCustomers implements CustomersDAO {

    /**
     *
     * The ObservableList of all of the customers in the database.
     *
     */
    ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     *
     * This is the method that will get all of the customers from the database and add the customers to an
     * observable list.
     *
     * @return A list of all the customers.
     *
     */
    @Override
    public ObservableList<Customers> getAllCustomers() {
        try {
            String sql = "SELECT * FROM customers INNER JOIN first_level_divisions ON customers.Division_ID =  " +
                    " first_level_divisions.Division_ID INNER JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentCustomerID = rs.getInt("Customer_ID");
                String appointmentCustomerName = rs.getString("Customer_Name");
                String appointmentCustomerAddress = rs.getString("Address");
                String appointmentCustomerPostalCode = rs.getString("Postal_Code");
                int appointmentCustomerCountryID = rs.getInt("Country_ID");
                String appointmentCustomerCountry = rs.getString("Country");
                int appointmentCustomerDivisionID = rs.getInt("Division_ID");
                String appointmentCustomerDivision = rs.getString("Division");
                String appointmentCustomerPhoneNumber = rs.getString("Phone");
                Customers customers = new Customers(appointmentCustomerID, appointmentCustomerName,
                        appointmentCustomerAddress, appointmentCustomerCountryID, appointmentCustomerCountry,
                        appointmentCustomerPostalCode, appointmentCustomerPhoneNumber, appointmentCustomerDivisionID,
                        appointmentCustomerDivision);
                allCustomers.add(customers);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return allCustomers;
    }

    /**
     *
     * This is the method that will get a particular customer in accordance with that particular customer's
     * ID.
     *
     * @param appointmentCustomerID This is the particular customer's ID.
     * @return The particular customer's information is returned.
     *
     */
    @Override
    public Customers getCustomer(int appointmentCustomerID) {
        try {
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE customers.Division_ID " +
                    " = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Division_ID " +
                    " Customer_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentCustomerID);

            ResultSet rs = ps.executeQuery();
            Customers customersResult = null;
            if (rs.next()) {
                appointmentCustomerID = rs.getInt("Customer_ID");
                String appointmentCustomerName = rs.getString("Customer_Name");
                String appointmentCustomerAddress = rs.getString("Address");
                String appointmentCustomerPostalCode = rs.getString("Postal_Code");
                int appointmentCustomerCountryID = rs.getInt("Country_ID");
                String appointmentCustomerCountry = rs.getString("Country");
                int appointmentCustomerDivisionID = rs.getInt("Division_ID");
                String appointmentCustomerDivision = rs.getString("Division");
                String appointmentCustomerPhoneNumber = rs.getString("Phone");
                Customers customers = new Customers(appointmentCustomerID, appointmentCustomerName,
                        appointmentCustomerAddress, appointmentCustomerCountryID, appointmentCustomerCountry,
                        appointmentCustomerPostalCode, appointmentCustomerPhoneNumber,
                        appointmentCustomerDivisionID, appointmentCustomerDivision);
            }
            return customersResult;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * This is the method that will get a list of customers that are tied to a specific country ID.
     *
     * @param appointmentCountryID This is the ID of the country.
     * @return Returns a list of customers that are located in a specific country.
     *
     */
    @Override
    public ObservableList<Customers> getCustomerCountry(int appointmentCountryID) {
        ObservableList<Customers> customerCountry = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE " +
                    "customers.Division_ID = first_level_divisions.Division_ID AND " +
                    "first_level_divisions.Country_ID = countries.Country_ID AND countries.Country_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentCountryID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentCustomerID = rs.getInt("Customer_ID");
                String appointmentCustomerName = rs.getString("Customer_Name");
                String appointmentCustomerAddress = rs.getString("Address");
                String appointmentCustomerPostalCode = rs.getString("Postal_Code");
                appointmentCountryID = rs.getInt("Country_ID");
                String appointmentCustomerCountry = rs.getString("Country");
                int appointmentCustomerDivisionID = rs.getInt("Division_ID");
                String appointmentCustomerDivision = rs.getString("Division");
                String appointmentCustomerPhoneNumber = rs.getString("Phone");
                Customers customers = new Customers(appointmentCustomerID, appointmentCustomerName,
                        appointmentCustomerAddress, appointmentCountryID, appointmentCustomerCountry,
                        appointmentCustomerPostalCode, appointmentCustomerPhoneNumber,
                        appointmentCustomerDivisionID, appointmentCustomerDivision);
                customerCountry.add(customers);
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
        return customerCountry;
    }

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
    @Override
    public int addNewCustomers(String appointmentCustomerName, String appointmentCustomerAddress,
                               String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                               int appointmentCustomerDivisionID) {
        int affectedRows = 0;
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointmentCustomerName);
            ps.setString(2, appointmentCustomerAddress);
            ps.setString(3, appointmentCustomerPostalCode);
            ps.setString(4, appointmentCustomerPhoneNumber);
            ps.setInt(5, appointmentCustomerDivisionID);
            affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("New customer was added successfully!");
            } else {
                System.out.println("New customer was not added!");
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return affectedRows;
    }

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
    @Override
    public int modifyCustomer(int appointmentCustomerID, String appointmentCustomerName, String appointmentCustomerAddress,
                              String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                              int appointmentCustomerDivisionID) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                    " Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointmentCustomerName);
            ps.setString(2, appointmentCustomerAddress);
            ps.setString(3, appointmentCustomerPostalCode);
            ps.setString(4, appointmentCustomerPhoneNumber);
            ps.setInt(5, appointmentCustomerDivisionID);
            ps.setInt(6, appointmentCustomerID);
            affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("New customer was added successfully!");
            } else {
                System.out.println("New customer was not added!");
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
        return affectedRows;
    }

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
    @Override
    public int deleteCustomer(int appointmentCustomerID, String appointmentCustomerName) {
        int affectedRows = 0;
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ? AND Customer_Name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentCustomerID);
            ps.setString(2, appointmentCustomerName);
            affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Deletion");
                alert.setContentText("Customer ID: " + appointmentCustomerID + ", Name: " + appointmentCustomerName + " was deleted!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Customer deletion failed!");
                alert.showAndWait();
            }
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
            return affectedRows;
        }
    }
