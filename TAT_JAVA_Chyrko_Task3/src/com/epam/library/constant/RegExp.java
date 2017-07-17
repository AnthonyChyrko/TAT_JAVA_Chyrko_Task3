package com.epam.library.constant;

public class RegExp {
	public static final String LOGIN = "[A-Za-z0-9-]{1,15}";
	public static final String PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
}
