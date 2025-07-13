package com.main.config;

import com.main.Enum.RoleMaster;

public class AuthResponse {

	private String token;
    private String type = "Bearer";
    private Long Id;
    private String userName;
    private String email;
    private RoleMaster role;
    
    
	/**
	 * 
	 */
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param token
	 * @param type
	 * @param id
	 * @param userName
	 * @param email
	 * @param role
	 */
	public AuthResponse(String token, String type, Long id, String userName, String email, RoleMaster role) {
		super();
		this.token = token;
		this.type = type;
		Id = id;
		this.userName = userName;
		this.email = email;
		this.role = role;
	}


	 


	public AuthResponse(String token2, Long id2, String userName2, String email2, String name) {
		super();
		this.token = token2;
		this.Id = id2;
		this.userName = userName2;
		this.email = email2;
		this.role = role;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	@Override
	public String toString() {
		return "AuthResponse [token=" + token + ", type=" + type + ", Id=" + Id + ", userName=" + userName + ", email="
				+ email + ", role=" + role + "]";
	}
    
    
}
