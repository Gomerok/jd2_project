<%@include file="/WEB-INF/jsp/_header.jsp"%>
<%session.removeAttribute("currentUser");%>
<h2>Home Page</h2>
<c:if test="${sessionScope.authorizedUser.userRole=='ROLE_ADMIN'}">
    <p>Hello Admin</p>
</c:if>

<%@include file="/WEB-INF/jsp/_footer.jsp"%>



