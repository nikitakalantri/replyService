package com.beta.replyservice.web;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.replyservice.model.ReplyMessage;
import com.beta.replyservice.service.ReplyService;

import org.springframework.web.bind.annotation.PathVariable;

@Validated
@RestController
public class ReplyController {

	private static final String MSG_PATTERN = "^[0-9]{2}-.+$"; 
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}
	
	@GetMapping("/reply/{message}")
	public ReplyMessage reply(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	/**
	 * V2 Get Method to accept the message in required Format.
	 * 
	 * @param message
	 * @return 200 OK with string after applying rules.
	 */
	@GetMapping("/v2/reply/{message}")
	public ResponseEntity<ReplyMessage> v2Reply(@PathVariable 
			@Pattern(regexp = MSG_PATTERN) String message) {
		return new ResponseEntity<ReplyMessage>(replyService.process(message), HttpStatus.OK);
	}

}