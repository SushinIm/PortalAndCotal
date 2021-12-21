package com.mini.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.mini.beans.StudentBean;

import com.mini.dao.RegisterDAO;

@WebServlet(urlPatterns = {"/registerClass", "/deleteClass"}) //이전 페이지의 이름으로 변경
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rgt = 0;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getRequestURI();

		int nowStu = Integer.parseInt(request.getParameter("nowStu")); 
		StudentBean std = (StudentBean) session.getAttribute("std");
		String lecNo = request.getParameter("lecNo");
		String stuid = std.getStuId();
		
		RequestDispatcher rd = request.getRequestDispatcher("/msg.jsp");
		request.setAttribute("loc", "/index");
		
		if("/registerClass".equals(action)) {
			rgt = new RegisterDAO().insertClass(lecNo, stuid, nowStu);
			
			if(rgt == 1){
				request.setAttribute("msg","수강신청이 등록되었습니다.");
			}
			else if(rgt <= 0){
				request.setAttribute("msg","수강신청 등록에 실패했습니다.");
			}
		}else if("/deleteClass".equals(action)) {
			String incNo = request.getParameter("incNo");
			rgt = new RegisterDAO().deleteClass(lecNo, incNo, nowStu);
			if(rgt == 1){
				request.setAttribute("msg","수강신청을 취소했습니다.");
			}
			else if(rgt <= 0){
				request.setAttribute("msg","수강신청 취소에 실패했습니다.");
			}
		}
		rd.forward(request, response);
	}
}