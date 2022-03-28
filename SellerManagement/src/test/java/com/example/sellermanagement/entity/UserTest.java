package com.example.sellermanagement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {


	@Test
	void UsertestCase() {
		User user = new User();
		user.setId(0);
		user.setName("test");
		user.setUserName("test123@username");
		user.setPassword("test123");
		user.setDob("01-01-2021");
		user.setRegistration("reg");
		user.setRoles("user");
		//user.setStatus("PENDING");
		assertEquals(0, user.getId(),"user id test case:");
		assertEquals("test",user.getName(),"user name test case:" );
		assertEquals("test123@username", user.getUserName(),"user username testcase");
		assertEquals("test123", user.getPassword(),"user password test case");
		assertEquals("01-01-2021", user.getDob(),"user dob test case:");	
		assertEquals("reg",user.getRegistration(),"user registration test case" );
		assertEquals("user", user.getRoles(),"user role test case");
		assertEquals("PENDING", user.getStatus(),"user status testcase");
		assertEquals(true, user.isActive(),"user active testcasse");
	}
	
	
	
}
