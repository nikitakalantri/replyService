package com.beta.replyservice.operation;

import java.util.LinkedList;
import java.util.List;

public class OperationChain {
	private List<StringOperation> chain = new LinkedList<>();

	/**
	 * Adds the operation in chain
	 * @param operation
	 */
	public void addOperation(final StringOperation operation) {
		chain.add(operation);
	}

	/**
	 * Executes the chain by applying the result of previous to next one.
	 * @param message
	 * @return the final message after applying all the operation.
	 */
	public String executeChain(String message) {
		String reply = message;

		for (StringOperation op : chain) {
			reply = op.apply(reply);
		}

		return reply;
	}

}
