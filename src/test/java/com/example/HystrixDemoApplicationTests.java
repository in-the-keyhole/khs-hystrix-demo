package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.util.Assert.isTrue;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.example.config.AppConfig;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class HystrixDemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	final static Logger logger = LoggerFactory.getLogger(HystrixDemoApplicationTests.class);

	@Before
	public void setup() throws Exception {

		if (mockMvc == null) {
			mockMvc = webAppContextSetup(webApplicationContext).build();
		}
	}

	@Test
	public void normalLoadTest() throws Exception {	
		for(int i = 0; i< 150; i++){
			MockHttpServletRequestBuilder rb = get("/payloads");
			MvcResult result = mockMvc.perform(rb).andExpect(content().contentType(contentType))
					.andReturn();
			String content = result.getResponse().getContentAsString();
			JSONArray o = (JSONArray) JSONValue.parse(content);
			isTrue(o.size() > 0);
		}	
	}

	@Test
	public void normalLoadFailureTest() throws Exception {
		for(int i = 0; i< 150; i++){
			MockHttpServletRequestBuilder rb = get("/payloads/fail");
			MvcResult result = mockMvc.perform(rb).andExpect(content().contentType(contentType))
					.andReturn();
			String content = result.getResponse().getContentAsString();
		}
	}

	@Test
	public void hystrixLoadTest() throws Exception {
		for(int i = 0; i< 150; i++){
			MockHttpServletRequestBuilder rb = get("/hystrix");
			MvcResult result = mockMvc.perform(rb).andExpect(content().contentType(contentType))
					.andReturn();
			String content = result.getResponse().getContentAsString();
			JSONArray o = (JSONArray) JSONValue.parse(content);
			isTrue(o.size() > 0);
		}
	}

	@Test
	public void hystrixLoadFailureTest() throws Exception {
		for(int i = 0; i< 500; i++){
			MockHttpServletRequestBuilder rb = get("/payloads/fail/hystrix");
			MvcResult result = mockMvc.perform(rb).andReturn();
			String content = result.getResponse().getContentAsString();
			JSONArray o = (JSONArray) JSONValue.parse(content);
		}
	}

}
