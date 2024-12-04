import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.entity.Candidate;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		List<Candidate> ll;
		try (CandidateDao c = new CandidateDaoImpl()) {
			ll = c.revAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body>");
		
		if (null != req.getCookies()) {
			String u = "";
			String r = "";
			for (Cookie c : req.getCookies()) {
				if (c.getName().equals("username")) {
					u = c.getValue();
				}
				if (c.getName().equals("role")) {
					r = c.getValue();
				}
			}
			out.printf("<h3>Hello %s, you are %s...!</h3>", u, r);
		}

		
		out.println("<h2>Voting Result</h2>");
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Id</th>");
		out.println("<th>Name</th>");
		out.println("<th>Party</th>");
		out.println("<th>Votes</th>");
		out.println("<th>Action</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		for (Candidate c : ll) {
			out.printf("<tr>");
			out.printf("<td>%d</td>", c.getId());
			out.printf("<td>%s</td>", c.getName());
			out.printf("<td>%s</td>", c.getParty());
			out.printf("<td>%d</td>", c.getVotes());
			out.printf("<td></td>");
			out.printf("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");		
		out.println("<hr>");
		out.println("<p><a href='newcandidate.html'>Add Candidate</a></p>");
		out.println("<hr>");
		out.println("<p><a href='logout'>Sign Out</a></p>");
		out.println("<hr>");
		out.println("</body>");
		out.println("</html>");
	}
}
