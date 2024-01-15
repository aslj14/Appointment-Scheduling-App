package DAO;

import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the First Level Divisions DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementDivisions implements DivisionsDAO {

    /**
     *
     * The ObservableList of all of the first level divisions in the database.
     *
     */
    ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    /**
     *
     * The list of first level divisions that are in a particular country.
     *
     */
    ObservableList<Divisions> divisionsCountry = FXCollections.observableArrayList();

    /**
     *
     * This is the method that will get all of the first level divisions from the database and add the divisions to an
     * observable list.
     *
     * @return A list of all the first level divisions.
     *
     */
    @Override
    public ObservableList<Divisions> getAllDivisions() {
        try {
            String sql = "SELECT * FROM first_level_divisions, countries WHERE " +
                   " first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                int divisionCountryID = rs.getInt("Country_ID");
                String divisionName = rs.getString("Division");
                String countryName = rs.getString("Country");
                Divisions divisions = new Divisions(divisionID, divisionCountryID, divisionName, countryName);
                allDivisions.add(divisions);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return allDivisions;
    }

    /**
     *
     * This is the method that will get a particular first level division in accordance with that particular division's
     * ID.
     *
     * @param divisionID This is the particular division's ID.
     * @return The particular division is returned.
     *
     */
    @Override
    public Divisions getDivision(int divisionID) {
        try {
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    " = countries.Country_ID AND Division_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, divisionID);

            ResultSet rs = ps.executeQuery();
            Divisions divisionsResults = null;
            if(rs.next()) {
                divisionID = rs.getInt("Division_ID");
                int countryID = rs.getInt("Country_ID");
                String divisionName = rs.getString("Division");
                String countryName = rs.getString("Country");
                divisionsResults = new Divisions(divisionID, countryID, divisionName, countryName);
            }
            return divisionsResults;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * This is the method that will get the first level divisions that are related to a particular country ID.
     *
     * @param countryID This is the country ID for the division.
     * @return Returns the list of divisions by country.
     *
     */
    @Override
    public ObservableList<Divisions> getDivisionCountry(int countryID) {
        try {
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    " = countries.Country_ID AND first_level_divisions.Country_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryID);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                countryID = rs.getInt("Country_ID");
                String divisionName = rs.getString("Division");
                String countryName = rs.getString("Country");
                Divisions divisions = new Divisions(divisionID, countryID, divisionName, countryName);
                divisionsCountry.add(divisions);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
        return divisionsCountry;
    }

    /**
     *
     * This is the Add New Division method. This method gives the user the ability to add a new division to the database.
     *
     * @param newDivision The new division name.
     * @param divisionCountryID The new division country ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int addNewDivision(String newDivision, int divisionCountryID) {
        int affectedRows = 0;
            try {
                String sql = "INSERT INTO first_level_divisions (Division, Country_ID) VALUES(?,?) ";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, newDivision);
                ps.setInt(2, divisionCountryID);
                affectedRows = ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        return affectedRows;
    }

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
    @Override
    public int modifyDivision(String currentDivision, String currentDivisionCountry, int newDivisionCountry) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE first_level_divisions SET Division = ? WHERE Division = ? AND Country_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, currentDivision);
            ps.setString(2, currentDivisionCountry);
            ps.setInt(3, newDivisionCountry);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return affectedRows;
    }

    /**
     *
     * This is the Modify Existing Division Country method. This method permits the user to select a division and
     * then modify/edit that division's country information.
     *
     * @param divisionName The name of the division.
     * @param country The name of the country.
     * @param newDivisionCountryID The current country ID of the division.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int modifyDivisionCountry(String divisionName, int country, int newDivisionCountryID) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE first_level_divisions SET Country_ID = ? WHERE Division = ? AND Country_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, country);
            ps.setString(2, divisionName);
            ps.setInt(3, newDivisionCountryID);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return affectedRows;
    }

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
    @Override
    public int deleteCurrentDivision(int currentDivisionID, String currentDivision) {
        int affectedRows = 0;
            try {
                String sql = "DELETE FROM first_level_divisions WHERE Division_ID = ? AND Division = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, currentDivisionID);
                ps.setString(2, currentDivision);
                affectedRows = ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        return affectedRows;
    }
}
