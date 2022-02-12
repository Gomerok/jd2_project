<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>

<div id="container" class="d-flex justify-content-center">
	<div class="col-7 border rounded m-2 p-2" style="background-color: #FBF8F8;">
		<h2 align="center">Send Message</h2>
		<c:forEach items="${userMessages}" var="message">
		<c:choose>
		<c:when test="${message.recipientId eq friendId}">
		<div class="d-flex justify-content-start">
			<div class="col-4 m-2 p-2 border rounded-2" style="background-color: #FFEE6F; overflow-wrap: break-word;">
				${message.value}<br>${message.timestamp}
			</div>
		</div>
		</c:when>
		<c:otherwise>
		<div class="d-flex justify-content-end">
			<div class="col-4 m-2 p-2 border rounded" style="background-color: #53E9F5; overflow-wrap: break-word;">
				${message.value}<br>${message.timestamp}
			</div>
		</div>
		</c:otherwise>
		</c:choose>
		</c:forEach>

		<form method="post">
			<div class="m-1 p-1">
				<textarea  class="form-control" name="value" placeholder="Write a message here" id="floatingTextarea" maxlength="254"></textarea>
			</div>
			<div class="d-flex justify-content-center">
				<div class="px-2">
					<button id ="button" type="submit" class="btn btn-primary" >Send</button>
					<button id ="button" type="submit" class="btn btn-primary">Update</button>
				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="_footer.jsp"/>