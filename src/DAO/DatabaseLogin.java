package DAO;

import Model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static Helper.JDBC.connection;

public class DatabaseLogin {

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

    public static LocalDateTime getLoginLocalDateTime() {
        LocalDateTime localDT = LocalDateTime.now(ZoneId.systemDefault());
        return localDT;
    }
}
