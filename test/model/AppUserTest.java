package model;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import models.AppUser;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;

public class AppUserTest extends WithApplication {
	
	@Before
	public void setUp(){
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
	}
	@Test
	public void createAppUser(){
		AppUser user1 = new AppUser();
		user1.userFirstName="uri";
		user1.userSurname="naor";
		user1.email="uri@test.com";
		user1.hashedPassword="test";
		AppUser.createAppUser(user1);
		assertEquals("Uri", user1.userFirstName);
		AppUser user2 = new AppUser();
		user2.email="uri@test.com";
		user2.hashedPassword="hola";
		Long id2= AppUser.createAppUser(user2);
		Long result =-1L;
		assertEquals(result, id2);
		
		AppUser user3 =	AppUser.finder.byId(user1.userId);
		assertEquals("uri@test.com", user3.email);
		
	}
	
	@Test
	public void authentication(){
		AppUser user = new AppUser();
		user.email="uri@test.com";
		user.hashedPassword="test";
		AppUser.createAppUser(user);
		AppUser authUser = AppUser.authenticate("uri@test.com", "test");
		assertNotNull(authUser);
		AppUser authUser1 = AppUser.authenticate("uri@test.com", "badPassword");
		assertNull(authUser1);		
		AppUser authUser2 = AppUser.authenticate("uri@t4st.com", "test");
		assertNull(authUser2);
	}
	
	@Test
	public void isEmailEmptyTest(){
		AppUser user1 = new AppUser();
		user1.userFirstName="uri";
		user1.userSurname="naor";
		user1.email="uri@test.com";
		user1.hashedPassword="test";
		AppUser.createAppUser(user1);
		assertTrue(AppUser.isEmailFree("rihana@google.com"));
		assertFalse(AppUser.isEmailFree("uri@test.com"));
	}

}