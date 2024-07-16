package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
			////////////CRUD Operations/////////////

			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudents(studentDAO);

		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count:  "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 5;
		System.out.println("Deleting student id: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// Retrieve student based on the id: Primary key
		int studentId = 1;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent = studentDAO.findById(1);

		// Apply modifications required to the object
		System.out.println("Updating student ...");
		myStudent.setFirstName("Saleem");

		// Update the student
		studentDAO.update(myStudent);

		// Display the updated student
		System.out.println("Updated student: "+ myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Obaidat");
		// Display list of students
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// Display list of students
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// Create a student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Basel", "Obaidat", "Basel@gmail.com");

		// Save the student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		int theId= tempStudent.getId();
		System.out.println("Saved student. Generated id: "+theId);

		// Retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: "+theId);
		Student myStudent = studentDAO.findById(theId);

		// Display student
		if(myStudent != null){
			System.out.println("Student found: "+myStudent);
		}else{
			System.out.println("No student found");
		}
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// Create multiple students
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("Mohammed", "Obaidat", "Mohammed@gmail.com");
		Student tempStudent2 = new Student("Ali", "Obaidat", "Ali@gmail.com");
		Student tempStudent3 = new Student("Ahmed", "Obaidat", "Abdallah.bus2001@gmail.com");

		// Save the students objects using DAO
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO){
		// Create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Abdallah", "Obaidat", "Abdallah.bus2001@gmail.com");

		// Save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		System.out.println("Saved student. Generated id: "+tempStudent.getId());
	}

}

/*
*	Notes:
*  	To update multiple records check lec 81
* 	To delete multiple records check lec 83
* */