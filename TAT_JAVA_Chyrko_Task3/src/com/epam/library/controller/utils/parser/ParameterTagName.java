package com.epam.library.controller.utils.parser;

public enum ParameterTagName {
	COMMANDS("commands"),
	COMMAND("command"),
	TITLE("title"),
	AUTHOR("author"),
	GENRE("genre"),
	YEAR("year"),
	QUANTITY("quantity"),
	AVAILABILITY("availability"), 
	ACTION_COMMAND("action_command"), 
	ADD_BOOK("add_book"), 
	REMOVE_BOOK("remove_book"),
	LOGIN("login"),
	PASSWORD("password"),
	ACCESS("access"),
	SIGN_IN("sign_in"),
	BOOK_ID("book_id"),
	USER_ID("user_id");
	
	private String tagName;
	
	private ParameterTagName(String tagName){
		this.tagName = tagName;
	}
	
	public String getTagName(String tagName){
		return this.tagName;
	}
	
}
