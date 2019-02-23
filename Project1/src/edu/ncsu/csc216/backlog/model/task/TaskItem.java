package edu.ncsu.csc216.backlog.model.task;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.task.xml.Task;

/**
 * TaskItem class representing each Task
 * @author Daiki Kudo
 */
public class TaskItem{
	
	/** Lists possible task types */
	public enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }

	/** id of this task */
	private int taskId;
	/**　state of this task */
	private TaskItemState state;
	/** type of this task */
	private Type type;
	/** title of this task */
	private String title;
	/** creator of this task */
	private String creator;
	/** note of this task*/
	private ArrayList<Note> notes;
	/** owner of this task */
	private String owner;
	/** whether this task is verified */
	private Boolean isVerified;
	/** counter which maintains the taskId number of the next TaskItem */
	private static int counter = 1;
	
	/** static TaskItemState which represents "Backlog" */
	private final TaskItemState backlogState = new BacklogState(); 
	/** static TaskItemState which represents "Owned" */
	private final TaskItemState ownedState = new OwnedState(); 
	/** static TaskItemState which represents "Processing" */
	private final TaskItemState processingState = new ProcessingState();
	/** static TaskItemState which represents "Verifying" */
	private final TaskItemState verifyingState = new VerifyingState();
	/** static TaskItemState which represents "Done" */
	private final TaskItemState doneState = new DoneState(); 
	/** static TaskItemState which represents "Rejected" */
	private final TaskItemState rejectedState = new RejectedState(); 
	
	/** static String which represents "Backlog" state */
	public static final String BACKLOG_NAME = "Backlog";
	/** static String which represents "Owned" state */
	public static final String OWNED_NAME = "Owned";
	/** static String which represents "Processing" state */
	public static final String PROCESSING_NAME = "Processing";
	/** static String which represents "Verifying" state */
	public static final String VERIFYING_NAME = "Verifying";
	/** static String which represents "Done" state */
	public static final String DONE_NAME = "Done";
	/** static String which represents "Rejected" state */
	public static final String REJECTED_NAME = "Rejected";
	
	/** static String which represents "Feature" Type */
	public static final String T_FEATURE = "F";
	/** static String which represents "Bug" Type */
	public static final String T_BUG = "B";
	/** static String which represents "Technical" Type */
	public static final String T_TECHNICAL = "TW";
	/** static String which represents "Knowledge acquisition" Type */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	
	/**
	 * Constructs TaskItem object
	 * @param title : title of the object
	 * @param type : type of the object
	 * @param creator : creator of the object
	 * @param notes : notes of the object
	 */
	public TaskItem(String title, Type type, String creator, String notes){
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		if (type == null) {
			throw new IllegalArgumentException();
		}
		if (creator == null || creator.equals("")) {
			throw new IllegalArgumentException();
		}
		if (notes == null || notes.equals("")) {
			throw new IllegalArgumentException();
		}
		
		this.title = title;
		this.type = type;
		this.creator = creator;
		this.notes.add(new Note(creator, notes));
		this.taskId = counter;
		
		this.state  = new BacklogState();
		
		// increment counter for id of the next TaskItem
		incrementCounter();
	}
	
	/**
	 * Creates TaskItem object from a given Task object
	 * @param task : Task object
	 */
	public TaskItem(Task task) {
		
	}
	
	/**
	 * Increments static field "counter" which represents the id of TaskItem object which would be constructed next.
	 */
	public static void incrementCounter() {
		counter += 1;
	}
	
	/**
	 * Returns id of this task.
	 * @return taskId : id of this task
	 */
	public int getTaskItemId(){
		return this.taskId;
	}
	
	/**
	 * Returns state name of this task
	 * @return String state name of this task
	 */
	public String getStateName() {
		return this.state.getStateName();	
	}
	
	/**
	 * Sets state of this TaskItem 
	 * @param state : String state name which this TaskItem would be set
	 */
	private void setState(String state) {
		//this.state = 
	}
	
	/**
	 * Sets type of this TaskItem 
	 * @param type : String type name which this TaskItem would be set
	 */
	private void setType(String type) {
		//
	}
	
	
	
	
	
	/**
	 * Returns type of this TaskItem
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns short String which represents type of this TaskItem
	 * @return short String which represents type
	 */
	public String getTypeString() {
		return "";
	}
	
	/**
	 * Returns full String which represents type of this TaskItem
	 * @return full String which represents type
	 */
	public String getTypeFullString() {
		return "";
	}

	/**
	 * Returns title of this TaskItem
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns creator of this TaskItem
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Returns ArrayList of notes of this TaskItem
	 * @return ArrayList of notes
	 */
	public ArrayList<Note> getNotes() {
		return null;
	}

	/**
	 * Returns the owner of this TaskItem
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Updates this TaskItem with a given command
	 * @param c : command 
	 */
	public void update(Command c) {
		
	}
	
	/**
	 * Returns Task object which corresponds to this TaskItem
	 * @return Task object
	 */
	public Task getXMLTask(){
		return null;
	}
	
	/**
	 * Sets static field "counter" at a given value
	 * @param i : value which counter would be set at (has be more than 0)
	 */
	public static void setCounter(int i) {
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		counter = i;
	}
	
	/**
	 * Returns String Array of Notes of this TaskItem
	 * @return Array of Notes 
	 */
	public String[][] getNotesArray(){
		return null;
	}
	
	
	/**
	 * BacklogState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 */
	private class BacklogState implements TaskItemState{

		@Override
		public void updateState(Command c) throws UnsupportedOperationException{
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return BACKLOG_NAME;
		}
	}
	
	/**
	 * OwnedState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class OwnedState implements TaskItemState{

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return OWNED_NAME;
		}
	}
	
	
	/**
	 * ProcessingState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class ProcessingState implements TaskItemState{

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return PROCESSING_NAME;
		}
	}
	
	
	/**
	 * VerifyingState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class VerifyingState implements TaskItemState{

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return VERIFYING_NAME;
		}
	}
	
	
	
	/**
	 * DoneState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class DoneState implements TaskItemState{

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return DONE_NAME;
		}
	}
	
	
	
	/**
	 * RejectedState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class RejectedState implements TaskItemState{

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return REJECTED_NAME;
		}
	}
	
	
	
	

	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskItemState{
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	}
}



