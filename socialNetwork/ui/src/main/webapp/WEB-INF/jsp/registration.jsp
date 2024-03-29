<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <style><%@include file="/WEB-INF/jsp/css/registration_styles.css"%></style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<form:form method="post" action="/uiT/registration.do" class="registration" modelAttribute="newUser" enctype="multipart/form-data">
  <div class="container">
    <h1>Create a new account</h1>
    <hr>
	<div class="error">
        <form:errors path="firstName"/>
    </div>
	<div>
	    <label for="first_name"><b>First Name</b></label>
        <form:input path="firstName" name="firstName" type="text" class="form-control" id="firstName" maxlength="19"/>
	</div>

	<div class="error">
        <form:errors path="lastName"/>
    </div>
	<div>
	    <label for="last_name"><b>Last Name</b></label>
        <form:input path="lastName" name="lastName" type="text" class="form-control" id="lastName" maxlength="19"/>
	</div>

	<div class="error">
        <form:errors path="login"/>
    </div>
	<div>
	    <label for="login"><b>User name</b></label>
        <form:input path="login" name="login" type="text" class="form-control" id="login" maxlength="14"/>
	</div>

	<div class="error">
        <form:errors path="email"/>
    </div>
	<div>
	    <label for="email" class="form-label"><b>Email address</b></label>
        <form:input path="email" name="email" type="text" class="form-control" id="email" maxlength="24"/>
    </div>

    <div class="error">
        <form:errors path="gender"/>
    </div>
	<div>
        <form:radiobutton path="gender" id="gender" name="gender" value="female"/>
        <label for="gender"><b>Female</b></label>

        <form:radiobutton path="gender" id="gender" name="gender" value="male"/>
        <label for="gender"><b>Male</b></label>

        <form:radiobutton path="gender" id="gender" name="gender" value="other"/>
        <label for="gender"><b>Other</b></label>
    </div>

	<br>
    <div class="error">
        <form:errors path="password"/>
    </div>
	<div>
        <label for="password"><b>Password</b></label>
        <form:input path="password" name="password" type="password" class="form-control" id="password" maxlength="20"/>
	</div>
    <div class="error">
        <form:errors path="profileImageName"/>
    </div>
    <div class="center">
        <label for="profileImage"><b>Profile image</b></label>
	    <br>
        <img id="uploadPreview" class="image" />
        <br>
	    <input id="uploadImage" type="file" name="profileImage" onchange="PreviewImage();"/>
        <script type="text/javascript">
            function PreviewImage() {
                var oFReader = new FileReader();
                oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

                oFReader.onload = function (oFREvent) {
                    document.getElementById("uploadPreview").src = oFREvent.target.result;
                };
            };
        </script>
    </div>

    <br>
    <div class="error">
        <form:errors path="profileText"/>
    </div>
    <div class="center">
        <label for="profileText" class="form-label"><b>Profile Text</b></label>
    <br>
        <form:textarea path="profileText" rows="5" cols="70" name="profileText" maxlength="254"/>
	</div>
    <hr>
    <form:button id ="button" type="submit" class="signinbtn">Sign Up</form:button>
    <p>If you have an account, you can log in. <a href="/uiT/login">Login</a></p>
  </div>
</form:form>

<%@include file="/WEB-INF/jsp/_footer.jsp"%>