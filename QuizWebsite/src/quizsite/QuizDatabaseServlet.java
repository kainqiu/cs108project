package quizsite;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuizDatabaseServlet
 */
@WebServlet("/QuizDatabaseServlet")
public class QuizDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizDatabaseServlet() {
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

		Quiz quiz = (Quiz) session.getAttribute("newQuiz");

		String title = request.getParameter("quiztitle");
		String description = request.getParameter("quizdescription");

		quiz.addQuizTitle(title);
		quiz.addQuizDescription(description);

		User currUser = (User) session.getAttribute("user");
		Quiz.registerQuiz(con, currUser, title, description);

//		String selectSQL = "SELECT id, description FROM quizzes";
//		PreparedStatement preStmt;
//		try {
//			preStmt = con.getConnection().prepareStatement(selectSQL);
//			ResultSet rs = preStmt.executeQuery();
//			while(rs.next()) {
//				if(rs.getString("description").equals(description)){
//					System.out.println("hi");
//					quiz.setID(rs.getInt("id"));
//					break;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String selectSQL = "SELECT id FROM quizzes ORDER BY createdAt DESC";
		PreparedStatement preStmt;
		try {
			preStmt = con.getConnection().prepareStatement(selectSQL);
			ResultSet rs = preStmt.executeQuery();
			if (rs.next()){
				quiz.setID(rs.getInt("id"));
			}
			System.out.println("hi");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatch = request.getRequestDispatcher("chooseQuestionType.jsp");
		dispatch.forward(request, response);	
	}
}
