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
<script>
	$(document).ready(function() {
		$("#submitBtn").click(function() {
			if (!$("#userId").val()) {
				alert("아이디를 입력하세요");
				$("#userId").focus();
				return false;
			} else if (!$("#userPw").val()) {
				alert("비밀번호를 입력하세요");
				$("#userPw").focus();
				return false;
			}
		});
		
		$("#returnBtn").click(function() {
			location.href="signin.do";
		});
	});
</script>
</head>
<body>
	<div id="joinDiv" class="container">
		<form class="form-horizontal" action="/join.do" method="post" id="joinForm" name="joinForm">
			<div class="form-group">
				<label class="col-sm-3 control-label">아이디</label>
				<div class="col-sm-9">
					<input class="form-control" type="text" id="userId" name="userId">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">비밀번호</label>
				<div class="col-sm-9">
					<input class="form-control" type="password" id="userPw" name="userPw">
				</div>
			</div>
			<div class="form-group" align="center">
				<button class="btn btn-success" type="submit" id="submitBtn">가입</button>
				<button class="btn btn-info" type="reset">취소</button>
				<button class="btn btn-warning" type="button" id="returnBtn">돌아가기</button>
			</div>
		</form>
	</div>
</body>
</html>