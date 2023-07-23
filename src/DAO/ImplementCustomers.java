package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

public class ImplementCustomers implements CustomersDAO {

    ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    @Override
    public ObservableList<Customers> getAllCustomers() {
        try {
            String sql = "SELECT * FROM customers, firet_level_divisons, countries WHERE customers.Division_ID " +
                    " = firet_level_divisons.Division_ID AND firet_level_divisons.Country_ID = countries.Division_ID ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentCustomerID = rs.getInt("Customer_ID");
                String appointmentCustomerName = rs.getString("Customer_Name");
                String appointmentCustomerAddress = rs.getString("Customer_Address");
                String appointmentCustomerPostalCode = rs.getString("Postal_Code");
                int appointmentCustomerCountryID = rs.getInt("Country_ID");
                String appointmentCustomerCountry = rs.getString("Country");
                int appointmentCustomerDivisionID = rs.getInt("Division_ID");
                String appointmentCustomerDivision = rs.getString("Division");
                String appointmentCustomerPhoneNumber = rs.getString("Phone_Number");
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

    @Override
    public Customers getCustomer(int appointmentCustomerID) {
        try {
            String sql = "SELECT * FROM customers, firet_level_divisons, countries WHERE customers.Division_ID " +
                    " = firet_level_divisons.Division_ID AND firet_level_divisons.Country_ID = countries.Division_ID " +
                    " Customer_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentCustomerID);

            ResultSet rs = ps.executeQuery();
            Customers customersResult = null;
            if (rs.next()) {
                appointmentCustomerID = rs.getInt("Customer_ID");
                String appointmentCustomerName = rs.getString("Customer_Name");
                String appointmentCustomerAddress = rs.getString("Customer_Address");
                String appointmentCustomerPostalCode = rs.getString("Postal_Code");
                int appointmentCustomerCountryID = rs.getInt("Country_ID");
                String appointmentCustomerCountry = rs.getString("Country");
                int appointmentCustomerDivisionID = rs.getInt("Division_ID");
                String appointmentCustomerDivision = rs.getString("Division");
                String appointmentCustomerPhoneNumber = rs.getString("Phone_Number");
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

    @Override
    public int addNewCustomers(String appointmentCustomerName, String appointmentCustomerAddress,
                               String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                               int appointmentCustomerDivisionID) {
        int affectedRows = 0;
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Divison_ID) " +
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

    @Override
    public int modifyCustomer(String appointmentCustomerName, String appointmentCustomerAddress,
                              String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                              int appointmentCustomerDivisionID) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                    " Division_ID = ?, WHERE Customer_ID = ?";
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

    @Override
    public int deleteCustomer(int appointmentCustomerID, String appointmentCustomerName) {
        int affectedRows = 0;
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?, AND Customer_Name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentCustomerID);
            ps.setString(2, appointmentCustomerName);
            affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("ID: " + appointmentCustomerID + ", " + appointmentCustomerName + " was deleted!");
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
