package com.beta.replyservice.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EncodeOperationTest {
	
	EncodeOperation classToTest;
	
	@BeforeEach
	public void init() {
		classToTest = new EncodeOperation();
	}
	
	@Test
	public void testGetOperationId() {
		assertEquals(2, classToTest.getOperationId());
	}
	
	@Test
	public void testApplyOperation() {
		assertEquals("0fafeaae780954464c1b29f765861fad", classToTest.apply("kbzw9ru"));
	}



}
