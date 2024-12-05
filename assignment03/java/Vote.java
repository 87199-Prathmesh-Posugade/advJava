import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.daos.UserDao;
import com.voters.daos.UserDaoImpl;
import com.voters.entity.User;

@WebServlet("/vote")
public class Vote extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Voting Status</h2>");

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

		HttpSession sess = req.getSession(false);
		if (sess == null) {
			resp.sendError(440);
			return;
		}
		else {
			User obj = (User) sess.getAttribute("curUser");
			if (obj.getStatus() == 0) {
				int votedFor = Integer.parseInt(req.getParameter("candidate"));
				try (CandidateDao c = new CandidateDaoImpl()) {
					int one = c.vote(votedFor);
					if (one == 1) {
						out.println("<h4>Successfully Voted..!</h4>");
						try (UserDao uu = new UserDaoImpl()) {
							obj.setStatus(1);
							uu.updateUser(obj);
						}
					} else {
						out.println("<h4>Voting Failed Successfully...!</h4>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} else {
				out.println("<h3>Already voted...!</h3>");
			}
			out.println("<p><a href='logout'>Sign Out</a></p>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

}
