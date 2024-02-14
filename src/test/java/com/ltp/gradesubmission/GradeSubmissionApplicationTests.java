package com.ltp.gradesubmission;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/?123");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("grade"));
	}

	@Test
	public void testSuccessfulSubmission() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/handleSubmit")
			.param("name", "Harry")
			.param("subject", "Potions")
			.param("score", "A-");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void testUnSuccessfulSubmission() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/handleSubmit")
			.param("name", " ")
			.param("subject", "Potions")
			.param("score", "Z");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"));
	}

	@Test
	public void testGetGrades() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/grades");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("grades"))
			.andExpect(model().attributeExists("grades"));
	}

}
