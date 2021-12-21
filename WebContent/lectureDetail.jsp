<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		function goMain(){
			location.href="/index";
		}
		function deleteClass(){
			document.frm.action = "/deleteClass";
			document.frm.submit();
		}
		function registerClass(){
			document.frm.action = "/registerClass";
			document.frm.submit();
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
	<form method="post" name="frm">
		<jsp:useBean id="lecture" scope="request" class="com.mini.beans.LectureBean" />
		<jsp:useBean id="subject" scope="request" class="com.mini.beans.SubjectBean" />
		<jsp:useBean id="inClass" scope="request" class="com.mini.beans.InClassBean" />
		<input type="hidden" value="<%=lecture.getLecNo() %>" name="lecNo"/>
		<input type="hidden" value="<%=lecture.getNowStu() %>" name="nowStu"/>
		<input type="hidden" value="<%=inClass.getIncNo() %>" name="incNo"/>
		<table border="1">
			<tr>
				<td>과정명</td>
				<td colspan="3"><%=lecture.getLecYear() %>년 <%=lecture.getLecDur() %>학기 <%=subject.getSubName() %> (<%=subject.getSubPart() %>)</td>
			</tr>
			<tr>
				<td colspan="1">일정</td>
				<td colspan="3"><%=lecture.getLecDay() %> <%=lecture.getLecStart() %> ~ <%=lecture.getLecEnd() %></td>
			</tr>
			<tr>
				<td>강의실</td>
				<td><%=lecture.getLecRoom() %></td>
				<td>교수</td>
				<td><%=lecture.getProfessor() %></td>
			</tr>
			<tr>
				<td>수료 시 취득 예정 학점</td>
				<td><%=subject.getSubScore() %></td>
				<td>신청인원</td>
				<td><%=lecture.getNowStu() %> / <%=lecture.getMaxStu() %></td>	
			</tr>
			<%
				if("Y".equals(inClass.getTakeFlag())){
			%>
			<tr>
				<td colspan="2">수료했던 과목입니다.</td>
				<td colspan="2">이전 취득 학점 : <%=inClass.getIncGrade() %></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="2"><input type="button" onclick="goMain()" value="과정 목록으로"/></td>
			<%
				if(lecture.getNowStu().equals(lecture.getMaxStu())){
			%>
				<td colspan="2">신청 마감</td>
			<%
				}else if(inClass.getIncNo() != null && "N".equals(inClass.getIncComp())){
			%>
				<td colspan="2"><input type="button" value="신청 취소" onclick="deleteClass()"/></td>
			<%	
				}else{ 
			%>
				<td colspan="2"><input type="button" value="수강 신청" onclick="registerClass()"/></td>			
			<%
				}
			%>
			</tr>
		</table>
	</form>
</body>
</html>