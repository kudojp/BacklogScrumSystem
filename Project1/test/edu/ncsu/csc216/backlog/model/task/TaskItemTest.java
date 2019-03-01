package edu.ncsu.csc216.backlog.model.task;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.task.xml.Task;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;

/**
 * Test Class for TaskItem class
 * @author Daiki Kudo
 *
 */
public class TaskItemTest {
	
	/** valid task title */
	private static final String VALID_TITLE = "Jump failure";
	/** valid task type */
	private static final String VALID_CREATOR = "John";
	/** valid task note */
	private static final String VALID_NOTE = "Mario does not jump!";
	/** test file with valid task records */
	private static final String VALID_FILE = "test_files/tasks_valid.xml";
	
	/** title of task at index 0 in VALID_FILE */
	private static final String TASK0_TITLE = "Express Carts";
	/** id of task at index 0 in VALID_FILE */
	private static final int TASK0_ID = 1;
	/** state of task at index 0 in VALID_FILE */
	private static final String TASK0_STATE = TaskItem.BACKLOG_NAME;
	/** type of task at index 0 in VALID_FILE */
	private static final String TASK0_TYPE = TaskItem.T_FEATURE;
	/** creator of task at index 0 in VALID_FILE */
	private static final String TASK0_CREATOR = "jep";
	/** author of task at index 0 in VALID_FILE */
	private static final String TASK0_NOTE1_AUTHOR = "jep";
	/** text of task at index 0 in VALID_FILE */
	private static final String TASK0_NOTE1_TEXT = "Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.";

	/** text of task at index 1 in VALID_FILE */
	private static final String TASK1_OWNER = "sesmith5";
	/** random counter value to be set.*/	
	private static final int COUNTER_VALUE = 10;
	
