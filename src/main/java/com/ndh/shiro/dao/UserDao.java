package com.ndh.shiro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.ndh.shiro.entity.User;

public class UserDao {
	
	public User getByUserName(Connection conn, String username) throws SQLException{
		User user = null;
		String sql = "select * from t_user where username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
		}
		return user;
	}

	public Set<String> getRoles(Connection conn, String username) throws SQLException {
		Set<String> roles = new HashSet<String>();
		String sql = "select * from t_user u,t_role r where u.role_id = r.id and u.username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			roles.add(rs.getString("rolename"));
		}
		return roles;
	}

	public Set<String> getPermissions(Connection conn, String username) throws SQLException {
		Set<String> permissions = new HashSet<String>();
		String sql = "select * from t_user u,t_role r,t_permission p where u.role_id = r.id and p.role_id = r.id and u.username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			permissions.add(rs.getString("permission_name"));
		}
		return permissions;
	}

}
