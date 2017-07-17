package com.epam.library.bean;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6171241758074046515L;

	private static User instance;
	
	private long userId;
	private String login;
	private String password;
	private String signIn;
	private String access;
//	private enum status {N,U,A,SA};
//	private String name;
//	private String surname;
	
	public User(){}
	
	public User(String login, String password){		
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password, String signIn, String access){		
		this.login = login;
		this.password = password;
		this.signIn = signIn;
		this.access = access;
	}	
	
	public User(long userId, String login, String password, String signIn, String access){		
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.signIn = signIn;
		this.access = access;
	}	
	
	public static User getInstance(){
		if(instance == null){
			instance = new User();
		}
		return instance;
	}	
	
	public void nullifyUser(){
		this.userId = 0;
		this.login = null;
		this.password = null;
		this.signIn = null;
		this.access = null;		
	}
	
	public User getUser(){
		return this;
	}

	public void setLogin(String login){
		this.login = login;
	}
	public void setPassword(String password){
		this.password = password;
	}	

	public void setSignIn(String signIn){
		this.signIn = signIn;
	}
	public void setAccess(String access){
		this.access = access;
	}
	
	public String getLogin(){
		return login;
	}	
	public String getPassword(){
		return password;
	}
	public String getSignIn(){
		return signIn;
	}
	public String getAccess(){
		return access;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public void setUser(long userId, String login, String password, String signIn, String access){	
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.signIn = signIn;
		this.access = access;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", login=" + login + ", password=" + password + ", signIn=" + signIn
				+ ", access=" + access + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((signIn == null) ? 0 : signIn.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (access == null) {
			if (other.access != null)
				return false;
		} else if (!access.equals(other.access))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (signIn == null) {
			if (other.signIn != null)
				return false;
		} else if (!signIn.equals(other.signIn))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}	

	
}
