package DAO;

import Model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static Helper.JDBC.connection;

/**
 *
 * This is the Database Login class. This class permits the user to log in to the database
 * that already exists.
 *
 * @author Ariel Johnson
 *
 */
public class DatabaseLogin {

    /**
     *
     * This is the method that permits the user to login to the database. The method will extract the username and
     * password that the user enters and then verifies the information.
     *
     * @param userName This is the username that the user inputs during the initial login to the application.
     * @param userPassword This is the password that the user inputs during the initial login to the application.
     * @return This returns the object of the user if the username and password entered equals to a user in the database.
     *
     */
    public static Users databaseLoginQuery(String userName, String userPassword) {
        try {
            String sql = "SELECT * FROM users WHERE User_Name=? AND PASSWORD=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);

            ResultSet rs = ps.executeQuery();
            Users usersResult = null;
            if (rs.next()) {
                int userID = rs.getInt("User_ID");
                userName = rs.getString("User_Name");
                userPassword = rs.getString("Password");
                usersResult = new Users(userID, userName, userPassword);
            }
            return usersResult;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * This method will get the local date and local time during the initial login. This method will produce the local
     * date and local time of the user's operating system.
     *
     * @return localDT, which is the local date and local time of the user's operating system
     *
     */
    public static LocalDateTime getLoginLocalDateTime() {
        LocalDateTime localDT = LocalDateTime.now(ZoneId.systemDefault());
        return localDT;
    }
}
