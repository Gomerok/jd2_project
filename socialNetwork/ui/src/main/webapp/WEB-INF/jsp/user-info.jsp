<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<div id="container" class="d-flex justify-content-start m-2 p-2">
	<div class="col-1">
	</div>
	<div class="col-2 m-2 p-2">
		<img src="/uiT/images/${user.profileImageName}" align="right" class="rounded-circle" height="200" width="200">
	</div>
	<div class="col-3 " >
        <b>${user.firstName} ${user.lastName}</b><br>
		<b>Gender:</b> ${user.gender}<br>
		<b>Email:</b> ${user.email}<br>
		<div style="overflow-wrap: break-word;">
		<b>About me:</b> ${user.profileText}
		</div>
		<div>
			<form method="post" action="/uiT/home-page/search-user/user-info/${user.id}">
				<c:if test = "${addFriendStatus == 'not_status'}">
				<button id ="button" name="addFriendStatus" value="not_status" type="submit" class="btn btn-success">Add to friends</button>
				</c:if>
				<c:if test = "${addFriendStatus == 'subscriber'}">
				<button id ="button" name="addFriendStatus" value="subscriber" type="submit" class="btn btn-warning">Cancel Subscription</button>
				</c:if>
				<c:if test = "${addFriendStatus == 'friend'}">
				<button id ="button" name="addFriendStatus" value="friend" type="submit" class="btn btn-danger">Friend</button>
				</c:if>
			</form>
		</div>
	</div>
</div>


<div id="container" class="d-flex justify-content-center">
	<div class="col-8 border rounded m-2 p-2" style="background-color: #FBF8F8;">
		<c:forEach items="${userNews}" var="news">
		<div class="border rounded-2" style="overflow-wrap: break-word;">
			<div class="m-1 p-1 border rounded-2" style="background-color: #BCD7D9;">
				${news.header}
			</div>
			<div class="m-1 p-1 border rounded-2" style="background-color: #CCD8D4;">
				${news.description}<br>${news.creationTime}
			</div>
		</div>
		</c:forEach>
	</div>
</div>
<jsp:include page="_footer.jsp"/>

