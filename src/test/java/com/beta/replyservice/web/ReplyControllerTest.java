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

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void testValidMessage() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "11-kbzw9ru")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("kbzw9ru"));
	}

	@Test
	public void testEmptyRule() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "-kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testEmptyString() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "11-")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithNoHyphen() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "11kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithOneRule() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "1-kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithExcessRules() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "121-kbzw9ru")).andExpect(status().isBadRequest());
	}

	@Test
	public void testMessageWithInvalidRule() throws Exception {
		this.mockMvc.perform(get("/reply/{message}", "13-kbzw9ru")).andExpect(status().isBadRequest());
	}
}
