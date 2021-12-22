package com.mini.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.beans.StudentBean;
import com.mini.dao.StudentDAO;


@WebServlet("/findInfo")
public class FindInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FindInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		RequestDispatcher rd = null;
		
		if(session.getAttribute("std") == null) {
			rd = request.getRequestDispatcher("/msg.jsp");
			
			String stuname = request.getParameter("stuname");
			String studob = request.getParameter("studob");
			String stuphone = request.getParameter("stuphone");
			String stuid = request.getParameter("stuid");
				
			StudentBean stb = null;
			request.setAttribute("loc", "/index");
			
			if("1".equals(request.getParameter("type"))) {
				stb = new StudentDAO().findIdInfo(stuname, studob,stuphone);
				request.setAttribute("msg", stb.getStuId());
			} else if("2".equals(request.getParameter("type"))) {
				stb = new StudentDAO().findPwInfo(stuid, stuphone);
				request.setAttribute("msg", stb.getStuPw());
			}
		}else {
			rd = request.getRequestDispatcher("/msg.jsp");
			request.setAttribute("msg", "이미 로그인되어 있습니다.");
			request.setAttribute("loc", "/lectureList");
		}
		
		rd.forward(request, response);
		
	}

}

