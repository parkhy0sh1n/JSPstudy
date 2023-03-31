<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3><a href="${contextPath}/today.do">오늘은 며칠입니까?</a></h3>
	
	<form action="${contextPath}/age.do">
    	<select name="birthyear">
         	<c:forEach var="y" begin="1900" end="2023" step="1">
            	<option value="${y}">${y}년</option>
   			</c:forEach>
   		</select>
  		<button>몇 살입니까?</button>
   </form>
	
</body>
</html>