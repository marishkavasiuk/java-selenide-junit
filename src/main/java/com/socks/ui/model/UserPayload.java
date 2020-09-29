package com.socks.ui.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPayload extends Model {

	@JsonProperty("password")
	private String password;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

	public UserPayload(String password, String email, String username, String firstname, String lastname) {
		this.password = password;
		this.email = email;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public UserPayload(String password, String email, String username) {
		this.password = password;
		this.email = email;
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "UserPayload{" +
				"password='" + password + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}