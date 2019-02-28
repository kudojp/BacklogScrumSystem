package edu.ncsu.csc216.backlog.model.scrum_backlog;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;


/**
 * Tests TaskItemList class.
 * @author Daiki Kudo
 */
public class TaskItemListTest extends TaskItemList {
	
	private static final String VALID_TITLE = "Jump errror";
	private static final String VALID_CREATOR = "John";
	private static final String VALID_NOTE  = "Mario does not jump.";
			
	private static final String VALID_FILE = "test_files/tasks_valid.xml";


	/**
	 * Tests constructor
	 */
	@Test
	public void testTaskItemList() {
		TaskItemList til = null;
		try {
			til = new TaskItemList();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(0, til.getTaskItems().size());
	}

	/**
	 * Tests addTaskItem() 
	 */
	@Test
	public void testAddTaskItem() {
		TaskItemList til = null;
		try {
			til = new TaskItemList();
			assertEquals(1, til.addTaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE));
			assertEquals(2, til.addTaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE));
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(2, til.getTaskItems().size());
		assertEquals(VALID_TITLE, til.getTaskItems().get(0).getTitle());
		assertEquals(VALID_CREATOR, til.getTaskItems().get(0).getCreator());
		assertEquals(TaskItem.Type.BUG, til.getTaskItems().get(0).getType());
		assertEquals(1, til.getTaskItems().get(0).getNotes().size());
	}
	
	
	/**
	 * Tests addXMLTasks()
	 */
	@Test
	public void testAddXMLTasks() {
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		TaskItemList til = new TaskItemList();
		til.addXMLTasks(tr.getTasks());
		
		assertEquals(20, til.getTaskItems().size());
		
	}

	/**
	 * Tests .getTaskItem()
	 */
	@Test
	public void testGetTaskItems() {
		TaskItemList til = new TaskItemList();
		til.addTaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);
	    til.addTaskItem(VALID_TITLE, TaskItem.Type.BUG, VALID_CREATOR, VALID_NOTE);

		assertEquals(2, til.getTaskItems().size());
		// first taskitem
		assertEquals(VALID_TITLE, til.getTaskItems().get(0).getTitle());
		assertEquals(VALID_CREATOR, til.getTaskItems().get(0).getCreator());
		assertEquals(TaskItem.Type.BUG, til.getTaskItems().get(0).getType());
		assertEquals(1, til.getTaskItems().get(0).getNotes().size());
		// second task item
		assertEquals(VALID_TITLE, til.getTaskItems().get(1).getTitle());
		assertEquals(VALID_CREATOR, til.getTaskItems().get(1).getCreator());
		assertEquals(TaskItem.Type.BUG, til.getTaskItems().get(1).getType());
		assertEquals(1, til.getTaskItems().get(0).getNotes().size());
		
	}

	/**
	 * Tests .getTaskItemsByOwner()
	 */
	@Test
	public void testGetTaskItemsByOwner() {
		
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		TaskItemList til = new TaskItemList();
		til.addXMLTasks(tr.getTasks());
		assertEquals(6, til.getTaskItems().size());
		
		List<TaskItem> tiList  = til.getTaskItemsByOwner("sesmith5");
		assertEquals(4, tiList.size());
		
		List<TaskItem> tiList2  = til.getTaskItemsByOwner("NONE");
		assertEquals(0, tiList2.size());
		
		
	}

	/**
	 * Tests .getTaskItemsByOwner()
	 */
	@Test
	public void testGetTaskItemsByCreator() {
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		TaskItemList til = new TaskItemList();
		til.addXMLTasks(tr.getTasks());
		assertEquals(6, til.getTaskItems().size());
		
		List<TaskItem> tiList  = til.getTaskItemsByCreator("jep");
		assertEquals(4, tiList.size());
		
		List<TaskItem> tiList2  = til.getTaskItemsByCreator("NONE");
		assertEquals(0, tiList2.size());
		
	}
	
	/**
	 * Tests .getTaskById()
	 */
	@Test
	public void testGetTaskItemById() {
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		TaskItemList til = new TaskItemList();
		til.addXMLTasks(tr.getTasks());
		assertEquals(6, til.getTaskItems().size());
		
		TaskItem ti  = til.getTaskItemById(1);
		assertEquals("Express Carts", ti.getTitle());
		assertEquals("jep", ti.getCreator());
		assertEquals(1, ti.getTaskItemId());

		
		
		TaskItem ti2  = til.getTaskItemById(0);
		assertNull(ti2);
	}

	/**
	 * Tests .executeCommand()
	 */
	@Test
	public void testExecuteCommand() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests .deleteTaskItemById()
	 */
	@Test
	public void testDeleteTaskItemById() {
		TaskReader tr = null;
		try {
			tr = new TaskReader(VALID_FILE);
		} catch (TaskIOException e) {
			fail();
		}
		
		TaskItemList til = new TaskItemList();
		til.addXMLTasks(tr.getTasks());
		assertEquals(6, til.getTaskItems().size());
		
		til.deleteTaskItemById(0);
		assertEquals(6, til.getTaskItems().size());

		til.deleteTaskItemById(1);
		assertEquals(5, til.getTaskItems().size());
		
		til.deleteTaskItemById(1);
		assertEquals(5, til.getTaskItems().size());
		
		til.deleteTaskItemById(2);
		assertEquals(4, til.getTaskItems().size());
	}
	
	

}
