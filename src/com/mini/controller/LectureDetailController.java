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

import com.mini.beans.InClassBean;
import com.mini.beans.LectureBean;
import com.mini.beans.StudentBean;
import com.mini.beans.SubjectBean;
import com.mini.dao.InClassDAO;
import com.mini.dao.LectureDAO;
import com.mini.dao.SubjectDAO;

/**
 * Servlet implementation class LectureDetailController
 */
@WebServlet("/lectureDetail")
public class LectureDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		RequestDispatcher rd = null;
		
		if(session.getAttribute("std") == null) {
			rd = request.getRequestDispatcher("/msg.jsp");
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("loc", "/index");
		}else {
			rd = request.getRequestDispatcher("/lectureDetail.jsp");
			
			StudentBean std = (StudentBean) session.getAttribute("std");
			String lecNo = request.getParameter("lecNo");
			String subNo = request.getParameter("subNo");
			String stuId = std.getStuId();
			
			LectureBean lecture = new LectureDAO().selectLectureOne(lecNo);
			SubjectBean subject = new SubjectDAO().selectSubjectOne(subNo);
			InClassBean inClass = new InClassDAO().selectEdLecture(subNo, stuId); 
			
			request.setAttribute("lecture", lecture);
			request.setAttribute("subject", subject);
			request.setAttribute("inClass", inClass);
		}
		rd.forward(request, response);
	}

}
