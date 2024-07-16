package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    // Define @PostConstruct to load the student data...only once!
    @PostConstruct
    public void loadData(){
        theStudents= new ArrayList<>();
        theStudents.add(new Student("Abdallah", "Obaidat"));
        theStudents.add(new Student("Ali", "Obaidat"));
        theStudents.add(new Student("Ahmed", "Obaidat"));
        theStudents.add(new Student("Mohammed", "Obaidat"));
    }

    // Define the endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    // Define endpoint for "/students/{studentId}" - return student at index of the list
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // Check the steudntId against list size
        if(studentId>=theStudents.size() || studentId<0){
            throw new StudentNotFoundException("Student id not found - "+studentId);
        }

        return theStudents.get(studentId);
    }

    //Local exception handling for each rest controller
    // The best practice is to create a global exception handling check StudentRestExceptionHandler using @ControllerAdvice annotation

//    // Adding Exception handler to handle the return msg when an exception occurs
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
//        // Create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // Return ResponseEntity
//        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//    }
//
//    // Add another exception handler ... to catch any exception (catch all)
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
//        // Create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // Return ResponseEntity
//        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }


}
