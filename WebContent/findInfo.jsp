<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/headerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/footerstyle.css">
    <link rel="stylesheet" type="text/css" href="/css/findinfo.css">
	<title>회원 정보 찾기</title>
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
            <div class="findinfo_title">
                <h2>학번/비밀번호 찾기</h2>
            </div>
			<div class="find_box">
                <div id="findId">
				    <h3 class="id_title">학번 찾기</h3>
				    <div class="line_box"></div>
				    <form method="post" action="/findInfo">
				        <input type="hidden" name="type" value="1"/>
				        <div class="box"><h6>이름</h6><input class="sq_box" type="text" name="stuname" placeholder="이름"/></div>
				        <div class="box"><h6>생년월일</h6><input class="sq_box" type="text" name="studob" placeholder="생년월일"/></div>
				        <div class="box"><h6>전화번호</h6><input class="sq_box" type="text" name="stuphone" placeholder="'-'제외 전화번호"/></div>
				        <input type="submit" class="Btn" value="확인"/>
				    </form>   
				</div>
                <div id="findPw">
			    <h3 class="id_title">비밀번호 찾기</h3>
			    <div class="line_box"></div>
			    <form method="post" action="/findInfo">
			        <input type="hidden" name="type" value="2"/>
			        <div class="box"><h6>학번</h6><input class="sq_box" type="text" name="stuid" placeholder="학번"/></div>
			        <div class="box"><h6>전화번호</h6><input class="sq_box" type="text" name="stuphone" placeholder="'-'제외 전화번호"/></div>
			        <input type="submit" class="Btn2" value="확인"/>
			    </form>   
			</div>
            </div>    
        </div>
    </div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

