package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	PrintWriter ps=resp.getWriter();
	String UserName=req.getParameter("uname");
	String password=req.getParameter("pass");
	if(UserName.equals("meriam") && password.equals("123") ) {
		 HttpSession session= req.getSession(true);
		 session.setAttribute("username",UserName );
		 session.setAttribute("password",password );
		resp.sendRedirect("Home.html");
		
	}
	else {
		ps.println("failed");
	}
			


}}
