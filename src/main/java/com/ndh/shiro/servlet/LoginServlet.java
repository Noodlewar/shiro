package com.ndh.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ndh.shiro.utils.CryptographyUtil;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login doGet");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login doPost");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, CryptographyUtil.md5(password, "ndh"));
		try {
			if (subject.isRemembered()) {
				System.out.println("isRememberMe");
			}else {
				token.setRememberMe(true);
			}
			subject.login(token);
			Session session = subject.getSession();
			System.out.println(session.getId());
			System.out.println(session.getHost());
			System.out.println(session.getLastAccessTime());
			resp.sendRedirect("success.jsp");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			req.setAttribute("errorInfo", "用户名或密码错误！");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

}
