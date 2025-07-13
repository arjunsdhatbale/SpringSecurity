package com.main.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "student_master")
public class StudentMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	@Version
	private Long version; 
	
	@NotNull(message = "Student name should not be null")
	@NotBlank(message = "Student name should not be Blank")
	@Column(name = "student_name")
	private String name; 
	
	@Column(name = "marks")
	private int marks;
	public StudentMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param version
	 * @param name
	 * @param marks
	 */
	public StudentMaster(Long id, Long version,
			@NotNull(message = "Student name should not be null") @NotBlank(message = "Student name should not be Blank") String name,
			int marks) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.marks = marks;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "StudentMaster [id=" + id + ", version=" + version + ", name=" + name + ", marks=" + marks + "]";
	}
	 
}
