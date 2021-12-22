<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"> 
	<title> 인내천대학교 수강신청 시스템 </title>
	<link rel="stylesheet" type="text/css" href="/css/headerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/footerstyle.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
	<script type="text/javascript" src="/js/indexScript.js"></script>
</head>
<body>
	<%
		session = request.getSession();
		if(session.getAttribute("std") != null){
			response.sendRedirect("/index");
		}
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="wrap">
	    <div id="contents">
	        <div class="login_title">
	            <h2>LOGIN</h2>
	        </div>
	        <div class="login_box">
	            <form method="post" class="loginForm" name="login" action="/login">
	                <input type="text" class="stuId" name="stuId" placeholder="학번">
	                <input type="password" class="stuPw"  name="stuPw" placeholder="비밀번호">
	                <input type="submit" class="btn" value="로그인" onclick="return buttonClick()">
	                <div class="login_line"></div>
	                <a href="findInfo.jsp">학번 / 비밀번호 찾기</a>
	            </form>
	       </div>
	    </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
