package com.beta.replyservice.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Reply Controller Integration test
 */
@SpringBootTest
public class ReplyControllerTest {
	
	private static final String REPLY_MESSAGE = "/v2/reply/{message}";

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void testValidMessage() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "11-kbzw9ru")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("kbzw9ru"));
	}

	@Test
	public void testEmptyRule() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "-kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testEmptyString() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "11-")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithNoHyphen() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "11kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithOneRule() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "1-kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithExcessRules() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "121-kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithInvalidRule() throws Exception {
		this.mockMvc.perform(get(REPLY_MESSAGE, "13-kbzw9ru")).andExpect(status().isBadRequest());
	}
}
