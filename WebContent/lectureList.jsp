<%@ page import="com.mini.beans.LectureBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/css/headerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/footerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/lectureList.css">
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
	<jsp:include page="header.jsp"></jsp:include>
	<div id="wrap">
	    <div id="contents">
	        <div class="titleArea">
	            <h2>수강신청</h2>
	        </div>
	        <div class="lectures">
				<form method="post" action="/detailPage" name="toDetailFrm">
					<input type="hidden" name="lecNo" value="" />
					<input type="hidden" name="subNo" value="" />
					<jsp:useBean id="lectures" scope="request" class="java.util.ArrayList" type="java.util.List<com.mini.beans.LectureBean>" />
		            <table class="lectures_list">
		                <tr class="list_menu">
		                    <th class="list_in">번호</th>
		                    <th class="list_in">강의명</th>
		                    <th class="list_in">정원</th>
		                    <th class="list_in">신청하기</th>
		                </tr>
						<%
						    for(LectureBean lecture: lectures) {
						%>
		                <tr class="list_menu2">
		                    <td class="list_in2"><%=lecture.getRowNo() %></td>
		                    <td class="list_in2"><%=lecture.getLecYear() %>년 <%=lecture.getLecDur() %>학기 <%=lecture.getSubName() %>(<%=lecture.getProfessor() %>)</td>
		                    <td class="list_in2"><%=lecture.getNowStu() %> / <%=lecture.getMaxStu() %></td>
		                    <td class="list_in2"><a href="#" onclick="getReady(<%=lecture.getLecNo()%>, <%=lecture.getSubNo()%>)">신청하기</a></td>
		                </tr>
						<%
						    }
						%>
		            </table>
	            </form>
	        </div>
	    </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>