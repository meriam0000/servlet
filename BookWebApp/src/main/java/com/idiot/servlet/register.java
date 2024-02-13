package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/register")
public class register extends HttpServlet {
   private static final String query="INSERT INTO BookData(BookName,BookEdition,BookPrice) VALUES(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get printWriter 
		PrintWriter pw=resp.getWriter();
		//set content type
		resp.setContentType("text/html");

	
		
		//GET THE BOOK INFO 
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
        
		
		//load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException se){
			se.printStackTrace();
		}
		//generate the connection
		try(Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","meriam","123456789");
				PreparedStatement ps = con.prepareStatement(query);){
				ps.setString(1, bookName);
				ps.setString(2, bookEdition);
				ps.setFloat(3, bookPrice);
				
				int count =ps.executeUpdate();
				if (count==1) {
					pw.println("<h2>Record Is registered successfully</h2>");
					
				}
				else {
					pw.println("<h2>Record is not registred succefully</h2>");
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		} catch (Exception e) {
			
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='Home.html'> Register Book ");
	
		pw.println("<br>");
		pw.println("<a href='bookList'> bookList </a>");
		pw.println("<br>");
		
	HttpSession session=req.getSession(true);
    String username=(String)session.getAttribute("username");
     pw.println("username is "+username);
		
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	
	
	
	
}
