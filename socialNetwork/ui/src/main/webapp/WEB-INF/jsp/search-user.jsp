<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<h1>Search people</h1>
<div class="col-md-3 ">
    <form class="d-flex" action="/uiT/home-page/search-user/1">
        <input name="param" class="form-control me-2" type="search" placeholder="Search" aria-label="Search" value="${sessionScope.str}">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
</div>
<br>
<table class="table">
<tbody>

<c:forEach items="${results}" var="result">
    <tr>
        <td>${result.firstName}</td>
        <td>${result.lastName}</td>
        <td>${result.gender}</td>
        <td>
		<form class="d-flex" action="/uiT/home-page/search-user/${pageId}">
		<input type="hidden" name="userId" value="${result.id}">
		<button type="submit" class="btn btn-info btn-sm">Info</button>
		</td>

		</form>
    </tr>
</c:forEach>

</tbody>
</table>

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