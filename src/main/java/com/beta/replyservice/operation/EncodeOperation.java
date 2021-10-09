package com.beta.replyservice.operation;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.beta.replyservice.exception.ReplyException;

@Component
public class EncodeOperation implements StringOperation {

	@Override
	public String apply(String textMessage) {
		
		byte[] bytesOfMessage = null;
		try {
			bytesOfMessage = textMessage.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ReplyException("Message encoding failed.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String md5Hex = DigestUtils.md5DigestAsHex(bytesOfMessage);
		return md5Hex;
	}

	@Override
	public int getOperationId() {
		return 2;
	}

}
