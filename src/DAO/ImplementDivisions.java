package DAO;

import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

public class ImplementDivisions implements DivisionsDAO {

    ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    ObservableList<Divisions> divisionsCountry = FXCollections.observableArrayList();
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

    @Override
    public ObservableList<Divisions> getDivisionCountry(int countryID) {
        try {
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    " AND first_level_divisions.Country_ID = ? AND countries.Country_ID";
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