	/**
	 * Sets the static counter of TaskItem to 0.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TaskItem.setCounter(1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#TaskItem(java.lang.String, edu.ncsu.csc216.backlog.model.task.TaskItem.Type, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testTaskItemStringTypeStringString() {
		// null title
		TaskItem ti = null;
		try {
			ti = new TaskItem(null, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}
		
		// empty title
		ti = null;
		try {
			ti = new TaskItem("", TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}		
		
		// null state
		ti = null;
		try {
			ti = new TaskItem(VALID_TITLE, null, VALID_CREATOR, VALID_NOTE);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}
		
		// null creator
		ti = null;
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, null, VALID_NOTE);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}
		
		// empty creator
		ti = null;
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, "", VALID_NOTE);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}
		
		// null note
		ti = null;
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}
		
		// empty note
		ti = null;
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ti);
		}
		
		
		// valid construction (type should be backlog state)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(VALID_TITLE, ti.getTitle());
		assertEquals(TaskItem.Type.BUG, ti.getType());
		assertEquals(VALID_CREATOR, ti.getCreator());
		assertEquals(1, ti.getNotes().size());
		assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());
		assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());
		assertNull(ti.getOwner());
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#TaskItem(edu.ncsu.csc216.task.xml.Task)}.
	 */
	@Test
	public void testTaskItemTask() {
		
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		Task t = tr.getTasks().get(0);
		TaskItem ti = new TaskItem(t);
		
		assertEquals(TASK0_TITLE, ti.getTitle());
		assertEquals(TASK0_ID, ti.getTaskItemId());
		assertEquals(TASK0_TYPE, ti.getTypeString());
		assertEquals(1, ti.getNotes().size());
		assertEquals(TASK0_NOTE1_AUTHOR, ti.getNotes().get(0).getNoteAuthor());
		assertEquals(TASK0_NOTE1_TEXT, ti.getNotes().get(0).getNoteText());
		assertNull(ti.getOwner());
		assertEquals(TASK0_STATE, ti.getStateName());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#incrementCounter()}.
	 */
	@Test
	public void testIncrementCounter() {
		TaskItem.incrementCounter();
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(2, ti.getTaskItemId());	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getTaskItemId()}.
	 */
	@Test
	public void testGetTaskItemId() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		TaskItem ti2 = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		
		assertEquals(1, ti.getTaskItemId());
		assertEquals(2, ti2.getTaskItemId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getStateName()}.
	 */
	@Test
	public void testGetStateName() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getType()}.
	 */
	@Test
	public void testGetType() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(TaskItem.Type.BUG, ti.getType());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getTypeString()}.
	 */
	@Test
	public void testGetTypeString() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		TaskItem ti2 = new TaskItem(VALID_TITLE, TaskItem.Type.FEATURE, VALID_CREATOR, VALID_NOTE);
		TaskItem ti3 = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		TaskItem ti4 = new TaskItem(VALID_TITLE, TaskItem.Type.TECHNICAL_WORK, VALID_CREATOR, VALID_NOTE);
		
		assertEquals(TaskItem.T_BUG, ti.getTypeString());
		assertEquals(TaskItem.T_FEATURE, ti2.getTypeString());
		assertEquals(TaskItem.T_KNOWLEDGE_ACQUISITION, ti3.getTypeString());
		assertEquals(TaskItem.T_TECHNICAL, ti4.getTypeString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getTypeFullString()}.
	 */
	@Test
	public void testGetTypeFullString() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		TaskItem ti2 = new TaskItem(VALID_TITLE, TaskItem.Type.FEATURE, VALID_CREATOR, VALID_NOTE);
		TaskItem ti3 = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		TaskItem ti4 = new TaskItem(VALID_TITLE, TaskItem.Type.TECHNICAL_WORK, VALID_CREATOR, VALID_NOTE);
		
		assertEquals(TaskItem.FULL_BUG, ti.getTypeFullString());
		assertEquals(TaskItem.FULL_FEATURE, ti2.getTypeFullString());
		assertEquals(TaskItem.FULL_KNOWLEDGE_ACQUISITION, ti3.getTypeFullString());
		assertEquals(TaskItem.FULL_TECHNICAL, ti4.getTypeFullString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(VALID_TITLE, ti.getTitle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getCreator()}.
	 */
	@Test
	public void testGetCreator() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(VALID_CREATOR, ti.getCreator());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getNotes()}.
	 */
	@Test
	public void testGetNotes() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(1, ti.getNotes().size());
		assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());
		assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertNull(ti.getOwner());
		
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		TaskItem ti0 = new TaskItem(tr.getTasks().get(0));
		TaskItem ti1 = new TaskItem(tr.getTasks().get(1));
		
		assertNull(ti0.getOwner());
		assertEquals(TASK1_OWNER, ti1.getOwner());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#update(edu.ncsu.csc216.backlog.model.command.Command)}.
	 * Just tests the case when an appropriate command is given to TaskItem whose state is Backlog and exception is thrown.
	 * Testing at each state of TaskItem is done in testFSM() 
	 */
	@Test
	public void testUpdate() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		try {
			ti.update(new Command(CommandValue.BACKLOG, "TestAuthor", "TestText"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getXMLTask()}.
	 */
	@Test
	public void testGetXMLTask() {
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		// read Task from a file
		Task t0 = tr.getTasks().get(0);
		// construct TaskItem from the task
		TaskItem ti = new TaskItem(t0);
		// return Task using .getXMLTask() method of the taskitem
		Task t1 = ti.getXMLTask();
		
		assertEquals(TASK0_TITLE, t1.getTitle());
		assertEquals(TASK0_ID, t1.getId());
		assertEquals(TASK0_TYPE, t1.getType());
		assertEquals(TASK0_CREATOR, t1.getCreator());
		assertEquals(1, t1.getNoteList().getNotes().size());
		assertEquals(TASK0_NOTE1_AUTHOR, t1.getNoteList().getNotes().get(0).getNoteAuthor());
		assertEquals(TASK0_NOTE1_TEXT, t1.getNoteList().getNotes().get(0).getNoteText());
		assertNull(t1.getOwner());
		assertEquals(TASK0_STATE, t1.getState());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#setCounter(int)}.
	 */
	@Test
	public void testSetCounter() {
		TaskItem.setCounter(COUNTER_VALUE);
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(COUNTER_VALUE, ti.getTaskItemId());	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getNotesArray()}.
	 */
	@Test
	public void testGetNotesArray() {
		
		// read Task from a file
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		String[][] notes = ti.getNotesArray();
		
		assertEquals(1, notes.length, 1);
		assertEquals(2, notes[0].length);
		assertEquals(1, notes.length, 1);
		assertEquals(2, notes[0].length);
		assertEquals(VALID_CREATOR, notes[0][0]);
		assertEquals(VALID_NOTE, notes[0][1]);
	}
	
	/**
	 * Tests path from Backlog state, that is, this tests inner class BacklogState.
	 */
	@Test
	public void testBacklogState() {
		// 1. BacklogA to Owned
		// create a TaskItem
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		// 
		ti.update(new Command(CommandValue.CLAIM, "bo", "BacklogA"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());								// owner name
		assertEquals(TaskItem.OWNED_NAME, ti.getStateName());  // State
		assertEquals(2, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(1).getNoteAuthor());       //The last note author
		assertEquals("BacklogA", ti.getNotes().get(1).getNoteText());   //The last note text
		
		
		// 2. BacklogB to Rejected
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.REJECT, "br", "BacklogB"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertNull(ti.getOwner());						// owner name
		assertEquals(TaskItem.REJECTED_NAME, ti.getStateName());  // State
		assertEquals(2, ti.getNotes().size());			   // Number of notes
		assertEquals("br", ti.getNotes().get(1).getNoteAuthor());       //The last note author
		assertEquals("BacklogB", ti.getNotes().get(1).getNoteText());   //The last note text
		
		// 3. Backlog to Processing (error)
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
			ti.update(new Command(CommandValue.PROCESS, "bp", "ERROR"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());								// owner name
			assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
			assertEquals(1, ti.getNotes().size());			   // Number of notes
			assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());       //The last note author
			assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());   //The last note text
		}
		
		
		// 4. Backlog to Verifying (error)
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
			ti.update(new Command(CommandValue.VERIFY, "bv", "ERROR"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());								// owner name
			assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
			assertEquals(1, ti.getNotes().size());			   // Number of notes
			assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());       //The last note author
			assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());   //The last note text
		}
		
				
		// 5. Backlog to Done (error)
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
			ti.update(new Command(CommandValue.COMPLETE, "bd", "ERROR"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());								// owner name
			assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
			assertEquals(1, ti.getNotes().size());			   // Number of notes
			assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());       //The last note author
			assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());   //The last note text
		}
		
		
		// 6. Backlog to Backlog (error)
		try {
			ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
			ti.update(new Command(CommandValue.BACKLOG, "bb", "ERROR"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());								// owner name
			assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
			assertEquals(1, ti.getNotes().size());			   // Number of notes
			assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());       //The last note author
			assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());   //The last note text
		}
		
				
	}
	
	
	
	
	
	
	/**
	 * Tests path from Owned state, that is, this tests inner class OwnedState.
	 */
	@Test
	public void testOwnedState() {
		//  1.OwnedA to Processing
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		//
		ti.update(new Command(CommandValue.PROCESS, "name not used", "ownedA"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());								// owner name
		assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
		assertEquals(3, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
		assertEquals("ownedA", ti.getNotes().get(2).getNoteText());   //The last note text
		
		
		//2. OwnedB to Rejected
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		// 
		ti.update(new Command(CommandValue.REJECT, "name not used", "ownedB"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertNull(ti.getOwner());						// owner name
		assertEquals(TaskItem.REJECTED_NAME, ti.getStateName());  // State
		assertEquals(3, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
		assertEquals("ownedB", ti.getNotes().get(2).getNoteText());   //The last note text
		
		// 3. OwnedC to backlog
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		// 
		ti.update(new Command(CommandValue.BACKLOG, "name not used", "ownedC"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertNull(ti.getOwner());						// owner name
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
		assertEquals(3, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
		assertEquals("ownedC", ti.getNotes().get(2).getNoteText());   //The last note text
		
		
		//4. Owned to Verifying (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		// 
		try {
			ti.update(new Command(CommandValue.VERIFY, "name not used", "error"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.OWNED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
			
		}
		
		// 5. OwnedC to done(error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		// 
		try {
			ti.update(new Command(CommandValue.COMPLETE, "name not used", "error"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.OWNED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
		}
		
		
		// 6. OwnedC to owned(error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		// 
		try {
			ti.update(new Command(CommandValue.CLAIM, "name not used", "error"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.OWNED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
		}
	
		
	}
	
	
	/**
	 * Tests path from Processing state of BUG type task, that is, this tests inner class ProcessingState.
	 */
	@Test
	public void testProcessingStateBUG (){
		// ProcessingA to processing
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		ti.update(new Command(CommandValue.PROCESS, "name not used", "PtoP"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
		assertEquals(4, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
		assertEquals("PtoP", ti.getNotes().get(3).getNoteText());   //The last note text
		

		
		
		
		// ProcessingB to verified (when Type is Bug)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.VERIFYING_NAME, ti.getStateName());  // State
		assertEquals(4, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
		assertEquals("PtoV", ti.getNotes().get(3).getNoteText());   //The last note text

		
		
		// ProcessingC to Done(This should throw an error since this is BUG type)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		try {
			ti.update(new Command(CommandValue.COMPLETE, "name not used", "PtoD"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
			assertEquals(3, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
			assertEquals("OtoP", ti.getNotes().get(2).getNoteText());   //The last note text
			
		}
		
		// ProcessingD to Backlog
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		ti.update(new Command(CommandValue.BACKLOG, "name not used", "PtoB"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertNull(ti.getOwner());						// owner name
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
		assertEquals(4, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
		assertEquals("PtoB", ti.getNotes().get(3).getNoteText());   //The last note text
			
		
		
		// Processing to Owned (This should throw an error since this is BUG type)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		try {
			ti.update(new Command(CommandValue.CLAIM, "name not used", "PtoO"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
			assertEquals(3, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
			assertEquals("OtoP", ti.getNotes().get(2).getNoteText());   //The last note text
			
		}
		
		// Processing to Rejected (This should throw an error since this is BUG type)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		try {
			ti.update(new Command(CommandValue.REJECT, "name not used", "PtoR"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
			assertEquals(3, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
			assertEquals("OtoP", ti.getNotes().get(2).getNoteText());   //The last note text
			
		}
		

	}
	
	
	/**
	 * Tests path from Processing state of KNOWLEDGE type task, that is, this tests inner class ProcessingState.
	 */
	@Test
	public void testProcessingStateKnowledge(){
		// ProcessingA to Processing
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		ti.update(new Command(CommandValue.PROCESS, "name not used", "PtoP"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
		assertEquals(4, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
		assertEquals("PtoP", ti.getNotes().get(3).getNoteText());   //The last note text
	
		
		//ProcessingB to Verified should throw exception (when type is KNOWLEDGE)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		try {
			ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
			assertEquals(3, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
			assertEquals("OtoP", ti.getNotes().get(2).getNoteText());   //The last note text
		}
	
		// ProcessingC to Done
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		ti.update(new Command(CommandValue.COMPLETE, "name not used", "PtoD"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.DONE_NAME, ti.getStateName());  // State
		assertEquals(4, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
		assertEquals("PtoD", ti.getNotes().get(3).getNoteText());   //The last note text
		
		// ProcessingD to Backlog
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		//
		ti.update(new Command(CommandValue.COMPLETE, "name not used", "PtoD"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.DONE_NAME, ti.getStateName());  // State
		assertEquals(4, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
		assertEquals("PtoD", ti.getNotes().get(3).getNoteText());   //The last note text
				
		
		
		
		//Processing to Owned should throw exception
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		try {
			ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoO"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
			assertEquals(3, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
			assertEquals("OtoP", ti.getNotes().get(2).getNoteText());   //The last note text
		}
				
		//Processing to Rejected should throw exception
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		try {
			ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoR"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
			assertEquals(3, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(2).getNoteAuthor());       //The last note author
			assertEquals("OtoP", ti.getNotes().get(2).getNoteText());   //The last note text
		}
		
				
	}
	
	/**
	 * Tests path from Verifying state, that is, this tests inner class VerifyingState.
	 * This state can be taken only by BUG, FEATURE, TECHNICAL types
	 */
	@Test
	public void testVerifying() {
		// VerifyingA to done
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "name not used", "OtoP"));
		ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
		//
		ti.update(new Command(CommandValue.COMPLETE, "vd", "VtoD"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.DONE_NAME, ti.getStateName());  // State
		assertEquals(5, ti.getNotes().size());			   // Number of notes
		assertEquals("vd", ti.getNotes().get(4).getNoteAuthor());       //The last note author
		assertEquals("VtoD", ti.getNotes().get(4).getNoteText());   //The last note text
		
		//VerifyingB to Processing
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "name not used", "OtoP"));
		ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
		//
		ti.update(new Command(CommandValue.PROCESS, "vp", "VtoP"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
		assertEquals(5, ti.getNotes().size());			   // Number of notes
		assertEquals("vp", ti.getNotes().get(4).getNoteAuthor());       //The last note author
		assertEquals("VtoP", ti.getNotes().get(4).getNoteText());   //The last note text
		
		
		// Verifying to Backlog (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "name not used", "OtoP"));
		ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
		//
		try {
			ti.update(new Command(CommandValue.BACKLOG, "vp", "VtoP"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.VERIFYING_NAME, ti.getStateName());  // State
			assertEquals(4, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
			assertEquals("PtoV", ti.getNotes().get(3).getNoteText());   //The last note text
		}
	
		
		
		
		// Verifying to Owned (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "name not used", "OtoP"));
		ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
		//
		try {
			ti.update(new Command(CommandValue.CLAIM, "vp", "VtoP"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.VERIFYING_NAME, ti.getStateName());  // State
			assertEquals(4, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
			assertEquals("PtoV", ti.getNotes().get(3).getNoteText());   //The last note text
		}
		
		
		
		// Verifying to Rejected (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "name not used", "OtoP"));
		ti.update(new Command(CommandValue.VERIFY, "name not used", "PtoV"));
		//
		try {
			ti.update(new Command(CommandValue.REJECT, "vp", "VtoP"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.BUG, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.VERIFYING_NAME, ti.getStateName());  // State
			assertEquals(4, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
			assertEquals("PtoV", ti.getNotes().get(3).getNoteText());   //The last note text
		}
		
		
		
		
		
	}
	
	
	/**
	 * Tests path from Done state, that is, this tests inner class DoneState.
	 */
	@Test
	public void testDoneState (){
		// DoneA to Processing
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		ti.update(new Command(CommandValue.COMPLETE, "pd", "PtoD"));
		// 
		ti.update(new Command(CommandValue.PROCESS, "dp", "DtoP"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertEquals("bo", ti.getOwner());						// owner name
		assertEquals(TaskItem.PROCESSING_NAME, ti.getStateName());  // State
		assertEquals(5, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(4).getNoteAuthor());       //The last note author
		assertEquals("DtoP", ti.getNotes().get(4).getNoteText());   //The last note text
	
		
		// DoneB to Backlog
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		ti.update(new Command(CommandValue.COMPLETE, "pd", "PtoD"));
		// 
		ti.update(new Command(CommandValue.BACKLOG, "db", "DtoB"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertNull(ti.getOwner());						// owner name
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
		assertEquals(5, ti.getNotes().size());			   // Number of notes
		assertEquals("bo", ti.getNotes().get(4).getNoteAuthor());       //The last note author
		assertEquals("DtoB", ti.getNotes().get(4).getNoteText());   //The last note text
		
		
		// Done to Owned (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		ti.update(new Command(CommandValue.COMPLETE, "pd", "PtoD"));
		// 
		try {
			ti.update(new Command(CommandValue.CLAIM, "do", "DtoO"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.DONE_NAME, ti.getStateName());  // State
			assertEquals(4, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
			assertEquals("PtoD", ti.getNotes().get(3).getNoteText());   //The last note text
		}
		
		
		
		// Done to Rejected (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		ti.update(new Command(CommandValue.COMPLETE, "pd", "PtoD"));
		// 
		try {
			ti.update(new Command(CommandValue.REJECT, "do", "DtoO"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.DONE_NAME, ti.getStateName());  // State
			assertEquals(4, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
			assertEquals("PtoD", ti.getNotes().get(3).getNoteText());   //The last note text
		}
		
		
		// Done to Verifying (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.CLAIM, "bo", "BtoO"));
		ti.update(new Command(CommandValue.PROCESS, "op", "OtoP"));
		ti.update(new Command(CommandValue.COMPLETE, "pd", "PtoD"));
		// 
		try {
			ti.update(new Command(CommandValue.VERIFY, "do", "DtoO"));
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertEquals("bo", ti.getOwner());						// owner name
			assertEquals(TaskItem.DONE_NAME, ti.getStateName());  // State
			assertEquals(4, ti.getNotes().size());			   // Number of notes
			assertEquals("bo", ti.getNotes().get(3).getNoteAuthor());       //The last note author
			assertEquals("PtoD", ti.getNotes().get(3).getNoteText());   //The last note text
		}
		
		
		
	}
	
	
	/**
	 * Tests path from Rejected state, that is, this tests inner class RejectedState.
	 */
	@Test
	public void testRejectedState (){
		
		// RejectA to Backlog
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.REJECT, "br", "BtoR"));
		// 
		ti.update(new Command(CommandValue.BACKLOG, "rb", "RtoB"));
		assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
		assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
		assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
		assertNull(ti.getOwner());						// owner name
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());  // State
		assertEquals(3, ti.getNotes().size());			   // Number of notes
		assertEquals("rb", ti.getNotes().get(2).getNoteAuthor());       //The last note author
		assertEquals("RtoB", ti.getNotes().get(2).getNoteText());   //The last note text
	
		
		// Reject to owned (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.REJECT, "br", "BtoR"));
		// 
		try {
			ti.update(new Command(CommandValue.CLAIM, "rb", "RtoB"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());						// owner name
			assertEquals(TaskItem.REJECTED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
			assertEquals("br", ti.getNotes().get(1).getNoteAuthor());       //The last note author
			assertEquals("BtoR", ti.getNotes().get(1).getNoteText());   //The last note text
		}
		
		// Rejected to Processing (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.REJECT, "br", "BtoR"));
		// 
		try {
			ti.update(new Command(CommandValue.PROCESS, "rp", "RtoP"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());						// owner name
			assertEquals(TaskItem.REJECTED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
			assertEquals("br", ti.getNotes().get(1).getNoteAuthor());       //The last note author
			assertEquals("BtoR", ti.getNotes().get(1).getNoteText());   //The last note text
		}
		
		// Rejected to Verifying(error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.REJECT, "br", "BtoR"));
		// 
		try {
			ti.update(new Command(CommandValue.VERIFY, "rv", "RtoV"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());						// owner name
			assertEquals(TaskItem.REJECTED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
			assertEquals("br", ti.getNotes().get(1).getNoteAuthor());       //The last note author
			assertEquals("BtoR", ti.getNotes().get(1).getNoteText());   //The last note text
		}
		
		
		// Rejected to Done (error)
		ti = new TaskItem(VALID_TITLE, TaskItem.Type.KNOWLEDGE_ACQUISITION, VALID_CREATOR, VALID_NOTE);
		ti.update(new Command(CommandValue.REJECT, "br", "BtoR"));
		// 
		try {
			ti.update(new Command(CommandValue.CLAIM, "rd", "RtoD"));
		} catch (UnsupportedOperationException e) {
			assertEquals(VALID_TITLE, ti.getTitle());		 //No change ever
			assertEquals(TaskItem.Type.KNOWLEDGE_ACQUISITION, ti.getType());   //No change ever
			assertEquals(VALID_CREATOR, ti.getCreator());    //No change ever
			assertNull(ti.getOwner());						// owner name
			assertEquals(TaskItem.REJECTED_NAME, ti.getStateName());  // State
			assertEquals(2, ti.getNotes().size());			   // Number of notes
			assertEquals("br", ti.getNotes().get(1).getNoteAuthor());       //The last note author
			assertEquals("BtoR", ti.getNotes().get(1).getNoteText());   //The last note text
		}
		
		

		
	
	}
	
	
	
	
	
	
	
}
