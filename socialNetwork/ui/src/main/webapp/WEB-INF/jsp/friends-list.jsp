<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<h2>My friends</h2>

<table class="table">
<tbody>

<c:forEach items="${friendsList}" var="result">

    <tr>
        <td><c:if test = "${result.firstName == null || result.firstName==''}">Non</c:if>${result.firstName}</td>
        <td><c:if test = "${result.lastName == null || result.lastName==''}">Non</c:if>${result.lastName}</td>
        <td>${result.login}</td>
        <td>${result.gender}</td>
        <td>
        <a class="btn btn-primary" href="/uiT/home-page/search-user/user-info/${result.id}" role="button">Info</a>
        </td>
        <td>
        <a class="btn btn-primary" href="/uiT/home-page/send-messages/${result.id}" role="button">Send message</a>
        </td>
    </tr>
</c:forEach>
</tbody>
</table>


<jsp:include page="_footer.jsp"/>