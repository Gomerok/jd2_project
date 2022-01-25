<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <c:choose>
            <c:when test="${sessionScope.authorizedUser.sessionToken != null}">
            <li class="nav-item">
            	<a class="nav-link" href="/uiT/home-page">Home</a>
            </li>

            <li class="nav-item">
				<a class="nav-link" href="/uiT/home-page/edit-profile">Profile</a>
            </li>

			<li class="nav-item">
				<a class="nav-link" href="/uiT/search-user">Search user</a>
            </li>

			<li class="nav-item">
				<a class="nav-link" href="/uiT/friends-list">Friends list</a>
            </li>

			<li class="nav-item">
				<a class="nav-link" href="/uiT/subscriptions">Subscriptions</a>
            </li>

			<li class="nav-item">
				<a class="nav-link" href="/uiT/black-list">Black list</a>
            </li>

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