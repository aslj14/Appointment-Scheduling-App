package DAO;

import Model.Contacts;
import javafx.collections.ObservableList;

/**
 *
 * This is the DAO class for the Contacts.
 * This class will function as the interface for the Implement Contacts class.
 *
 * @author Ariel Johnson
 *
 */
public interface ContactsDAO {

    /**
     *
     * This is the method that will get all of the contacts from the database and add the contacts to an
     * observable list.
     *
     * @return A list of all the contacts.
     *
     */
    ObservableList<Contacts> getAllContacts();

    /**
     *
     * This is the method that gets a particular contact by their ID.
     *
     * @param contactID The contact ID for that contact.
     * @return The particular contact and all information associated to that contact is returned.
     *
     */
    public Contacts getContacts(int contactID);

    /**
     *
     * This is the method that allows an existing contact to be modified.
     *
     * @param contactID The existing contact's ID.
     * @param contactName The existing contact's name.
     * @param modifiedContactName The modified contact's name.
     * @return Returns the rows in the database that were affected.
     *
     */
    public int modifyContacts(int contactID, String contactName, String modifiedContactName);

    /**
     *
     * This method permits the user to delete an existing contact from the database.
     *
     * @param contactID The selected contact's ID.
     * @return Returns the rows in the database that were affected.
     *
     */
    public int deleteContacts(int contactID);

    /**
     *
     * This method permits the user to add a new contact to the database.
     *
     * @param newContactName The name for the new contact.
     * @return Returns the rows in the database that were affected.
     * 
     */
    public int addNewContact (String newContactName);

};
