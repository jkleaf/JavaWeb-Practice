<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
</head>
<body>
	Welcome!
	<%
		String name=(String)session.getAttribute("name");
		if(name!=null){
			out.println(name);
	
	%>
	<%
		}else{
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>