package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.UserParam;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class SignOut implements Command {
	private final static Logger logger = Logger.getLogger(SignOut.class);
	UtilController uc = new UtilController();
	@Override
	public String execute(String request) {
		String[] param = request.split("&");
		String login = uc.recognizeParam(UserParam.LOGIN, param); 
		String response = null;
		logger.info(login);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {	
			clientService.signOut(login);
			response = "Good Bye!";
			logger.info(response);
		} catch (ServiceException e) {
			response = e.getMessage();
		}
				
		return response;
	}

}
