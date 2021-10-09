package com.beta.replyservice.operation;

import org.springframework.stereotype.Component;

@Component
public class ReverseOperation implements StringOperation {

	@Override
	public String apply(String textMessage) {
		return new StringBuilder(textMessage).reverse().toString();
	}

	@Override
	public int getOperationId() {
		return 1;
	}
}
