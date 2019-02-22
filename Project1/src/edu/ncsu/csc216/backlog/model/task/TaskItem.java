package edu.ncsu.csc216.backlog.model.task;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.task.xml.Task;

/**
 * @author Daiki Kudo
 *
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
	private Note notes;
	/** owner of this task */
	private String owner;
	/** whether this task is verified */
	private Boolean isVerified;
	/** counter which maintains the taskId number of the next TaskItem */
	
	private final TaskItemState backlogState = new BacklogState(); 
	private final TaskItemState ownedState = new OwnedState(); 
	private final TaskItemState processingState = new ProcessingState(); 
	private final TaskItemState verifyingState = new VerifyingState(); 
	private final TaskItemState doneState = new DoneState(); 
	private final TaskItemState rejectedState = new RejectedState(); 
	
	public static final String BACKLOG_NAME = "Backlog";
	public static final String OWNED_NAME = "Owned";
	public static final String PROCESSING_NAME = "Processing";
	public static final String VERIFYING_NAME = "Verifying";
	public static final String DONE_NAME = "Done";
	public static final String REJECTED_NAME = "Rejected";
	
	public static final String T_FEATURE = "F";
	public static final String T_BUG = "B";
	public static final String T_TECHNICAL = "TW";
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	private static int counter = 1;
	
	/**
	 * 
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
		this.notes = new Note(creator, notes);
		this.taskId = counter;
		
		this.state  = new BacklogState();
		
		
		incrementCounter();
	}
	
	public TaskItem(Task task) {
		
	}
	
	
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
	
	
	private void setState(String stateName) {
		//this.state = 
	}
	
	private void setType(String type) {
		//
	}
	
	
	
	
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * @return the type
	 */
	public String getTypeString() {
		return "";
	}
	
	/**
	 * @return the type
	 */
	public String getTypeFullString() {
		return "";
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @return the notes
	 */
	public ArrayList<Note> getNotes() {
		return null;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	public void update(Command c) {
		
	}
	
	public Task getXMLTask(){
		return null;
	}
	

	public static void setCounter(int i) {
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		counter = i;
	}
	
	public String[][] getNotesArray(){
		return null;
	}
	
	
	
	/**
	 * @author Daiki Kudo
	 *
	 */
	public class Note {

		/** Author of this note*/
		private String noteAuthor;
		/** Note about a task */
		private String noteText;

		/**
		 * Constructs Note object which contains note and the author's name
		 * @param noteAuthor : author's name of a task
		 * @param noteAuthor : note about a task
		 * @throws IllegalArgumentException if a note or a noteAuthor is invalid
		 */
		public Note(String noteAuthor, String noteText) throws IllegalArgumentException{
			if (noteAuthor == null || noteAuthor.equals("")) {
				throw new IllegalArgumentException();
			}
			this.setNoteAuthor(noteAuthor);
			
			if (noteText == null || noteText.equals("")) {
				throw new IllegalArgumentException();
			}
			this.setNoteText(noteText);
		}



		/**
		 * @return the noteAuthor
		 */
		public String getNoteAuthor() {
			return noteAuthor;
		}
		
		/**
		 * @param noteAuthor the noteAuthor to set
		 */
		private void setNoteAuthor(String noteAuthor) {
			this.noteAuthor = noteAuthor;
		}
		
		/**
		 * @return the noteText
		 */
		public String getNoteText() {
			return noteText;
		}

		/**
		 * @param noteText the noteText to set
		 */
		private void setNoteText(String noteText) {
			this.noteText = noteText;
		}

		/**
		 * Note.getNoteArray() returns an array of length 2 with the author at index 0 and the text at index 1. 
		 * This is used when creating a list of notes to display in the GUI.
		 * @return noteArray : String array of an author name and text of a note
		 */
		public String[] getNoteArray() {
			String[] noteArray =  {this.getNoteAuthor(), this.getNoteText()};
			return noteArray;
		}



	}

	
	
	
	
	
	
	/**
	 * 
	 * @author Daiki Kudo
	 *
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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



