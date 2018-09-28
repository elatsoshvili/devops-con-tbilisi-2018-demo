package ge.devopsgeorgia.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import ge.devopsgeorgia.demo.Application;
import ge.devopsgeorgia.demo.model.User;

@SpringBootTest(classes = Application.class)
public class UserControllerTest extends BaseControllerTest {

	 
    @Test
    public void registerInvalidUser() throws Exception {
    	
         // Register with invalid input
    	 User incomplete = new User();
    	 incomplete.setFirstname("Elle");
    	 mvc.perform(post("/users")
    			 .content(mapper.writeValueAsString(incomplete))
    	         .contentType(MediaType.APPLICATION_JSON))
    	         .andExpect(status().is4xxClientError())
    	         .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    
    @Test
    public void registerUser() throws Exception {
	// Register and activate user
	 mvc.perform(post("/users")
			 .content(getTestDataJson("testUser1"))
	         .contentType(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk());
    }
}