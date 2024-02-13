package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	   private static final String query="SELECT id ,BookName,BookEdition,BookPrice  From BookData";
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//get printWriter 
			PrintWriter pw=resp.getWriter();
			//set content type
			resp.setContentType("text/html");
			
			
			//load jdbc driver
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException se){
				se.printStackTrace();
			}
			//generate the connection
			try(Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","meriam","123456789");
					PreparedStatement ps = con.prepareStatement(query);){
					ResultSet rs=ps.executeQuery();
					pw.println("<table border='1' align='center' >");
					pw.println("<tr>");
					pw.println("<th> Book Id </th>");
					pw.println("<th> Book Name </th>");
					pw.println("<th> Book Edition </th>");
					pw.println("<th> Book Price </th>");
					pw.println("</tr>");
					while(rs.next()) {
					pw.println("<tr>");
					pw.println("<td>"+rs.getInt(1) +"</td>");
					pw.println("<td>"+rs.getString(2) +"</td>");
					pw.println("<td>"+rs.getString(3) +"</td>");
					pw.println("<td>"+rs.getFloat(4) +"</td>");
					pw.println("</tr>");
					}
					pw.println("</table>");
					
					
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				pw.println("<h1>"+e.getMessage()+"</h1>");
			} catch (Exception e) {
				
				e.printStackTrace();
				pw.println("<h1>"+e.getMessage()+"</h1>");
			}
			pw.println("<a href='Home.html'>Register Book</a>");
			}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
