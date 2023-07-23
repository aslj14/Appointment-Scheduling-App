package DAO;

import Model.Divisions;
import javafx.collections.ObservableList;

public interface DivisionsDAO {

    public ObservableList<Divisions> getAllDivisions();

    public Divisions getDivision(int divisionID);

    public int addNewDivision(String newDivision, int divisionCountryID);

    public int modifyDivision(String currentDivision, int currentDivisionCountryID, int newDivisionCountryID);

    public int modifyDivisionCountry(String currentDivision, int currentDivisionCountryID, int newDivisionCountryID);

    public int deleteCurrentDivision(int currentDivisionID, String currentDivision);



}
