<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp" %>

<form action="uploadFormAction" method="post" enctype="multipart/form-data">
	<input type="file"  id="uploads" name="uploads" multiple>
	<input type="submit" id="submitBtn" value="전송">
	
<script>
    var fileCheck = document.getElementById("uploads").value;
    if(!fileCheck){
        alert("파일을 첨부해 주세요");
        return false;
    }
</script>
	
<!-- 	<input type="button" value="upload" id="uploadBtn"> -->
</form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#submitBtn").on("click", function(e) {
			var fileCheck = document.getElementById("uploads").value;
		    if(!fileCheck){
		        alert("파일을 첨부해 주세요");
		        return false;
		    }
		});
	});
</script>

<!-- <script type="text/javascript">
	$(document).ready(function() {
		$("#uploadBtn").on("click", function(e) {
			var formData = new FormData();
			var inputFile = $("input[name='uploads']");
			var files = inputFile[0].files;
			console.log(files);
		});
	});
</script> -->

<%@ include file="../includes/footer.jsp" %>    