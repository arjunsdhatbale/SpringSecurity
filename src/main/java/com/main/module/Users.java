package com.main.module;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.main.Enum.RoleMaster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Id;

	@Version
	private Long version;

	@NotBlank(message = "User name is required")
	@Size(min =  3,max =  15, message = "User name must be between 3 and 15 Charactor")
	@Column(name = "user_name", nullable = false, unique = true)
	String userName;
	
	@NotBlank(message = "Password is required")
	@Size(min = 4, message =  "Password must be atleast 4 character.")
	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@NotBlank(message = "Eamil is required.")
	@Email(message = "Email should be valid")
	@Column(name = "email",unique =  true,nullable =  false)
	private String email; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role",nullable = false)
	private RoleMaster role; 
	
	@Column(name = "is_active", nullable =  false)
	private Boolean isActive = true;
	
	@CreationTimestamp
	private LocalDateTime createdAt; 
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	 

	/**
	 * @param id
	 * @param version
	 * @param userName
	 * @param password
	 * @param email
	 * @param role
	 */
	public Users(Long id, Long version,
			@NotBlank(message = "User name is required") @Size(min = 3, max = 15, message = "User name must be between 3 and 15 Charactor") String userName,
			@NotBlank(message = "Password is required") @Size(min = 4, message = "Password must be atleast 4 character.") String password,
			@NotBlank(message = "Eamil is required.") @Email(message = "Email should be valid") String email,
			RoleMaster role) {
		super();
		Id = id;
		this.version = version;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}



	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public Long getVersion() {
		return version;
	}



	public void setVersion(Long version) {
		this.version = version;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public RoleMaster getRole() {
		return role;
	}



	public void setRole(RoleMaster role) {
		this.role = role;
	}



	public Boolean getIsActive() {
		return isActive;
	}



	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	@Override
	public String toString() {
		return "Users [Id=" + Id + ", version=" + version + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + ", role=" + role + ", isActive=" + isActive + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

 
	 
}
