package quizsite;

import java.sql.Time;

public class RecordInfo {

	public int userId;
	public int score;
	public Time elapsedTime;
	public java.sql.Timestamp finishAt;
	
	public RecordInfo(int userId, int score, Time elapsedTime, java.sql.Timestamp finishAt) {
		this.userId = userId;
		this.score = score;
		this.elapsedTime = elapsedTime;
		this.finishAt = finishAt;
	}
}
