package Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Time {

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
            }
            if(i > 23) {
                timesList.add(LocalTime.of(midnightOrMore, 0));
                midnightOrMore += 1;
            }
        }

        return timesList;
    }

}
