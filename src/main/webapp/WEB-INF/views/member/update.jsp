<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../includes/header.jsp" %>

<div class="container">
	<h2>회원수정</h2>
	<form action="#" method="post">
		<div class="row">
				<input type="hidden" 	id="id" value=${sUser.id} name="id">
			<div class="col">
				<label for="username">아이디:</label> <input type="text" class="form-control"
					id="username" value=${sUser.username} name="username" readonly>
			</div>
			
		</div>
		<div class="form-group">
			<label for="email">email:</label> <input type="text" class="form-control"
				id="email"  value=${sUser.email} name="email">
		</div>
		<button type="button" class="btn btn-primary btn-sm" id="btnUpdate">수정하기</button>
			<button type="button" class="btn btn-primary badge-danger btn-sm" id="btnDelete">탈퇴하기</button>
	</form>

</div>

<script type="text/javascript" src="../js/member.js"></script>

<%@ include file="../includes/footer.jsp" %>









