<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"> 
	<title> 인내천대학교 수강신청 시스템 </title>
	<link type="text/css" rel="stylesheet" href="/css/indexStyle.css">
	<script type="text/javascript" src="/js/indexScript.js"></script>
</head>
<body>
	<%
		session = request.getSession();
		if(session.getAttribute("std") != null){
			response.sendRedirect("/index");
		}
	%>
	<header class="add_header">
		<h1 class="add_header_title">인내천대학교 수강신청</h1>
		<p class="add_header_text"> 사람이 곧 한울 </p>
	</header>
	
    <form method="post" class="loginForm" name="login" action="/login">
		<div class="idForm">
			<input type="text" class="stuId" id="stuId" name="stuId" placeholder="학 번">
		</div>
		<div class="passForm">
			<input type="password" class="stuPw" id="stuPw"  name="stuPw" placeholder="비밀번호">
		</div>

		<input type="submit" class="btn" value="로그인" onclick="return buttonClick()">
		<div class="bottomText">
		             학번/비밀번호를 잊어버렸나요? <a href="findInfo.jsp">클릭</a>
		</div>
    </form>
    <footer>
		<span>B1층, 335 효령로 서초1동 서초구 서울특별시 </span><br/>
		<span>전화번호 : (국번없이) 1331 &nbsp &nbsp 교훈 : 사람이 곧 한울</span><br/>
		<span>COPYRIGHT© 2021 INECHEON UNIV. ALL RIGHTS RESERVED.</span>  
    </footer>
</body>
</html>
