package Model;

public class Divisions {

    private int divisionID;

    private String divisionName;

    private String divisionCountryID;

    public Divisions(int divisionID, String divisionName, String divisionCountryID) {
        this.divisionID = divisionID;
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
