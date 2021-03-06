package edu.ncsu.csc216.backlog.model.scrum_backlog;

import java.util.ArrayList;
import java.util.List;
import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;

/**
 * TaskItemList Class which is responsible for storing TaskItems in it
 * @author Daiki Kudo
 */
public class TaskItemList {
	
	/** counter used for the first TaskItem stored in this TaskItemList.*/
	static final int INITIAL_COUNTER_VALUE = 1;

	/**　List of TaskItems */
	private ArrayList<TaskItem> tasks;
	
	/**
	 * Constructs ItemTaskList object.
	 * Sets the counter to 1 for the first ItemTask to be stored.
	 */
	public TaskItemList() {
		this.emptyList();
	}
	
	/**
	 * Resets tasks to empty arrayList.
	 */
	private void emptyList() {
		this.tasks = new ArrayList<TaskItem>();
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
	}
	
	/**
	 * Adds new Task Item to this TaskItemList
	 * @param title : title of the new TaskItem
	 * @param type : type of the new TaskItem
	 * @param creator : creator of the new TaskItem
	 * @param notes : notes of the new TaskItem
	 * @return newId : ID of newly added TaskItem
	 */
	public int addTaskItem(String title, Type type, String creator, String notes){ 
		TaskItem newTaskItem = new TaskItem(title, type, creator, notes);
		int newId = newTaskItem.getTaskItemId();
		tasks.add(newTaskItem);  
		return newId;
	}
	
	/**
	 * Adds List of Tasks
	 * static field "setCounter" in TaskItem class is incremented by 1
	 * @param taskList : List of Tasks
	 */
	public void addXMLTasks(List<Task> taskList) { 
		if (taskList == null) {
			throw new IllegalArgumentException();
		}
		
		int maxId = 0;
		for (Task eachTask : taskList) {
			maxId = Math.max(maxId, eachTask.getId());
			this.tasks.add(new TaskItem(eachTask));
		}
		TaskItem.setCounter(maxId + 1);
	}
	
	/**
	 * Returns List of TaskItems of this TaskItemList
	 * @return List of TaskItems
	 */
	public List<TaskItem> getTaskItems(){
		return this.tasks;
	}
	
	/**
	 * Returns List of TaskItems which are created by a given owner
	 * @param owner : owner of the TaskItems to be returned
	 * @return List of TaskItems which are owned by a given owner
	 * 			(if there is not, null is returned)
	 */
	public List<TaskItem> getTaskItemsByOwner(String owner){
		
		if (owner == null) {
			throw new IllegalArgumentException();
		}
		
		List<TaskItem> filteredTasks = new ArrayList<TaskItem>();

		for (TaskItem eachTaskItem : this.tasks) {
			if (eachTaskItem.getOwner() != null && eachTaskItem.getOwner().equals(owner)) {
				filteredTasks.add(eachTaskItem);
			}
		}
		return filteredTasks;
	}
	
	/**
	 * Returns List of TaskItems which are created by a given creator
	 * @param creator : creator of the TaskItems to be returned
	 * @return List of TaskItems which are created by a given creator
	 * 			(if there is not, null is returned)
	 */
	public List<TaskItem> getTaskItemsByCreator(String creator){
		
		if (creator == null) {
			throw new IllegalArgumentException();
		}
		List<TaskItem> filteredTasks = new ArrayList<TaskItem>();
		
		for (TaskItem eachTaskItem : this.tasks) {
			if (eachTaskItem.getCreator() != null && eachTaskItem.getCreator().equals(creator)) {
				filteredTasks.add(eachTaskItem);
			}
		}
		return filteredTasks;
	}
	
	/**
	 * Returns TaskItem which has a given id
	 * @param id : id of the TaskItem to be returned
	 * @return TaskItem which has a given id
	 * 			(if there is not, null is returned)
	 */
	public TaskItem getTaskItemById(int id){
		
		for (TaskItem eachTaskItem : this.tasks) {
			if (eachTaskItem.getTaskItemId() == id) {
				return eachTaskItem;
			}
		}
		return null;
	
	}

	/**
	 * Executes given command
	 * @param id : id of the task to be handeled
	 * @param c : command givenå
	 */
	public void executeCommand(int id, Command c) {
		TaskItem ti = this.getTaskItemById(id);
		if (ti != null) {
			ti.update(c);
		}
			
	}
	
	/**
	 * Removes TaskItem which has given id from this TaskItemList 
	 * @param id : id of the TaskItem to be deleted
	 */
	public void deleteTaskItemById(int id) {
		
		for (int i = this.tasks.size() - 1 ;  0 <= i ; i--) {
			if (this.tasks.get(i).getTaskItemId() == id) {
				this.tasks.remove(i);	
			}
		}		
	}
	
	
}
