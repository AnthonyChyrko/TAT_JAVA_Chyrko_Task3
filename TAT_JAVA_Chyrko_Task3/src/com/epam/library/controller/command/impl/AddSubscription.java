package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class AddSubscription implements Command {
	private final static Logger logger = Logger.getLogger(AddSubscription.class);
	UtilController uc = new UtilController();
	OrderBooksList orderBooksList = OrderBooksList.getInstance();
	Book book;
	@Override
	public String execute(String request) {
		String[] param = request.split("&");	
		orderBooksList = uc.prepareOrderBooksList(param);
		
		String response = null;
		System.out.println(orderBooksList.toString());
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		try {			
			libraryService.addSubscription(orderBooksList);
			response = "OrderBooksList is changed!";
			logger.info(response);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}				
		return response;
	}

}
