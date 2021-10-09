package com.beta.replyservice.operation;


/**
 * Interface to be extended for all the String Operations.
 *
 */
public interface StringOperation {

	/**
	 * Operation To be performed on a String message
	 * @param textMessage
	 * @return the String after performing rule/operation
	 */
	String apply(String textMessage);
	
	
	/**
	 * Return the Id of each Operation.
	 * Note: Id has to be Unique for each Operation.
	 * @return
	 */
	int getOperationId();

}
