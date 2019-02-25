package edu.ncsu.csc216.backlog.model.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;

/**
 * Tests Command class
 * @author Daiki Kudo
 */
public class CommandTest {
	
	/** Valid author name for a note */
	private static final String VALID_AUTHOR = "John";
	/** Valid text for a note */
	private static final String VALID_TEXT = "So hard!";
	

	/**
	 * Sets up for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tests constructor.
	 */
	@Test	
	// constructing with null command value
	public void testCommand() {
		
		// constructing with null command value
		Command c = null;
		try {
			c = new Command(null, VALID_AUTHOR, VALID_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		// constructing with null author
		c = null;
		try {
			c = new Command(CommandValue.BACKLOG, null, VALID_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		// constructing with empty author
		c = null;
		try {
			c = new Command(CommandValue.BACKLOG, "", VALID_TEXT);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		// constructing with null text
		c = null;
		try {
			c = new Command(CommandValue.BACKLOG, VALID_AUTHOR, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		// constructing with empty text
		c = null;
		try {
			c = new Command(CommandValue.BACKLOG, VALID_AUTHOR, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		// valid construction
		c = null;
		try {
			c = new Command(Command.CommandValue.BACKLOG, VALID_AUTHOR, VALID_TEXT);
			assertEquals(c.getNoteAuthor(), VALID_AUTHOR);
			assertEquals(c.getNoteText(), VALID_TEXT);
			assertEquals(c.getCommand(), CommandValue.BACKLOG);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests .getCommamd()
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(Command.CommandValue.BACKLOG, VALID_AUTHOR, VALID_TEXT);
		assertEquals(c.getCommand(), CommandValue.BACKLOG);
	}

	/**
	 * Tests .getNoteText()
	 */
	@Test
	public void testGetNoteText() {
		Command c = new Command(Command.CommandValue.BACKLOG, VALID_AUTHOR, VALID_TEXT);
		assertEquals(c.getNoteText(), VALID_TEXT);
	}

	/**
	 * Tests .getNoteAuthor()
	 */
	@Test
	public void testGetNoteAuthor() {
		Command c = new Command(Command.CommandValue.BACKLOG, VALID_AUTHOR, VALID_TEXT);
		assertEquals(c.getNoteAuthor(), VALID_AUTHOR);
	}

}
