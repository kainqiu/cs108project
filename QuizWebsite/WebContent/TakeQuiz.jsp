<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="quizsite.DBConnection, quizsite.*, java.util.*, java.sql.*, java.text.*"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
DBConnection con = (DBConnection) session.getAttribute("connection");
int quizID = Integer.parseInt(request.getParameter("id"));
Quiz currQuiz = new Quiz();
if(con == null) {
	con = new DBConnection();
	session.setAttribute("connection", con);
}
try {
	String selectSQL = "SELECT type, question, answer, MCoptions FROM questions WHERE quizId = ? ORDER BY id";
	PreparedStatement preStmt = con.getConnection().prepareStatement(selectSQL);
	preStmt.setInt(1, quizID);
	ResultSet rs = preStmt.executeQuery();
	while(rs.next()) {
		Question newQn = null;
		int type = rs.getInt("type");
		String question = rs.getString("question");
		String answer = rs.getString("answer");
		String MC = rs.getString("MCoptions");
		switch(type){
		case 1: newQn = new QResponse(question, answer);
		break;
		case 2: newQn = new FillIn(question, answer);
		break;
		case 3: newQn =  new MultiChoice(question, answer);
		MC = request.getParameter("choices");
		((MultiChoice) newQn).addMCOptions(MC);
		case 4: newQn = new PictureResponse(question, answer);
		break;
		default: newQn = null;
		break;
		}
		if(!newQn.equals(null)) currQuiz.addQuestion(newQn);
	}
} catch (SQLException e) {
	e.printStackTrace();
} 
session.setAttribute("currQuiz", currQuiz);
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><%= request.getParameter("title") %></title>
</head>
<body>



</body>
</html>