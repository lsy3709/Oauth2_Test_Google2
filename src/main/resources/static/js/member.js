/**
 * 
 */
 
$(document).ready(function(){
	//alert("aaaaaaaaaaaaaaa");
	//var exp = /^[0-9]{3}[0-9]{4}[0-9]{4}$/
	
		$("#btnIdCheck").click(function() {

		$.ajax({
			type : "post",
			url : "/idCheck",
			data : {
				"username" : $("#username").val(),
			}
		}).done(function(resp) {
			if (resp == "fail") {
				alert("중복된 회원 아이디입니다.");
				location.href = "/join"
			} else if (resp == "success") {
				alert("회원 가입 가능한 아이디입니다.");
			} else {
				alert("아이디를 확인하세요");
			}
		})
	});
	
	$("#btnJoin").click(function(){
		if($("#username").val()==""){
			alert("아이디를 입력하세요");
			$("#username").focus();
			return false;
		}
		if($("#password").val() == "") {
			alert("비밀번호를 입력하세요");
			$("#password").focus();
            return false;
		}
		if($("#pass_check").val() == "") {
			alert("비밀번호를 입력하세요");
			$("#pass_check").focus();
            return false;
		}
		if($("#password").val() != $("#pass_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#pass_check").focus();
            return false;
		}
		
		if($("#email").val() == "") {
			alert("이메일을 입력하세요");
			$("#email").focus();
            return false;
		}
		if($("#role").val() == "") {
			alert("role를 입력하세요");
			$("#role").focus();
            return false;
		}
		
		var data={
			"username":$("#username").val(),
			"email":$("#email").val(),
			"password":$("#password").val(),
			"role":$("#role").val()
		}
		
		$.ajax({
			type:"post",
			url:"/join",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(res){
			if(res=="success"){
				alert("회원가입을 축하합니다");
				location.href="/login";
			}else if(res=="fail"){
				alert("아이디 중복확인하세요");
				$("#id").val("")
			}
		})
		//$("#frm").submit();
		
	});
	
		$("#btnUpdate").click(function() {
			
			if($("#email").val() == "") {
			alert("이메일을 입력하세요");
			$("#email").focus();
            return false;
		}

	var data={
			"email":$("#email").val(),
			"username":$("#username").val()
		}

		$.ajax({
			type : "post",
			url : "/update",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
			
		}).done(function(resp) {
			 if (resp == "success") {
				alert("수정하였습니다.");
				location.href = "/logout"
			} 
		})
	})
	
$("#btnDelete").click(function() {
     if(confirm("정말 삭제할까요?")){
	
	var data={
			"id":$("#id").val()
		}	
	
		$.ajax({
			type : "post",
			url : "/delete",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
			
		}).done(function(resp) {
			if (resp == "success") {
				alert("탈퇴 하셨습니다.");
				location.href = "/logout"
			}
		})
	}
	})
	
	$("#btnLogin").click(function() {

		$.ajax({
			type : "post",
			url : "/login",
			data : {
				"username" : $("#username").val(),
				"password" : $("#password").val()
			}
		}).done(function(resp) {
			if (resp == "no") {
				alert("회원이 아닙니다. 회원가입하세요");
				location.href = "/join"
			} else if (resp == "success") {
				alert("로그인 성공");
				location.href = "/board/list"
			} else {
				alert("비밀번호를 확인하세요");
			}
		})
	})
	
});