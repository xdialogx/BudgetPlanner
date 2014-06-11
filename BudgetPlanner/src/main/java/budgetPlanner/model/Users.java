package budgetPlanner.model;

import java.io.Serializable;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String name;
	private String surname;
	private String password;
	private String userName;
	private String email;
	private String type;
	
	public Users() {
		this.name = null;
		this.surname = null;
		this.password = null;
		this.userName = null;
		this.email = null;
		this.type = "user";		
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public boolean setPassword(String password) {
		if (password.equals(null)) {
			return false;
		}
		this.password = password;
		return true;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean validatePassword(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
