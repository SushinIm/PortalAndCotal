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
		session = request.getSession();
		if(session.getAttribute("std") != null){
			response.sendRedirect("/index");
		}
	%>
	<form method="post" action="/findInfo">
		<input type="hidden" name="type" value="1"/>
		<p>이름 : <input type="text" name="stuname" placeholder="이름"/></p>
		<p>생년월일 : <input type="text" name="studob" placeholder="생년월일"/></p>
		<p>전화번호 : <input type="text" name="stuphone" placeholder="'-'제외 전화번호"/></p>
		<input type="submit" value="확인"/>
	</form>
	<hr/>
	<form method="post" action="/findInfo">
		<input type="hidden" name="type" value="2"/>
		<p>학번 : <input type="text" name="stuid" placeholder="학번"/></p>
		<p>전화번호 : <input type="text" name="stuphone" placeholder="'-'제외 전화번호"/></p>
		<input type="submit" value="확인"/>
	</form>
</body>
</html>

