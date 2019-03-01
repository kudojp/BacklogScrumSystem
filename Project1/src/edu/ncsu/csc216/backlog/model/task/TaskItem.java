package edu.ncsu.csc216.backlog.model.task;

import java.util.*;

import edu.ncsu.csc216.backlog.model.command.Command;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * TaskItem class representing each Task
 * @author Daiki Kudo
 */
public class TaskItem {
	
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
	
	/** static full String which represents "Feature" Type */
	public static final String FULL_FEATURE = "Feature";
	/** static full String which represents "Bug" Type */
	public static final String FULL_BUG = "Bug";
	/** static full String which represents "Technical" Type */
	public static final String FULL_TECHNICAL = "Technical";
	/** static full String which represents "Knowledge acquisition" Type */
	public static final String FULL_KNOWLEDGE_ACQUISITION = "Knowledge acquisition";
	
	
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
		
		this.setState(BACKLOG_NAME);
		
		// the first state should be "Backlog"
		this.state  = new BacklogState(); 
		this.type = type;
		this.title = title;
		this.creator = creator;
		this.notes = new ArrayList<Note>();
		this.notes.add(new Note(creator, notes));
		this.taskId = counter;
		
		// increment counter for id of the next TaskItem
		incrementCounter();
	}
	
	/**
	 * Creates TaskItem object from a given Task object
	 * @param task : Task object
	 */
	public TaskItem(Task task) {
		this.taskId = task.getId();
		this.setState(task.getState());
		this.setType(task.getType());
		this.title = task.getTitle();
		this.isVerified = task.isVerified();
		this.creator = task.getCreator();
		this.owner = task.getOwner();
		this.notes = new ArrayList<Note>();
		List<NoteItem> notelist = task.getNoteList().getNotes();
		for (NoteItem ni : notelist) {
			Note n = new Note(ni.getNoteAuthor(), ni.getNoteText());
			this.notes.add(n);
		}
		this.taskId = task.getId();
		this.isVerified = task.isVerified();
		
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
		if (state == null) {
			throw new IllegalArgumentException();
		} else if (state.equals(BACKLOG_NAME)){
			this.state = backlogState;
		} else if (state.equals(OWNED_NAME)) {
			this.state = ownedState;
		} else if (state.equals(PROCESSING_NAME)){
			this.state = processingState;
		} else if (state.equals(VERIFYING_NAME)) {
			this.state = verifyingState;
		} else if (state.equals(DONE_NAME)){
			this.state = doneState;
		} else if (state.equals(REJECTED_NAME)) {
			this.state = rejectedState;
		} else {
			throw new IllegalArgumentException();
		} 
	}
	
	/**
	 * Sets type of this TaskItem 
	 * @param type : String type name which this TaskItem would be set
	 */
	private void setType(String type) {
		if (type == null) {
			throw new IllegalArgumentException();
		} else if (type.equals(T_FEATURE)){
			this.type = Type.FEATURE;
		} else if (type.equals(T_BUG)) {
			this.type = Type.BUG;
		} else if (type.equals(T_TECHNICAL)){
			this.type = Type.TECHNICAL_WORK;
		} else if (type.equals(T_KNOWLEDGE_ACQUISITION)) {
			this.type = Type.KNOWLEDGE_ACQUISITION;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
	
	
	
	/**
	 * Returns type of this TaskItem
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * Returns short String which represents type of this TaskItem
	 * @return short String which represents type
	 */
	public String getTypeString() {
		if (this.type.equals(Type.FEATURE)){
			return T_FEATURE;
		} else if (this.type.equals(Type.BUG)) {
			return T_BUG;
		} else if (this.type.equals(Type.TECHNICAL_WORK)) {
			return T_TECHNICAL;
		} else if (this.type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			return T_KNOWLEDGE_ACQUISITION;
		} else {
			throw new IllegalArgumentException("Somthing is wrong with type");
		}
	}
	
	/**
	 * Returns full String which represents type of this TaskItem
	 * @return full String which represents type
	 */
	public String getTypeFullString() {
		if (this.type.equals(Type.FEATURE)){
			return FULL_FEATURE;
		} else if (this.type.equals(Type.BUG)) {
			return FULL_BUG;
		} else if (this.type.equals(Type.TECHNICAL_WORK)) {
			return FULL_TECHNICAL;
		} else if (this.type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			return FULL_KNOWLEDGE_ACQUISITION;
		} else {
			throw new IllegalArgumentException("Somthing is wrong with type");
		}
	}

	/**
	 * Returns title of this TaskItem
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
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
		return this.notes;
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
		state.updateState(c);
	}
	
	/**
	 * Returns Task object which corresponds to this TaskItem
	 * @return Task object
	 */
	public Task getXMLTask(){
		Task t = new Task();
		t.setTitle(this.getTitle());
		t.setId(this.getTaskItemId());
		t.setType(this.getTypeString());
		t.setState(this.getStateName());
		t.setCreator(this.getCreator());
		t.setVerified(this.isVerified);
		t.setOwner(this.getOwner());
		
		NoteList nl = new NoteList();
		for (Note eachNI : this.getNotes()) {
			NoteItem ni = new NoteItem();
			ni.setNoteAuthor(eachNI.getNoteAuthor());
			ni.setNoteText(eachNI.getNoteText());
			nl.getNotes().add(ni);
		}
		t.setNoteList(nl);

		return t;
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
		int nNotes = this.notes.size();
		String[][] nArray = new String[nNotes][2];
		for (int i = 0 ; i < nNotes ; i++) {
			nArray[i][0] = this.notes.get(i).getNoteAuthor();
			nArray[i][1] = this.notes.get(i).getNoteText();
		}
		return nArray;
	}
	
	
	/**
	 * BacklogState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 */
	private class BacklogState implements TaskItemState {

		@Override
		public void updateState(Command c) throws UnsupportedOperationException{
			//NO OWNER !!!
			//BacklogA(Backlog to Owned) add owner
			//BacklogB(Backlog to Rejected)　
			
			switch(c.getCommand()) {
			case CLAIM:
				owner = c.getNoteAuthor();
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				state = ownedState;
				break;
			case REJECT:
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				state = rejectedState;
				break;
			default:
				throw new UnsupportedOperationException("");
			}
			
		}

		@Override
		public String getStateName() {
			return BACKLOG_NAME;
		}
	}
	
	/**
	 * OwnedState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class OwnedState implements TaskItemState {

		@Override
		public void updateState(Command c) {
			//NO AUTHOR with command!!!
			//OwnedA(Owned to Processing)
			//OwnedB(Owned to Rejected) remove owner
			//OwnedC(Owned to Backlog) remove owner
			
			switch(c.getCommand()) {
			case PROCESS:
				notes.add(new Note(owner, c.getNoteText()));
				state = processingState;
				break;
			case REJECT:
				notes.add(new Note(owner, c.getNoteText()));
				owner = null;
				state = rejectedState;
				break;
			case BACKLOG:
				notes.add(new Note(owner, c.getNoteText()));
				owner = null;
				state = backlogState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
			
		}

		@Override
		public String getStateName() {
			return OWNED_NAME;
		}
	}
	
	
	/**
	 * ProcessingState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class ProcessingState implements TaskItemState {

		@Override
		public void updateState(Command c) {
			// ProcessingA(Processing to Processing)
			// ProcessingB(Processing to Verifying)
								//only when type is features,bugs,or technical
			// ProcessingC(Processing to Done) :
								//when type is features,bugs,or technical = isVerified has to be true
								//when type is knowledge = OK
			// ProcessingD(Processing to Backlog) remove owner
			
			switch(c.getCommand()) {
			case PROCESS:
				notes.add(new Note(owner, c.getNoteText()));
				state = processingState;  // actually no change
				break;
			case VERIFY:
				if (type == Type.FEATURE || type == Type.BUG || type == Type.TECHNICAL_WORK) {
					notes.add(new Note(owner, c.getNoteText()));
					state = verifyingState;
				} else {
					throw new UnsupportedOperationException();
				}
				break;
			case COMPLETE:
				if (type == Type.KNOWLEDGE_ACQUISITION) {
					notes.add(new Note(owner, c.getNoteText()));
					state = doneState;
				} else if (isVerified) {
					notes.add(new Note(owner, c.getNoteText()));
					state = doneState;
				}
				break;
			case BACKLOG:
				notes.add(new Note(owner, c.getNoteText()));
				owner = null;
				state = backlogState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
			
		}

		@Override
		public String getStateName() {
			return PROCESSING_NAME;
		}
	}
	
	
	/**
	 * VerifyingState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class VerifyingState implements TaskItemState {

		@Override
		public void updateState(Command c) {
			// There is an owner for this item. but note is written by another member!
			// This state is only for the type is BUG, FEATURE, or TECHNICAL
			// VerifyingA(Verifying to Done) set ifVerifiedd to True
			// VerifyingB(Verifying to Processing)
			
			if (type == Type.KNOWLEDGE_ACQUISITION) {
				throw new UnsupportedOperationException();
			}
			
			switch (c.getCommand()) {
			case COMPLETE:
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				isVerified = true;
				state = doneState;
				break;
			case PROCESS:
				
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				state = processingState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
			
		}

		@Override
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}
	
	
	
	/**
	 * DoneState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class DoneState implements TaskItemState {

		@Override
		public void updateState(Command c) {
			// DoneA(Done to Processing) reset isVerified to false
			// DoneB(Done to Backlog) remove owner , reset isVerified to false
			switch (c.getCommand()) {
			case PROCESS:
				notes.add(new Note(owner, c.getNoteText()));
				state = processingState;
				isVerified = false;
				state = processingState;
				break;
			case BACKLOG:
				notes.add(new Note(owner, c.getNoteText()));
				owner = null;
				isVerified = false;
				state = backlogState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
			
		}

		@Override
		public String getStateName() {
			return DONE_NAME;
		}
	}
	
	
	
	/**
	 * RejectedState class which is an inner class of TaskItemState
	 * @author Daiki Kudo
	 *
	 */
	private class RejectedState implements TaskItemState {

		@Override
		public void updateState(Command c) {
			// NO OWNER !!!
			//RejectedA(Rejected to Backlog)
			
			switch(c.getCommand()) {
			case BACKLOG:
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				state = backlogState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
			
		}

		@Override
		public String getStateName() {
			return REJECTED_NAME;
		}
	}
	

	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskItemState {
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		void updateState(Command c) throws UnsupportedOperationException;
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	}
}



