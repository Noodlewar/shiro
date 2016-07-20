<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h2>欢迎你！</h2>
	<shiro:hasRole name="admin">
		欢迎admin
	</shiro:hasRole>
	<shiro:hasPermission name="student:create">
		欢迎student:create
	</shiro:hasPermission>
</body>
</html>