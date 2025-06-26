package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subject {
	private final String name;
	private final List<Student> students;
	private String answerKey;
	
	public Subject(String name) {
		this.name = name;
		this.students = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void addStudent(Student s) {
		students.add(s);
	}
	
	public void setAnswerKey(String answerKey) {
		this.answerKey = answerKey;
	}
	
	public void applyAnswerKey() {
		for (Student s : students) {
			s.calculateScore(answerKey);
		}
	}
	
	public double getAverage() {
		return students.stream().mapToInt(Student::getScore).average().orElse(0.0);
	}
	
	public void loadStudentsFromFile(File file) throws IOException {
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String[] parts = scanner.nextLine().split("\t");
				if (parts.length == 2) {
					Student s = new Student(parts[1], parts[0].toUpperCase());
					students.add(s);
				}
			}
		}
	}
	
	
	
}
