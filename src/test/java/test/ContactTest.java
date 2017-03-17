package test;

import impl.ContactImpl;
import spec.Contact;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Ginestra Ferraro
 */

public class ContactTest {

    private Contact person1, person2, person3, person4, person5;

    // Create mock object
    @Before
    public void mockContact() {
        person1 = new ContactImpl(7, "Einstein, Albert", "King of math and physics");
        person2 = new ContactImpl(17, "Galilei, Galileo", "Had to lie to stay alive");
        person3 = new ContactImpl(23, "Lovelace, Ada", "Wish more people knew about her");
        person4 = new ContactImpl(29, "Da Vinci, Leonardo", "Genius");
        person5 = new ContactImpl(77, "Curie, Marie", "Mrs X-rays");
    }

    @Test
    public void getIdTest() {
        System.out.println("==== Testing the getId() method");
        int getTheId = person1.getId();
        int expected = 7;
        assertEquals(expected, getTheId);
    }

    @Test
    public void getNameTest() {
        System.out.println("==== Testing the getName() method");
        String getTheName = person4.getName();
        String expected = "Da Vinci, Leonardo";
        assertEquals(expected, getTheName);
    }

    @Test
    public void getNotesTest() {
        System.out.println("==== Testing the getNotes() method");
        String getTheNotes = person1.getNotes();
        String expected = "King of math and physics";
        assertEquals(expected, getTheNotes);

    }

    @Test
    public void addNotesTest() {
        System.out.println("==== Testing the addNotes() method");

    }

    // Destroy mock object
    @After
    public void cleanUp() {
        person1 = null;
        person2 = null;
        person3 = null;
        person4 = null;
        person5 = null;
    }
}
