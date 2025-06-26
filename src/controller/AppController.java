import java.util.Scanner;


public class AppController {
	Scanner keyboard = new Scanner(System.in)
	FileWriter = fr;
	
	public void createMenu(){
	
		
		
		
	}
	
	public int catchStudent(){
		System.out.println("Quantos alunos tem na sua disciplina?");
		int qtdStudents = keyboard.nextInt();
		return qtdStudents;
	}
	
	public void createSubject(){
		System.out.println("Ola, professor!\nQual o nome da sua disciplina:");
		String nameDiscipline = keyboard.nextLine();
		fr = new FileWriter(nameDiscipline + ".txt", true);
	}
	
	public void putStudentsinSubject()
	
}