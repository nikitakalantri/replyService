package com.beta.replyservice.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReverseOperationTest {
	
	ReverseOperation classToTest;
	
	@BeforeEach
	public void init() {
		classToTest = new ReverseOperation();
	}
	
	@Test
	public void testGetOperationId() {
		assertEquals(1, classToTest.getOperationId());
	}
	
	@Test
	public void testApplyOperation() {
		assertEquals("ur9wzbk", classToTest.apply("kbzw9ru"));
	}

}
