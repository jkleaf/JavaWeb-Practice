<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<!-- <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script> -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<script type="text/javascript">
function checkEmpty(){
		var userID=window.register.userID.value;
		var username=window.register.username.value;
		var psw=window.register.psw.value;
		var repeatpsw=window.register.repeatpsw.value;
		var major=window.register.major.value;
		var birthday=window.register.birthday.value;
		var tel=window.register.tel.value;
		var email=window.register.email.value;
	    if(userID=="填写用户ID..."){
	    	alert("用户ID不能为空!");
	    	document.register.userID.focus();
	    	return false;
	    }
	    if(username=="填写用户名..."){
	    	alert("用户名不能为空!");
	    	document.register.username.focus(); 
	        return false;
	    }
	    if(major=="填写专业..."){
	    	alert("专业不能为空!");
	    	document.register.major.focus(); 
	        return false;
	    }
	    if(psw==""){
	    	alert("用户密码不能为空!");
	    	document.register.psw.focus(); 
	        return false;
	    }
	    if(repeatpsw==""){
	    	alert("请确认并再次输入密码!")
	    	document.register.repeatpsw.focus(); 
	        return false;
	    }
	    if(birthday==""){
	    	alert("出生日期不能为空!");
	    	document.register.birthday.focus();
	    	return false;
	    }
	    if(tel=="填写联系方式(手机号码)..."){
	    	alert("联系方式不能为空!");
	    	document.register.tel.focus();
	    	return false;
	    }
	    if(email=="填写邮箱..."){
	    	alert("邮箱不能为空!");
	    	document.register.email.focus();
	    	return false;
	    }
	    //location.href="showUserInfo.jsp";
	}
	
	function checkPswMatch(){
		var ps1=window.register.psw.value;
		var ps2=window.register.repeatpsw.value;
		if(ps2!=""&&ps1!=ps2){
			alert("两次输入密码不一致!");
			document.register.repeatpsw.focus();
			document.register.repeatpsw.value='';
			return false;
		}
	}
	
	window.onload=function(){
		function $(id) {
			return document.getElementById(id);
		}
		$("userID").onfocus=function fn(){
			if($("userID").value=="填写用户ID..."){
				$("userID").value="";
			}
		}
		$("userID").onblur=function fn(){
			if($("userID").value==""){
				$("userID").value="填写用户ID...";
			}
		}
		$("username").onfocus=function fn(){
			if($("username").value=="填写用户名..."){
				$("username").value="";
			}
		}
		$("username").onblur=function fn(){
			var username=$("username").value;
			if(username==""){
				$("username").value="填写用户名...";
			}else if(username.length<3){
				alert("用户名至少为3位!");
	 		    document.register.username.focus();
	 		    $("username").value="";
			}
		}
		$("major").onfocus=function fn(){
			if($("major").value=="填写专业..."){
				$("major").value="";
			}
		}
		$("major").onblur=function fn(){
			if($("major").value==""){
				$("major").value="填写专业...";
			}
		}
		$("psw").onblur=function fn(){
			var psw=$("psw").value;
			if(psw!=""&&psw.length<6){
				alert("用户密码至少为6位!");
		        document.register.psw.focus(); 
		        $("psw").value="";
			}
		}
		$("tel").onfocus=function fn(){
			if($("tel").value=="填写联系方式(手机号码)..."){
				$("tel").value="";
			}
		}
		$("tel").onblur=function fn(){
			if($("tel").value==""){
				$("tel").value="填写联系方式(手机号码)...";
			}
		}
		$("email").onfocus=function fn(){
			if($("email").value=="填写邮箱..."){
				$("email").value="";
			}
		}
		$("email").onblur=function fn(){
			if($("email").value==""){
				$("email").value="填写邮箱...";
			}
		}
	}
</script>
</head>
<body>
<div class="container">
<h1>LOGIN FORM</h1>
	<div class="signup">
     	<form action="showUserInfo.jsp" name="register" method="post" onsubmit="return checkEmpty();">
			<input type="text" class="registertextfield" name="userID" value="填写用户ID..." id="userID"/> 
	      	<input type="text" class="registertextfield" name="username" value="填写用户名..." id="username"/>
			<input type="text" class="registertextfield" name="major" value="填写专业..." id="major"/>
			<input type="password" class="registertextfield" name="psw" id="psw" style="font-weight:bold;" placeholder="填写密码..."/>
			<input type="password" class="registertextfield" name="repeatpsw" style="font-weight:bold;" 
				placeholder="请再次确认密码..." onchange="return checkPswMatch();"/>
			<div style="text-align:center;color:white; font-size:20px;">选择性别:&nbsp;&nbsp;&nbsp;&nbsp;
	      	<input type="radio" name="gender" value="男" checked/>男&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="gender" value="女"/>女<br><br></div><div style="text-align:center;color:white; font-size:20px;">出生日期:
	      	<input type="date" class="registertextfield" style="width:170px" name="birthday"/></div>
	      	<input type="text" class="registertextfield" name="tel" value="填写联系方式(手机号码)..." id="tel"/>
	      	<input type="email" class="registertextfield" name="email" value="填写邮箱..." id="email"/>
			<div align="center">
				<input type="submit" value="注 册" />
				<input type="reset" value="重 置" />
			</div>
	 	</form>
	</div>
</div>
<div class="footer">
     <!-- <p></p> -->
</div>
</body>
</html>