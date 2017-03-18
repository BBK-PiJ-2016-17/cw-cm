package impl;

import spec.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ginestra Ferraro
 */

public class ContactManagerImpl implements ContactManager, Serializable {

    private static final long serialVersionUID = 1L; // Serializable version num
    private HashMap<Integer, Contact> contacts = new HashMap<>();
    private HashMap<Integer, PastMeeting> pastMeetings = new HashMap<>();
    private HashMap<Integer, FutureMeeting> futureMeetings = new HashMap<>();
    private int lastContactId;
    private int lastMeetingId;

    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {

        if (date == null) {
            throw new NullPointerException();
        }

        Calendar now = Calendar.getInstance();

        if (contacts == null || date.before(now)) {
            throw new IllegalArgumentException();
        }

        for (Contact contact : contacts) {
            if (!this.contacts.containsKey(contact.getId())) {
                throw new IllegalArgumentException();
            }
        }

        int id = this.getNewMeetingId();

        FutureMeeting futureMeeting = new FutureMeetingImpl(id, date, contacts);

        this.futureMeetings.put(id, futureMeeting);
        return id;
    }

    public PastMeeting getPastMeeting(int id) {

        PastMeeting pastMeeting = this.pastMeetings.get(id);

        Calendar now = Calendar.getInstance();

        if (pastMeeting != null && pastMeeting.getDate().after(now)) {
            throw new IllegalStateException();
        }

        return pastMeeting;
    }

    public FutureMeeting getFutureMeeting(int id) {
        FutureMeeting futureMeeting = this.futureMeetings.get(id);

        Calendar now = Calendar.getInstance();

        if (futureMeeting != null && futureMeeting.getDate().after(now)) {
            throw new IllegalStateException();
        }

        return futureMeeting;
    }

    public Meeting getMeeting(int id) {
        for (Meeting meeting : this.futureMeetings.values()) {
            if (meeting.getId() == id) {
                return meeting;
            }
        }
        for (Meeting meeting : this.pastMeetings.values()) {
            if (meeting.getId() == id) {
                return meeting;
            }
        }
        return null;
    }

    public List<Meeting> getFutureMeetingList(Contact contact) {

        if (contact == null) {
            throw new NullPointerException();
        }

        if (!this.contacts.containsKey(contact.getId())) {
            throw new IllegalArgumentException();
        }

        List<Meeting> meetings = new ArrayList<>();

        for (Meeting meeting : this.futureMeetings.values()) {
            if (meeting.getContacts().contains(contact)) {
                meetings.add(meeting);
            }
        }

        Collections.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting one, Meeting two) {
                return one.getDate().compareTo(two.getDate());
            }
        });

        return meetings;
    }

    public List<Meeting> getMeetingListOn(Calendar date) {
        if (date == null) {
            throw new NullPointerException();
        }

        List<Meeting> meetings = new ArrayList<>();

        for (Meeting meeting : this.pastMeetings.values()) {
            if (meeting.getDate().equals(date)) {
                meetings.add(meeting);
            }
        }

        for (Meeting meeting : this.futureMeetings.values()) {
            if (meeting.getDate().equals(date)) {
                meetings.add(meeting);
            }
        }

        Collections.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting one, Meeting two) {
                return one.getDate().compareTo(two.getDate());
            }
        });

        return meetings;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        if (contact == null) {
            throw new NullPointerException();
        }

        if (!this.contacts.containsKey(contact.getId())) {
            throw new IllegalArgumentException();
        }

        List<PastMeeting> meetings = new ArrayList<>();
        for (PastMeeting meeting : this.pastMeetings.values()) {
            if (meeting.getContacts().contains(contact)) {
                meetings.add(meeting);
            }
        }

        Collections.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting one, Meeting two) {
                return one.getDate().compareTo(two.getDate());
            }
        });
        return meetings;
    }

    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String
            notes) {
        if (contacts == null || date == null || notes == null) {
            throw new NullPointerException();
        }

        if (contacts.size() == 0) {
            throw new IllegalArgumentException();
        }

        for (Contact c : contacts) {
            if (!this.contacts.containsKey(c.getId())) {
                throw new IllegalArgumentException();
            }
        }

        int id = this.getNewMeetingId();
        PastMeeting meeting = new PastMeetingImpl(id, date, contacts, notes);
        this.pastMeetings.put(id, meeting);

        return id;
    }

    public PastMeeting addMeetingNotes(int id, String notes) {
        throw new UnsupportedOperationException("No notes");
    }

    public int addNewContact(String name, String notes) {
        if (name == null || notes == null) {
            throw new NullPointerException();
        }

        if (name.isEmpty() || notes.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Contact contact = new ContactImpl(this.getNewContactId(), name, notes);
        this.contacts.put(contact.getId(), contact);
        return contact.getId();
    }

    public Set<Contact> getContacts(String name) {
        if (name == null) {
            throw new NullPointerException();
        }

        HashSet<Contact> contacts = new HashSet<>();
        for(Contact contact : this.contacts.values()) {
            if (contact.getName().equals(name)) {
                contacts.add(contact);
            }
        }

        return contacts;
    }

    public Set<Contact> getContacts(int... ids) {
        if (ids.length == 0) {
            throw new IllegalArgumentException();
        }

        HashSet<Contact> contacts = new HashSet<>();
        for(int id : ids) {
            Contact contact = this.contacts.get(id);
            if (contact == null) {
                throw new IllegalArgumentException();
            }

            contacts.add(contact);
        }

        return contacts;
    }

    public void flush() {
        String home = System.getProperty("user.home");
        String filePath = new File(home, "contacts.txt").toString();

        try {
            FileOutputStream fileOutput = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(this);
            outputStream.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Private method to increment contact ids and keep track
     * @return lastContactId
     */

    private int getNewContactId() {
        this.lastContactId++;
        return this.lastContactId;
    }

    /**
     * Private method to increment meeting ids and keep track
     * @return lastContactId
     */

    private int getNewMeetingId() {
        this.lastMeetingId++;
        return this.lastMeetingId;
    }
}
