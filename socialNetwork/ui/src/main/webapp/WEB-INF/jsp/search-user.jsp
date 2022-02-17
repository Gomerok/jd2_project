<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<h2>Search people</h2>
<div class="col-md-3 ">
    <form class="d-flex" action="/uiT/home-page/search-user/1">
        <input name="param" class="form-control me-2" type="search" placeholder="Search" aria-label="Search" value="${sessionScope.str}">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
</div>
<br>
<form class="d-flex" action="/uiT/home-page/search-user/${pageId}">
<table class="table table-success table-striped">
<tbody>

<c:forEach items="${results}" var="result">
    <tr>
        <td><c:if test = "${result.firstName == null || result.firstName==''}">Non</c:if>${result.firstName}</td>
        <td><c:if test = "${result.lastName == null || result.lastName==''}">Non</c:if>${result.lastName}</td>
        <td>${result.login}</td>
        <td>${result.gender}</td>
        <td>
		<a class="btn btn-primary" href="/uiT/home-page/search-user/user-info/${result.id}" role="button">Info</a>
		</td>
		<c:if test = "${sessionScope.authorizedUser.userRole=='ROLE_ADMIN'}">
		<td>
            <a class="btn btn-primary" href="/uiT/home-page/send-messages/${result.id}" role="button">Send message</a>
        </td>
        <td>
        <c:if test = "${result.status == 'ACTIVE'}">
            <button id ="blockedButton" name="adminAction" value="${result.status} ${result.id}" type="submit" class="btn btn-warning">Blocked</button>
        </c:if>
        <c:if test = "${result.status == 'BLOCKED'}">
            <button id ="activeButton" name="adminAction" value="${result.status} ${result.id}" type="submit" class="btn btn-success">Active</button>
        </c:if>
        </td>
		<td>
		    <button id ="deleteButton" name="adminAction" value="DELETE ${result.id}" type="submit" class="btn btn-danger">Delete</button>
        </td>
        </c:if>
    </tr>
</c:forEach>

</tbody>
</table>
</form>
<nav aria-label="Page navigation">
  <ul class="pagination">
	<c:if test = "${(pageId-1) != 0}">
    <li class="page-item"><a class="page-link" href="/uiT/home-page/search-user/${pageId-1}">Previous</a></li>
	<li class="page-item"><a class="page-link" href="/uiT/home-page/search-user/${pageId-1}">${pageId-1}</a></li>
	</c:if>

    <li class="page-item"><a class="page-link" href="/uiT/home-page/search-user/${pageId}">${pageId}</a></li>

	<c:if test = "${pageId != pageCount}">
    <li class="page-item"><a class="page-link" href="/uiT/home-page/search-user/${pageId+1}">${pageId+1}</a></li>
    <li class="page-item"><a class="page-link" href="/uiT/home-page/search-user/${pageId+1}">Next</a></li>
	</c:if>
  </ul>
</nav>

<jsp:include page="_footer.jsp"/>