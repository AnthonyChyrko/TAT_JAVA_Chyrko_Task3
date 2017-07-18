package com.epam.library.controller.command;

import com.epam.library.bean.User.Role;

public enum CommandName {
	SIGN_IN(null),
	SIGN_OUT(null),
	REGISTRATION(null),
	ADD_BOOK(Role.ADMIN),
	EDIT_BOOK(Role.ADMIN),	
	SHOW_ALL_BOOKS(null),	
	EDIT_LOGIN(Role.USER),
	EDIT_PASSWORD(Role.USER),
	EDIT_ACCESS(Role.ADMIN),
	BAN_USER(Role.ADMIN),
	BOOK_AVAILABILITY(Role.ADMIN),
	EDIT_ORDER_BOOKS_LIST(Role.USER),	
	ADD_SUBSCRIPTION(Role.ADMIN),
	REMOVE_SUBSCRIPTION(Role.ADMIN),	
	WRONG_REQUEST(null);
	
	private Role role;
	
	public Role getRole(){
		return this.role;
	}
	
	private CommandName(Role role){
		this.role = role;
	}
	
}
