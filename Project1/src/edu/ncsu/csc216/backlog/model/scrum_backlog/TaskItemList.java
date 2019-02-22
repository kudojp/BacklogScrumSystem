/**
 * 
 */
package edu.ncsu.csc216.backlog.model.scrum_backlog;

import java.util.List;
import java.util.ArrayList;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.backlog.model.task.TaskItem;
import edu.ncsu.csc216.backlog.model.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;

/**
 * @author Daiki Kudo
 *
 */
public class TaskItemList {
	
	static final int INITIAL_COUNTER_VALUE = 1;

	private List<TaskItem> tasks;
	
	public TaskItemList() {
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
	}
	
	private void emptyList() {
		
	}
	
	public int addTaskItem(String title, Type type, String creator, String notes){
		TaskItem newTaskItem = new TaskItem(title, type, creator, notes);
		tasks.add(newTaskItem);
		return 0;
	}
	
	public void addXMLTasks(List<Task> TaskList) { 
		int maxId = 0;
		for (Task eachTask : TaskList) {
			maxId = Math.max(maxId, eachTask.getId());
			this.tasks.add(new TaskItem(eachTask));
		}
		TaskItem.setCounter(maxId + 1);
	}
	
	public List<TaskItem> getTaskItems(){
		return this.tasks;
	}
	
	public List<TaskItem> getTaskItemsByOwner(String owner){
		
		List<TaskItem> selectedTasks = null;
		
		for (TaskItem eachTask : this.tasks) {
			if (eachTask.getOwner().equals(owner)) {
				selectedTasks.add(eachTask);
			}
		}
		return selectedTasks;
	}
	
	public List<TaskItem> getTaskItemsByCreator(String creator){
		
		List<TaskItem> selectedTasks = null;
		
		for (TaskItem eachTask : this.tasks) {
			if (eachTask.getCreator().equals(creator)) {
				selectedTasks.add(eachTask);
			}
		}
		return selectedTasks;
	}
	
	public TaskItem getTaskItemsById(int id){
		
		for (TaskItem eachTask : this.tasks) {
			if (eachTask.getTaskItemId() == id) {
				return eachTask;
			}
		}
		return null;
	}

	public void executeCommand(int i, Command c) {
		
	}
	
	public void deleteTaskItemById(int id) {
		
		for (int i = this.tasks.size() ;  0 <= i ; i--) {
			if (this.tasks.get(i).getTaskItemId() == id) {
				this.tasks.remove(i);	
			}
		}		
	}
	
	
}
