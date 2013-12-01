package quizsite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Quiz {

	//        private int question_response;
	//        private int multiple_choice;
	//        private int picture_response;
	//        private int fill_inblank;

	public static int count = 0;

	boolean display_random = false;
	boolean display_one_page = false;
	boolean display_multiple_pages = false;
	boolean display_immediate_correction = false;
	boolean display_final_correction= false;

	private String title;
	private String description;
	private int quizID;

	Set<Question> questions = new HashSet<Question>();

	DBConnection dbCon;

	public void setID(){
		quizID = Quiz.count;
		Quiz.count++;
	}

	public int getID(){
		return quizID;
	}

	static public boolean registerQuiz(int qzID, DBConnection dbCon, User currentUser) {

		java.util.Date createdAt = new Date();
		Object param = new java.sql.Timestamp(createdAt.getTime());
		//java.sql.Datetime time = new java.sql.Date(createdAt.getTime());
		
		int userID = currentUser.getId();
		String key = currentUser.getUsername() + Integer.toString(qzID);
		System.out.println("quizID: " + qzID + "userID: "  + userID + "timeCreated: " + param + "key: " + key);

		try {
			PreparedStatement preStmt = dbCon.getConnection().prepareStatement("INSERT INTO quizzes(id, creatorId, createdAt, idKey) VALUES (?, ?, ?, ?)");
			preStmt.setInt(1, qzID);
			preStmt.setInt(2, userID);
			preStmt.setObject(3, param);
			preStmt.setString(4, key);
			preStmt.executeUpdate();
			System.out.println("in registerQuiz");
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

	// time and score

}