package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class ShowAllBooks implements Command {
	private final static Logger logger = Logger.getLogger(ShowAllBooks.class);

	@Override
	public String execute(String request) {
		String response;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		try {
			libraryService.showAllBooks();
			response = "New USER added!";
			logger.info(response);
		} catch (ServiceException e) {
			logger.error(e.getMessage());	
		}
		return null;	
	}

}
