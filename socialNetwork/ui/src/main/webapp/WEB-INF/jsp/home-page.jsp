<%@include file="/WEB-INF/jsp/_header.jsp"%>
<%session.removeAttribute("currentUser");
session.removeAttribute("str");%>

<div id="container" class="d-flex justify-content-start m-2 p-2">
	<div class="col-1">
	</div>
	<c:if test="${sessionScope.authorizedUser.userRole=='ROLE_USER'}">
	<div class="col-2 m-2 p-2">
		<img src="/uiT/images/${currentUser.profileImageName}" align="right" class="rounded-circle" height="200" width="200">
	</div>
	<div class="col-3 " >
        <b>${currentUser.firstName} ${currentUser.lastName}</b><br>
        <b>Login:</b> ${currentUser.login}<br>
		<b>Gender:</b> ${currentUser.gender}<br>
		<b>Email:</b> ${currentUser.email}<br>
		<b>Number of friends:</b> ${friendsCount}<br>
		<b>Number of subscribers:</b> ${subscribersCount}<br>
		<div style="overflow-wrap: break-word;">
		<b>About me:</b> ${currentUser.profileText}
		</div>
	</div>
	</c:if>

	<c:if test="${sessionScope.authorizedUser.userRole=='ROLE_ADMIN'}">
	<div class="col-2 m-2 p-2">
    </div>
    <div class="col-3 " >
	<b>Hello Admin</b><br>
	<b>Number of bad people:</b> ${subscribersCount}<br>
	</div>
	</c:if>

</div>


<div id="container" class="d-flex justify-content-center">
	<div class="col-8 border rounded m-2 p-2" style="background-color: #F4F5E8;">
		<form method="post" action="/uiT/home-page">
		<c:forEach items="${userNews}" var="news">
		<div class="border rounded-2 m-1 p-1" style="overflow-wrap: break-word; background-color: #EEF4D6;">
			<div class="m-1 p-1 border rounded-2" style="background-color: #BCD7D9;">
				${news.header}
			</div>
			<div class="m-1 p-1 border rounded-2" style="background-color: #D9E8EB;">
				${news.description}<br>${news.creationTime}
			</div>
			<div class="d-flex justify-content-end">
                <button name="deleteNews" value="${news.id}" id ="deleteButton" type="submit" class="btn btn-outline-danger btn-sm" >Delete news</button>
            </div>
		</div>
		</c:forEach>


			<div>
				<label><b>New news</b></label>
				<input class="form-control" placeholder="News name" name="newsHeader" type="text" id="newsHeader" maxlength="49"/>

				<textarea  class="form-control" name="newsDescription" placeholder="Write a news description here" id="floatingTextarea2" style="height: 100px" maxlength="499"></textarea>
			</div>
			<div class="d-flex justify-content-center m-1 p-1">
				<button id ="addButton" type="submit" class="btn btn-primary" >Add news</button>
			</div>
		</form>
	</div>
</div>
<%@include file="/WEB-INF/jsp/_footer.jsp"%>
