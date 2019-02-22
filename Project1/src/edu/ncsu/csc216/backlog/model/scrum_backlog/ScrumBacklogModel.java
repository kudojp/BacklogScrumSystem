/**
 * 
 */
package edu.ncsu.csc216.backlog.model.scrum_backlog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.Task;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;
import edu.ncsu.csc216.task.xml.TaskWriter;

/**
 * @author Daiki Kudo
 */
public class ScrumBacklogModel {
	
	private static ScrumBacklogModel instance;
	private TaskItemList taskItemList;

	/**
	 * 
	 */
	private ScrumBacklogModel() {
	}
	
	public static ScrumBacklogModel getInstance() {
		if (instance == null) {
			instance = new ScrumBacklogModel();
		}
		return instance;
	}
	
	public void saveTasksToFile(String fileName) {
		
		TaskWriter writer = new TaskWriter(fileName);
		try {
			writer = new TaskWriter(fileName);
			for (int i = 0 ; i <this.taskItemList.getTaskItems().size() ; i++) {
				writer.addItem(this.taskItemList.getTaskItems().get(i).getXMLTask());
			}
			writer.marshal();
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
		
	}
	
	public void loadTasksFromFile(String fileName) {
		TaskReader reader = null;
		try {
			//Create the TaskReader
			reader = new TaskReader(fileName);
			
			//Get the lists of Tasks
			List<Task> tasks = reader.getTasks();
						
			this.taskItemList.addXMLTasks(tasks);
			
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
		
	}
	
	public void createNewTaskItemList() {
		this.taskItemList = new TaskItemList();
	}
	
	public Object[][] getTaskItemListAsArray(){
		List<TaskItem> taskItems = this.taskItemList.getTaskItems();
		int taskCount = taskItems.size();
		Object[][] taskItemArray = new Object[taskCount][];
		
		for (int i = 0 ; i < taskCount ; i++) {
			// task item id 
			taskItemArray[i][0] = taskItems.get(i).getTaskItemId();
			// task item state
			taskItemArray[i][0] = taskItems.get(i).getStateName();
			// task item title
			taskItemArray[i][0] = taskItems.get(i).getTitle();
		}
		return taskItemArray;
	}
	
	public Object[][] getTaskItemListByOwnerAsArray(String owner){
		List<TaskItem> taskItems = this.taskItemList.getTaskItems();
		int taskCount = taskItems.size();
		Object[][] taskItemArray = new Object[taskCount][];
		
		for (int i = 0 ; i < taskCount ; i++) {
			// task item id 
			taskItemArray[i][0] = taskItems.get(i).getTaskItemId();
			// task item state
			taskItemArray[i][0] = taskItems.get(i).getStateName();
			// task item title
			taskItemArray[i][0] = taskItems.get(i).getTitle();
		}
		return taskItemArray;
	}
	
	public Object[][] getTaskItemListByCreatorAsArray(String creator){
		List<TaskItem> taskItems = this.taskItemList.getTaskItemsByCreator(creator);
		int taskCount = taskItems.size();
		Object[][] taskItemArray = new Object[taskCount][];
		
		for (int i = 0 ; i < taskCount ; i++) {
			// task item id 
			taskItemArray[i][0] = taskItems.get(i).getTaskItemId();
			// task item state
			taskItemArray[i][0] = taskItems.get(i).getStateName();
			// task item title
			taskItemArray[i][0] = taskItems.get(i).getTitle();
		}
		return taskItemArray;
		
	}
	
	public TaskItem getTaskItemById(int id) {
		return this.taskItemList.getTaskItemsById(id);
		
	}
	
	public void executeCommand(int i, Command c) {
		
	}
	
	public void deleteTaskItemById(int id) {
		this.taskItemList.deleteTaskItemById(id);
	}
	
	public void addTaskItemToList(String title, Type type, String creator, String notes) {
		this.addTaskItemToList(title, type, creator, notes);
	}

}
