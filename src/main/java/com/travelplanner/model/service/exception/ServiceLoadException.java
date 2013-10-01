package com.travelplanner.model.service.exception;

/**Class for handling a ServiceLoadException.
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class ServiceLoadException extends Exception {
	
	/**Constructor for creating instance of ServiceLoadException class.
	 * @param message Incoming exception message.
	 * @param e Incoming exception.
	 */
	public ServiceLoadException(String message, Exception e) {
		super(message);
	}
}