package com.epam.library.controller.session;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.epam.library.bean.User;
import com.epam.library.bean.User.Role;
import com.epam.library.controller.command.CommandName;

public class SessionStorage {
	
	private volatile Map<Integer, User> sessionUserMap = new ConcurrentHashMap<>();
	
	private SessionStorage(){}
	
	private static SessionStorage instance;
	
	public synchronized static SessionStorage getInstance() {
		if (null == instance) {
			instance = new SessionStorage();
		}
		return instance;
	}
	
	public User getUserFromSession(int threadHash){
		if(null == sessionUserMap.get(threadHash)){
			addUserToSessionUserMap(new User());
		}
		return sessionUserMap.get(threadHash);
	}
	
	public void addUserToSessionUserMap(User user){
		int threadHash = Thread.currentThread().hashCode();
		this.sessionUserMap.put(threadHash, user);
	}
	
	public boolean emptyCurrentSessionStorage() {
		boolean isSessionClosed = false;
		int threadHash = Thread.currentThread().hashCode();
		if (null != this.sessionUserMap.remove(threadHash)) {
			isSessionClosed = true;
		}
		return isSessionClosed;
	}
	
	public boolean checkPermission(CommandName commandName) {

//		Role role = commandName.getRole();
//		if (null != role) {
			int threadHash = Thread.currentThread().hashCode();
			User sessionUser = getUserFromSession(threadHash);
			System.out.println("HELLO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(sessionUser.toString()+"!!!!!!!!!!!!!!!!!!!!!!!");
			if (null != sessionUser) {
				addUserToSessionUserMap(sessionUser);
			}
//			return false;
//		}
		return true;
	}
	
}
