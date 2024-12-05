
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.entity.Candidate;

@WebServlet("/editCand")
public class EditCand extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Edit Candidate</h2>");
		out.println("<form method='post' action='editCand'>");

		try (CandidateDao c = new CandidateDaoImpl()) {
			Candidate obj = c.findById(id);
			if (obj != null) {
				out.printf("<input type='hidden' name='id' value='%s'>", obj.getId());
				out.printf("<hr>name: <input type='text' name='name' value='%s'>", obj.getName());
				out.printf("<hr>party: <input type='text' name='party' value='%s'>", obj.getParty());
				out.printf("<hr>votes: <input type='text' name='votes' value='%s' readonly><hr>", obj.getVotes());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.println("<input type='submit' value='Update Candidate'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String party = request.getParameter("party");
		int votes = Integer.parseInt(request.getParameter("votes"));
		Candidate obj = new Candidate(id, name, party, votes);
		try (CandidateDao c = new CandidateDaoImpl()) {
			int aff = c.updateCand(obj);
			String mess = "Candidate update: " + aff;
			request.setAttribute("mess", mess);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("result");
		rd.forward(request, response);
	}

}
