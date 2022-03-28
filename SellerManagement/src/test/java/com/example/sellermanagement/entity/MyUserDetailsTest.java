package com.example.sellermanagement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter.Active;

class MyUserDetailsTest {

	@Test
	void Userdetailstest() {
		User user = new User();
		user.setUserName("test@username");
		user.setPassword("test123");
		user.isActive();
		MyUserDetails details = new MyUserDetails(user); 
		assertEquals("test@username", details.getUsername(),"user details username test case:");
		assertEquals("test123", details.getPassword(),"user details pasword test case:");
		assertEquals(true, details.isAccountNonExpired(),"user details account not expired testcase:");
		assertEquals(true, details.isAccountNonLocked(),"user details account not locked testcase:");
		assertEquals(true, details.isCredentialsNonExpired(),"user details credentials not expired testcase:");
		assertEquals(true, details.isEnabled(),"user details isenabled test case:");
	}	
}
