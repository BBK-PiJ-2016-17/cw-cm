package impl;

import spec.Contact;
import spec.PastMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Ginestra Ferraro
 */

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String notes;

    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts,
                           String notes) {

        super(id, date, contacts);

        // Here the only check i need is for the notes
        if (notes == null) {
            throw new NullPointerException("ERROR: The notes can't be null");
        }

        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }
}
