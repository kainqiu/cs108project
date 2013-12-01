package quizsite;

import java.io.IOException;
import java.util.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DBConnection con = (DBConnection) session.getAttribute("connection");
		if(con == null) {
			con = new DBConnection();
			session.setAttribute("connection", con);
		}
		User currUser = (User) session.getAttribute("user");

		Time elapsedTime = new Time(20000);
		java.util.Date finishAt = new java.util.Date();
		History.createHistory(1, 1, 20, elapsedTime, new java.sql.Date(finishAt.getTime()), con);
		History.createHistory(3, 4, 40, elapsedTime, new java.sql.Date(finishAt.getTime()), con);
		History.createHistory(1, 2, 40, elapsedTime, new java.sql.Date(finishAt.getTime()), con);
	}

}
