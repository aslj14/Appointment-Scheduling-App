package DAO;

import Helper.JDBC;
import Model.Countries;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

public class ImplementCountries implements CountriesDAO {

    ObservableList<Countries> allCountries = FXCollections.observableArrayList();

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
