<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="../includes/header.jsp" %>
    
    <div class="container">
  <h2>수정하기</h2>
  <form action="/board/update" method="post">
  	<div class="form-group">
      <label for="title">번호:</label>
      <input type="text" class="form-control" id="id" name="id" value="${board.id}" readonly>
    </div>
    <div class="form-group">
      <label for="title">제목:</label>
      <input type="text" class="form-control" id="title" name="title" value="${board.title}">
    </div>
    <div class="form-group">
      <label for="writer">작성자:</label>
      <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}"readonly>
    </div>
    
  
      <c:forEach items="${filelist}" var="fileInfo">
	      	<li style="list-style:nome">
	      		<c:choose>
	      			<c:when test="${fileInfo.filetype=='image'}">
	      				<%-- <img src="/resources/upload/${fileInfo.savefolder}/${fileInfo.savefile}" height="50"> --%>
	      				<%-- <img src="${filePath}/${fileInfo.savefolder}/${fileInfo.savefile}" height="50"> --%>
	      				<img src="${fileInfo.savefileurl}" height="100">
	      			</c:when>
	      			<c:otherwise>
	      				<img src="/resources/upload/file.png" height="50">
	      			</c:otherwise>
	      		</c:choose>
	      		${fileInfo.originfile}
	      		   <a class="fileDel" href="#" fno="${fileInfo.fno}" 
	      	    sfile="${fileInfo.savefilename}"
	      		sfolder="${fileInfo.savefolder}"
	      		ofile="${fileInfo.originfile}" 
	      		sfileurl="${fileInfo.savefileurl}">[삭제]</a>
	      		  </li><br>
      	</c:forEach> 
      
    <div class="form-group">
      <label for="content">내용:</label>
      <textarea class="form-control" rows="5" id="content" name="content">${board.content}</textarea>
    </div>
    <button type="submit" class="btn btn-primary btn-sm">수정하기</button>
          <button type="button" class="btn btn-primary btn-sm" id="btnList">목록보기</button>
  </form>
</div>

<script type="text/javascript">
$("#insertImagExtra").on("click", function(e) {
	var fileCheck = document.getElementById("uploads").value;
    if(!fileCheck){
        alert("파일을 첨부해 주세요");
        return false;
    }
});

$(".fileDel").click(function(){
	if(confirm("삭제 할까요?")){
		var fno=$(this).attr('fno');
		alert("fno: "+fno+" sfile (키 또는 s3 저장된 파일 이름) : " + $(this).attr('sfile'));
		location.href="/board/delete2/"+fno;
	}
	
});

$("#btnList").click(function(){
	if(confirm("정말 이동할까요?")){
		location.href="/board/list?pageNum=${pageNum}&field=${field}&word=${word}"
	}
});

</script>   
    
    <%@ include file="../includes/footer.jsp" %>