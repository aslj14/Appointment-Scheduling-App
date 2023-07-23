package DAO;

import Model.Countries;
import javafx.collections.ObservableList;

public interface CountriesDAO {

    public ObservableList<Countries> getAllCountries();

    public Countries getCountry(int countryID);

    public int addNewCountry(String newCountry);

    public int modifyCountry(int newCountryID, String currentCountry, String newCountry);

    public int deleteCountry(int currentCountryID, String currentCountryName);


}
