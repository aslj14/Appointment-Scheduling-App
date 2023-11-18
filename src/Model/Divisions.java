package Model;

public class Divisions {

    private int divisionID;

    private int countryID;

    private String divisionName;

    private String divisionCountryID;

    public Divisions(int divisionID, int countryID, String divisionName, String divisionCountryID) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.divisionName = divisionName;
        this.divisionCountryID = divisionCountryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionCountryID() {
        return divisionCountryID;
    }

    public void setDivisionCountryID(String divisionCountryID) {
        this.divisionCountryID = divisionCountryID;
    }
}
