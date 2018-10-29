<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>
Employee Management
</title>
      <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
  <meta charset="UTF-8">
  <title>Sign-Up/Login Form</title>
<meta name="_csrf" content="${_csrf.token}"/>
<sec:csrfMetaTags /> 
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
      <link rel="stylesheet" href="styles/index.css">
</head>
<body>
  <div class="form">
      <h1 align="center">Employee Management</h1>
      <ul class="tab-group">
        <li class="tab active"><a href="#login">Log In</a></li>
        <li class="tab"><a href="#signup">Sign Up</a></li>
      </ul>
      <div class="tab-content">
        <div id="login">   
          <h1>Welcome Back!</h1>
<form name='loginForm' action="login" method='POST'>
          <div class="field-wrap">
            <label>
              Email Address
            </label>
            <input type="email" name="email" value="${signinEmail}" required autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
               Password
            </label>
            <input type="password" name="password" required autocomplete="off"/>
           <label><c:out value="${loginFail}"/></label>
           <font color="red">
           <label><c:out value="${signinFail}"/></label>
           <label> <c:out value="${signUpFail}"/></label>
       <label> <c:out value="${userFail}"/></label>
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
        <c:if test="${param.error != null}">
            <p class="errorCustomLogin">Login failed. Please try your
                username/password again.</p>
        </c:if>
           </font>
           <font color="green">
           <label><c:out value="${signUpSuccess}"/></label>
           </font>
          </div>
          <button type="submit" class="button button-block"/>Login</button>
          </form>
        </div>
        <div id="signup">   
          <form role="form" action="signup" method="post">
         
            <div class="field-wrap">
              <label>
                Email Address<span class="req">*</span>
              </label>
              <input type="email" name="email" required autocomplete="off" />
            </div>
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password" name="password" id="password" required autocomplete="off"/>
          </div> 
          <div class="field-wrap">
            <label>
              Confirm Password<span class="req">*</span>
            </label>
            <input type="password" name= "confirmPassword" id="confirmPassword"  required autocomplete="off"/>
          </div>
            <div class="field-wrap">
              <label class="active">
                 Role<span class="req">*</span>
              </label>
              <select name="role" required>
             <option value="Employee" selected>Employee</option>
               <option value="Admin">Admin</option>
              </select>
            </div>
          <button id="register" class="button button-block"/>Get Started</button>
          </form>
        </div>
      </div>
</div> 
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script  src="script/index.js"></script>
</body>
</html>
