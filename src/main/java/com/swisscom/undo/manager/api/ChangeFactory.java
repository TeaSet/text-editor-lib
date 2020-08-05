package com.swisscom.undo.manager.api;

/**
 * An interface that allows creation of a deletion or insertion {@link Change}
 * into a {@link Document}.
 */
public interface ChangeFactory {

	/**
	 * Creates a deletion change.
	 * 
	 * @param pos The position to start the deletion.
	 * @param s The string to delete.
	 * @return The deletion {@link Change}.
	 */
	public Change createDeletion(int pos, String s);
	
	/**
	 * Creates an insertion change.
	 * 
	 * @param pos The position at which to insert.
	 * @param s The string to insert.
	 * @return The insertion {@link Change}.
	 */
	public Change createInsertion(int pos, String s);
}
