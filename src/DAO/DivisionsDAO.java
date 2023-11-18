package DAO;

import Model.Divisions;
import javafx.collections.ObservableList;

public interface DivisionsDAO {

    public ObservableList<Divisions> getAllDivisions();

    public Divisions getDivision(int divisionID);

    public ObservableList<Divisions> getDivisionCountry(int divisionID);

    public int addNewDivision(String newDivision, int divisionCountryID);

    int modifyDivision(String currentDivision, String currentDivisionCountry, int newDivisionCountry);

    public int modifyDivisionCountry(String divisionName, int countryName, int newDivisionCountryID);

    public int deleteCurrentDivision(int currentDivisionID, String currentDivision);



}
