package Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * This class is for controlling the company's business hours in a list.
 *
 * @author Ariel Johnson
 *
 */
public class Time {

    /**
     *
     * This is the Business Hours method for the company.
     *
     * @param systemZoneID This is the zone ID for the user's operating system.
     * @param businessZoneID This is the zone ID for the company's business hours which are in Eastern Standard Time.
     * @param businessStart This is the start time for the company's business hours.
     * @param businessHours This is the number of hours for the company.
     * @return timesList This returns the list of the company's business hours.
     *
     */
    public static ObservableList<LocalTime> businessHours(ZoneId systemZoneID, ZoneId businessZoneID,
                                                          LocalTime businessStart, int businessHours) {
        ObservableList<LocalTime> timesList = FXCollections.observableArrayList();
        ZonedDateTime businessZoneDT = ZonedDateTime.of(LocalDate.now(), businessStart, businessZoneID);
        ZonedDateTime localZoneDT = ZonedDateTime.ofInstant(businessZoneDT.toInstant(),  systemZoneID);
        int localStartHour = localZoneDT.getHour();
        int allHours = localStartHour + businessHours;
        int midnightOrMore = 0;

        for(int i = localStartHour; i <= allHours; i++) {
            if(i < 24) {
                timesList.add(LocalTime.of(i, 0));
                timesList.add(LocalTime.of(i, 15));
                timesList.add(LocalTime.of(i, 30));
                timesList.add(LocalTime.of(i, 45));
            }
            if(i > 23) {
                timesList.add(LocalTime.of(midnightOrMore, 0));
                midnightOrMore += 1;
            }
        }

        return timesList;
    }

}
