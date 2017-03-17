package test;

import impl.ContactImpl;
import impl.PastMeetingImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import spec.Contact;
import spec.PastMeeting;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Ginestra Ferraro
 */

public class PastMeetingTest {

    private Calendar date;
    private PastMeeting meeting;
    private Set<Contact> contacts;
    private String notes;

    @Before
    public void setUp() {
        date = Calendar.getInstance();
        contacts = new HashSet<>();
        contacts.add(new ContactImpl(1, "The Project Manager"));
        notes = "Introducing the project manager to the team.";
        meeting = new PastMeetingImpl(1, date, contacts, notes);
    }

    @Test
    public void getNotesTest() {
        String output = meeting.getNotes();
        String expected = notes;
        assertEquals(expected, output);
    }

    @Test(expected = NullPointerException.class)
    public void NotesNullPointerExTest() {
        new PastMeetingImpl(2, date, contacts, null);
    }

    @After
    public void cleanUp() {
        date = null;
        contacts = null;
        notes = null;
        meeting = null;
    }
}
