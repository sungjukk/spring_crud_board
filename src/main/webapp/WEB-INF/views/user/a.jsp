<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		$("#modBtn").click(function() {
			location.href='signup_mod.do';
		});
		$("#logoutBtn").click(function() {
			location.href='logout.do';
		});
		$("#writeBtn").click(function() {
			location.href='bbs/writeForm.do';
		});
		$("#homeBtn").click(function() {
			location.href='a.do?pageNo=1';
		});
		
		$("#delBtn").click(function() {
			if($("input:checked").length == 0) {
				alert("체크된 게시물이 없습니다.");
				return false;
			}
		});

		// new 라벨 붙이기
		for(var i = 1; i <= "${fn:length(boardList)}"; i++) {
			addNewLabel($("#regDate" +i).val(), i);
		}
		
		// 체크박스 전체선택
		$("#checkAll").click(function() {
			$("input[id^=chk]").prop("checked", ($("#checkAll").prop("checked") == true) ? true : false);
		});
		
		// 검색버튼 누르면 ajax
		$("#searchBtn").click(function() {
			if(!$("#searchWord").val()) {
				alert("검색할 단어를 입력해주세요.");
				return false;
			}
			
			$.ajax({
				url: "/bbs/searchWord.json",
				data: {"searchWord": $("#searchWord").val()},
				dataType: "json"
			})
			.done(function(boardList) {
				searchResult(boardList);
			});
		});
		
		// pagenation 액티브 활성화
		pageSelect();
	});
	
	// 오늘 날짜 글 제목 옆에 new 붙이기
	function addNewLabel(regDate, i) {
		var fDate = formatDate(new Date(), 2);
		if (regDate == fDate) {
			$("#title" + i).after(' <span class="label newLabel">New</span>');
		}
	};
	
	// 날짜 형식 변환
	function formatDate(cDate, type) {
		var fDate = '';
		if (type == 1) {
			fDate = cDate.getFullYear() + '-'
					+ leadingZeros(cDate.getMonth() + 1, 2) + '-'
					+ leadingZeros(cDate.getDate(), 2) + ' '
					+ leadingZeros(cDate.getHours(), 2) + ":"
					+ leadingZeros(cDate.getMinutes(), 2) + ':'
					+ leadingZeros(cDate.getSeconds(), 2);
		} else if (type == 2) {
			fDate = cDate.getFullYear()
					+ leadingZeros(cDate.getMonth() + 1, 2)
					+ leadingZeros(cDate.getDate(), 2);
		}
		return fDate;
	}
	
	// 날짜 형식을 위해 한자리수에 0 붙이기
	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();
		if (n.length < digits) {
		  for (i = 0; i < digits - n.length; i++)
		    zero += '0';
		}
		return zero + n;
	}
	
	// 겸색 결과
	function searchResult(boardList) {
		$("tbody").empty();
		$("#paging").empty();
		var html = '';
		for (var i = 0; i < boardList.length; i++) {
			html += "<input type='hidden' id='regDate"+i+"' value='"+formatDate(new Date(boardList[i].regDate), 2)+"'>";
			html += "<tr>";
			if ("${userInfo.userNo}" == boardList[i].userNo) {
				html += "<td><input id='chk"+boardList[i].bbsNo+"' type='checkbox' name='bbsNoList' value='"+boardList[i].bbsNo+"'></td>";
			} else {
				html += "<td></td>";
			}
			html += "<td>"+boardList[i].bbsNo+"</span></td>";
			html += "<td><a id='title"+i+"' href='bbs/detail.do?bbsNo="+boardList[i].bbsNo+"'>"+boardList[i].bbsTitle+"</a></td>";
			html += "<td>"+boardList[i].userId+"</td>";
			if (boardList[i].modifyDate != null) {
				html += "<td>"+formatDate(new Date(boardList[i].modifyDate), 1)+"</td>";
			} else {
				html += "<td>"+formatDate(new Date(boardList[i].regDate), 1)+"</td>";
			}
			html += "</tr>";
			
		}
		$("tbody").html(html);
		
 		for(var i = 0; i < boardList.length; i++) {
			addNewLabel($("#regDate" + i).val(), i);
		}
	}
	
	// pagenation 액티브 활성화
	function pageSelect() {
		var selectNo = $("input[name=pageNo]").val();
		var pageCnt = $("input[name=pageNoCnt]").val();
		 for (var i = 1; i <= pageCnt; i++) {
			if (i == selectNo) {
				$("#selectPage" + i).addClass("active");
				break;
			}
		}
	}

