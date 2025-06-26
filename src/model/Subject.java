public class Subject {
	private String name;
	private ArrayList<Student> students;
	
	public Subject(String name) {
		this.name = name;
		this.students = new ArrayList<>;
		
	}
	
	public String getName() {
		return name;
	}
}