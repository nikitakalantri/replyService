package com.beta.replyservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.beta.replyservice.exception.ReplyException;
import com.beta.replyservice.model.ReplyMessage;
import com.beta.replyservice.operation.EncodeOperation;
import com.beta.replyservice.operation.ReverseOperation;
import com.beta.replyservice.operation.StringOperation;

public class ReplyServiceTest {

	
	private List<StringOperation> operations = new ArrayList<StringOperation>();
	
	ReplyService classToTest;
	
	@BeforeEach
	void init() {
		classToTest = new ReplyService();
		generateOperations();
		ReflectionTestUtils.setField(classToTest, "operations", operations);
		classToTest.generateOperationMap();
	}
	
	@Test
	public void testWithValidMessage() {
		String message = "11-kbzw9ru";
		ReplyMessage replyMessage = new ReplyMessage("kbzw9ru");
		assertEquals(replyMessage, classToTest.process(message));
	}
	
	@Test
	public void testWithValidMessageAndMultipleRules() {
		String message = "12-kbzw9ru";
		ReplyMessage replyMessage = new ReplyMessage("5a8973b3b1fafaeaadf10e195c6e1dd4");
		assertEquals(replyMessage, classToTest.process(message));
	}
	
	@Test
	public void testDuplicateOperationIDFail() {
		operations.add(new StringOperation() {
			
			@Override
			public int getOperationId() {
				return 1;
			}
			
			@Override
			public String apply(String textMessage) {
				return null;
			}
		});
		assertThrows(ReplyException.class, () ->{			
			classToTest.generateOperationMap();
		});
	}
	
	
	@Test
	public void testWithInvalidRule() {
		String message = "13-kbzw9ru";
		assertThrows(ReplyException.class, () -> {
			classToTest.process(message);
		});
		
	}
	
	private void generateOperations() {
		operations.add(new ReverseOperation());
		operations.add(new EncodeOperation());
	}
	
}
