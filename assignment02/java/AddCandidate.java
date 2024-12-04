import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.entity.Candidate;

@WebServlet("/addCandi")
public class AddCandidate extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String party = req.getParameter("party");
		try (CandidateDao c = new CandidateDaoImpl()) {
			int affRows = c.insertRecord(new Candidate(0, name, party, 0));
			if (affRows == 1) {
				resp.sendRedirect("result");
			} else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>failed</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3>Something went wrong...!</h3>");
				out.println("<a href='newcandidate.html'>add candidate again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
