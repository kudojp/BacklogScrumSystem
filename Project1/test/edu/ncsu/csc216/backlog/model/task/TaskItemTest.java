/**
 * 
 */
package edu.ncsu.csc216.backlog.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;

/**
 * @author Daiki Kudo
 *
 */
class TaskItemTest {
	
	/** valid task title */
	private static final String VALID_TITLE = "Jump failure";
	/** valid task type */
	private static final String VALID_CREATOR = "John";
	/** valid task note */
	private static final String VALID_NOTE = "Mario does not jump!";

	private static final String VALID_FILE = "test_files/tasks_valid.xml";
	
	private static final String TASK0_TITLE = "Express Carts";
	private static final int TASK0_ID = 1;
	private static final String TASK0_STATE = TaskItem.BACKLOG_NAME;
	private static final String TASK0_TYPE = TaskItem.T_FEATURE;
	private static final String TASK0_CREATOR = "jep";
	private static final boolean TASK0_ISVERIFIED = false;
	private static final String TASK0_NOTE1_AUTHOR = "jep";
	private static final String TASK0_NOTE1_TEXT = "Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.";

	private static final String TASK1_OWNER = "sesmith5";
			
	private static final int COUNTER_VALUE = 10;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		TaskItem.setCounter(1);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#TaskItem(java.lang.String, edu.ncsu.csc216.backlog.model.task.TaskItem.Type, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testTaskItemStringTypeStringString() {
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
	void testTaskItemTask() {
		
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
		assertEquals(TASK0_CREATOR, ti.getCreator());
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
	void testIncrementCounter() {
		TaskItem.incrementCounter();
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(2, ti.getTaskItemId());	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getTaskItemId()}.
	 */
	@Test
	void testGetTaskItemId() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		TaskItem ti2 = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		
		assertEquals(1, ti.getTaskItemId());
		assertEquals(2, ti2.getTaskItemId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getStateName()}.
	 */
	@Test
	void testGetStateName() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(TaskItem.BACKLOG_NAME, ti.getStateName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getType()}.
	 */
	@Test
	void testGetType() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(TaskItem.Type.BUG, ti.getType());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getTypeString()}.
	 */
	@Test
	void testGetTypeString() {
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
	void testGetTypeFullString() {
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
	void testGetTitle() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(VALID_TITLE, ti.getTitle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getCreator()}.
	 */
	@Test
	void testGetCreator() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(VALID_CREATOR, ti.getCreator());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getNotes()}.
	 */
	@Test
	void testGetNotes() {
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(1, ti.getNotes().size());
		assertEquals(VALID_CREATOR, ti.getNotes().get(0).getNoteAuthor());
		assertEquals(VALID_NOTE, ti.getNotes().get(0).getNoteText());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getOwner()}.
	 */
	@Test
	void testGetOwner() {
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
	 */
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getXMLTask()}.
	 */
	@Test
	void testGetXMLTask() {
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		// read Task from a file
		Task t0 = tr.getTasks().get(0);
		// construct Taskitem from the task
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
	void testSetCounter() {
		TaskItem.setCounter(COUNTER_VALUE);
		TaskItem ti = new TaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
		assertEquals(COUNTER_VALUE, ti.getTaskItemId());	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.task.TaskItem#getNotesArray()}.
	 */
	@Test
	void testGetNotesArray() {
		
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
}
