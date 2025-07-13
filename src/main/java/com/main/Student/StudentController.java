package com.main.Student;

  
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.Exception.StudentNotFoundException;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService; 
	

	@PostMapping("/add")
	public ResponseEntity<StudentMaster> addStudent(@RequestBody StudentMaster student) {
		logger.info("Request receive to Add Student.");
		try {
			StudentMaster student_Master = this.studentService.saveStudent(student); 
			return new ResponseEntity<StudentMaster>(student_Master,HttpStatus.ACCEPTED);	
		}catch (Exception e) {
			logger.error("Error adding studetn : " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<StudentMaster>> getAllStudent(){
		logger.info("Request receive to fetch all Student.");
		try {
			List<StudentMaster> list = this.studentService.getAllStudent(); 
			 
			return new ResponseEntity<List<StudentMaster>>(list,HttpStatus.OK);
		}catch (StudentNotFoundException e) {
			logger.error("No student found: ", e);
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}catch (Exception ex) {
			logger.error("Error while retrieving student :" ,ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	private List<Student> students = new ArrayList(List.of(
//	new Student(1,"Arjun",54),
//	new Student(2,"Vishla",949)
//	));

//@GetMapping
//public List<Student>  getStudents (){
//
//return students; 
//}

//@GetMapping("/csrf-token")
//public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest){
//return (CsrfToken)httpServletRequest.getAttribute("_csrf");
//}

	
	
}
