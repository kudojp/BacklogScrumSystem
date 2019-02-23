package edu.ncsu.csc216.backlog.model.scrum_backlog;

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
	private List<TaskItem> tasks;
	
	/**
	 * Constructs ItemTaskList object.
	 * Sets the counter to 1 for the first ItemTask to be stored.
	 */
	public TaskItemList() {
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
	}
	
	/**
	 * to be described more later!!!!!!
	 */
	private void emptyList() {
		
	}
	
	/**
	 * Adds new Task Item to this TaskItemList
	 * @param title : title of the new TaskItem
	 * @param type : type of the new TaskItem
	 * @param creator : creator of the new TaskItem
	 * @param notes : notes of the new TaskItem
	 * @return integer??????
	 */
	public int addTaskItem(String title, Type type, String creator, String notes){
		TaskItem newTaskItem = new TaskItem(title, type, creator, notes);
		tasks.add(newTaskItem);  
		return 0;
	}
	
	/**
	 * Adds List of Tasks
	 * static field "setCounter" in TaskItem class is incremented by 1
	 * @param taskList : List of Tasks
	 */
	public void addXMLTasks(List<Task> taskList) { 
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
		
		List<TaskItem> selectedTasks = null;
		
		for (TaskItem eachTask : this.tasks) {
			if (eachTask.getOwner().equals(owner)) {
				selectedTasks.add(eachTask);
			}
		}
		return selectedTasks;
	}
	
	/**
	 * Returns List of TaskItems which are created by a given creator
	 * @param creator : creator of the TaskItems to be returned
	 * @return List of TaskItems which are created by a given creator
	 * 			(if there is not, null is returned)
	 */
	public List<TaskItem> getTaskItemsByCreator(String creator){
		
		List<TaskItem> selectedTasks = null;
		
		for (TaskItem eachTask : this.tasks) {
			if (eachTask.getCreator().equals(creator)) {
				selectedTasks.add(eachTask);
			}
		}
		return selectedTasks;
	}
	
	/**
	 * Returns TaskItem which has a given id
	 * @param id : id of the TaskItem to be returned
	 * @return TaskItem which has a given id
	 * 			(if there is not, null is returned)
	 */
	public TaskItem getTaskItemById(int id){
		
		for (TaskItem eachTask : this.tasks) {
			if (eachTask.getTaskItemId() == id) {
				return eachTask;
			}
		}
		return null;
	}

	/**
	 * Executes given command (To be described more!!!!!!!!!)
	 * @param i : int
	 * @param c : command
	 */
	public void executeCommand(int i, Command c) {
		
	}
	
	/**
	 * Removes TaskItem which has given id from this TaskItemList 
	 * @param id : id of the TaskItem to be deleted
	 */
	public void deleteTaskItemById(int id) {
		
		for (int i = this.tasks.size() ;  0 <= i ; i--) {
			if (this.tasks.get(i).getTaskItemId() == id) {
				this.tasks.remove(i);	
			}
		}		
	}
	
	
}
