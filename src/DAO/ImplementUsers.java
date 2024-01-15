package DAO;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the Users DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementUsers implements UsersDAO {

    /**
     *
     * The ObservableList of all of the users in the database.
     *
     */
    ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /**
     *
     * This is the method that will get all of the users from the database and add the users to an
     * observable list.
     *
     * @return A list of all users.
     *
     */
    @Override
    public ObservableList<Users> getAllUsers() {
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users users = new Users(userID, userName, userPassword);
                allUsers.add(users);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return allUsers;
    }

    /**
     *
     * This is the method that will get a particular user in accordance with that particular user's
     * ID.
     *
     * @param userID This is the particular user's ID.
     * @return The particular user's information is returned.
     *
     */
    @Override
    public Users getUsers(int userID) {
        try {
            String sql = "SELECT * FROM users WHERE User_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();
            Users ur = null;

            if (rs.next()) {
                userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password: ");
                ur = new Users(userID, userName, userPassword);
            }
            return ur;
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
        }
        return null;
    }

    /**
     *
     * This is the Add User method that permits a new user to be added to the database.
     *
     * @param userName The new user's username.
     * @param userPassword The user's password.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int addNewUser(String userName, String userPassword) {

        int affectedRows = 0;

        try {
            String sql = "INSERT INTO users (User_Name, Password) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
            return affectedRows;
    }

    /**
     *
     * This is the Modify Username method. This method permits the user's username to be modified/edited.
     *
     * @param userName The user's username.
     * @param userPassword The user's password.
     * @param newUserName The user's modified username.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int modifyUserName(String userName, String userPassword, String newUserName) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE users SET User_Name = ? WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ps.setString(3, newUserName);
            affectedRows = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return affectedRows;
    }

    /**
     *
     * This is the Modify Password method. This method permits the user's password to be modified/edited.
     *
     * @param userName The user's username.
     * @param userPassword The user's password.
     * @param newPassword The user's modified password.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int modifyUserPassword(String userName, String userPassword, String newPassword) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE users SET Password = ? WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ps.setString(3, newPassword);
            affectedRows = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return affectedRows;
    }

    /**
     *
     * This is the Delete User method. This method allows for a user to be deleted from the database.
     *
     * @param userID The user's ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    @Override
    public int deleteCurrentUser(int userID) {
        int affectedRows = 0;

        try {
            String sql = "DELETE FROM users WHERE User_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            affectedRows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
            return affectedRows;
    }



}
