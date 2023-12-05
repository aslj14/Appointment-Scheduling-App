package DAO;

import Model.Customers;
import javafx.collections.ObservableList;

public interface CustomersDAO {

    public ObservableList<Customers> getAllCustomers();

    public Customers getCustomer(int appointmentCustomerID);

    public int addNewCustomers(String appointmentCustomerName, String appointmentCustomerAddress,
                               String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                               int appointmentCustomerDivisionID);

    public int modifyCustomer(int appointmentCustomerID, String appointmentCustomerName, String appointmentCustomerAddress,
                              String appointmentCustomerPostalCode, String appointmentCustomerPhoneNumber,
                              int appointmentCustomerDivisionID);
    public int deleteCustomer(int appointmentCustomerID, String appointmentCustomerName);
}
