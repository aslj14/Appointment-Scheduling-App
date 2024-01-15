package Model;

/**
 *
 * This Countries class holds the necessary information for the countries.
 *
 * This Countries class will also model the customer countries.
 *
 * @author Ariel Johnson
 *
 */
public class Countries {

    /**
     *
     * int variable for the country ID.
     *
     */
    private int countryID;

    /**
     *
     * String variable for the country name.
     *
     */
    private String countryName;

    /**
     *
     * This is the constructor for the countries.
     *
     * @param countryID This is the id for the country.
     * @param countryName This is the name for the country.
     *
     */
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     *
     * This is the getter for the ID of the country.
     *
     * @return getCountryID This returns the ID of the country.
     *
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     *
     * This is the setter for the ID of the country.
     *
     * @param countryID This is the ID of the country.
     *
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     *
     * This is the getter for the name of the country.
     *
     * @return getCountryName This returns the name of the country.
     *
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * This is the setter for the name of the country.
     *
     * @param countryName This is the name of the country.
     *
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     *
     * This method is the Countries toString method.
     *
     * This method will supply the syntax for the necessary information for the customer countries.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {
        return countryName;
    }
}
