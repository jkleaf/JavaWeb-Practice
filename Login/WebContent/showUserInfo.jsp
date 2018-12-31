<%@page import="org.jdbc.*,java.sql.*"%>
<%@page import="org.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册信息</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String userID=request.getParameter("userID");
	String username=request.getParameter("username");
	String createday=request.getParameter("createday");
	String major=request.getParameter("major");
	String psw=request.getParameter("psw");
	String gender=request.getParameter("gender");
	String birthday=request.getParameter("birthday");
	String tel=request.getParameter("tel");
	String email=request.getParameter("email");
%>
<h1 align="center">注册成功!</h1>
<br><br><br>
<p align="center">
	您的信息如下，请核对:<br>
	用户ID:<%=userID %><br>
	用户名:<%=username %><br>
	入学时间:<%=createday %><br>
	专业:<%=major %><br>
	联系方式:<%=tel %><br>
	邮箱:<%=email %><br>
	性别:<%=gender %><br>
	出生年月:<%=birthday %><br>
</p>
<%
	Register register=new Register();
	Users users=new Users();
	register.setR_id(userID);
	register.setR_password(psw);
	register.setR_email(email);
	users.setU_id(userID);
	users.setU_name(username);
	users.setU_major(major);
// 	users.setU_createDay(createday);
// 	users.setU_age(birthday);
	users.setU_sex(gender);
	users.setU_tel(tel);
	users.setU_email(email);
	new Insert2Tabel().insertRegister(register);
	new Insert2Tabel().insertUsers(users);
%>
</body>
</html>