package DAO;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

public class ImplementUsers implements UsersDAO {

    ObservableList<Users> allUsers = FXCollections.observableArrayList();

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
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return allUsers;
    }

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
