package com.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.info.pojo.Marks;



@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	List<Marks> l;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		l = new ArrayList<Marks>();
		l.add(new Marks(60, "Maths"));
		l.add(new Marks(70, "Hindi"));
		l.add(new Marks(80, "Science"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>marks</title></head><body>");							
		out.println("<h1>Marks and Subjects</h1>");									
		for (Marks m : l) {
			out.println("<h3>"+ m +"</h3>");												
		}
		out.println("</body></html>");
	}
}
