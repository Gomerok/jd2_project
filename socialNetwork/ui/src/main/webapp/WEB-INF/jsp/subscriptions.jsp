<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<h2>My subscriptions</h2>

<table class="table table-success table-striped">
<tbody>
<form class="d-flex" action="/uiT/home-page/subscriptions" method="post">

<c:forEach items="${subscriptionsList}" var="result">

    <tr>
        <td><c:if test = "${result.firstName == null || result.firstName==''}">Non</c:if>${result.firstName}</td>
        <td><c:if test = "${result.lastName == null || result.lastName==''}">Non</c:if>${result.lastName}</td>
        <td>${result.gender}</td>
        <td>
            <button name="add" class="btn btn-success " type="submit" value="${result.id}">Add to friends</button>
            <button name="reject" class="btn btn-danger " type="submit" value="${result.id}">Reject</button>
        </td>
    </tr>
</c:forEach>
</form>
</tbody>
</table>


<jsp:include page="_footer.jsp"/>