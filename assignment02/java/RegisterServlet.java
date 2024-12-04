import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voters.daos.UserDao;
import com.voters.daos.UserDaoImpl;
import com.voters.entity.User;

@WebServlet("/addUser")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String birthj = req.getParameter("birth");
		Date birth = Date.valueOf(birthj);
		String role = req.getParameter("role");
		try (UserDao u = new UserDaoImpl()) {
			int count = u.insertUser(new User(0, firstName, lastName, email, password, birth, 0, role));
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration status</title>");
			out.println("</head>");
			out.println("<body>");
			if (count == 1) {
				out.println("<h4>User registration successful...!</h4>");
				out.println("<h4><a href='index.html'>login</a></h4>");
			} else {
				out.println("<h4>Registration failed...!</h4>");
				out.println("<h4><a href='newuser.html'>register again</a></h4>");
			}
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
}
