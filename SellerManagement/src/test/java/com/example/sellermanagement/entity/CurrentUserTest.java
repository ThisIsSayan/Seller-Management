package com.example.sellermanagement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CurrentUserTestCase {

	@Test
	void CurrentUserTest() {
		CurrentUser currentUser = new CurrentUser();
		currentUser.setName("test");
		currentUser.setUserName("test123@username");
		currentUser.setRegistration("reg");
		currentUser.setDob("01-01-2021");
		currentUser.setStatus("PENDING");
		
		assertEquals("test",currentUser.getName(),"currentuser name test case:" );
		assertEquals("test123@username", currentUser.getUserName(),"currentuser username testcase");
		assertEquals("01-01-2021", currentUser.getDob(),"currentuser dob test case:");	
		assertEquals("reg",currentUser.getRegistration(),"currentuser registration test case" );
		assertEquals("PENDING", currentUser.getStatus(),"currrentuser status testcase");
		
	}

}
