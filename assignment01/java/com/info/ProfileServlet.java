package com.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>info</title></head><body bgcolor='pink'><h3>Basic Information</h3><table><tr><th>First Name:</th><td>Prathamesh</td></tr><tr><th>Last Name:</th><td>Posugade</td></tr></table></body></html>");
	}
}
