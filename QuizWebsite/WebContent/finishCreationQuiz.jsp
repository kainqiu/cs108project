<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="quizsite.DBConnection, quizsite.*, java.util.*, java.sql.*, java.text.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Home.jsp" method = "post">
<%
Quiz quiz = (Quiz) session.getAttribute("newQuiz");

String randomQuestionsCheckBoxValue = request.getParameter("randomQuestions");

if(randomQuestionsCheckBoxValue.equals("1")){
	quiz.setDisplayRandomTrue();
}

String pageNumbers = request.getParameter("pagenumbers");
if(pageNumbers.equals("1")){
	quiz.setDisplayOnePageTrue();
}
else{
	quiz.setDisplayMultiplePagesTrue();
}

String immediateCorrectionCheckBoxValue = request.getParameter("immediateCorrection");
if(immediateCorrectionCheckBoxValue.equals("1")){
	quiz.setImmediateCorrectionTrue();
}
else{
	quiz.setFinalCorrectionTrue();
}


DBConnection con = (DBConnection) session.getAttribute("connection");

String title = quiz.getQuizTitle();
String description = quiz.getQuizDescription();

User currUser = (User) session.getAttribute("user");

boolean random = quiz.isDisplayRandom();
boolean pages = quiz.isDisplayOnePageTrue();
boolean correction = quiz.isImmediateCorrectionTrue();


Quiz.registerQuiz(con, currUser, title, description, random, pages, correction);

String selectSQL = "SELECT id FROM quizzes ORDER BY createdAt DESC";

PreparedStatement preStmt;
try {
	preStmt = con.getConnection().prepareStatement(selectSQL);
	ResultSet rs = preStmt.executeQuery();
	if (rs.next()){
		quiz.setID(rs.getInt("id"));
	}				
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

%>

<h1> Congratulations on finishing creating your quiz! </h1>

<img src = "http://www.mucky-pups.co/wp-content/uploads/2013/05/9.jpg" alt = "happy dog">
<p> <input type="submit" value = "Return to Homepage" /></p>
</form>

</body>
</html>
