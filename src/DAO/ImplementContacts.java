package DAO;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

public class ImplementContacts implements ContactsDAO {

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
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactID);

            ResultSet rs = ps.executeQuery();
            Contacts contactsResult = null;
            if (rs.next()) {
                contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Contacts contacts = new Contacts(contactID, contactName);
                allContacts.add(contacts);
            }
            return contactsResult;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public int modifyContacts(int contactID, String contactName, String modifiedContactName) {
        try {
            String sql = "UPDATE contacts SET Contact_Name = ? WHERE Contact_Name = ? AND Contact_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contactName);
            ps.setString(2, modifiedContactName);
            ps.setInt(3, contactID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteContacts(int contactID) {
        try {
            String sql = "DELETE FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return 0;
    }

    @Override
    public int addNewContact(String contactName) {
        try {
            String sql = "INSERT INTO contacts (Contact_Name) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contactName);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return 0;
    }
}
