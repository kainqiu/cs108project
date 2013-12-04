<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="quizsite.*, quizsite.DBConnection, quizsite.History, quizsite.User, quizsite.Mail, java.util.*, java.sql.ResultSet, java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/QuizWebsite/Home.css" type="text/css">

<title>Quizzes</title>
</head>
<%
DBConnection con = (DBConnection) session.getAttribute("connection");
if(con == null) {
	con = new DBConnection();
	session.setAttribute("connection", con);
}

%>
<h1> Below is a list of all the quizzes with their categories</h1>



<div class="lists">
<div class="pop_quiz list_block">
<p class="block_title">Category: People</p>
<%
String people = "People";
ArrayList<Rank.QuizInfo> peopleCategory = Rank.getSameCategory(con, people);

for(int i = 0; i < peopleCategory.size(); i++) {
	out.println("<p class='each_quiz'><a href='QSummary.jsp?id=" + peopleCategory.get(i).id + "'>" + peopleCategory.get(i).title + "</a></p>");
}
%>
</div>
<div class="recent_created_quiz list_block">
<p class="block_title">Category: Animals</p>
<%
String animal = "Animals";
ArrayList<Rank.QuizInfo> animalsCategory = Rank.getSameCategory(con, animal);

for(int i = 0; i < animalsCategory.size(); i++) {
	out.println("<p class='each_quiz'><a href='QSummary.jsp?id=" + animalsCategory.get(i).id + "'>" + animalsCategory.get(i).title + "</a></p>");
}
%>
</div>
<div class="taken_quiz_activity list_block">
<p class="block_title">Category: Things</p>
<%
String things = "Things";
ArrayList<Rank.QuizInfo> thingsCategory = Rank.getSameCategory(con, animal);

for(int i = 0; i < thingsCategory.size(); i++) {
	out.println("<p class='each_quiz'><a href='QSummary.jsp?id=" + thingsCategory.get(i).id + "'>" + thingsCategory.get(i).title + "</a></p>");
}
%>

</div>
<div class="recent_self_created_quiz list_block">
<p class="block_title">No Category</p>
<%
String nothing = "No Category";
ArrayList<Rank.QuizInfo> noCategory = Rank.getSameCategory(con, nothing);

for(int i = 0; i < noCategory.size(); i++) {
	out.println("<p class='each_quiz'><a href='QSummary.jsp?id=" + noCategory.get(i).id + "'>" + noCategory.get(i).title + "</a></p>");
}
%>
</div>
</div>


<form action="Home.jsp" method = "post">
<p> <input type="submit" value = "Return to Homepage" /></p>
</form>


<body>

</body>
</html>