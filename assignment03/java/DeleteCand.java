
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;

@WebServlet("/delCand")
public class DeleteCand extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int candId = Integer.parseInt(req.getParameter("id"));
		try (CandidateDao c = new CandidateDaoImpl()) {
			int deleted = c.deleteRecord(candId);
			String mess = "Candidate deleted:" + deleted;
			req.setAttribute("mess", mess);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		RequestDispatcher rd = req.getRequestDispatcher("result");
		rd.forward(req, resp);
	}
}
