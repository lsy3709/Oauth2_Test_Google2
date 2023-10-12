<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron" style="margin-bottom: 0">
		<h1>JPA Multi Image Board 이상용 </h1>
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-3">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="/index">HOME</a>

		<!-- Links -->
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="/board/list">Board</a>
			</li>
		<!-- 	<li class="nav-item"><a class="nav-link" href="/files/uploadForm"> FILE(글쓰기시 파일 업로드 가능)이 버튼 참고용.</a></li> -->
		</ul>
		<ul class="navbar-nav">
		<c:choose>
			<c:when test="${empty sessionScope.user }">
				<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/join">회원가입</a></li>
				<li class="nav-item"><a class="nav-link" href="/oauth2/authorization/google">Google Login</a></li>
			</c:when>
			<c:otherwise>
			<li class="nav-item"><a class="nav-link" href="/update">회원수정</a></li>
				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃(${user.name})</a></li>
				<li class="nav-item"><img src="${user.picture}" height="50"></li>
			</c:otherwise>
		</c:choose>
		</ul>
	</nav>