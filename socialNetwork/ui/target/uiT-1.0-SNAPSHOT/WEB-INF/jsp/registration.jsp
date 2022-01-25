<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style><%@include file="/WEB-INF/jsp/css/registration_styles.css"%></style>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<form:form method="post" action="/uiT/registration.do" class="registration" modelAttribute="addNewUser" enctype="multipart/form-data">
  <div class="container">
    <h1>Create a new account</h1>
    <hr>
	<div class="error">
        <form:errors path="firstName"/>
    </div>
	<div>
	    <label for="first_name"><b>First Name</b></label>
        <form:input path="firstName" name="firstName" type="text" class="form-control" id="firstName"/>
	</div>

	<div class="error">
        <form:errors path="lastName"/>
    </div>
	<div>
	    <label for="last_name"><b>Last Name</b></label>
        <form:input path="lastName" name="lastName" type="text" class="form-control" id="lastName"/>
	</div>

	<div class="error">
        <form:errors path="login"/>
    </div>
	<div>
	    <label for="login"><b>User name</b></label>
        <form:input path="login" name="login" type="text" class="form-control" id="login"/>
	</div>

	<div class="error">
        <form:errors path="email"/>
    </div>
	<div>
	    <label for="email" class="form-label"><b>Email address</b></label>
        <form:input path="email" name="email" type="text" class="form-control" id="email"/>
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
        <form:input path="password" name="password" type="password" class="form-control" id="password"/>
	</div>

    <div class="center">
        <label for="profileImage"><b>Profile image</b></label>
	    <br>
        <img id="uploadPreview" class="image" />
        <br>
	    <input id="uploadImage" type="file" name="profileImage" onchange="PreviewImage();" />
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
        <form:textarea path="profileText" rows="10" cols="70" name="profileText"/>
	</div>
    <hr>
    <form:button id ="button" type="submit" class="signinbtn">Sign Up</form:button>
    <p>If you have an account, you can log in. <a href="/uiT/login">Login</a></p>
  </div>
</form:form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>