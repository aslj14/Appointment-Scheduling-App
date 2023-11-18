package DAO;

import Model.Users;
import javafx.collections.ObservableList;

public interface UsersDAO {

    public ObservableList<Users> getAllUsers();

    public Users getUsers(int userID);

    public int addNewUser(String userName, String userPassword);

    public int deleteCurrentUser(int userID);
}
