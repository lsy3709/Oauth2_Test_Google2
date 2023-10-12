<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>
<div class="container">
	<h2>Board rowNum(${count})</h2>
	<h2>Board pageSize(${pageSize})</h2>
	<h2>Board startPage(${startPage})</h2>
	<h2>Board endPage-1(${endPage})</h2>
	<h2>Board totPage(${totPage})</h2>
	<h2>Board currPage(${cp})</h2>
	<div class="form-group text-right">
	<c:if test="${not empty sessionScope.sUser }">
		<button type="button" class="btn btn-secondary btn-sm" id="btnWrite">글쓰기</button>
	</c:if>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일시</th>
				<th>조회수</th>
			</tr>
		</thead>

	</table>
	
	<h1>검색 내용이 없습니다.</h1>

<script type="text/javascript">
 $("#btnWrite").click(function(){
	 location.href="/board/register"
 });
</script>

<%@ include file="../includes/footer.jsp" %>








