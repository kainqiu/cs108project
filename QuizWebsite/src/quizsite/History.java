package quizsite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class History {

	private int userId;
	private int quizId;
	private int score;
	private Time elapsedTime;
	private java.sql.Timestamp finishAt;
	
	public History(int quizId, int score, Time elapsedTime, java.sql.Timestamp finishAt) {
		//this.userId = userId;
		this.quizId = quizId;
		this.score = score;
		this.elapsedTime = elapsedTime;
		this.finishAt = finishAt;
	}
	
	static public boolean createHistory(int userId, int quizId, int score, Time elapsedTime, java.util.Date finishAt, DBConnection dbCon) {
		try {
			PreparedStatement preStmt = dbCon.getConnection().prepareStatement("INSERT INTO histories (userId, quizId, score, elapsedTime, finishAt) VALUES (?, ?, ?, ?, ?)");
			preStmt.setInt(1, userId);
			preStmt.setInt(2, quizId);
			preStmt.setInt(3, score);
			preStmt.setTime(4, elapsedTime);
			Object timestamp = new java.sql.Timestamp(finishAt.getTime());
			preStmt.setObject(5, timestamp);
			preStmt.executeUpdate();
			System.out.println("in createHistory");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public String getElapsedTime() {
		return this.elapsedTime.toString();
	}
	
	public String getFinishAt() {
		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.finishAt);
		return datetime;
	}
	
}
