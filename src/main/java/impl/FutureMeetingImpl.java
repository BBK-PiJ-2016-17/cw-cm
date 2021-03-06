package impl;

import spec.Contact;
import spec.FutureMeeting;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Ginestra Ferraro
 */

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
    private static final long serialVersionUID = 1L; // Serializable version num

    public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);

        if (contacts.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
