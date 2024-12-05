import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.voters.daos.UserDao;
import com.voters.daos.UserDaoImpl;
import com.voters.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		try (UserDao u = new UserDaoImpl()) {
			User obj = u.findByEmail(email);
			if (obj != null && obj.getPassword().equals(password)) {

				HttpSession sess = req.getSession();
				sess.setAttribute("curUser", obj);

				Cookie c1 = new Cookie("username", email);
				c1.setMaxAge(3600);
				resp.addCookie(c1);

				Cookie c2 = new Cookie("role", obj.getRole());
				c2.setMaxAge(3600);
				resp.addCookie(c2);

				if (obj.getRole().equals("admin")) {
					// ResultServlet
					resp.sendRedirect("result");
				} else {
					// CadidateListServlet
					resp.sendRedirect("cadidateList");
				}

			} else {
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>Login Failed</h2>");
				out.println("<h5>User Id or Password is wrong...!</h5>");
				out.println("<a href='index.html'>login again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
