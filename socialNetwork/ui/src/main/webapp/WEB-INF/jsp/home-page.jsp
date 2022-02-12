<%@include file="/WEB-INF/jsp/_header.jsp"%>
<%session.removeAttribute("currentUser");
session.removeAttribute("str");%>

<div id="container" class="d-flex justify-content-start m-2 p-2">
	<div class="col-1">
	</div>
	<div class="col-2 m-2 p-2">
		<img src="/uiT/images/${currentUser.profileImageName}" align="right" class="rounded-circle" height="200" width="200">
	</div>
	<div class="col-3 " >
        <b>${currentUser.firstName} ${currentUser.lastName}</b><br>
		<b>Gender:</b> ${currentUser.gender}<br>
		<b>Email:</b> ${currentUser.email}<br>
		<div style="overflow-wrap: break-word;">
		<b>About me:</b> ${currentUser.profileText}
		</div>
	</div>
</div>


<div id="container" class="d-flex justify-content-center">
	<div class="col-8 border rounded m-2 p-2" style="background-color: #FBF8F8;">
		<form method="post" action="/uiT/home-page">
		<c:forEach items="${userNews}" var="news">
		<div class="border rounded-2" style="overflow-wrap: break-word;">
			<div class="m-1 p-1 border rounded-2" style="background-color: #BCD7D9;">
				${news.header}
			</div>
			<div class="m-1 p-1 border rounded-2" style="background-color: #CCD8D4;">
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
