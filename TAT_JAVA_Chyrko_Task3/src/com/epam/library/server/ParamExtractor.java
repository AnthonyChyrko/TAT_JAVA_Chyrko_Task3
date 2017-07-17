package com.epam.library.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamExtractor {
	private static final String PARAMS_PATTERN = "([A-z_]+=['A-z-\\d_$@~%&*()^!#\\s]+)|([A-z_]+)";

	private String paramStr;
	private String commandName;
	private String commandValue;

	public ParamExtractor(String paramStr) {
		super();
		this.setCommandStr(paramStr);
	}

	public void setCommandStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public boolean formCommand(){
		if (checkParamsExist() && checkParamsFormat()) {
			checkParamsFormat();
			extractParams();
			return true;
		}
		return false;
	}

	public boolean checkParamsExist() {
		if (null == paramStr || paramStr.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean checkParamsFormat() {
		Matcher matcher = null;
		matcher = Pattern.compile(PARAMS_PATTERN).matcher(paramStr);
		return matcher.matches();
	}

	public void extractParams() {
		char paramDelim = '=';
		try{
		this.commandName = paramStr.substring(0, paramStr.indexOf(paramDelim));
		this.commandValue = paramStr.substring(paramStr.indexOf(paramDelim) + 1, paramStr.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			commandName = paramStr;
		}
	}

	public String getCommandName() {
		return commandName;
	}

	public String getCommandValue() {
		return commandValue;
}
}
