<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 로그인 시나리오
		// id=admin, pw=1234인 경우에 로그인 성공
		if(id.equals("admin") && pw.equals("1234")){
			
			// 로그인 한 회원 정보를 session에 보관하기
			session.setAttribute("loginId", id);
			session.setMaxInactiveInterval(60 * 10);  // 세션유효시간 : 600초(10분)
			
		}
		
		// 다시 로그인 폼으로 돌아가기
		response.sendRedirect(request.getContextPath() + "${contextPath}/ex07_jstl/practice01_login/02_login.jsp");
		
	%>

</body>
</html>