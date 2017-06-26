<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/script/jquery.min.js"></script>
<script type="text/javascript" src="/script/common.js"></script>

<!-- 부트스트랩 -->
<script src="/script/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<link rel="stylesheet" href="/css/signin.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#hiddenF").hide();
		
		$("#modBtn").click(function() {
			location.href="/bbs/updateForm.do?bbsNo=${board.bbsNo}";
		});
 		$("#delBtn").click(function() {
			location.href="/bbs/delete.do?bbsNoList=${board.bbsNo}";
		});
		$("#returnBtn").click(function() {
			location.href="/a.do?pageNo=1";
		});
		
		// 댓글 init
		replyInit();
		
		// 댓글 등록
		$("#replyRegBtn").click(function() {
			if(!$("#replyContent").val()) {
				alert("댓글의 내용을 입력해주세요.");
				return false;
			}
			
			$.ajax({
				url: "/bbs/insertReply.json",
				type: "POST",
				data: {
					"bbsNo": $("#bbsNo").val(),
					"replyContent": $("#replyContent").val(),
					"replyParent": 0,
					"replyDepth": 0,
					"regUserId": "${userInfo.userId}"
					},
				dataType: "json"
			})
			.done(function(result) {
				makeReplyList(result);
			});
		});
	});
	
	// 댓글 init
	function replyInit() {
		$.ajax({
			url: "/bbs/replyList.json",
			data: {
				"bbsNo": $("#bbsNo").val()
			},
			dataType: "json"
		})
		.done(makeReplyList);
	}
	
	// 댓글 리스트 ajax
	function makeReplyList(result) {
		$("#replyBody").empty();
		$("#replyContent").val('');
		var html = '';
		if (result.length != 0) {
			for (var i = 0; i < result.length; i++) {
				html += '<tr id = "' + result[i].replyNo + '">';
				html += '<input type="hidden" id="replyNo" name="replyNo">';
				html += '<input type="hidden" id="replyParent" name="replyParent" value="' + result[i].replyParent + '">';
				html += '<input type="hidden" id="replyDepth" name="replyDepth" value="' + result[i].replyDepth + '">';
				html += '<td width="10%;">' + result[i].regUserId + '</td>';
				html += '<td width="58%;" id="replyContent' + result[i].replyNo + '">' + result[i].replyContent + '</td>';
				html += '<td width="15%;">' + formatDate(new Date(result[i].regDate), 1) + '</td>';
				html += '<td width="17%;" align="center">';
				html += '<button type="button" class="btn btn-default" id="replyAddBtn">';
				html += '<small>댓글 추가</small>';
				html += '</button>';
				if ("${userInfo.userId}" == result[i].regUserId) {
					html += ' <button type="button" class="btn btn-default" id="replyModFormBtn" onclick="replyModForm(' + result[i].replyNo + ')">';
					html += '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>';
					html += '</button>';
					html += ' <button type="button" class="btn btn-default" id="replyDelBtn" onclick="deleteReply(' + result[i].replyNo + ')">';
					html += '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>';
					html += '</button>';
				}
				html += '</td>';
				html += '</tr>';
			}
		} else {
			html += '<tr>';
			html += '<td align="center" colspan="4">댓글이 없습니다.</td>';
			html += '</tr>';
		}
		$("#replyBody").html(html);
	}
	
	// 댓글 삭제
	function deleteReply(replyNo) {
		$.ajax({
			url: "/bbs/deleteReply.json",
			data: { 
				"replyNo" : replyNo,
				"bbsNo": $("#bbsNo").val()
			},
			dataType: "json"
		})
		.done(function(result) {
			makeReplyList(result);
		});
	}
	
	// 댓글 수정 폼
	function replyModForm(replyNo) {
		var $parent = $("#" + replyNo);
		var rContent = $("#replyContent" + replyNo).html();
		$parent.empty();
		
		var html = '<td colspan="3">';
		html += '<input class="form-control" type="text" id="replyModContent" name="replyContent" value="' + rContent + '">';
		html += '</td>';
		html += '<td align="center">';
		html += '<button class="btn btn-primary" id="replyRegBtn" type="button" onclick="updateReply(' + replyNo + ')">수정</button>';
		html += '</td>';
		
		$parent.html(html);
	}
	
	// 댓글 수정
	function updateReply(replyNo) {
		$.ajax({
			url: "/bbs/updateReply.json",
			data: {
				"replyNo": replyNo,
				"replyContent": $("#replyModContent").val(),
				"bbsNo": $("#bbsNo").val()
			},
			type: "POST",
			dataType: "json"
		})
		.done(function(result) {
			makeReplyList(result);
		});
	}
</script>
</head>
<body>
	<div class="container">
		<table id="contentTb" class="table table-striped">
			<tr>
				<th width="10%;">제목</th>
				<td width="90%;" colspan="3">${board.bbsTitle}</td>
			</tr>
			<tr>
				<th width="10%;">작성자</th>
				<td width="40%;">${board.userId}</td>
				<th width="10%;">등록일</th>
				<c:choose>
					<c:when test="${board.modifyDate ne null}">
						<td width="40%"><fmt:formatDate value="${board.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
					</c:when>
					<c:otherwise>
						<td width="40%"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td id="contentTd" colspan="4">${board.bbsContent}</td>
			</tr>
			<tr>
				<c:if test="${file ne null}">
					<td colspan="4" align="center">
						<img alt="사진" src="${file.filePath}/${file.realFileName}" width="300px">
					</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<c:if test="${userInfo.userNo eq board.userNo}">
	 					<button class="btn btn-primary" id="modBtn">수정</button>
						<button class="btn btn-danger" id="delBtn">삭제</button>
					</c:if>
					<button class="btn btn-warning" id="returnBtn">목록으로</button>
				</td>
			</tr>
		</table>
		<table id="replyTb" class="table">
			<input type="hidden" id="bbsNo" name="bbsNo" value="${board.bbsNo}">
			<tr>
				<td width="83%;" colspan="3">
					<input class="form-control" type="text" id="replyContent" name="replyContent" placeholder="댓글을 입력하세요">
				</td>
				<td width="17%;" align="center">
					<button class="btn btn-success" id="replyRegBtn" type="button">등록</button>
				</td>
			</tr>
			<tbody id="replyBody"></tbody>
		</table>
	</div>
</body>
</html>