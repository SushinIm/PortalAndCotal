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
import com.mini.dao.LectureDAO;

/**
 * Servlet implementation class LectureController
 */
@WebServlet("/lectureList")
public class LectureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//String stuId = session.getAttribute("stuId").toString();
		
		List<LectureBean> lbl = new LectureDAO().selectLectureList();
		
		RequestDispatcher rd = request.getRequestDispatcher("/lectureList.jsp");
		request.setAttribute("lectures", lbl);
		
		rd.forward(request, response);
	}

}
