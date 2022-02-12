<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Social Network</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<c:choose>
				<c:when test="${sessionScope.authorizedUser != null}">
				<c:if test="${sessionScope.authorizedUser.userRole=='ROLE_USER'}">

				<li class="nav-item">
					<a class="nav-link" href="/uiT/home-page">Home</a>
				</li>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Profile
					</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="/uiT/home-page/edit-profile">Edit profile</a>
						<li><a class="dropdown-item" href="/uiT/home-page/edit-password">Edit password</a></li>
					</ul>
				</li>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Friends
					</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="/uiT/home-page/search-user/1">Search people</a>
						<li><a class="dropdown-item" href="/uiT/home-page/friends-list">My friends</a></li>
						<li><a class="dropdown-item" href="/uiT/home-page/subscriptions">Subscriptions</a></li>
					</ul>
				</li>

				</c:if>
				<c:if test="${sessionScope.authorizedUser.userRole=='ROLE_ADMIN'}">

				<li class="nav-item">
              	    <a class="nav-link" href="/uiT/home-page">Home</a>
                </li>
                <li class="nav-item">
					<a class="nav-link" href="/uiT/home-page/edit-password">Edit password</a>
				</li>

                <li class="nav-item dropdown">
                	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                		People
                	</a>
                	<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                		<li><a class="dropdown-item" href="/uiT/home-page/search-user/1">Search people</a>
                		<li><a class="dropdown-item" href="/uiT/home-page/friends-list">Bad peoples</a></li>
                	</ul>
                </li>

				</c:if>

				<li class="nav-item">
					<a class="nav-link" href="/uiT/home-page/logout">Logout</a>
				</li>

				</c:when>
				<c:otherwise>

				<li class="nav-item">
					<a class="nav-link" href="/uiT">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/uiT/registration">Registration</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/uiT/login">Login</a>
				</li>

				</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</div>
    </nav>
	</header>
