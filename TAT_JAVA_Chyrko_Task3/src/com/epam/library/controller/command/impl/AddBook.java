package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.Book;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.util.UtilController;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class AddBook implements Command {
	private final static Logger logger = Logger.getLogger(AddBook.class);
	UtilController uc = new UtilController();
	Book book;
	@Override
	public String execute(String request) {
		String[] param = request.split("&");		
		book = uc.prepareBook(param);
		String response = null;
		logger.info(book.toString());		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		try {
			libraryService.addNewBook(book);
			response = "New Book added!";
			logger.info(response);
		} catch (ServiceException e) {
			logger.error(e.getMessage());			
		}
				
		return response;
	}

}
