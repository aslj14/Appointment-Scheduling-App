package DAO;

import Model.Users;
import javafx.collections.ObservableList;

/**
 *
 * This is the DAO class for the Users.
 * This class will function as the interface for the Implement Users class.
 *
 * @author Ariel Johnson
 *
 */
public interface UsersDAO {

    /**
     *
     * This is the method that will get all of the users from the database and add the users to an
     * observable list.
     *
     * @return A list of all users.
     *
     */
    public ObservableList<Users> getAllUsers();

    /**
     *
     * This is the method that will get a particular user in accordance with that particular user's
     * ID.
     *
     * @param userID This is the particular user's ID.
     * @return The particular user's information is returned.
     */
    public Users getUsers(int userID);

    /**
     *
     * This is the Add User method that permits a new user to be added to the database.
     *
     * @param userName The new user's username.
     * @param userPassword The user's password.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int addNewUser(String userName, String userPassword);

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
    public int modifyUserName(String userName, String userPassword, String newUserName);

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
    public int modifyUserPassword(String userName, String userPassword, String newPassword);

    /**
     *
     * This is the Delete User method. This method allows for a user to be deleted from the database.
     *
     * @param userID The user's ID.
     * @return The amount of rows in the database that were affected.
     *
     */
    public int deleteCurrentUser(int userID);
}
