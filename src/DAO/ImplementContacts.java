package DAO;

import Model.Contacts;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

public abstract class ImplementContacts implements ContactsDAO {

    ObservableList<Contacts> allContacts = FXCollections.observableArrayList();


    @Override
    public ObservableList<Contacts> getAllContacts() {
        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Contacts contacts = new Contacts(contactID, contactName);
                allContacts.add(contacts);
            }
            return allContacts;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }


    @Override
    public Contacts getContacts(int contactID) {
        return null;
    }

    @Override
    public int modifyContacts(int contactID, String contactName, String modifiedContactName) {
        return 0;
    }

    @Override
    public int deleteContacts(int contactID) {
        return 0;
    }

    @Override
    public int addNewContact(int contactName) {
        return 0;
    }
}
