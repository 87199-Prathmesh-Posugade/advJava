import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.entity.Candidate;

@WebServlet("/cadidateList")
public class CadidateListServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		ServletContext ctx = this.getServletContext();
		String announcement = (String) ctx.getAttribute("annouc");

		List<Candidate> ll;
		try (CandidateDao c = new CandidateDaoImpl()) {
			ll = c.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>vote</title>");
		out.println("</head>");

		ServletContext cxt = this.getServletContext();
		String colorName = (String) cxt.getInitParameter("color");
		String appName = (String) cxt.getInitParameter("app.title");
		out.printf("<body bgcolor='%s'>", colorName);
		Cookie[] arr = req.getCookies();
		if (arr != null) {
			String hello = "";
			String rola = "";
			for (Cookie c : arr) {
				if (c.getName().equals("username")) {
					hello = c.getValue();
				}
				if (c.getName().equals("role")) {
					rola = c.getValue();
				}
			}
			if (hello.equals("")) {
				out.println("<h2>Please login</h2>");
				out.println("<a href='index.html'>here</a>");
				out.println("</body>");
				out.println("</html>");
			} else {
				if (null != appName)
					out.printf("<h1> %s </h1>", appName);
				out.printf("<h4>Hello %s, you are %s...!</h4>", hello, rola);
				out.println("<h2>Candidate List</h2>");

				if (announcement != null) {
					out.printf("<h5>Announcment:  %s </h5>", announcement);
				}

				out.println("<form method='post' action='vote'>");
				for (Candidate c : ll) {
					out.printf("<input type='radio' name='candidate' value='%d'> %s <br>", c.getId(), c.getName());
				}
				out.println("<input type='submit'>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
		}
	}
}
