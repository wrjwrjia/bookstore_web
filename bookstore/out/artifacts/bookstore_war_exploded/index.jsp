<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome</title>
    <script src="js/jQuery.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>

  <body>
  <div class='content'>
    <div class="loginheader">
      <div class="logintitle">Login</div>
    </div>
    <div class="login">
      <div class='loginform'>
        <label>Username:</label>
        <input type="text" id="username" maxlength="60">
      </div>
      <div class='loginform'>
        <label>Password:</label>
        <input type="password" id="password" maxlength="60">
      </div>

      <div class='loginsubmit'>
        <button class="login_btn" onclick="logon()">Submit</button>
      </div>
    </div>
  </div>
  </body>

  <script>
      function logon(){
          var username = $('#username').val();
          var password = $('#password').val();
          $.ajax({
              type:"post",//request
              url:"controller/loginController.jsp",
              data:{"username":username,"password":password},//pass varianble to controller
              error:function(){
                  alert("Failed submission!");
              },
              success:function(data){ //success
                  if(data == -1){
                      alert('Username or password cannot be null!');
                  }else if(data == -2){
                      alert('User does not exist!');
                  }else if(data == -3){
                      alert('Invalid username or password');
                  }else{
                      //jump to another page
                      window.location.href = "base.jsp";
                  }
              }
          });
      }
  </script>
</html>