package impl;

import spec.Contact;

/**
 * @author Ginestra Ferraro
 *
 * From the specs in the coursework PDF:
 * The implementation of this interface must have two constructors. The most general constructor must have three
 * parameters: int, String, String. The first one corresponds to the ID provided by the ContactManager, the next one
 * corresponds to the name, and the last one corresponds to the initial set of notes about the contact. Another, more
 * restricted constructor must have two parameters only: ID and name. If the ID provided is zero or negative, an
 * IllegalArgumentException must be thrown. If any of the references / pointers passed as parameters to the constructor
 * is null, a NullPointerException must be thrown.
 */

public class ContactImpl implements Contact {

    private int contactID;
    private String contactName;
    private String notes;

    public ContactImpl(int contactID, String contactName, String notes) {

        if (contactID <= 0 || contactName.isEmpty() || notes.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Invalid arguments provided");
        } else if (contactName == null) {
            throw new NullPointerException("ERROR: The contact name can't be null");
        }

        this.contactID = contactID;
        this.contactName = contactName;
        this.notes = notes;

        addNotes(notes);
    }

    public ContactImpl(int contactID, String contactName) {
        if (contactID <= 0) {
            throw new IllegalArgumentException("ERROR: invalid ID number");
        } else if (contactName == null) {
            throw new NullPointerException("ERROR: the contact name can't be null");
        }

        this.contactID = contactID;
        this.contactName = contactName;
    }

    public int getId() {
        return this.contactID;
    }

    public String getName() {
        return this.contactName;
    }

    public String getNotes() {
        return this.notes;
    }

    public void addNotes(String note) {
        if (note.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Notes can't be empty");
        } else if (note == null) {
            throw new NullPointerException("ERROR: Notes can't be null");
        }

        notes = note;
    }
}
