<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <title>Login ZF System</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="source/img/user.ico" type="image/x-icon"/>    
  </head>
  <body>
  	<div id="bg"></div>
    <div class="login-box">
      <h1>Login</h1>
        <form action="LoginController" method="POST">
        <div class="textbox">
          <i class="fa fa-user fa-lg" aria-hidden="true"></i>
          <input type="text" placeholder="Username" name="name" value="">
        </div>
        <div class="textbox">
          <i class="fa fa-lock fa-lg" aria-hidden="true"></i>
          <input type="password" placeholder="Password" name="pwd" value="">
        </div>
      		<input class="btn" type="submit" name="" value="Sign in">
        </form>
    </div>
  </body>
</html>
