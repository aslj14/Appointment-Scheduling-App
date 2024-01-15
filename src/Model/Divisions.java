package Model;

/**
 *
 * This Divisions class holds the necessary information for the first level divisions (states and provinces).
 *
 * This Appointments class will also model the first level divisions for the countries.
 *
 * @author Ariel Johnson
 *
 */
public class Divisions {

    /**
     *
     * int variable for the division ID.
     *
     */
    private int divisionID;

    /**
     *
     * int variable for the country ID.
     *
     */
    private int countryID;

    /**
     *
     * String variable for the division name.
     *
     */
    private String divisionName;

    /**
     *
     * String variable for the division's country name.
     *
     */
    private String divisionCountryName;

    /**
     *
     * This is the constructor for the first level divisions.
     * .
     * @param divisionID This is the division ID.
     * @param countryID This is the country ID.
     * @param divisionName This is the division name.
     * @param divisionCountryName This is the division's country name.
     *
     */
    public Divisions(int divisionID, int countryID, String divisionName, String divisionCountryName) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.divisionName = divisionName;
        this.divisionCountryName = divisionCountryName;
    }

    /**
     *
     * This is the getter for the ID of the division.
     *
     * @return getDivisionID This returns the ID of the division.
     *
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     *
     * This is the setter for the ID of the division.
     *
     * @param divisionID This is the ID of the division.
     *
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     *
     * This is the getter for the name of the division.
     *
     * @return getDivisionName This returns the name of the division.
     *
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     *
     * This is the setter for the name of the division.
     *
     * @param divisionName This is the name of the division.
     *
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     *
     * This is the getter for the country name of the division.
     *
     * @return getDivisionCountryName This returns the country name of the division.
     *
     */
    public String getDivisionCountryName() {
        return divisionCountryName;
    }

    /**
     *
     * This is the setter for the country name of the division.
     *
     * @param divisionCountryName This is the country name of the division.
     *
     */
    public void setDivisionCountryName(String divisionCountryName) {
        this.divisionCountryName = divisionCountryName;
    }

    /**
     *
     * This method is the divisions toString method.
     *
     * This method will supply the syntax for the necessary information for the first level division.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {
        return (divisionName);
    }
}
