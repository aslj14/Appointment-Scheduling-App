package Model;

/**
 *
 * This Users class holds the necessary information for the users within the database.
 *
 * This Users class will also model the users that login to the application.
 *
 * @author Ariel Johnson
 *
 */
public class Users {

    /**
     *
     * int variable for the user's ID.
     *
     */
    private int userID;

    /**
     *
     * String variable for the username of the user.
     *
     */
    private String userName;

    /**
     *
     * String variable for the user's password.
     *
     */
    private String userPassword;

    /**
     * This is the constructor for the users within the database.
     *
     * @param userID This is the user's ID.
     * @param userName This is the username of the user.
     * @param userPassword This is the user's password.
     */
    public Users(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     *
     * This is the getter for the ID of the user.
     *
     * @return getUserID This returns the ID of the user.
     *
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * This is the setter for the ID of the user.
     *
     * @param userID This is the ID of the user.
     *
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * This is the getter for the username of the user.
     *
     * @return getUserName This returns the username of the user.
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * This is the setter for the username of the user.
     *
     * @param userName This is the username of the user.
     *
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * This is the getter for the password of the user.
     *
     * @return getUserPassword This returns the password of the user.
     *
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * This is the setter for the password of the user.
     *
     * @param userPassword This is the password of the user.
     *
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * This method is the Users in the database toString method.
     *
     * This method will supply the syntax for the necessary information for the users.
     *
     * This method also converts the hashcode to strings.
     *
     */
    @Override
    public String toString() {
        return ("[" + Integer.toString(userID) + "] " + userName);
    }
}
