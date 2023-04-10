<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- 
	pageContext : pageContext 객체는 JSP 기본객체로 JSP 페이지에서 따로 선언하지 않아도 참조하여 사용 가능하다.
				  pageContext는 JSP 페이지에 대해 1:1로 연결된 객체로 JSP 페이지당 하나의 pageContext 객체가 생성된다.
				  따라서 같은 JSP 페이지 내에서는 서로 값을 공유할 수 있으며, 주요 기능은 다른 기본객체들을 구할 때 사용하거나 페이지 흐름을 제어할 때 사용된다.
--%>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
</head>
<body>

	<div class="wrap">
	
		<h1>회원 관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id" onkeyup="fnCheckId()"> <span id="check_id">아이디 중복 체크</span>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<input type="radio" id="male" name="gender" value="M">
				<label for="male">남자</label>
				<input type="radio" id="female" name="gender" value="F">
				<label for="female">여자</label>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="hidden" id="memberNo" name="memberNo">
				<input type="button" value="초기화" onclick="fnInit()">
				<input type="button" value="신규등록" id="btn_add" onclick="fnAddMember()">
				<input type="button" value="정보수정" id="btn_modify" onclick="fnModifyMember()">
				<input type="button" value="회원삭제" id="btn_remove" onclick="fnRemoveMember()">
			</div>
		</form>
	
		<hr>
		
		<table border="1">
			<caption>전체 회원 수 : <span id="member_count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
	
	</div>

	<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
	<script>
		
		/* 함수 호출 */
		fnInit();
		fnGetAllMember();
		
		/* 함수 정의 */
		function fnInit(){
			$('#id').val('').prop('disabled', false);  // 조회 기능이 동작할 때 처리된 disabled 속성을 없앤다.
			$('#name').val('');
			$(':radio[name=gender]').prop('checked', false);
			$('#address').val('');
			$('#btn_add').prop('disabled', false);    // 조회 기능이 동작하지 않으면 신규등록 버튼을 사용할 수 있다.
			$('#btn_modify').prop('disabled', true);  // 조회 기능이 동작하지 않으면 정보수정 버튼을 사용할 수 없다.
			$('#btn_remove').prop('disabled', true);  // 조회 기능이 동작하지 않으면 회원삭제 버튼을 사용할 수 없다.
		}
		
		function fnGetAllMember(){
			$.ajax({
				// 요청
				type: 'get',
				url: '${contextPath}/list.do',
				// 응답
				dataType: 'json',
				success: function(resData){  // 응답 데이터는 resData로 전달된다.
					/*
						resData <--- out.println(obj.toString())
						resData = {
							"memberCount": 2,
							"memberList": [
								{ },
								{ }
							]
						}
					*/
					
					$('#member_count').text(resData.memberCount);
					
					let memberList = $('#member_list');
					memberList.empty();  // 기존의 회원 목록을 지운다.
					
					if(resData.memberCount === 0){
						memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>');
					} else {
						/* $.each(배열, (인덱스, 요소)=>{})       */
						/* $.each(배열, function(인덱스, 요소){}) */
						$.each(resData.memberList, function(i, element){  // element는 하나의 회원 객체를 의미한다. 
							let str = '<tr>';
							str += '<td>' + element.memberNo + '</td>';
							str += '<td>' + element.id + '</td>';
							str += '<td>' + element.name + '</td>';
							str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';
							str += '<td>' + element.address + '</td>';
							str += '<td><input type="button" value="조회" onclick="fnGetMember(' + element.memberNo + ')"></td>';
							memberList.append(str);
						})
					}
				}
			})
		}
		
		function fnCheckId() {  // 입력된 아이디가 DB에 있는 아이디인지 점검(아이디 중복 체크)
			$.ajax({
				// 요청
				type: 'get',
				url: '${contextPath}/checkId.do',
				data: 'id=' + $('#id').val(),
				// 응답
				dataType: 'json',
				success: function(resData){  // resData = {"isAvailable": true}
					if(resData.isAvailable){
						$('#check_id').text('');
					} else {
						$('#check_id').text('이미 사용 중인 아이디입니다.').css('color', 'red');
					}
				}
			})
		}
		
		function fnAddMember(){
			$.ajax({
				// 요청
				type: 'post',
				url: '${contextPath}/add.do',
				data: $('#frm_member').serialize(),  // 폼의 모든 입력 요소를 파라미터로 전송한다. (입력 요소에는 name 속성이 필요하다.)
				// 응답
				dataType: 'json',
				success: function(resData){  // try문의 응답이 resData에 저장된다. resData = {"insertResult": 1}
					if(resData.insertResult === 1) {
						alert('신규 회원이 등록되었습니다.');
						fnInit();          // 입력란 초기화
						fnGetAllMember();  // 새로운 회원 목록으로 갱신하기
					} else {
						alert('신규 회원 등록이 실패했습니다.');
					}
				},
				error: function(jqXHR) {  // jqXHR 객체에는 예외코드(응답코드: 404, 500 등)와 예외메시지 등이 저장된다.
					                      // catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다.
					                      // catch문의 응답 메세지는 jqXHR 객체의 responseText 속성에 저장된다.
					alert(jqXHR.responseText + '(' + jqXHR.status + ')');
				}
			})
		}
		
		// onclick="fnGetMember(element.memberNo)"
		// fnGetMember() 함수를 호출할 때 회원번호(element.memberNo)를 인수로 전달하면 매개변수 memberNo가 받는다.
		function fnGetMember(memberNo){
			$.ajax({
				// 요청
				type: 'get',
				url: '${contextPath}/detail.do',
				data: 'memberNo=' + memberNo,
				// 응답
				dataType: 'json',
				success: function(resData){  // resData = {"member": {"memberNo":회원번호, "gender": "M", ...}}
					alert('회원 정보가 조회되었습니다.');
					$('#id').val(resData.member.id).prop('disabled', true);  // disabled를 이용해서 아이디의 수정을 방지한다.
					$('#name').val(resData.member.name);
					$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);
					$('#address').val(resData.member.address);
					$('#memberNo').val(resData.member.memberNo);  // <input type="hidden">에 저장하는 값
					$('#btn_add').prop('disabled', true);         // 조회 기능이 동작하면 신규등록 버튼을 사용할 수 없다.
					$('#btn_modify').prop('disabled', false);     // 조회 기능이 동작하면 정보수정 버튼을 사용할 수 있다.
					$('#btn_remove').prop('disabled', false);     // 조회 기능이 동작하면 회원삭제 버튼을 사용할 수 있다.
				}
			})
		}
		
		function fnModifyMember(){
			$.ajax({
				// 요청
				type: 'post',
				url: '${contextPath}/modify.do',
				data: $('#frm_member').serialize(),
				// 응답
				dataType: 'json',
				success: function(resData){  // resData = {"updateResult": 1}
					if(resData.updateResult === 1) {
						alert('회원 정보가 수정되었습니다.');
						fnGetAllMember();   // 새로운 회원 목록으로 갱신
					} else {
						alert('회원 정보 수정이 실패했습니다.');
					}
				},
				error: function(jqXHR) {  // jqXHR 객체에는 예외코드(응답코드: 404, 500 등)와 예외메시지 등이 저장된다.
					                      // catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다.
					                      // catch문의 응답 메세지는 jqXHR 객체의 responseText 속성에 저장된다.
					alert(jqXHR.responseText + '(' + jqXHR.status + ')');
				}
			})
		}
		
		function fnRemoveMember(){
			if(confirm('회원 정보를 삭제할까요?') == false) {
				alert('취소되었습니다.');
				return;
			}
			$.ajax({
				// 요청
				type: 'get',
				url: '${contextPath}/remove.do',
				data: 'memberNo=' + $('#memberNo').val(),
				// 응답
				dataType: 'json',
				success: function(resData){  // resData = {"deleteResult": 1}
					if(resData.deleteResult === 1) {
						alert('회원 정보가 삭제되었습니다.');
						fnGetAllMember();   // 새로운 회원 목록으로 갱신
					} else {
						alert('회원 정보 삭제가 실패했습니다.');
					}
				}
			})
		}
		
	</script>

</body>
</html>