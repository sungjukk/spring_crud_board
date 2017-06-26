<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>\
<script type="text/javascript" src="/script/jquery.min.js"></script>

<!-- 부트스트랩 -->
<script src="/script/bootstrap.min.js"></script>
<link href="/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="/css/signin.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#returnBtn").click(function() {
			location.href="/a.do?pageNo=1";
		});
		
		$("#submitBtn").click(function() {
			if (!$("#bbsTitle").val()) {
				alert("제목을 입력하세요");
				$("#bbsTitle").focus();
				return false;
			} else if (!$("#bbsContent").val()) {
				alert("내용을 입력하세요");
				$("#bbsContent").focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div id="modFormDiv" class="container">
		<form action="/bbs/update.do" method="post" id="modForm" name="modForm">
			<input type="hidden" id="bbsNo" name="bbsNo" value="${board.bbsNo}">
			<div class="form-group">
				<label>제목</label>
				<input class="form-control" type="text" id="bbsTitle" name="bbsTitle" value="${board.bbsTitle}">
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" id="bbsContent" name="bbsContent" rows="10">${board.bbsContent}</textarea>
			</div>
			<div class="form-group" align="center">
				<button class="btn btn-success" type="submit" id="modBtn">수정</button>
				<button class="btn btn-warning" type="button" id="returnBtn">돌아가기</button>
			</div>
		</form>
	</div>
</body>
</html>