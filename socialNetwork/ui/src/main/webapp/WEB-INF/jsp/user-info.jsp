<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<h1>User information</h1>

<div class="container-fluid px-4 mt-4">
    <div>
        <div class="container-fluid row">
			<div class="col-md-2 ">
            <img src="/uiT/images/${user.profileImageName}" class="rounded-circle" height="175" width="175">
            </div>
			<div class="col-md-4">
        	<h3>${user.firstName} ${user.lastName}</h3>
			<h4>Gender: ${user.gender}</h4>
			<h4>Email: ${user.email}</h4>
			<h4>About me: ${user.profileText}</h4>
            </div>
		</div>
	 </div>
</div>
<jsp:include page="_footer.jsp"/>