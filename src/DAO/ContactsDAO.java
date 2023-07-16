package DAO;

import Model.Contacts;
import javafx.collections.ObservableList;

public interface ContactsDAO {

    public ObservableList<Contacts> allContacts();

    ObservableList<Contacts> getAllContacts();

    public Contacts getContacts(int contactID);

    public int modifyContacts(int contactID, String contactName, String modifiedContactName);

    public int deleteContacts(int contactID);

    public int addNewContact (int contactName);
};
