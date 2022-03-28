package com.example.sellermanagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sellermanagement.entity.GetUsers;
import com.example.sellermanagement.entity.Product;
import com.example.sellermanagement.entity.ProductEntry;
import com.example.sellermanagement.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	@Autowired
	private AppController  appController;
	Product productdone= new Product();

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
	}
	@Test
	void testsignup()throws Exception {
		Map<String, String> elements = new HashMap();
		elements.put("userName", "dummy@example.com");
		elements.put("password", "test123");
		elements.put("roles", "USER");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(elements);
		System.out.println(json);
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/signup").accept(MediaType.APPLICATION_JSON)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "USER")
	void testlogin()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/login").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test123@exam.com",password = "pass2",roles = "USER")
	void testdeleteaccount()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/deleteaccount").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "USER")
	void testgetcurrentuser()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/getcurrentuser").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testgetallusers()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/getallusers").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "USER")
	void testgetproducts()throws Exception {

		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/getproducts").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void getallproducts()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/getallproducts").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "USER")
	void testaddproduct()throws Exception {
		Map<String, String> elements = new HashMap();
		elements.put("productName", "example");
		elements.put("price", "123");
		elements.put("productImage", "image");
		elements.put("category", "jean");
		elements.put("quantity", "1");
		elements.put("description", "hello");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(elements);
		System.out.println(json);
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/addproduct").accept(MediaType.APPLICATION_JSON)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "USER")
	void testeditproduct()throws Exception {
		Map<String, String> elements = new HashMap();
		elements.put("productName", "example");
		elements.put("price", "123");
		elements.put("productImage", "image");
		elements.put("category", "jean");
		elements.put("quantity", "1");
		elements.put("description", "hello");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(elements);
		System.out.println(json);
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/editproduct").accept(MediaType.APPLICATION_JSON)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testaddadmin()throws Exception {
		Map<String, String> elements = new HashMap();
		elements.put("userName", "test@example.com");
		elements.put("password", "test123");
		elements.put("roles", "USER");
		elements.put("dob", "01-01-2021");
		elements.put("registration", "reg");
		elements.put("Name", "hello");
		elements.put("status", "PENDING");
		elements.put("active", "true");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(elements);
		System.out.println(json);
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/addadmin").accept(MediaType.APPLICATION_JSON)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());

	}
	@Test
	void testgetUsername() {
		User user = new User();
		user.setUserName("test123@username");
		assertEquals("test123@username", user.getUserName(),"user username testcase");
	}
	@Test
	void testgetStatus() {
		User user = new User();
		user.setStatus("PENDING");
		assertEquals("PENDING", user.getStatus(),"user username testcase");
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testsetuserstatus()throws Exception {
		Map<String, String> elements = new HashMap(); elements.put("userName", "dummy@example.com");
		elements.put("roles", "USER");
		elements.put("userName", "dummy@example.com");
		elements.put("password", "test123");
		elements.put("roles", "USER");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(elements);
		System.out.println(json);
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/setuserstatus").accept(MediaType.APPLICATION_JSON)
				.param("id", "1")
				.param("status", "true")
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());

	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testgettotalitemsno()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/totalitemsno").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testgettotalsellersno()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/totalsellersno").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testpendingsellers()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/pendingsellers").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testapprovedsellers()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/approvedsellers").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	@WithMockUser(username = "test@exam.com",password = "pass2",roles = "ADMIN")
	void testdeniedsellers()throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/deniedsellers").accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}

