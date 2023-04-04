<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function(){
		// 서브밋
		$('#frm_write').on('submit', function(event){
			if($('#title').val() === ''){
				alert('제목은 필수입니다.');
				event.preventDefault();		// submit 방지
				return;
			}
		})
		// 목록
		$('#btn_list').on('click', function(){
			location.href = '${contextPath}/getAllBoardList.do';
		})
	})
</script>
</head>
<body>

	<div>
		<h1>${board.board_no}게시글 보기</h1>
		<h1>${board.title}</h1>
		<h1>${board.content}</h1>
		<h1>${board.modified_date}</h1>
		<h1>${board.created_date}</h1>
	</div>

</body>
</html>