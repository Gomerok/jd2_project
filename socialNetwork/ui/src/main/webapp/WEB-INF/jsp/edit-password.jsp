<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/jsp/_header.jsp"%>
<style>
.error {
  color: #ba3939;
  background: #ffe0e0;
}
</style>
<div id="container" class="d-flex justify-content-center m-2 p-2" >
    <div class="col-4 border rounded m-2 p-2" style="background-color: #F9F5F5;">
		<form method="post" action="/uiT/home-page/edit-password.do">
            <div class="container-fluid row">
				<div class="error">
					${invalidPassword}
				</div>
				<div class="m-2 pu-2">
					<label for="old_password"><b>Old password</b></label>
					<input name="oldPassword" type="password" class="form-control" id="oldPassword" maxlength="24"/>
				</div>
				<div class="error">
					${errorPassword}
				</div>
				<div class="m-2 pd-2">
					<label for="new_password"><b>New password</b></label>
                    <input maxlength="10" name="newPassword" type="password" class="form-control" id="newPassword" maxlength="24"/>
               	</div>
				<hr>
				<div align="center">
					<button id ="button" type="submit" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</form>
	</div>
</div>

<%@include file="/WEB-INF/jsp/_footer.jsp"%>