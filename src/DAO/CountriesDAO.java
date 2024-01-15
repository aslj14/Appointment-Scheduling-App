package DAO;

import Model.Countries;
import javafx.collections.ObservableList;

/**
 *
 * This is the DAO class for the Countries.
 * This class will function as the interface for the Implement Countries class.
 *
 * @author Ariel Johnson
 *
 */
public interface CountriesDAO {

    /**
     *
     * This is the method that will get all of the countries from the database and add the countries to an
     * observable list.
     *
     * @return A list of all the countries.
     *
     */
    public ObservableList<Countries> getAllCountries();

    /**
     *
     * This is the method that will get a particular country in accordance with that particular country's
     * ID.
     *
     * @param countryID This is the particular country's ID.
     * @return The particular country's information is returned.
     *
     */
    public Countries getCountry(int countryID);

    /**
     *
     * This is the Add Country method for the Countries DAO class.
     *
     * @param newCountry The new country's name.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int addNewCountry(String newCountry);

    /**
     *
     * This is the Modify Country method in the Countries DAO class.
     *
     * @param newCountryID The ID for the modified country.
     * @param currentCountry The current name of the country.
     * @param newCountry The new name of the country.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int modifyCountry(int newCountryID, String currentCountry, String newCountry);

    /**
     *
     * This is the Delete Country method in the Countries DAO class.
     *
     * @param currentCountryID The ID of the current country.
     * @param currentCountryName The name of the current country.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int deleteCountry(int currentCountryID, String currentCountryName);


}
