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

    private Meeting meeting, anotherMeeting;

    @Before
    public void mockMeeting() {
        date = Calendar.getInstance();
        contacts = new HashSet<Contact>();

        Contact contact = new ContactImpl(24, "Marty McFly");
        contacts.add(contact);

        meeting = new FutureMeetingImpl(2, date, contacts);
    }

    @Test(expected = NullPointerException.class)
    public void isItNull() {
        System.out.println("==== Testing constructor for NPE");
        meeting = new FutureMeetingImpl(3, null, contacts);
        anotherMeeting = new FutureMeetingImpl(4, date, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isItLegal() {
        System.out.println("==== Testing constructor for IAE / Negative id");
        meeting = new FutureMeetingImpl(-2, date, contacts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isAnyoneIn() {
        System.out.println("==== Testing constructor for IAE / Empty contacts");
        contacts = null;
        contacts = new HashSet<Contact>();
        meeting = new FutureMeetingImpl(13, date, contacts);
    }

    @Test
    public void getIdTest() {
        System.out.println("==== Testing the getId() method");
        int getTheId = meeting.getId();
        int expected = 2;
        assertEquals(expected, getTheId);
    }

    @Test
    public void getDateTest() {
        System.out.println("==== Testing the getDate() method");
        date = null;
        date = Calendar.getInstance();
        meeting = new FutureMeetingImpl(13, date, contacts);
        Calendar getTheDate = meeting.getDate();
        Calendar expected = date;
        assertEquals(expected, getTheDate);
    }

    @Test
    public void getContactsTest() {
        System.out.println("==== Testing the getContacts() method");
        // How do I test that the hash is the same?
    }

    @After
    public void cleanup() {
        date = null;
        contacts = null;
    }
}
