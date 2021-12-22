<%@ page import="com.mini.beans.LectureBean" %>
<%@ page import="com.mini.util.UtilTools" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/headerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/footerstyle.css">
    <link rel="stylesheet" type="text/css" href="/css/lectureList.css">
	<link rel="stylesheet" type="text/css" href="/css/myPage.css">
	<title>학생 상세 페이지</title>
	<script>
		function delIt(lecNo, incNo, nowStu){
			document.delLecture.lecNo.value = lecNo;
			document.delLecture.incNo.value = incNo;
			document.delLecture.nowStu.value = nowStu;
			document.delLecture.submit();
		}
	</script>
</head>
<body>
	<%
		session = request.getSession();
		if(session.getAttribute("std") == null){
			response.sendRedirect("/index");
		}
		request.setCharacterEncoding("UTF-8");
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:useBean id="student" scope="request" class="com.mini.beans.StudentBean" />
	<div id="wrap">
	    <div id="contents">
	        <div class="titleArea">
	            <h2>MY PAGE</h2>
	        </div>
	        <div id="stuInfo">
	            <div class="detail_box">
	                <div class="detail02">
	                    <div class="blue_bt2">이름</div>
	                    <div class="white_bt2"><%=student.getStuName() %></div>
	                </div>
	                <div class="detail03">
	                    <div class="blue_bt3">학과</div>
	                    <div class="white_bt3"><%=student.getStuDept() %></div>
	                </div>
	                <div class="detail04">
	                    <div class="blue_bt4">휴대전화</div>
	                    <div class="white_bt4"><%=UtilTools.phoneType(student.getStuPhone()) %></div>
	                </div>
	            </div>
	            <div class="detail_box2">
	                <div class="detail02">
	                    <div class="blue_bt2">학년</div>
	                    <div class="white_bt2"><%=student.getStuGrade() %>학년</div>
	                </div>
	                <div class="detail03">
	                    <div class="blue_bt3">이메일</div>
	                    <div class="white_bt3"><%=student.getStuEmail() %></div>
	                </div>
	                <div class="detail04">
	                    <div class="blue_bt4">상태</div>
	                    <div class="white_bt4"><%=student.getStuStat() %></div>
	                </div>
	            </div>
	        </div>
	
	        <div class="sub_titleArea">
	            <h3>현재 수강 신청중인 과정</h3>
	        </div>
	        <div class="lectures">
		        <form method="post" action="/deleteClass" name="delLecture">
					<input type="hidden" name="lecNo"/>
					<input type="hidden" name="incNo"/>
					<input type="hidden" name="nowStu"/>
					<jsp:useBean id="curLectures" scope="request" class="java.util.ArrayList" type="java.util.List<com.mini.beans.LectureBean>" />
					<table class="lectures_list">
						<tr class="list_menu">
		                    <th class="list_in">번호</th>
		                    <th class="list_in">강의명</th>
		                    <th class="list_in">정원</th>
		                    <th class="list_in">취소하기</th>
						</tr>
					<%
					    for(LectureBean lecture: curLectures) {
					%>
						<tr class="list_menu2">
							<td class="list_in2"><%=lecture.getRowNo() %></td>	
							<td class="list_in2"><%=lecture.getLecYear() %>년 <%=lecture.getLecDur() %>학기 <%=lecture.getSubName() %>(<%=lecture.getProfessor() %>)</td>	
							<td class="list_in2"><%=lecture.getNowStu() %> / <%=lecture.getMaxStu() %></td>	
							<td class="list_in2"><a href="#" onclick="delIt(<%=lecture.getLecNo() %>, <%=lecture.getIncNo() %>, <%=lecture.getNowStu() %>)">취소하기</a></td>	
						</tr>
					<%
					    }
					%>
					</table>
				</form>
	        </div>
	        
	        <div class="sub_titleArea">
	            <h3>수료한 과정</h3>
	        </div>
	        <div class="lectures">
				<jsp:useBean id="preLectures" scope="request" class="java.util.ArrayList" type="java.util.List<com.mini.beans.LectureBean>" />
		        <table class="lectures_list">
	                <tr class="list_menu">
	                    <th class="list_in">번호</th>
	                    <th class="list_in">강의명</th>
	                    <th class="list_in">정원</th>
	                    <th class="list_in">등급</th>
					</tr>
				<%
				    for(LectureBean lecture: preLectures) {
				%>
					<tr class="list_menu2">
						<td class="list_in2"><%=lecture.getRowNo() %></td>	
						<td class="list_in2"><%=lecture.getLecYear() %>년 <%=lecture.getLecDur() %>학기 <%=lecture.getSubName() %>(<%=lecture.getProfessor() %>)</td>	
						<td class="list_in2"><%=lecture.getNowStu() %> / <%=lecture.getMaxStu() %></td>	
						<td class="list_in2"><%=lecture.getIncGrade() %></td>	
					</tr>
				<%
				    }
				%>
				</table>
		    </div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>