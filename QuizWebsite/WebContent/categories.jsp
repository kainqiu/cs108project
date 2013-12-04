<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, quizsite.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category </title>

<%
Quiz quiz = (Quiz) session.getAttribute("newQuiz");

String title = request.getParameter("quiztitle");
String description = request.getParameter("quizdescription");

quiz.addQuizTitle(title);
quiz.addQuizDescription(description);
%>

</head>
<body>
<h1> Select the category of your quiz from the following dropdown: </h1>

<form action="optionsQuiz.jsp" method = "post">

<select name = "categories">
  <option value="1"> People </option>
  <option value="2"> Animals </option>
  <option value="3"> Things </option>
  <option value="4"> No Category </option>
</select>

<p> <input type = "submit"  value = "Choose options for your quiz!"  /> </p>

</form>


</body>
</html>