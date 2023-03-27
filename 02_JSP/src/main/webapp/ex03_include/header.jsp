<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("환영합니다");
%>
<title><%=title%></title>
<style>
	nav ul {
		display: flex;
		list-style-type: none;
	}
	nav ul li {
		width: 100px;
		height: 30px;
		text-align: center;
		line-height: 30px;                                       
	}
	nav ul li a {
		text-decoration: none;
		color: #181818;
		display: block;
		width: 100%;
		height: 100%;
	}
</style>
</head>
<body>

	<nav>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>
	
	<hr>

</body>
</html>