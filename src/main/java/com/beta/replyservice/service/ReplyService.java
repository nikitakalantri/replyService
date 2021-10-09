package com.beta.replyservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.beta.replyservice.exception.ReplyException;
import com.beta.replyservice.model.ReplyMessage;
import com.beta.replyservice.operation.OperationChain;
import com.beta.replyservice.operation.StringOperation;

@Service
public class ReplyService {

	@Autowired
	private List<StringOperation> operations;

	private Map<Integer, StringOperation> operationMap;

	/**
	 * To apply the respective Operations on message
	 * 
	 * @param message Message to be processed
	 * @return the response after applying the Rules on message.
	 */
	public ReplyMessage process(String message) {
		
		String[] arr = message.split("-", 2);

		String operationString = arr[0];
		OperationChain chain = generateOperationChain(operationString);

		return new ReplyMessage(chain.executeChain(arr[1]));
	}

	/**
	 * Generates a chain of rules to be applied.
	 * @param operationString
	 * @return the Operation Chain.
	 * @throws Reply Exception if Rule number is invalid.
	 */
	private OperationChain generateOperationChain(String operationString) {
		OperationChain chain = new OperationChain();
		final int[] ruleNumbers = Stream.of(operationString.split("")).mapToInt(Integer::parseInt).toArray();
		for (int rule : ruleNumbers) {

			// validate if rule exists
			StringOperation operation = operationMap.get(rule);

			if (operation == null) {
				throw new ReplyException("Invalid rule number " + rule, HttpStatus.BAD_REQUEST);
			}

			chain.addOperation(operation);
		}
		return chain;
	}
	
	/**
	 * generate the map of rule number and its respective Operation to be performed.
	 * 
	 * @throws ReplyException If operation Id is already defined.
	 */
	@PostConstruct
	void generateOperationMap() {
		operationMap = new HashMap<>();
		for (StringOperation op : operations) {
			if (operationMap.containsKey(op.getOperationId())) {
				throw new ReplyException(
						"Operation Id - " + op.getOperationId() + " is already defined in " + op.getClass().getName(),
						HttpStatus.CONFLICT);
			} else {
				operationMap.put(op.getOperationId(), op);
			}
		}
	}

}
