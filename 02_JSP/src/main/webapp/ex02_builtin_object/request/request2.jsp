<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");
		// Optional : null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
		// of()는 인자로서 null값을 받지 않는다는 것이고, ofNullable()은 null값을 허용한다는 것이다.
		Optional opt = Optional.ofNullable(request.getParameter("price"));
		// orElse()는 null이 있든 말든 해당 값이 null이거나 아니어도 실행 가능하다.
		// orElseGet()는 null일 때만 실행된다.
		int price = Integer.parseInt(opt.orElse("0"));
	%>
	
	<h1>모델 : <%=model%></h1>
	<h1>가격 : <%=price%></h1>

</body>
</html>