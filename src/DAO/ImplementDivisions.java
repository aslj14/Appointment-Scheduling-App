package DAO;

import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import static Helper.JDBC.connection;

public class ImplementDivisions implements DivisionsDAO {

    ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    @Override
    public ObservableList<Divisions> getAllDivisions() {
        return null;
    }

    @Override
    public Divisions getDivision(int divisionID) {
        return null;
    }

    @Override
    public int addNewDivision(String newDivision, int divisionCountryID) {
        return 0;
    }

    @Override
    public int modifyDivision(String currentDivision, int currentDivisionCountryID, int newDivisionCountryID) {
        return 0;
    }

    @Override
    public int modifyDivisionCountry(String currentDivision, int currentDivisionCountryID, int newDivisionCountryID) {
        return 0;
    }

    @Override
    public int deleteCurrentDivision(int currentDivisionID, String currentDivision) {
        return 0;
    }

    ObservableList



}
