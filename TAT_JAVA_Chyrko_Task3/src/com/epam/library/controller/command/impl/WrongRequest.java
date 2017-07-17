package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;

public class WrongRequest implements Command {
	private final static Logger logger = Logger.getLogger(WrongRequest.class);

	@Override
	public String execute(String request) {
		logger.info("wrongRequest:(");
		return null;
	}

}
