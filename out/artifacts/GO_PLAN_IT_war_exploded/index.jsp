<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 5-4-2018
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rest.js"></script>
  </head>
  <body>
  <div>
    <div>
      <label>username</label>
      <input id="username" type="text">
    </div>
    <div>
      <label>password</label>
      <input id="password" type="text">
    </div>
    <div id="error" style="color: red; display: none">not valid credentials</div>
    <input type="button" value="login" onclick="login()">
  </div>
<script>
  function login() {
      var data = JSON.parse('{}');
      data["username"] = $("#username").val();
      data["password"] = $("#password").val();

      var output = postCall(data, 'rest/login','text');

      if (output != "not valid credentials"){
          sessionStorage.setItem("token", output);
          window.location.href = "home.jsp";
      }else{
          $("#error").css("display", "block");
      }
  }


   </script>
  </body>
</html>
