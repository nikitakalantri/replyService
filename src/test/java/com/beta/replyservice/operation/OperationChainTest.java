package com.beta.replyservice.operation;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.beta.replyservice.operation.EncodeOperation;
import com.beta.replyservice.operation.ReverseOperation;
import com.beta.replyservice.operation.StringOperation;

public class OperationChainTest {

	private List<StringOperation> chain = new LinkedList<>();
	
	OperationChain classToTest;
	
	@BeforeEach
	void init() {
		classToTest = new OperationChain();
		ReflectionTestUtils.setField(classToTest, "chain", chain);
	}
	
	@Test
	public void testAddOperation() {
		classToTest.addOperation(new ReverseOperation());
		classToTest.addOperation(new EncodeOperation());
		assertEquals(2, chain.size());
	}
	
	@Test
	public void testExecuteChain() {
		String message = "kbzw9ru";
		String reply = "5a8973b3b1fafaeaadf10e195c6e1dd4";
		classToTest.addOperation(new ReverseOperation());
		classToTest.addOperation(new EncodeOperation());
		assertEquals(reply, classToTest.executeChain(message));
	}
}
