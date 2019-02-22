/**
 * 
 */
package edu.ncsu.csc216.backlog.model.command;

/**
 * @author Daiki Kudo
 *
 */
public class Command {
	
	private CommandValue c;
	private String note;
	private String noteAuthor;	
	
	/**
	 * 
	 * @param c
	 * @param noteAuthor
	 * @param noteText
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) {
		
		if (c == null || noteAuthor == null || noteText == null || noteAuthor.equals("") || noteText.equals("") ){
			throw new IllegalArgumentException();
		}
	}
	
	
	/**
	 * @return the note
	 */
	public CommandValue getCommand(){
		return this.c;
	}


	/**
	 * @return the noteAuthor
	 */
	public String getNoteText() {
		return this.noteAuthor;
	}



	/**
	 * @return the noteAuthor
	 */
	public String getNoteAuthor() {
		return this.noteAuthor;
	}



	
		
	
	/** Lists possible commands */
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }

}
