package com.ndh.shiro.test;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.ndh.shiro.utils.ShiroUtils;

public class RoleTest {
	
	@Test
	public void testHasRole(){
		Subject currentUser = ShiroUtils.login("classpath:shiro_role.ini", "Noodlewar", "123456");
		System.out.println(currentUser.hasRole("role3") ? "有这个角色":"没这个角色");
	}
	
	@Test
	public void testHasRoles(){
		Subject currentUser = ShiroUtils.login("classpath:shiro_role.ini", "jack", "123");
		boolean hasRoles[] = currentUser.hasRoles(Arrays.asList("role1","role2"));
		for (int i = 0; i < hasRoles.length; i++) {		
			System.out.println(hasRoles[i] ? "有这个角色":"没这个角色");
		}
	}
	
	@Test
	public void testHasAllRoles(){
		Subject currentUser = ShiroUtils.login("classpath:shiro_role.ini", "jack", "123");
		System.out.println(currentUser.hasAllRoles(Arrays.asList("role1","role2")) ? "有这个角色":"没这个角色");
	}
	
	@Test
	public void testCheckRoles(){
		Subject currentUser = ShiroUtils.login("classpath:shiro_role.ini", "Noodlewar", "123456");
		currentUser.checkRole("role1");
		currentUser.checkRoles("role1", "role2");
		currentUser.checkRoles(Arrays.asList("role1","role2"));
		//System.out.println(currentUser.hasAllRoles(Arrays.asList("role1","role2")) ? "有这个角色":"没这个角色");
	}

}
