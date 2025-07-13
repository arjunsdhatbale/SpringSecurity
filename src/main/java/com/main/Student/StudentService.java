package com.main.Student;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Transactional
	public StudentMaster saveStudent(StudentMaster student) {
		try {
			StudentMaster studentMaster = this.studentRepo.save(student);
			return studentMaster;	
		}catch (Exception e) {
			throw new RuntimeException("Failed to save student: " + e.getMessage(), e);
		}
		
	}

	public List<StudentMaster> getAllStudent() {
		List<StudentMaster> list = this.studentRepo.findAll(); 
	
		return list;
	}
	
	
}
