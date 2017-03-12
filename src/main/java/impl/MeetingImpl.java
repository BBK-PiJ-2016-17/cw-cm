package impl;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.Set;


/**
 * @author Ginestra Ferraro
 */

public abstract class MeetingImpl implements Meeting {
    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        if (date == null || contacts == null) {
            throw new NullPointerException();
        } else if (id <= 0 || contacts.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
}
