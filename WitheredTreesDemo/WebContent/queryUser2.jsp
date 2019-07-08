<%@page import="dao.UserDao"%>
<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QueryUser2</title>
</head>
<body>
	<%
		User user=UserDao.getUser("666");
		if(user!=null){
			out.print(user.getAccount()+"<br>");
			out.print(user.getUsername()+"<br>");
			out.print(user.getPassword()+"<br>");
			out.print(user.getGender()+"<br>");
			out.print(user.getEmail()+"<br>");
			out.print(user.getTelephone()+"<br>");
		}else{
			out.print("null");
		}
	%>
</body>
</html>