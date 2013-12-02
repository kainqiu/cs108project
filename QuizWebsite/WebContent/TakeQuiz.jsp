<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="quizsite.DBConnection, quizsite.*, java.util.*, java.sql.*, java.text.*"%>

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
	String selectSQL = "SELECT type, question, answer, MCoption FROM questions WHERE quizId = ? ORDER BY id";
	PreparedStatement preStmt = con.getConnection().prepareStatement(selectSQL);
	preStmt.setInt(1, quizID);
	ResultSet rs = preStmt.executeQuery();
	while(rs.next()) {
		Question newQn = null;
		int type = rs.getInt("type");
		String question = rs.getString("question");
		String answer = rs.getString("answer");
		String MC = rs.getString("MCoption");
		switch(type){
		case 1: newQn = new QResponse(question, answer);
		newQn.printString();
		break;
		case 2: newQn = new FillIn(question, answer);
		newQn.printString();
		break;
		case 3: newQn =  new MultiChoice(question, answer);
		newQn.printString();
		((MultiChoice) newQn).addMCOptions(MC);
		break;
		case 4: newQn = new PictureResponse(question, answer);
		newQn.printString();
		break;
		default: newQn = null;
		break;
		}
		if(!newQn.equals(null)){
			newQn.setType(type);
			currQuiz.addQuestion(newQn);
			newQn.printString();
		}
	}
} catch (SQLException e) {
	e.printStackTrace();
} 

ArrayList<Question> questions = currQuiz.setOfQuestions();
session.setAttribute("currQuiz", currQuiz);
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Take Quiz: <%= request.getParameter("title") %></title>
</head>
<body>
<div class="quiz">
<h1><%= request.getParameter("title") %></h1>

<form action="" method="post">
<ol>
	<%
for(int i=0; i<questions.size(); i++){
	out.print("<li>");
	Question curr = questions.get(i);
	int currType = curr.getType();
	switch(currType){
	case 1: //Question-Response
	case 2: //Fill-in
		out.print(curr.getQuestion() + "<br>");
		out.print("<input type = \"text\" name=\" " + i + " \" />");
	break;
	case 3: //Multiple Choice
		out.print(curr.getQuestion() + "<br>");
		ArrayList<String> options = ((MultiChoice) curr).getMCOptions();
		options.add(curr.getAnswer());
		Collections.shuffle(options);
		for(int j = 0; j<options.size(); j++){
			out.print("<input type = \"radio\" name = \"" + i + "\" value = \"" + options.get(j) + "\">" + options.get(j) + "<br>");
		}
	break;
	case 4: //Picture-Response
		out.print("<img src= \"" + curr.getQuestion() + " \" /> <br>");
		out.print("<input type = \"text\" name=\" " + i + " \" />");
		break;
	default: out.print("ERROR");
	break;
	}
	out.print("</li>");
	
}
	
		
	%>
</ol>

<input type="submit" value="Submit">
</form>

</div>



</body>
</html>