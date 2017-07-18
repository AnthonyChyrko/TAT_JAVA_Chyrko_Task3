package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.UserParam;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class BanUser implements Command {
	private final static Logger logger = Logger.getLogger(BanUser.class);
	UtilController uc = new UtilController();	
	
	@Override
	public String execute(String request) {
		String[] param = request.split("&");
		String login = uc.recognizeParam(UserParam.LOGIN, param);	
		String signIn = uc.recognizeParam(UserParam.SIGNIN, param);
		String response = null;		
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {			
			clientService.banUser(login, signIn);			
			response = "Profile is changed!";
//			logger.info(response);
		} catch (ServiceException e) {
			response = e.getMessage();
			logger.error(e.getMessage());
		}
				
		return response;
	}
}
