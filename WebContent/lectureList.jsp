<%@ page import="com.mini.beans.LectureBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		function getReady(lecNo, subNo){
			document.toDetailFrm.lecNo.value = lecNo;
			document.toDetailFrm.subNo.value = subNo;
			document.toDetailFrm.submit();
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
	<div>
		<div>학생 정보</div>
	</div>
	<form method="post" action="/detailPage" name="toDetailFrm">
		<input type="hidden" name="lecNo" value="" />
		<input type="hidden" name="subNo" value="" />
		<jsp:useBean id="lectures" scope="request" class="java.util.ArrayList" type="java.util.List<com.mini.beans.LectureBean>" />
		<table>
			<tr>
				<th>과정번호</th>
				<th>과정명</th>
				<th>담당교수</th>
				<th>신청</th>
			</tr>
		<%
		    for(LectureBean lecture: lectures) {
		%>
			<tr>
				<td><%=lecture.getRowNo() %></td>	
				<td><a href="#" onclick="getReady(<%=lecture.getLecNo()%>, <%=lecture.getSubNo()%>)"><%=lecture.getLecYear() %>년 <%=lecture.getLecDur() %>학기 <%=lecture.getSubName() %></a></td>	
				<td><%=lecture.getProfessor() %></td>	
				<td><%=lecture.getNowStu() %> / <%=lecture.getMaxStu() %></td>	
			</tr>
		<%
		    }
		%>
		</table>
	</form>
</body>
</html>