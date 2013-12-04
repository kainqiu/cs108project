<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz tags</title>
</head>
<body>
<h1>Attach </h1>
<h3>If you have multiple correct answers, please type each answer on a new line.</h3>

<form action="QServlet" method="post">
<p>Question:<textarea rows = "1" cols = "60" name="question"></textarea> </p> 
<p>Answer(s): <br> <textarea rows = "4" cols = "50" name="answer"></textarea> </p>
<input name = "type" type = "hidden" value = "1"/>
<input type="submit" name="action" value="Create & Continue"/>
<input type="submit" name="action" value="Create & Finish Quiz"/>
</form>


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