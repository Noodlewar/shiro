package com.ndh.shiro.realm;

import java.sql.Connection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ndh.shiro.dao.UserDao;
import com.ndh.shiro.entity.User;
import com.ndh.shiro.utils.DBUtils;

public class ShiroRealm extends AuthorizingRealm{
	
	private UserDao userDao = new UserDao();
	private DBUtils dbUtils = new DBUtils();
	

	/**
	 * 
	 */
	@SuppressWarnings("static-access")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Connection conn = null;
		try {
			conn = dbUtils.getConnection();
			authorizationInfo.setRoles(userDao.getRoles(conn, username));
			authorizationInfo.setStringPermissions(userDao.getPermissions(conn, username));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@SuppressWarnings("static-access")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Connection conn = null;
		try {
			conn = dbUtils.getConnection();
			User user = userDao.getByUserName(conn, username);
			if (user != null) {
				AuthenticationInfo aInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "xx");
				return aInfo;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtils.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

}
