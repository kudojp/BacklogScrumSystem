/**
 * 
 */
package edu.ncsu.csc216.backlog.model.task;

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