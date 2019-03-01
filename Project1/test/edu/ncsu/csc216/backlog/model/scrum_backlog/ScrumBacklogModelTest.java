package edu.ncsu.csc216.backlog.model.scrum_backlog;

import static org.junit.Assert.*;
import org.junit.Test;


import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.model.task.TaskItem;

/**
 * Test class for ScrumBacklogModel class
 * @author Daiki Kudo
 *
 */
public class ScrumBacklogModelTest {



	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title", TaskItem.Type.BUG, "first creator", "first note text");
		assertEquals("title", ScrumBacklogModel.getInstance().getTaskItemById(1).getTitle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#saveTasksToFile(java.lang.String)}.
	 */
	@Test
	public void testSaveTasksToFile() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.loadTasksFromFile("test_files/tasks_valid.xml");
		sb.saveTasksToFile("test_files/tasks_valid_written.xml");
		
		sb.createNewTaskItemList();
		sb.loadTasksFromFile("test_files/tasks_valid_written.xml");
		assertEquals(6, sb.getTaskItemListAsArray().length);
	}
	
	

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#loadTasksFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadTasksFromFile() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.loadTasksFromFile("test_files/tasks_valid.xml");
		
		assertEquals(6, sb.getTaskItemListAsArray().length);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#createNewTaskItemList()}.
	 */
	@Test
	public void testCreateNewTaskItemList() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("a", TaskItem.Type.BUG, "first creator", "first note text");
		sb.createNewTaskItemList();
		assertEquals(0, sb.getTaskItemListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#getTaskItemListAsArray()}.
	 */
	@Test
	public void testGetTaskItemListAsArray() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "first creator", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.FEATURE, "second creator", "second text");
		
		Object[][] taskArray = sb.getTaskItemListAsArray();
		assertEquals(2, taskArray.length);
		assertEquals(taskArray[0][0], 1);
		assertEquals(taskArray[0][1], TaskItem.BACKLOG_NAME);
		assertEquals(taskArray[0][2], "title1");
		
