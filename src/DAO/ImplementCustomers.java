package DAO;

import Model.Customers;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import static Helper.JDBC.connection;

public class ImplementCustomers implements CustomersDAO {

    ObservableList
    @Override
    public ObservableList<Customers> getAllCustomers() {
        return null;
    }

    @Override
    public Customers getCustomer(int appointmentCustomerID) {
        return null;
    }

    @Override
    public int addNewCustomers(String appointmentCustomerName, String appointmentCustomerAddress,
                               String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                               int appointmentCustomerDivisionID) {
        return 0;
    }

    @Override
    public int modifyCustomer(String appointmentCustomerName, String appointmentCustomerAddress,
                              String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                              int appointmentCustomerDivisionID) {
        return 0;
    }

    @Override
    public int deleteCustomer(int appointmentCustomerID, String appointmentCustomerName) {
        return 0;
    }
}
