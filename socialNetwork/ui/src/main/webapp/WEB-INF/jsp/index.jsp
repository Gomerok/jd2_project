<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
    <h2 style="font-family:Comic Sans Ms;text-align:center;font-size:30pt;">
    Welcome to the best social network!!!
    </h2>

	<div id="container" class="d-flex justify-content-center">
	<div class="col-8 border rounded m-2 p-2" style="background-color: #FBF8F8;">
		<c:forEach items="${userNewsList}" var="news">
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