</script>
</head>
<body>	
	<div class="container">
		<div class="row" id="btnDiv">
			<button class="btn btn-info" type="button" id="modBtn">회원정보수정</button>
			<button class="btn btn-danger" type="button" id="logoutBtn">로그아웃</button>
			<button class="btn btn-primary" type="button" id="homeBtn">홈</button>
		</div>
		<form action="/bbs/delete.do" method="post" id="aForm">
			<div class="row" id="boardListDiv">
				<table class="table table-hover">
					<colgroup>
						<col width="5%">
						<col width="5%">
						<col width="55%">
						<col width="15%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th><input id="checkAll" type="checkbox"></th>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList}" var="board" varStatus="count">
							<input type="hidden" id="regDate${count.count}" value="<fmt:formatDate value="${board.regDate}" pattern="yyyyMMdd"></fmt:formatDate>">
							<tr>
								<c:choose>
									<c:when test="${userInfo.userNo eq board.userNo}">
										<td><input id="chk${board.bbsNo}" type="checkbox" name="bbsNoList" value="${board.bbsNo}"></td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								<td>${board.bbsNo}</span></td>
								<td><a id="title${count.count}" href="bbs/detail.do?bbsNo=${board.bbsNo}">${board.bbsTitle}</a></td>
								<td>${board.userId}</td>
								<c:choose>
									<c:when test="${board.modifyDate ne null}">
										<td><fmt:formatDate value="${board.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
									</c:when>
									<c:otherwise>
										<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="paging" class="row" align="center">
				<nav>
					<input type="hidden" name="pageNo" value="${pv.pageCnt}">
					<input type="hidden" name="pageNoCnt" value="${pv.pageNoCnt}">
					<ul class="pagination" style="margin: 0">
						<li>
					    	<c:choose>
					    		<c:when test="${pv.pageCnt - 1 ge 1}">
									<a href="/a.do?pageNo=${pv.pageCnt - 1}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
					    		</c:when>
					    		<c:otherwise>
									<a href="/a.do?pageNo=1" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
					    		</c:otherwise>
					    	</c:choose>
						</li>
						<c:forEach begin="1" end="${pv.pageNoCnt}" var="i">
							<li id="selectPage${i}" class="">
								<a href="/a.do?pageNo=${i}">${i}</a>
							</li>
					    </c:forEach>
					    <li>
					    	<c:choose>
					    		<c:when test="${pv.pageCnt + 1 le pv.pageNoCnt}">
									<a href="/a.do?pageNo=${pv.pageCnt + 1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
					    		</c:when>
					    		<c:otherwise>
									<a href="/a.do?pageNo=${pv.pageNoCnt}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
					    		</c:otherwise>
					    	</c:choose>
					    </li>
					</ul>
				</nav>
			</div>
			<div class="row" align="right" style="margin-top: 30px;">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div class="input-group">
						<input type="text" class="form-control" id="searchWord" name="searchWord" placeholder="검색할 단어를 입력하세요">
						<span class="input-group-btn">
							<button id="searchBtn" class="btn btn-default" type="button">검색</button>
						</span>
					</div>
				</div>
				<div class="col-md-3">
					<button class="btn btn-warning" type="submit" id="delBtn">삭제</button>
					<button class="btn btn-success" type="button" id="writeBtn">글쓰기</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>