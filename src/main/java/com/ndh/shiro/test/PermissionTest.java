package com.ndh.shiro.test;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.ndh.shiro.utils.ShiroUtils;

public class PermissionTest {
	
	/**
	 * permission操作方法类似role
	 * checkPermitted也类似role
	 */
	@Test
	public void testIsPermitted(){
		Subject currentUser = ShiroUtils.login("classpath:shiro_permission.ini", "Noodlewar", "123456");
		currentUser.isPermitted("user:select");
		System.out.println(currentUser.isPermitted("user:select") ? "有权限":"没权限");		
	}

}
