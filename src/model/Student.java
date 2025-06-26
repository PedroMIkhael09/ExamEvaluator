package model;

public class Student {
	private final String name;
	private final String answers;
	private int score;
	
	public Student(String name, String answers) {
		this.name = name;
		this.answers = answers;
		this.score = 0;
	}
	
	public void calculateScore(String answerKey) {
		if (answers.equals("VVVVVVVVVV") || answers.equals("FFFFFFFFFF")) {
			score = 0;
			return;
		}
		
		int hits = 0;
		for (int i = 0; i < 10; i++) {
			if (answers.charAt(i) == answerKey.charAt(i)) {
				hits++;
			}
		}
		score = hits;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
}
