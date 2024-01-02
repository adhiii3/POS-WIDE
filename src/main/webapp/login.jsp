<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="aresource/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="apple-touch-icon" href="aresource/assets/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="aresource/assets/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="aresource/assets/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="aresource/assets/manifest.json">
<link rel="mask-icon" href="aresource/assets/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="aresource/assets/favicon.ico">
<meta name="theme-color" content="#7952b3">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="aresource/css/signin.css" rel="stylesheet">

</head>
<body class="text-center">

<main class="form-signin">
  <form action="login.action" method="post">
    <img class="mb-4" src="aresource/assets/WIDE-LOGO.PNG" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">Login</h1>
	
	<c:if test="${!empty message}">
	<div class="alert alert-warning d-flex align-items-center" role="alert">
	  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
	  <div>
	    <c:out value="${message}"></c:out>
	  </div>
	</div>
	</c:if>
    <div class="form-floating">
      <input type="text" name="nama" class="form-control" id="floatingInput" placeholder="username">
      <label for="floatingInput">username</label>
    </div>
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
  </form>
</main>
</body>
</html>