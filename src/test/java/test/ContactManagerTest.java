package test;

import impl.ContactManagerImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spec.Contact;
import spec.FutureMeeting;
import spec.PastMeeting;

import java.util.HashMap;

/**
 * @author Ginestra Ferraro
 */

public class ContactManagerTest {
    private static final long serialVersionUID = 1L; // Serializable version num
    private HashMap<Integer, Contact> contacts;
    private HashMap<Integer, PastMeeting> pastMeetings;
    private HashMap<Integer, FutureMeeting> futureMeetings;
    private int lastContactId;
    private int lastMeetingId;

    @Before
    public void setUp() {
        contacts = new HashMap<>();
        pastMeetings = new HashMap<>();
        futureMeetings = new HashMap<>();
        lastContactId = 13;
        lastMeetingId = 17;
    }

    @After
    public void cleanUp() {
        contacts = null;
        pastMeetings = null;
        futureMeetings = null;
        lastContactId = 0;
        lastMeetingId = 0;
    }

}
