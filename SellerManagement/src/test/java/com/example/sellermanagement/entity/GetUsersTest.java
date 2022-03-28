package com.example.sellermanagement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetUsersTest {

	@Test
	void GetUsersTestCase() {
		GetUsers getUsers= new GetUsers();
		getUsers.setId(0);
		getUsers.setUserName("test123@username");
		getUsers.setDob("01-01-2021");
		getUsers.setRegistration("reg");
		getUsers.setName("test");
		
		assertEquals(0, getUsers.getId(),"getUsers id test case:");
		assertEquals("test",getUsers.getName(),"getUsers name test case:" );
		assertEquals("test123@username", getUsers.getUserName(),"getUsers username testcase");
		assertEquals("01-01-2021", getUsers.getDob(),"getUsers dob test case:");	
		assertEquals("reg",getUsers.getRegistration(),"getUsers registration test case" );
		assertEquals("PENDING", getUsers.getStatus(),"getUsers status testcase");
	}

}
