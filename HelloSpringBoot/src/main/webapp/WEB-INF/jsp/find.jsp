<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>

<!-- direct data from *.properties -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- foreach -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC
 "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8">
	<style>
		body{ font-size:13pt; color: gray; margin:5px 25px; }
		h1{ font-size:18pt; font-weight:bold; color:gray; }
		h2{ font-weight:bold; margin:0px; color:gray; }
		h6{ font-weight:bold; margin:0px; color:gray; }
		tr { margin:5px; }
		th { padding:5px; color:white; background:darkgray; }
		td { padding:5px; color:black; background:#e0e0ff; }
		pre{ border:solid 3px #ddd; padding:10px;}
	</style>	
	<title>JSP Index Page2</title>
</head>
<body>
	<h1><spring:message code="content.title"></spring:message></h1>
	<h6>src/main/webapp/WEB-INF/jsp/find.jsp</h6>
	<p><%=new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()) %></p>

	<hr>	
	<p>검색 by name</p>
	<form method="post" action="/find">
		<input type="text" name="name">
		<input type="submit" value="검색" >
	</form>
	<hr>	
	
	<p>val=${val}</p>
	<form method="post" action="/">
		<input type="text" name="text1">
		<input type="submit" >
	</form>
	
	
	<hr>
	<table>
	<tr><th>ID</th><th>이름</th></tr>
	<c:forEach var="obj" items="${datalist}">
		<tr>
			<td>${obj.id}</td>
			<td>${obj.name}</td>
		</tr>
	</c:forEach>
	</table>

	

		
</body>
</html>