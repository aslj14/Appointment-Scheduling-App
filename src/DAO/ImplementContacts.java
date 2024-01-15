package DAO;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Helper.JDBC.connection;

/**
 *
 * This is the implementation class that implements the Contacts DAO class.
 *
 * @author Ariel Johnson
 *
 */
public class ImplementContacts implements ContactsDAO {

    /**
     *
     * The ObservableList of all of the contacts in the database.
     *
     */
    ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     *
     * This method is the method that gets all of the contacts from the database and then adds those contacts to
     * a list.
     *
     * @return This method returns the list of all contacts.
     *
     */
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

    /**
     *
     * This is the method that will get a particular contact based on the contact's ID.
     *
     * @param contactID The contact ID for that contact.
     * @return This method returns the particular contact and that contact's information.
     *
     */
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

    /**
     *
     * This is the Modify Existing Contact method.
     *
     * <p>This method will allow the user to select an existing contact and then modify/edit the information for that
     * contact and then save it to the database.</p>
     *
     * @param contactID The existing contact's ID.
     * @param contactName The existing contact's name.
     * @param modifiedContactName The modified contact's name.
     * @return Returns the rows in the database that were affected.
     *
     */
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

    /**
     *
     * This is the Delete Existing Contact method.
     *
     * <p>This method permits the user to select an existing contact and then delete the contact and all
     * information associated with the contact, from the database.</p>
     *
     * @param contactID The selected contact's ID.
     * @return Returns the rows in the database that were affected.
     *
     */
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

    /**
     *
     * This is the Add New Contact method.
     *
     * <p>This method opens the connection to the database and then adds the new contact and their information to the
     * database.</p>
     *
     * @param contactName The name for the new contact.
     * @return Returns the rows in the database that were affected.
     * 
     */
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
