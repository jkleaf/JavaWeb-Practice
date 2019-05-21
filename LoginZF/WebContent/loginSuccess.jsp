<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginSuccess</title>
</head>
<body>
	<%
		int returnCode=(int)request.getAttribute("returnCode");
		if(returnCode==1){
	%>
		<script>alert("登录成功!")</script>
	<%		
		}
		response.setHeader("refresh", "0.5;url=login.jsp");
	%>
</body>
</html>