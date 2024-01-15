package Utility;

import DAO.DivisionsDAO;
import DAO.ImplementDivisions;
import Model.Divisions;
import javafx.collections.ObservableList;

/**
 *
 * This Lists class is for controlling the lists of first level divisions.
 *
 * @author Ariel Johnson
 *
 */
public class Lists {

    /**
     *
     * This is the method that will process/filter the first level divisions. This method will take the country that is
     * selected and use the country ID to return the list of first level divisions for that selected country.
     *
     * @param countryID This is the ID for the country that is selected.
     * @return The first level divisions associated with the selected country will be returned.
     * 
     */
    public static ObservableList<Divisions> getProcessedDivisions(int countryID) {
        DivisionsDAO divisionsDAO = new ImplementDivisions();
        return divisionsDAO.getDivisionCountry(countryID);
    }
}
