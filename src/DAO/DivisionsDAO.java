package DAO;

import Model.Divisions;
import javafx.collections.ObservableList;

/**
 *
 * This is the DAO class for the First Level Divisions.
 * This class will function as the interface for the Implement Divisions class.
 *
 * @author Ariel Johnson
 *
 */
public interface DivisionsDAO {

    /**
     *
     * This is the method that will get all of the first level divisions from the database and add the divisions to an
     * observable list.
     *
     * @return A list of all the first level divisions.
     *
     */
    public ObservableList<Divisions> getAllDivisions();

    /**
     *
     * This is the method that will get a particular first level division in accordance with that particular division's
     * ID.
     *
     * @param divisionID This is the particular division's ID.
     * @return The particular division is returned.
     *
     */
    public Divisions getDivision(int divisionID);

    /**
     *
     * This is the method that will get the first level divisions that are related to a particular country ID.
     *
     * @param countryID This is the country ID for the division.
     * @return Returns the list of divisions by country.
     *
     */
    public ObservableList<Divisions> getDivisionCountry(int countryID);

    /**
     *
     * This is the Add New Division method. This method gives the user the ability to add a new division to the database.
     *
     * @param newDivision The new division name.
     * @param divisionCountryID The new division country ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int addNewDivision(String newDivision, int divisionCountryID);

    /**
     *
     * This is the Modify Existing Division method. This method permits the user to select a division and then modify/edit
     * that division's information.
     *
     * @param currentDivision The current name of the division.
     * @param currentDivisionCountry The current country of the division.
     * @param newDivisionCountry The new division.
     * @return The amount of rows in the database that were affected.
     *
     */
    int modifyDivision(String currentDivision, String currentDivisionCountry, int newDivisionCountry);

    /**
     *
     * This is the Modify Existing Division Country method. This method permits the user to select a division and
     * then modify/edit that division's country information.
     *
     * @param divisionName The name of the division.
     * @param countryName The name of the country.
     * @param newDivisionCountryID The current country ID of the division.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int modifyDivisionCountry(String divisionName, int countryName, int newDivisionCountryID);

    /**
     *
     * This is the Delete an Existing Division method. This method permits the user to select an existing division and
     * delete that division from the database.
     *
     * @param currentDivisionID The ID for the current division.
     * @param currentDivision The name for the current division.
     * @return The amount of rows in the database that were affected.
     * 
     */
    public int deleteCurrentDivision(int currentDivisionID, String currentDivision);

}
