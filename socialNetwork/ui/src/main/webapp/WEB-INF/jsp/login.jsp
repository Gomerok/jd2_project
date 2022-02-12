<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <style><%@include file="/WEB-INF/jsp/css/login_styles.css"%></style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<form:form method="post" action="/uiT/login.do" class="login" modelAttribute="loginUser">
  <div class="container">
    <h1>Login</h1>
    <hr>

    <div class="error">
        <form:errors path="login"/>
    </div>
	<div>
	<label for="login"><b>User name</b></label>
        <form:input path="login" name="login" type="text" class="form-control" id="login" maxlength="14"/>
	</div>

    <div class="error">
        <form:errors path="password"/>
    </div>
	<div>
    <label for="password"><b>Password</b></label>
    <form:input path="password" name="password" type="password" class="form-control" id="password" maxlength="24"/>
	</div>

    <hr>
    <form:button id ="button" type="submit" class="signinbtn">Login</form:button>

	<p align="center">
    <a href="/uiT/registration">Sign up</a>
	</p>
  </div>
</form:form>

<%@include file="/WEB-INF/jsp/_footer.jsp"%>