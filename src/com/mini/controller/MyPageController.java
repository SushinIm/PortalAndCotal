package com.mini.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.beans.LectureBean;
import com.mini.beans.StudentBean;
import com.mini.dao.MyPageDAO;


@WebServlet("/myPage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyPageController() {
        super();
      
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		if(session.getAttribute("std") == null) {
			rd = request.getRequestDispatcher("/msg.jsp");
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("loc", "/indexPage.jsp");
		}else {
			rd = request.getRequestDispatcher("/myPage.jsp");

			StudentBean stb = (StudentBean)session.getAttribute("std");
			List<LectureBean> clb = new MyPageDAO().selectCurLectureList(stb.getStuId());
			List<LectureBean> plb = new MyPageDAO().selectPreLectureList(stb.getStuId());
			
			request.setAttribute("student", stb);
			request.setAttribute("curLectures", clb);
			request.setAttribute("preLectures", plb);
			
		}
		rd.forward(request, response);	
	}
}