		assertEquals(taskArray[1][0], 2);
		assertEquals(taskArray[1][1], TaskItem.BACKLOG_NAME);
		assertEquals(taskArray[1][2], "title2");
		
		
		
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#getTaskItemListByOwnerAsArray(java.lang.String)}.
	 */
	@Test
	public void testGetTaskItemListByOwnerAsArray() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "first creator", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.BUG, "second creator", "second text");
		sb.addTaskItemToList("title3", TaskItem.Type.BUG, "third creator", "third text");
		
		sb.getTaskItemById(1).update(new Command(CommandValue.CLAIM, "owner1", "text1"));
		sb.getTaskItemById(2).update(new Command(CommandValue.CLAIM, "owner2", "text2"));
		sb.getTaskItemById(3).update(new Command(CommandValue.CLAIM, "owner1", "text2"));
		
		// Search by existing owner name
		Object[][] taskArray = sb.getTaskItemListByOwnerAsArray("owner1");
		
		assertEquals(2, taskArray.length);
		assertEquals(taskArray[0][0], 1);
		assertEquals(taskArray[0][1], TaskItem.OWNED_NAME);
		assertEquals(taskArray[0][2], "title1");
		
		assertEquals(taskArray[1][0], 3);
		assertEquals(taskArray[1][1], TaskItem.OWNED_NAME);
		assertEquals(taskArray[1][2], "title3");
		
		// Search by not existing owner name
		taskArray = sb.getTaskItemListByOwnerAsArray("NONE");
		assertEquals(0, taskArray.length);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#getTaskItemListByCreatorAsArray(java.lang.String)}.
	 */
	@Test
	public void testGetTaskItemListByCreatorAsArray() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "creator1", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.BUG, "creator2", "second text");
		sb.addTaskItemToList("title3", TaskItem.Type.BUG, "creator1", "third text");
		
		sb.getTaskItemById(1).update(new Command(CommandValue.CLAIM, "owner1", "text1"));
		sb.getTaskItemById(2).update(new Command(CommandValue.CLAIM, "owner2", "text2"));
		sb.getTaskItemById(3).update(new Command(CommandValue.CLAIM, "owner3", "text2"));
		
		// Search by existing creator name
		Object[][] taskArray = sb.getTaskItemListByCreatorAsArray("creator1");
		
		assertEquals(2, taskArray.length);
		assertEquals(taskArray[0][0], 1);
		assertEquals(taskArray[0][1], TaskItem.OWNED_NAME);
		assertEquals(taskArray[0][2], "title1");
		
		assertEquals(taskArray[1][0], 3);
		assertEquals(taskArray[1][1], TaskItem.OWNED_NAME);
		assertEquals(taskArray[1][2], "title3");
		
		// Search by not existing creator name
		taskArray = sb.getTaskItemListByCreatorAsArray("NONE");
		assertEquals(0, taskArray.length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#getTaskItemById(int)}.
	 */
	@Test
	public void testGetTaskItemById() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "creator1", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.BUG, "creator2", "second text");
		sb.addTaskItemToList("title3", TaskItem.Type.BUG, "creator3", "third text");
		
		sb.getTaskItemById(1).update(new Command(CommandValue.CLAIM, "owner1", "text1"));
		sb.getTaskItemById(2).update(new Command(CommandValue.CLAIM, "owner2", "text2"));
		sb.getTaskItemById(3).update(new Command(CommandValue.CLAIM, "owner3", "text2"));
		
		// Search by existing id
		TaskItem t = sb.getTaskItemById(1);
		
		assertEquals(1, t.getTaskItemId());
		assertEquals(TaskItem.OWNED_NAME, t.getStateName());
		assertEquals("title1", t.getTitle());
		
		// Search by not existing id
		assertNull(sb.getTaskItemById(0));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#executeCommand(int, edu.ncsu.csc216.backlog.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "creator1", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.BUG, "creator2", "second text");
		sb.addTaskItemToList("title3", TaskItem.Type.BUG, "creator3", "third text");
		sb.executeCommand(2, new Command(CommandValue.CLAIM, "owner2", "text2") );
		
		assertEquals(TaskItem.OWNED_NAME, sb.getTaskItemById(2).getStateName());
		assertEquals("owner2", sb.getTaskItemById(2).getOwner());
		assertEquals(TaskItem.Type.BUG, sb.getTaskItemById(2).getType());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#deleteTaskItemById(int)}.
	 */
	@Test
	public void testDeleteTaskItemById() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "creator1", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.BUG, "creator2", "second text");
		sb.addTaskItemToList("title3", TaskItem.Type.BUG, "creator3", "third text");
		
		sb.getTaskItemById(1).update(new Command(CommandValue.CLAIM, "owner1", "text1"));
		sb.getTaskItemById(2).update(new Command(CommandValue.CLAIM, "owner2", "text2"));
		sb.getTaskItemById(3).update(new Command(CommandValue.CLAIM, "owner3", "text2"));
		
		// Delete existing id item
		sb.deleteTaskItemById(1);
		assertEquals(2, sb.getTaskItemListAsArray().length);
		assertNull(sb.getTaskItemById(1));
		
		// for not existing id, nothing happens
		sb.deleteTaskItemById(0);
		assertEquals(2, sb.getTaskItemListAsArray().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.backlog.model.scrum_backlog.ScrumBacklogModel#addTaskItemToList(java.lang.String, edu.ncsu.csc216.backlog.model.task.TaskItem.Type, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddTaskItemToList() {
		ScrumBacklogModel sb = ScrumBacklogModel.getInstance();
		sb.createNewTaskItemList();
		sb.addTaskItemToList("title1", TaskItem.Type.BUG, "creator1", "first text");
		sb.addTaskItemToList("title2", TaskItem.Type.BUG, "creator2", "second text");
		sb.addTaskItemToList("title3", TaskItem.Type.BUG, "creator3", "third text");
		
		assertEquals(3, sb.getTaskItemListAsArray().length);
		assertEquals("title1", sb.getTaskItemById(1).getTitle());
		assertEquals("title2", sb.getTaskItemById(2).getTitle());
		assertEquals("title3", sb.getTaskItemById(3).getTitle());
	}

}
