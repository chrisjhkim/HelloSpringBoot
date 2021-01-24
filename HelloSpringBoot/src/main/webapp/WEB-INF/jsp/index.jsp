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
	<script src="http://code.jquery.com/jquery.min.js"></script>
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
<script type="text/javascript">
console.log('console test    0030  ');
$(document).ready(function(){
	console.log('ho!');
	var num = <%= request.getParameter("id") %>
	console.log('num='+num);
	$.get("/rest/"+num , null, callback);
});
function callback(result){
	console.log('callback called');
	console.log('result = '+result);
	console.log('result.id = '  +result.id);
	console.log('result.name = '+result.name);
	console.log('result.mail = '+result.mail);
	console.log('result.age = ' +result.age);
	console.log('result.memo = '+result.memo);
	$('#obj').append('<li>id: '+ result.id + '</li>');
	$('#obj').append('<li>name: '+ result.name + '</li>');
	$('#obj').append('<li>mail: '+ result.mail + '</li>');
	$('#obj').append('<li>age: '+ result.age + '</li>');
	$('#obj').append('<li>memo: '+ result.memo + '</li>');
}

	
</script>
</head>
<body>
	<h1><spring:message code="content.title"></spring:message></h1>
	<h6>src/main/webapp/WEB-INF/jsp/index.jsp</h6>
	<p><%=new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()) %></p>
	<p>${msg }<p>
	<form method="post" action="/" name="formModel">
	<!-- <form method="post" action="/"   th:object="${formModel} }"> -->
		<table>
			<tr><td><label for="name">이름</label></td>
				<td><input type="text" name="name" /></td> </tr>
			
			<tr><td><label for="age">연령</label></td>
				<td><input type="text" name="age" /></td> </tr>
			
			<tr><td><label for="mail">메일</label></td>
				<td><input type="text" name="mail" /></td> </tr>
			<tr><td><label for="memo">메모</label></td>
				<td><textarea name="memo" cols="20" rows="5"></textarea></td> </tr>
			<tr><td></td>
				<td><input type="submit" value="등록"/></td></tr>
		</table>
	</form>

	<hr>	
	<p>검색 by name</p>
	<form method="post" action="/find">
		<input type="text" name="name">
		<input type="submit" >
	</form>
	<hr>	
	
	<ol id="obj"></ol>
	
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
			<td>${obj.mail}</td>
			<td>${obj.age}</td>
			<td>${obj.memo}</td>
		</tr>
	</c:forEach>
	</table>

	

		
</body>
</html>