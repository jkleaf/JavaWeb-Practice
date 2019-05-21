<%@page import="java.util.Calendar"%>
<%@page import="dao.UserDao"%>
<%@page import="bean.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>user manager</title>
    <link rel="stylesheet" href="sources/userTable.css">
    <script src="sources/userTable.js"></script>
  </head>
  <body>
  	<%
		String loginName=(String)session.getAttribute("loginName");
		String loginPwd=(String)session.getAttribute("loginPwd");
		if(loginName==null||loginPwd==null){
			response.sendRedirect("mainPage.jsp");
		}
		Calendar now=Calendar.getInstance();
	%>
	<div class="top-nav">
		<h1>
		<%if(now.get(Calendar.AM_PM)==Calendar.AM){%>
			早上好
		<%}else{%>
			下午好
		<%}%>!
		<%=loginName %></h1>
 		<a href="LogoutController" id="logout-btn" class="fr">退出登录</a>
  	</div>
    <div class="tablebox">
      <div class="tablehead">
         <span>用户表</span>
         <div class="search-box">
          <form class="" action="QueryUsersController" method="post" style="display: inline">
	          <input type="text" placeholder="请输入任意关键字" name="keywordtxt">
	          <input type="submit" value="搜索" id="search-btn">
	          <input type="radio" id="userall" name="sitem" value="all" checked="checked">
	        	<label for="userall"></label>
	       		<span>所有</span>
	          <input type="radio" id="userid" name="sitem" value="id">
	        	<label for="userid"></label>
	       		<span>用户Id</span>
	          <input type="radio" id="username" name="sitem" value="name">
	        	<label for="username"></label>
	       		<span>用户名</span>
	          <input type="radio" id="userpwd" name="sitem" value="pwd">
	        	<label for="userpwd"></label>
	        	<span>密码</span>
	          <input type="radio" id="useremail" name="sitem" value="email">
	        	<label for="useremail"></label>
	        	<span>邮箱</span>
	          <input type="radio" id="userphonenum" name="sitem" value="phonenum">
	        	<label for="userphonenum"></label>
	        	<span>电话号码</span>
          	</form>
          </div>
          <a href="#" id="add-btn">增加</a>
      </div>
      <table class="main-table">
      <tr>
      	<th>用户ID</th><th>用户名</th><th>密码</th>
        <th>邮箱</th><th>电话号码</th>
        <th>操作</th><th>操作</th>
      </tr>
      <%
      	List<User> users=(List<User>)request.getAttribute("usersList"); 
		if(users==null){
			users=UserDao.queryAllUsers(null);
		}
      	for(User user:users){
      %>
      <tr>
      		<td><%=user.getId() %></td>	
      		<td><%=user.getName() %></td>	
      		<td><%=user.getPwd() %></td>	
      		<td><%=user.getEmail() %></td>	
      		<td><%=user.getPhonenum() %></td>
      		<td><a href="#" class="a1" id="modify-btn">修改</a></td>
      		<td><a href="DeleteUserController?id=<%=user.getId() %>" class="a2">删除</a></td>
      </tr>
      <%
      	}
      %>
      </table>
    </div>
    <div id="popDiv1" class="popDiv">
      <form class="" action="AddUserController" method="post" onsubmit="return checkInfo()">
        <div class="textbox1">
          <input type="text" placeholder="用户Id" id="id" name="id" value="">
        </div>
        <div class="textbox1">
          <input type="text" placeholder="用户名" id="name" name="name" value="">
        </div>
        <div class="textbox1">
          <input type="text" placeholder="密码" id="pwd" name="pwd" value="">
        </div>
        <div class="textbox1">
          <input type="email" placeholder="邮箱" id="email" name="email" value="">
        </div>
        <div class="textbox1">
          <input type="text" placeholder="电话号码" id="phonenum" name="phonenum" value="">
        </div>
        <input type="submit" class="submit-btn" id="add-submit-btn" value="增加">
      </form>
    </div>
    <div id="popDiv2" class="popDiv">
      <form class="" id="update-form">
        <div class="textbox2">
          <input type="text" name="id" value="" readonly="readonly" style="background-color: #dddddd">
        </div>
        <div class="textbox2">
          <input type="text" name="name" value="" required="required">
        </div>
        <div class="textbox2">
          <input type="text" name="pwd" value="" required="required">
        </div>
        <div class="textbox2">
          <input type="email" name="email" value="" required="required">
        </div>
        <div class="textbox2">
          <input type="text" name="phonenum" value="" required="required">
        </div>
        <input type="submit" class="submit-btn" value="更新">
      </form>
    </div>
    <div id="fade" class="black_overlay" onclick="closeDiv()"></div>
  </body>
</html>
