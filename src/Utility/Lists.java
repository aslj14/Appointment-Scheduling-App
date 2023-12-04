package Utility;

import DAO.DivisionsDAO;
import DAO.ImplementDivisions;
import Model.Divisions;
import javafx.collections.ObservableList;

public class Lists {

    public static ObservableList<Divisions> getProcessedDivisions(int countryID) {
        DivisionsDAO divisionsDAO = new ImplementDivisions();
        return divisionsDAO.getDivisionCountry(countryID);
    }
}
