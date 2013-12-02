package quizsite;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Quiz {

	//public static int count = 0;
	boolean display_random = false;
	boolean display_one_page = false;
	boolean display_multiple_pages = false;
	boolean display_immediate_correction = false;
	boolean display_final_correction= false;

	private String title;
	private String description;
	private int quizID;

	private static java.sql.Timestamp param;
	Set<Question> questions = new HashSet<Question>();

	DBConnection dbCon;

	public void setID(int id){
		quizID = id;
	}

	public int getID(){
		return quizID;
	}

	public static boolean registerQuiz(DBConnection dbCon, User currentUser, String title, String description) {

		Date createdAt = new Date();
		param = new java.sql.Timestamp(createdAt.getTime());
		
		int timeTaken = 0;
		int userID = currentUser.getId();
		//String key = currentUser.getUsername() + Integer.toString(qzID);
		//System.out.println("userID: "  + userID + " timeCreated: " + param +  " title: " + title + " description: " + description);

		try {
			PreparedStatement preStmt = dbCon.getConnection().prepareStatement("INSERT INTO quizzes(creatorId, createdAt, title, description, timesTaken) VALUES (?, ?, ?, ?, ?)");
			preStmt.setInt(1, userID);
			preStmt.setTimestamp(2, param);
			preStmt.setString(3, title);
			preStmt.setString(4, description);
			preStmt.setInt(5, timeTaken);
			preStmt.executeUpdate();
			//System.out.println("in registerQuiz");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	
	public void addQuizTitle(String str){
		title = str;
	}

	public String getQuizTitle(){
		return title;
	}

	public void addQuizDescription(String str){
		description = str;
	}

	public String getQuizDescription(){
		return description;
	}

	public void addQuestion(Question ques){
		questions.add(ques);
	}

	public Set<Question> setOfQuestions(){
		return questions;
	}

	public void setDisplayRandomTrue(){
		display_random = true;
	}
	public boolean isDisplayRandom(){
		return display_random;
	}

	public void setDisplayOnePageTrue(){
		display_one_page = true;
	}
	public boolean isDisplayOnePageTrue(){
		return display_one_page;
	}

	public void setDisplayMultiplePagesTrue(){
		display_multiple_pages = true;
	}

	public boolean isDisplayMultiplePagesTrue(){
		return display_multiple_pages;
	}
	public void setImmediateCorrectionTrue(){
		display_immediate_correction = true;
	}
	public boolean isImmediateCorrectionTrue(){
		return display_immediate_correction;
	}

	public void setFinalCorrectionTrue(){
		display_final_correction = true;
	}
	public boolean isFinalCorrectionTrue(){
		return display_final_correction;
	}
	
	static public String getTitleById(int id, DBConnection dbCon) {
		try {
			String selectSQL = "SELECT title FROM quizzes WHERE id = ?";
			PreparedStatement preStmt = dbCon.getConnection().prepareStatement(selectSQL);
			preStmt.setInt(1, id);
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()) {
				return rs.getString("title");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		return null;
	}

	// time and score

}