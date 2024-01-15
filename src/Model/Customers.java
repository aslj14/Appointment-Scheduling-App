package Model;

/**
 *
 * This Customers class holds the necessary information for the customers.
 *
 * This Customers class will also model the customers.
 *
 * @author Ariel Johnson
 *
 */
public class Customers {

    /**
     *
     * int variable for the customer ID.
     *
     */
    private int customerID;

    /**
     *
     * String variable for the customer's name.
     *
     */
    private String customerName;

    /**
     *
     * String variable for the customer's address.
     *
     */
    private String customerAddress;

    /**
     *
     * Int variable for the customer's country ID.
     *
     */
    private int customerCountryID;

    /**
     *
     * String variable for the customer's country name.
     *
     */
    private String customerCountryName;

    /**
     *
     * String variable for the customer's postal code.
     *
     */
    private String customerPostalCode;

    /**
     *
     * String variable for the customer's phone number.
     *
     */
    private String customerPhoneNumber;

    /**
     *
     * Int variable for the customer's division ID.
     *
     */
    private int customerDivisionID;

    /**
     *
     * String variable for the customer's division.
     *
     */
    private String customerDivision;

    /**
     *
     * This is the constructor for the customers.
     *
     * @param customerID This is the customer ID.
     * @param customerName This is the customer's name.
     * @param customerAddress This is the customer's address.
     * @param customerCountryID This is the customer's country ID.
     * @param customerCountryName This is the customer's country name.
     * @param customerPostalCode This is the customer's postal code.
     * @param customerPhoneNumber This is the customer's phone number.
     * @param customerDivisionID This is the customer's division ID.
     * @param customerDivision This is the customer's division.
     */
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

    /**
     *
     * This is the getter for the ID of the customer.
     *
     * @return getCustomerID This returns the ID of the customer.
     *
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     *
     * This is the setter for the ID of the customer.
     *
     * @param customerID This is the ID of the customer.
     *
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * This is the getter for the name of the customer.
     *
     * @return getCustomerName This returns the name of the customer.
     *
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * This is the setter for the name of the customer.
     *
     * @param customerName This is the name of the customer.
     *
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * This is the getter for the address of the customer.
     *
     * @return getCustomerAddress This returns the address of the customer.
     *
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * This is the setter for the address of the customer.
     *
     * @param customerAddress This is the address of the customer.
     *
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *
     * This is the getter for the country ID of the customer.
     *
     * @return getCustomerCountryID This returns the country ID of the customer.
     *
     */
    public int getCustomerCountryID() {
        return customerCountryID;
    }

    /**
     *
     * This is the setter for the country ID of the customer.
     *
     * @param customerCountryID This is the country ID of the customer.
     *
     */
    public void setCustomerCountryID(int customerCountryID) {
        this.customerCountryID = customerCountryID;
    }

    /**
     *
     * This is the getter for the country name of the customer.
     *
     * @return getCustomerCountryName This returns the country name of the customer.
     *
     */
    public String getCustomerCountryName() {
        return customerCountryName;
    }

    /**
     *
     * This is the setter for the country name of the customer.
     *
     * @param customerCountryName This is the country name of the customer.
     *
     */
    public void setCustomerCountryName(String customerCountryName) {
        this.customerCountryName = customerCountryName;
    }

    /**
     *
     * This is the getter for the postal code of the customer.
     *
     * @return getCustomerCountryName This returns the postal code of the customer.
     *
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     *
     * This is the setter for the postal code of the customer.
     *
     * @param customerPostalCode This is the postal code of the customer.
     *
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     *
     * This is the getter for the phone number of the customer.
     *
     * @return getCustomerCountryName This returns the phone number of the customer.
     *
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     *
     * This is the setter for the phone number of the customer.
     *
     * @param customerPhoneNumber This is the phone number of the customer.
     *
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     *
     * This is the getter for the division ID of the customer.
     *
     * @return getCustomerCountryName This returns the division ID of the customer.
     *
     */
    public int getCustomerDivisionID() {
        return customerDivisionID;
    }

    /**
     *
     * This is the setter for the division ID of the customer.
     *
     * @param customerDivisionID This is the division ID of the customer.
     *
     */
    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }

    /**
     *
     * This is the getter for the division of the customer.
     *
     * @return getCustomerCountryName This returns the division of the customer.
     *
     */
    public String getCustomerDivision() {
        return customerDivision;
    }

    /**
     *
     * This is the setter for the division of the customer.
     *
     * @param customerDivision This is the division of the customer.
     *
     */
    public void setCustomerDivision(String customerDivision) {
        this.customerDivision = customerDivision;
    }

    /**
     *
     * This method is the customers toString method.
     *
     * This method will supply the syntax for the necessary information for the customers.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {
        return ("[" + Integer.toString(customerID) + "] " + customerName);
    }
}
