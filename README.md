# Student Evaluation System

This project was developed for the Object-Oriented Programming course. It is a Java-based system that manages students' multiple-choice test answers, automatically generating results and reports.

## Description

The application allows the user (professor) to create subjects, register students and their answers to a 10-question true/false exam, and then, based on an official answer key, generate reports showing each student's score and the class average.

All files are organized into folders by subject, and reports can be viewed directly in the console or saved as text files.

## Features

- Creation of subjects and storage of student answers.
- Support for registering any number of students per subject.
- Validation of answers (must be exactly 10 characters: only 'V' or 'F').
- Persistence of responses in a file.
- Generation of the official answer key.
- Calculation of each student's score.
- Automatic penalty if all answers are marked 'V' or all 'F'.
- Generation of two reports:
  - One sorted by student name.
  - One sorted by score, with the class average at the end.
- Display of results in the console.

## Project Structure

The project is organized as follows:

- `model/`
  - `Student.java`: represents a student, containing name, answers, and score.
  - `Subject.java`: represents a subject, containing students and the answer key.

- `controller/`
  - `AppController.java`: responsible for handling user interaction and coordinating the application.

- `Main.java`: entry point of the application.

During execution, a `data/` folder is created, with subfolders for each subject.

## How to Run

Swtich to develop branch to run the Main.java.

## Example File Structure

```
data/
└── Math/
    ├── student_answers.txt
    ├── answer_key.txt
    ├── results_by_name.txt
    └── results_by_score.txt
```

## Notes

This is an academic project developed for learning and practicing OOP concepts. The system can be expanded with new features, such as exporting in different formats or implementing a graphical user interface.
