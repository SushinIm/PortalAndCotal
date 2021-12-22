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
import com.mini.dao.LoginDAO;
import com.mini.dao.RegisterDAO;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	request.setCharacterEncoding("UTF-8");
		String action = request.getRequestURI();
		RequestDispatcher rd = request.getRequestDispatcher("/msg.jsp");
		
		HttpSession session = request.getSession();
    	if("/logout".equals(action)) {
			session.invalidate();
			request.setAttribute("msg", "로그아웃 되었습니다");
			request.setAttribute("loc", "/index");
		}

    	rd.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getRequestURI();
		HttpSession session = request.getSession();
		
		if("/login".equals(action)) {
			String stuId = request.getParameter("stuId"); 
			String stuPw = request.getParameter("stuPw");
			
			StudentBean std = new StudentBean();
			std = new LoginDAO().loginCheck(stuId, stuPw);
			if(std != null){
				session.setAttribute("std", std);
			}
		}else if("/logout".equals(action)) {
			session.invalidate();
		}
		
		response.sendRedirect("/index");
	}
}


