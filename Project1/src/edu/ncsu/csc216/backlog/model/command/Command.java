/**
 * 
 */
package edu.ncsu.csc216.backlog.model.command;

/**
 * @author admin
 *
 */
public class Command {
	
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
		
	
	/** Lists possible commands */
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }

}
