/**
 * 
 */
package edu.ncsu.csc216.backlog.model.scrum_backlog;

import java.util.List;
import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;
import edu.ncsu.csc216.task.xml.TaskWriter;

/**
 * ScrumBacklogModel Class which is responsible for dealing handling taskItemList
 * This is a singleton class.
 * @author Daiki Kudo
 */
public class ScrumBacklogModel {
	 
	/** the only instance of ScrumBacklogModel */
	private static ScrumBacklogModel instance;
	/** taskItem List of ScrumBacklogModel*/
	private TaskItemList taskItemList;

	/**
	 * Constructs ScrumBacklogModel instance
	 * This constructor is called only by getInstance() static method.
	 */
	private ScrumBacklogModel() {
		this.taskItemList = new TaskItemList();
	}
	
	/**
	 * Returns instance of ScrumBacklogModel.
	 * This static method constructs instance if it is not constructed yet.
	 * @return instance : instance of ScrumBacklogModel
	 */
	public static ScrumBacklogModel getInstance() {
		if (instance == null) {
			instance = new ScrumBacklogModel();
		}
		return instance;
	}
	
	/**
	 * Saves Tasks in the taskItemList to the given file
	 * @param fileName : path of the file where tasks are saved
	 */
	public void saveTasksToFile(String fileName) {
		
		TaskWriter writer = new TaskWriter(fileName);
		try {
			writer = new TaskWriter(fileName);
			for (int i = 0 ; i < this.taskItemList.getTaskItems().size() ; i++) {
				writer.addItem(this.taskItemList.getTaskItems().get(i).getXMLTask());
			}
			writer.marshal();
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Loads Tasks to taskItemList from the given file
	 * @param fileName : path of the file to be loaded
	 */
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
	
	/**
	 * Resets taskItemList
	 */
	public void createNewTaskItemList() {
		this.taskItemList = new TaskItemList();
	}
	
	/**
	 * Returns tasks represented by String Array
	 * @return teskItemList : 2D string array whose each row represents each task.
	 * 			Each row has 3 columns, which stores ID, State, Title in this order.
	 */
	public Object[][] getTaskItemListAsArray(){
		List<TaskItem> taskItems = this.taskItemList.getTaskItems();
		int taskCount = taskItems.size();
		Object[][] taskItemArray = new Object[taskCount][3];
		
		for (int i = 0 ; i < taskCount ; i++) {
			// task item id 
			taskItemArray[i][0] = taskItems.get(i).getTaskItemId();
			// task item state
			taskItemArray[i][1] = taskItems.get(i).getStateName();
			// task item title
			taskItemArray[i][2] = taskItems.get(i).getTitle();
		}
		return taskItemArray;
	}
	
	/**
	 * Returns tasks which is owned by a given owner represented by String Array
	 * @param owner : owner name used for filtering
	 * @return teskItemList : 2D string array whose each row represents each filtered task.
	 * 			Each row has 3 columns, which stores ID, State, Title in this order.
	 */
	public Object[][] getTaskItemListByOwnerAsArray(String owner){
		List<TaskItem> taskItems = this.taskItemList.getTaskItemsByOwner(owner);
		
		int taskCount = taskItems.size();
		Object[][] taskItemArray = new Object[taskCount][3];
		
		for (int i = 0 ; i < taskCount ; i++) {
			// task item id 
			taskItemArray[i][0] = taskItems.get(i).getTaskItemId();
			// task item state
			taskItemArray[i][1] = taskItems.get(i).getStateName();
			// task item title
			taskItemArray[i][2] = taskItems.get(i).getTitle();
		}
		
		return taskItemArray;
		
		/**
		int taskCount = 0;
		for (int i = 0 ; i < taskItems.size() ; i++) {
			if (taskItems.get(i).getOwner().equals(owner)){
				taskCount++;
			}
		}
			
		Object[][] taskItemArray = new Object[taskCount][3];
		int arrayIndex = 0;
		for (int i = 0 ; i < taskItems.size() ; i++) {
			if (taskItems.get(i).getOwner().equals(owner)){
				// task item id 
				taskItemArray[arrayIndex][0] = taskItems.get(i).getTaskItemId();
				// task item state
				taskItemArray[arrayIndex][1] = taskItems.get(i).getStateName();
				// task item title
				taskItemArray[arrayIndex][2] = taskItems.get(i).getTitle();
				arrayIndex++;
			}
		}
		return taskItemArray;
		*/
	}
	
	/**
	 * Returns tasks which is created by a given creator represented by String Array
	 * @param creator : creator name used for filtering
	 * @return teskItemList : 2D string array whose each row represents each filtered task.
	 * 			Each row has 3 columns, which stores ID, State, Title in this order.
	 */
	public Object[][] getTaskItemListByCreatorAsArray(String creator){
		
List<TaskItem> taskItems = this.taskItemList.getTaskItemsByOwner(creator);
		
	
		int taskCount = taskItems.size();
		Object[][] taskItemArray = new Object[taskCount][3];
		
		for (int i = 0 ; i < taskCount ; i++) {
			// task item id 
			taskItemArray[i][0] = taskItems.get(i).getTaskItemId();
			// task item state
			taskItemArray[i][1] = taskItems.get(i).getStateName();
			// task item title
			taskItemArray[i][2] = taskItems.get(i).getTitle();
		}
		
		return taskItemArray;
		
		
		/**
		List<TaskItem> taskItems = this.taskItemList.getTaskItemsByCreator(creator);
		
		int taskCount = 0;
		for (TaskItem t : taskItems) {
			if (t.getCreator().equals(creator)) {
				taskCount++;
			}
		}
		
		Object[][] taskItemArray = new Object[taskCount][3];
		int count = 0;
		
		for (TaskItem t : taskItems) {
			if (t.getCreator().equals(creator)) {
				taskItemArray[count][0] = t.getTaskItemId();
				taskItemArray[count][1] = t.getStateName();
				taskItemArray[count][2] = t.getTitle();
				count++;
			}
		}
		
		return taskItemArray;
		*/
		

	}
	
	/**
	 * Returns taskItem which has given id  
	 * @param id : id used for filtering
	 * @return taskItem which has given id  
	 */
	public TaskItem getTaskItemById(int id) {
		return this.taskItemList.getTaskItemById(id);
	}
	
	/**
	 * Executes Command
	 * @param id : id of the task to be handled
	 * @param c : Command
	 */
	public void executeCommand(int id, Command c) {
		this.taskItemList.executeCommand(id, c);
	}
	
	/**
	 * Removes TaskItem which has given id from taskItemList
	 * @param id : id of the taskItem to be removed 
	 */
	public void deleteTaskItemById(int id) {
		this.taskItemList.deleteTaskItemById(id);
	}
	
	/**
	 * Adds new TaskItem to the taskItemList
	 * @param title : title of the new TaskItem
	 * @param type : type of the new TaskItem
	 * @param creator : creator of the new TaskItem
	 * @param notes : notes of the new TaskItem
	 */
	public void addTaskItemToList(String title, Type type, String creator, String notes) {
		this.taskItemList.getTaskItems().add(new TaskItem(title, type, creator, notes));
	}

}
