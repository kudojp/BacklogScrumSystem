package edu.ncsu.csc216.backlog.model.command;

/**
 * Command Class which is responsibe for the command of user input
 * @author Daiki Kudo
 */
public class Command {
	/** Command Value of this command */
	private CommandValue c;
	/** Note of this Command */
	private String note;
	/** Author of note of this Command */
	private String noteAuthor;	
	/** Lists possible commands */
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }

	
	/**
	 * Constructs Command object 
	 * @param c : command value
	 * @param noteAuthor : author of the note
	 * @param noteText : text of the note
	 * @throws IllegalArgumentException if either of given parameters is invalid.
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) throws IllegalArgumentException{
		
		if (c == null || noteAuthor == null || noteText == null || noteAuthor.equals("") || noteText.equals("") ){
			throw new IllegalArgumentException();
		}
	}
	
	
	/**
	 * Returns the command value
	 * @return the command value
	 */
	public CommandValue getCommand(){
		return this.c;
	}


	/**
	 * Returns the note text
	 * @return the noteText
	 */
	public String getNoteText() {
		return this.note;
	}

	/**
	 * Returns the note author
	 * @return the noteAuthor
	 */
	public String getNoteAuthor() {
		return this.noteAuthor;
	}
}
