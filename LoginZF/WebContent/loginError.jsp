<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Error</title>
</head>
<body>
	<%
		int returnCode=(int)request.getAttribute("returnCode");
		switch(returnCode){
    		case -1:
    %>
    		<script type="text/javascript">alert("服务器异常!");</script>
    <%
    		break;
    		case 2:
    %>
    		<script type="text/javascript">alert("验证码错误!");</script>			
    <%
    		break;
    		case 3:
    %>
    		<script type="text/javascript">alert("密码错误!");</script>			
    <%
    		break;
    		case 4:	
    %>
 		   	<script type="text/javascript">alert("用户名不存在或未按照要求参加教学活动!");</script>			
    <%
    	}
    	response.setHeader("refresh", "0.1;url=login.jsp");
    %>
</body>
</html>