package test;

import impl.ContactImpl;
import impl.FutureMeetingImpl;
import impl.MeetingImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import spec.Contact;
import spec.Meeting;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Ginestra Ferraro
 */

public class MeetingTest {
    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    private Meeting meeting;

    @Before
    public void mockMeeting() {
        date = Calendar.getInstance();
        contacts = new HashSet<Contact>();

        Contact contact = new ContactImpl(24, "Marty McFly");
        contacts.add(contact);

        meeting = new FutureMeetingImpl(2, date, contacts);
    }

    @Test
    public void getIdTest() {
        System.out.println("==== Testing the getId() method");
        int getTheId = meeting.getId();
        int expected = 2;
        assertEquals(expected, getTheId);
    }

    @After
    public void cleanup() {
        date = null;
        contacts = null;
    }
}
