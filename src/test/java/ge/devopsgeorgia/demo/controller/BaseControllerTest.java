package ge.devopsgeorgia.demo.controller;

import java.util.HashMap;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ge.devopsgeorgia.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
	scripts = "classpath:ge/devopsgeorgia/demo/api/sql/beforeTests.sql")
public abstract class BaseControllerTest {
	
	@Autowired
    protected MockMvc mvc;
	
	@Autowired
	protected ObjectMapper mapper;
	
	protected static HashMap<String, Object> testData;
	
	{
		testData = new HashMap<>();
		testData.put("testUser1", new User("base-email", "Base", "user", "q", "12345"));
		testData.put("testUser2", new User("q", "Elle", "Latsoshvili", "q", "12345"));
	}
	
	protected String getTestDataJson(String key) {
		Object obj = testData.get(key);
		String result = "";
		try {
			 result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO log error Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}