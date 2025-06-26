public class Student {
	private String name;
	private String answersStudent;
	
	public Student(String name, String answersStudent) {
		this.name = name;
		this.answersStudent = answersStudent;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAnswersStudent() {
		return answersStudent;
	}
	public void setAnswersStudent(String answersStudent) {
		this.answersStudent = answersStudent;
	}
}