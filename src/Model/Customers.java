package Model;

public class Customers {

    private int customerID;

    private String customerName;

    private String customerAddress;

    private int customerCountryID;

    private String customerCountryName;

    private String customerPostalCode;

    private String customerPhoneNumber;

    private int customerDivisionID;

    private String customerDivision;

    public Customers(int customerID, String customerName, String customerAddress, int customerCountryID,
                     String customerCountryName, String customerPostalCode, String customerPhoneNumber,
                     int customerDivisionID, String customerDivision) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCountryID = customerCountryID;
        this.customerCountryName = customerCountryName;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerDivisionID = customerDivisionID;
        this.customerDivision = customerDivision;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerCountryID(int customerCountryID) {
        return customerCountryID;
    }

    public void setCustomerCountryID(int customerCountryID) {
        this.customerCountryID = customerCountryID;
    }

    public String getCustomerCountryName() {
        return customerCountryName;
    }

    public void setCustomerCountryName(String customerCountryName) {
        this.customerCountryName = customerCountryName;
    }

    public String getCustomerPostalCode() {

        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
        customerPhoneNumber.toString();
    }

    public int getCustomerDivisionID() {
        return customerDivisionID;
    }

    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }

    public String getCustomerDivision(String customerDivision) {
        return customerDivision;
    }

    public void setCustomerDivision(String customerDivision) {
        this.customerDivision = customerDivision;
    }

    @Override
    public String toString() {
        return (Integer.toString(customerID) + customerName);
    }
}
