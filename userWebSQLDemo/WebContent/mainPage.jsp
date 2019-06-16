<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Main</title>
    <link rel="stylesheet" href="sources/main.css">
    <script type="text/javascript" src="sources/main.js"></script>
  </head>
  <body>
    <div class="mainPanel">
      <div id="pop1" class="popDiv1 loginDiv">
        <h1>Login Here</h1>
<!--         <form class="" id="login-submit" onsubmit="return checkLogin()"> -->
		<form action="LoginController" id="login-submit" method="post">
         <div class="textbox">
            <input type="text" placeholder="用户名" name="name" id="loginName" value="" required="required">
          </div>
          <div class="textbox">
            <input type="password" placeholder="密码" name="pwd" id="loginPwd" value="" required="required">
          </div>
          <input type="submit" class="signsubmit signsubmit-margin1" name="" value="登录">
          <div class="trans2Regit-Login">
            <a href="javascript:void(0)" id="trans2Regit">注册</a>
          </div>
        </form>
      </div>
      <div id="pop2" class="popDiv2 regDiv">
        <h1>Sign Up</h1>
        <form action="RegisterController" id="sign-submit" method="post"> 
<!--         onsubmit="return checkRegister()"> -->
          <div class="textbox">
            <input type="text" placeholder="用户Id" id="id" name="id" value="" required="required">
          </div>
          <div class="textbox">
            <input type="text" placeholder="用户名" id="name" name="name" value="" required="required">
          </div>
          <div class="textbox">
            <input type="text" placeholder="密码" id="pwd" name="pwd" value="" required="required">
          </div>
          <div class="textbox">
            <input type="email" placeholder="邮箱" id="email" name="email" value="" required="required">
          </div>
          <div class="textbox">
            <input type="text" placeholder="电话号码" id="phonenum" name="phonenum" value="" required="required">
          </div>
          <input type="submit" class="signsubmit signsubmit-margin2" name="" value="注册">
          <div class="trans2Regit-Login">
            <a href="javascript:void(0)" id="trans2Login">登录</a>
          </div>
        </form>
      </div>
  </div>
  </body>
</html>