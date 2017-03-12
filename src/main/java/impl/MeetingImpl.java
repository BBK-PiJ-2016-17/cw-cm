package impl;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.Set;


/**
 * @author Ginestra Ferraro
 */

public class MeetingImpl implements Meeting {
    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    public int getId() {
        return this.id;
    }

    public Calendar getDate() {
        return this.date;
    }

    public Set<Contact> getContacts() {
        return this.contacts;
    }
}
