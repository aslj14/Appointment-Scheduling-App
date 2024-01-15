package DAO;

import Helper.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the Countries DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementCountries implements CountriesDAO {

    /**
     *
     * The ObservableList of all of the countries in the database.
     *
     */
    ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /**
     *
     * This is the method that will get all of the countries from the database and add the countries to an
     * observable list.
     *
     * @return A list of all the countries.
     *
     */
    @Override
    public ObservableList<Countries> getAllCountries() {
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                Countries countries = new Countries(countryID, country);
                allCountries.add(countries);
            }
        } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        return allCountries;
        }

    /**
     *
     * This is the method that will get a particular country in accordance with that particular country's
     * ID.
     *
     * @param countryID This is the particular country's ID.
     * @return The particular country's information is returned.
     *
     */
    @Override
    public Countries getCountry(int countryID) {
        try {
            String sql = "SELECT * FROM countries WHERE Country_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryID);

            ResultSet rs = ps.executeQuery();
            Countries countryResult = null;
            if(rs.next()) {
                countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                countryResult = new Countries(countryID, country);
            }
            return countryResult;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * This is the Add Country method that will permit the user to add a new country to the
     * database..
     *
     * @param newCountry The new country's name.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int addNewCountry(String newCountry) {
        int affectedRows = 0;
            try {
                String sql = "INSERT INTO countries (Country) VALUES (?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, newCountry);
                affectedRows = ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
            return affectedRows;
    }

    /**
     *
     * This is the Modify Country method that will allow for a selected country to be
     * modified/edited.
     *
     * @param newCountryID The ID for the modified country.
     * @param currentCountry The current name of the country.
     * @param newCountry The new name of the country.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int modifyCountry(int newCountryID, String currentCountry, String newCountry) {
        int affectedRows = 0;
            try {
                String sql = "UPDATE countries SET Country = ? WHERE Country = ?, AND Country_ID = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, newCountry);
                ps.setString(2, currentCountry);
                ps.setInt(3, newCountryID);
                affectedRows = ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        return affectedRows;
    }

    /**
     *
     * This is the Delete Country method that will delete a selected country
     * from the database.
     *
     * @param currentCountryID The ID of the current country.
     * @param currentCountryName The name of the current country.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int deleteCountry(int currentCountryID, String currentCountryName) {
        int affectedRows = 0;
        JDBC.openConnection();
        DivisionsDAO divisionsDAO = new ImplementDivisions();

        try {
            if (divisionsDAO.getDivisionCountry(currentCountryID).isEmpty()) {
                try {
                    String sql = "DELETE FROM contacts WHERE Country_ID = ? AND Country = ?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, currentCountryID);
                    ps.setString(2, currentCountryName);
                    affectedRows = ps.executeUpdate();
                } catch (Exception e) {
                    System.out.println("Error:" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return affectedRows;
    }
}
