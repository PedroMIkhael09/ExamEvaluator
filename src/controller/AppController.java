package controller;

import model.Student;
import model.Subject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AppController {
	private static final String DATA_DIR = "data";
	private final Scanner keyboard = new Scanner(System.in);
	
	public void createMenu() {
		while (true) {
			System.out.println("\nSeja bem-vindo, professor!");
			System.out.println("1. Criar nova disciplina");
			System.out.println("2. Gerar resultado da disciplina");
			System.out.println("3. Sair");
			
			try {
				int option = keyboard.nextInt();
				keyboard.nextLine();
				
				switch (option) {
					case 1 -> createSubject();
					case 2 -> createResult();
					case 3 -> {
						System.out.println("Encerrando sistema.");
						return;
					}
					default -> System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Por favor, digite um número válido.");
				keyboard.nextLine();
			}
		}
	}
	
	private void createSubject() {
		try {
			System.out.print("Digite o nome da disciplina: ");
			String subjectName = keyboard.nextLine();
			
			if (subjectName.isBlank()) {
				System.out.println("Nome da disciplina não pode ser vazio.");
				return;
			}
			Subject subject = new Subject(subjectName);
			int qtd = askNumberOfStudents();
			
			
			Path subjectDir = Paths.get(DATA_DIR, subjectName);
			Files.createDirectories(subjectDir);
			
			File file = new File(subjectDir.toFile(), "student_answers.txt");
			
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
				for (int i = 0; i < qtd; i++) {
					System.out.print("Nome do aluno " + (i + 1) + ": ");
					String name = keyboard.nextLine();
					
					if (name.isBlank()) {
						System.out.println("Nome do aluno não pode ser vazio.");
						i--;
						continue;
					}
					
					String answers = "";
					while (!answers.matches("[VFvf]{10}")) {
						System.out.print("Respostas (10 letras V ou F): ");
						answers = keyboard.nextLine().toUpperCase();
						
						if (!answers.matches("[VF]{10}")) {
							System.out.println("Formato inválido. Use exatamente 10 caracteres (V ou F).");
						}
					}
					
					subject.addStudent(new Student(name, answers));
					writer.write(answers + "\t" + name);
					writer.newLine();
				}
			}
			
			System.out.println("Arquivo com respostas criado com sucesso em: " + file.getPath());
			
		} catch (IOException e) {
			System.out.println("Erro ao criar disciplina: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		}
	}
	
	private void createResult() {
		try {
			System.out.print("Digite o nome da disciplina: ");
			String subjectName = keyboard.nextLine();
			
			Path subjectDir = Paths.get(DATA_DIR, subjectName);
			if (!Files.exists(subjectDir)) {
				System.out.println("Disciplina não encontrada.");
				return;
			}
			
			File studentsFile = new File(subjectDir.toFile(), "student_answers.txt");
			File answerFile = new File(subjectDir.toFile(), "answer_key.txt");
			
			if (!studentsFile.exists()) {
				System.out.println("Arquivo de alunos não encontrado.");
				return;
			}
			
			Subject subject = new Subject(subjectName);
			subject.loadStudentsFromFile(studentsFile);
			
			if (!answerFile.exists()) {
				System.out.print("Gabarito não encontrado. Deseja criar agora? (S/N): ");
				String resposta = keyboard.nextLine();
				if (resposta.equalsIgnoreCase("S")) {
					createAnswerForExam(subjectName);
				} else {
					System.out.println("Operação cancelada.");
					return;
				}
			}
			
			String answerKey = readAnswerKey(answerFile);
			subject.setAnswerKey(answerKey);
			subject.applyAnswerKey();
			
			List<Student> studentsByName = subject.getStudents().stream()
					.sorted(Comparator.comparing(Student::getName))
					.toList();
			
			List<Student> studentsByScore = subject.getStudents().stream()
					.sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
					.toList();
			
			File resultByNameFile = new File(subjectDir.toFile(), "results_by_name.txt");
			File resultByScoreFile = new File(subjectDir.toFile(), "results_by_score.txt");
			
			saveResultsToFile(studentsByName, resultByNameFile);
			saveResultsToFileWithAverage(studentsByScore, resultByScoreFile, subject.getAverage());
			
			showResults(subject.getStudents(), subject.getAverage());
			
		} catch (IOException e) {
			System.out.println("Erro ao gerar resultado: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		}
	}
	
	private int askNumberOfStudents() {
		while (true) {
			try {
				System.out.print("Quantos alunos a disciplina possui? ");
				int qtd = keyboard.nextInt();
				keyboard.nextLine();
				
				if (qtd <= 0) {
					System.out.println("O número deve ser maior que zero.");
					continue;
				}
				
				return qtd;
			} catch (InputMismatchException e) {
				System.out.println("Por favor, digite um número válido.");
				keyboard.nextLine();
			}
		}
	}
	
	private void createAnswerForExam(String subjectName) {
		try {
			String gabarito = "";
			while (!gabarito.matches("[VFvf]{10}")) {
				System.out.print("Digite o gabarito (10 letras V ou F): ");
				gabarito = keyboard.nextLine().toUpperCase();
				
				if (!gabarito.matches("[VF]{10}")) {
					System.out.println("Formato inválido. Use exatamente 10 caracteres (V ou F).");
				}
			}
			
			Path subjectDir = Paths.get(DATA_DIR, subjectName);
			Files.createDirectories(subjectDir);
			
			File file = new File(subjectDir.toFile(), "answer_key.txt");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				writer.write(gabarito);
			}
			System.out.println("Gabarito criado em: " + file.getPath());
		} catch (IOException e) {
			System.out.println("Erro ao criar gabarito: " + e.getMessage());
		}
	}
	
	private String readAnswerKey(File file) throws IOException {
		try (Scanner sc = new Scanner(file)) {
			if (!sc.hasNextLine()) {
				throw new IOException("Arquivo de gabarito vazio");
			}
			return sc.nextLine().toUpperCase();
		}
	}
	
	private void showResults(List<Student> students, double average) {
		System.out.println("\n--- Alunos em ordem alfabética ---");
		students.stream()
				.sorted(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER))
				.forEach(s -> {
					System.out.println(s.getName());
					System.out.println("Nota: " + s.getScore());
					System.out.println();
				});
		
		System.out.println("\n--- Alunos por nota ---");
		students.stream()
				.sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
				.forEach(s -> {
					System.out.println(s.getName());
					System.out.println("Nota: " + s.getScore());
					System.out.println();
				});
		
		System.out.println("Média da turma: " + String.format("%.2f", average));
	}
	
	private void saveResultsToFile(List<Student> students, File file) throws IOException {
		List<Student> sortedStudents = students.stream()
				.sorted(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER))
				.toList();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (Student s : sortedStudents) {
				writer.write(s.getName());
				writer.newLine();
				writer.write("Nota: " + s.getScore());
				writer.newLine();
				writer.newLine();
			}
		}
	}
	
	private void saveResultsToFileWithAverage(List<Student> students, File file, double average) throws IOException {
		List<Student> sortedStudents = students.stream()
				.sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
				.toList();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (Student s : sortedStudents) {
				writer.write(s.getName());
				writer.newLine();
				writer.write("Nota: " + s.getScore());
				writer.newLine();
				writer.newLine();
			}
			writer.write("Média da turma: " + String.format("%.2f", average));
			writer.newLine();
		}
	}
}