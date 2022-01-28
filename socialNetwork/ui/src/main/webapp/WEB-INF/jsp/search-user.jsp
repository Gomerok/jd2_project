<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<h1>Search people</h1>
<div class="col-md-3 ">
    <form class="d-flex" action="/uiT/home-page/search-user/${pageid}">
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
    </tr>
</c:forEach>
</tbody>
</table>

 <a href="/uiT/home-page/search-user/1">1</a>
   <a href="/uiT/home-page/search-user/2">2</a>
   <a href="/uiT/home-page/search-user/3">3</a>

<jsp:include page="_footer.jsp"/>