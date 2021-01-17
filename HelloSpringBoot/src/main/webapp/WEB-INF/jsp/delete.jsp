<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- direct data from *.properties -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
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
	<title>JSP edit page</title>
</head>
<body>
	<h1><spring:message code="content.title"></spring:message></h1>
	<h1>deleting ${formModel}</h1>
	<form method="post" action="/delete" name="formModel">
		<input type="hidden" name="id" value="${formModel.id}" >
		<table>
	 	<tr><td><label for="name">이름</label></td>
	 		<td><p>${formModel.name}</p></td></tr>
	 		
	 	<tr><td><label for="age">연령</label></td>
	 		<td><input type="text" name="age" value="${formModel.age}"></td></tr>
	 		
	 	<tr><td><label for="mail">메일</label></td>
	 		<td><input type="text" name="mail" value="${formModel.mail}"></td></tr>
	 	<tr><td><label for="memo">메모</label></td>
	 		<td><textarea name="memo" >${formModel.memo}</textarea></td></tr>
	 	<tr><td></td><td><input type="submit"  /></td></tr>
	</table>
	</form>

</body>
</html>