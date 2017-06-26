<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/script/jquery.min.js"></script>

<!-- 부트스트랩 -->
<script src="/script/bootstrap.min.js"></script>
<link href="/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="/css/signin.css">
<script type="text/javascript">
	$(document).ready(function() {
		console.log("${msg}");
		isLogin();
		
		$("#joinBtn").click(function() {
			location.href='signup.do';
		});
		
		$("#submitBtn").click(function() {
			if(!$("#userId").val()) {
				alert("아이디를 입력하세요");
				$("#userId").focus();
				return false;
			} else if (!$("#userPw").val()) {
				alert("비밀번호를 입력하세요");
				$("#userPw").focus();
				return false;
			}
		});
	});
	
	function isLogin() {
		if ("${msg}") {
			alert("${msg}");
		};
	};
</script>
</head>
<body>
	<div id="mainDiv" class="container">
		<form id="joinForm" method="post" action="/login.do" name="joinForm">
			<table class="table">
				<tr>
					<td>아이디</td>
					<td><input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력하세요"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button class="btn btn-primary" type="button" id="joinBtn">회원가입</button>
						<button class="btn btn-success" type="submit" id="submitBtn">로그인</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>