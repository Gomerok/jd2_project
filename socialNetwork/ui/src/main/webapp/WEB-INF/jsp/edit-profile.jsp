<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/jsp/_header.jsp"%>

<div class="container-fluid px-4 mt-4">
    <div>
		<form:form method="post" action="/uiT/home-page/edit-profile.do" class="registration" modelAttribute="editUser" enctype="multipart/form-data">
        <div class="container-fluid row">
			<div class="col-md-4 ">
            <label for="profileImage"><b>Profile image</b></label>
            <br>
            <img src="/uiT/images/${editUser.profileImageName}" height="275" width="250">
        	</div>
			<br>
			<div class="col-md-4">
        	<label for="newProfileImage"><b>New profile image</b></label>
        	<br>
            <img id="uploadPreview" height="275" width="250" />
			<br>
        	<input id="uploadImage" type="file" name="profileImage" onchange="PreviewImage();" />
            </div>
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
            <div class="container-fluid row">
                        <!-- Form Group (username)-->
                        <div class="error">
                            <form:errors path="firstName"/>
                        </div>
                       	<div>
                      	    <label for="first_name"><b>First Name</b></label>
                            <form:input path="firstName" name="firstName" type="text" placeholder="${currentUser.firstName}" class="form-control" id="firstName" value="${currentUser.firstName}"/>
                      	</div>

                      	<div class="error">
                            <form:errors path="lastName"/>
                        </div>
                      	<div>
                       	    <label for="last_name"><b>Last Name</b></label>
                            <form:input path="lastName" name="lastName" type="text" placeholder="${currentUser.lastName}" class="form-control" id="lastName"/>
                       	</div>

                       	<div class="error">
                            <form:errors path="login"/>
                        </div>
                       	<div>
                      	    <label for="login"><b>User name</b></label>
                            <form:input path="login" name="login" type="text" placeholder="${currentUser.login}" class="form-control" id="login"/>
                      	</div>

                       	<div class="error">
                            <form:errors path="email"/>
                        </div>
                       	<div>
                      	    <label for="email" class="form-label"><b>Email address</b></label>
                            <form:input path="email" name="email" type="text" placeholder="${currentUser.email}" class="form-control" id="email"/>
                        </div>

                        <div class="error">
                            <form:errors path="gender"/>
                        </div>
                       	<div>
                            <form:radiobutton path="gender" id="genderFemale" name="gender" value="female"/>
                            <label for="gender"><b>Female</b></label>

                            <form:radiobutton path="gender" id="genderMale" name="gender" value="male"/>
                            <label for="gender"><b>Male</b></label>

                            <form:radiobutton path="gender" id="genderOther" name="gender" value="other"/>
                            <label for="gender"><b>Other</b></label>
                        </div>
                      	<br>
                        <div class="error">
                            <form:errors path="password"/>
                        </div>
                       	<div>
                            <label for="password"><b>New password</b></label>
                            <form:input path="password" name="password" type="password" class="form-control" id="password"/>
                       	</div>

					<div class="error">
						<form:errors path="profileText"/>
					</div>
					<div class="center">
						<label for="profileText" class="form-label" placeholder="${currentUser.profileText}"><b>Profile Text</b></label>
					<br>
						<form:textarea path="profileText" rows="10" cols="70" name="profileText"/>
					</div>
					<hr>
					<div>
					<form:button id ="button" type="submit" class="btn btn-primary">Save changes</form:button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<%@include file="/WEB-INF/jsp/_footer.jsp"%>