<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
    <div class="header_img">
        <a href="/lectureList"><img src="/img/top_banner.jpg"></a>
    </div>
    <div class="header_area">
        <div class="search_bar">
            <input class="search_text" type="text" placeholder="검색어를 입력하세요">
            <input class="search_icon" type="button">
        </div>
        <div class="logo">
            <h1><a href="/index"><img src="/img/logo.png"></a></h1>
        </div>
        <%if(session.getAttribute("std") != null){ %>
        <div class="top_menu">
            <ul class="top_menu_icon">
                <li><a href="/myPage"><img src="/img/mypage.png">MY PAGE</a></li>
               	<li><a href="/logout"><img src="/img/language.png">LOGOUT</a></li>
            </ul>
        </div>
        <%} %>
    </div>
    <div class="menu_bar">
        <hr class="line">
        <ul class="meau_bar_list">
            <li><a href="#">학교소개</a></li>
            <li><a href="#">입학안내</a></li>
            <li><a href="#">대학/대학원</a></li>
            <li><a href="#">연구/산학</a></li>
            <li><a href="#">학사안내</a></li>
            <li><a href="#">대학생활</a></li>
            <li><a href="#">뉴스센터</a></li>
        </ul>
        <hr class="line">
    </div>
</div>