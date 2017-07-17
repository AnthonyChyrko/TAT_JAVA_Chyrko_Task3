package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.BookParam;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class BookAvailability implements Command {
	private final static Logger logger = Logger.getLogger(BookAvailability.class);
	UtilController uc = new UtilController();	
	
	@Override
	public String execute(String request) {
		String[] param = request.split("&");
		Long b_id = Long.parseLong(uc.recognizeParam(BookParam.BOOK_ID, param));	
		String availability = uc.recognizeParam(BookParam.AVAILABILITY, param);
		System.out.println(b_id);
		String response = null;		
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		
		try {			
			libraryService.bookAvailability(b_id, availability);			
			response = "Book availability is changed!";
			logger.info(response);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
				
		return response;
	}
}
