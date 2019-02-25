package edu.ncsu.csc216.backlog.model.task;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NoteTest {
	
	/** Valid author name for a note */
	private static final String VALID_AUTHOR = "John";
	/** Valid author name 2 */
	private static final String VALID_AUTHOR2 = "Paul";
	/** Valid text for a note */
	private static final String VALID_TEXT = "So hard!";
	/** Valid text 2 */
	private static final String VALID_TEXT2 = "So easy!";
	
	
	
	/**
	 * Sets up for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tests constructor.
	 */
	@Test
	public void testNote() {
		Note n = null;
		
		// testing for null author
		try {
			n = new Note(null, VALID_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(n);
		}
		
		
		// testing for empty author
		try {
			n = new Note("", VALID_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(n);
		}
		
		
		// testing for null text
		try {
			n = new Note(VALID_AUTHOR, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(n);
		}
		
		
		// testing for empty author
		try {
			n = new Note(VALID_AUTHOR, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(n);
		}
		
		
		// testing for valid construction
		try {
			n = new Note(VALID_AUTHOR, VALID_TEXT);
			assertEquals(VALID_AUTHOR, n.getNoteAuthor());
			assertEquals(VALID_TEXT, n.getNoteText());
		} catch (IllegalArgumentException e) {
			fail();
		}	
	}

	
	/**
	 * Tests getNoteAuthor()
	 */
	@Test
	public void testGetNoteAuthor() {
		Note n = new Note(VALID_AUTHOR, VALID_TEXT);
		assertEquals(n.getNoteAuthor(), VALID_AUTHOR);	
	}

	@Test
	public void testGetNoteText() {
		Note n = new Note(VALID_AUTHOR, VALID_TEXT);
		assertEquals(n.getNoteText(), VALID_TEXT);
	}
	

	@Test
	public void testGetNoteArray() {
		Note n = new Note(VALID_AUTHOR, VALID_TEXT);
		String[] array = n.getNoteArray();
		assertEquals(array.length, 2);
		assertEquals(array[0], VALID_AUTHOR);
		assertEquals(array[1], VALID_TEXT);	
	}

}




