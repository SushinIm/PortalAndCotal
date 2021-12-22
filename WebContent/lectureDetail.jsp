<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/headerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/footerstyle.css">
	<link rel="stylesheet" type="text/css" href="/css/lectureDetail.css">
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
	<jsp:include page="header.jsp"></jsp:include>
	<div id="wrap">
	    <div id="contents">
	        <div class="titleArea">
	            <h2>과정 현황</h2>
	        </div>
			<form method="post" name="frm">
				<jsp:useBean id="lecture" scope="request" class="com.mini.beans.LectureBean" />
				<jsp:useBean id="subject" scope="request" class="com.mini.beans.SubjectBean" />
				<jsp:useBean id="inClass" scope="request" class="com.mini.beans.InClassBean" />
				<input type="hidden" value="<%=lecture.getLecNo() %>" name="lecNo"/>
				<input type="hidden" value="<%=lecture.getNowStu() %>" name="nowStu"/>
				<input type="hidden" value="<%=inClass.getIncNo() %>" name="incNo"/>
				<input type="hidden" value="<%=subject.getSubScore() %>" name="subScore"/>
		        <div class="detail_box">
		            <div class="detail01">
		                <div class="blue_bt">과정명</div>
		                <div class="white_bt"><%=lecture.getLecYear() %>년 <%=lecture.getLecDur() %>학기 <%=subject.getSubName() %> (<%=subject.getSubPart() %>)</div>
		            </div>                        
		            <div class="detail01">
		                <div class="blue_bt">일정</div>
		                <div class="white_bt"><%=lecture.getLecDay() %> <%=lecture.getLecStart() %> ~ <%=lecture.getLecEnd() %></div>
		            </div>                        
		            <div class="detail01">
		                <div class="detail02">
		                    <div class="blue_bt2">강의실</div>
		                    <div class="white_bt2"><%=lecture.getLecRoom() %></div>
		                </div>
		                <div class="detail03">
		                    <div class="blue_bt3">교수</div>
		                    <div class="white_bt3"><%=lecture.getProfessor() %></div>
		                </div>
		            </div>                                                
		            <div class="detail01">
		                <div class="detail02">
		                    <div class="blue_bt2">학점</div>
		                    <div class="white_bt2"><%=subject.getSubScore() %>점</div>
		                </div>
		                <div class="detail03">
		                    <div class="blue_bt3">신청인원</div>
		                    <div class="white_bt3"><%=lecture.getNowStu() %> / <%=lecture.getMaxStu() %></div>
		                </div>
		            </div>     
					<%
						if("Y".equals(inClass.getTakeFlag())){
					%>  
		            <p class="red">수료한 적 있는 과목입니다 : 이전 취득 <%=inClass.getIncGrade() %></p>
					<%
						}
					%>
		            <div class="bt_area">
		                <input class="B_bt" type="button" onclick="goMain()" value="과정 목록으로"/>
						<%
							if(lecture.getNowStu().equals(lecture.getMaxStu())){
						%>
						<input class="B_bt" type="button" onclick="alert('신청이 마감되었습니다.')" value="신청이 마감되었습니다."/>
						<%
							}else if(inClass.getIncNo() != null && "N".equals(inClass.getIncComp())){
						%>
						<input type="button" class="B_bt" value="신청 취소" onclick="deleteClass()"/>
						<%
							}else{
						%>
		                <input type="button" class="B_bt" value="수강 신청" onclick="registerClass()"/>
						<%
							}
						%>
		            </div>                 
		        </div>
	        </form>
	    </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>