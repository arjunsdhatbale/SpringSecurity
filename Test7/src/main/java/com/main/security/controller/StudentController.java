package com.main.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.student.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

	
	List<Student> students = new ArrayList<>(List.of(
			
			new Student(1,"Arjun",99),
			new Student(2,"Vikas",84)
			
			));
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students; 
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest	) {
		return (CsrfToken) httpServletRequest.getAttribute("_csrf");
	}
	
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {

		students.add(student);
		return student;
		
	}
}











