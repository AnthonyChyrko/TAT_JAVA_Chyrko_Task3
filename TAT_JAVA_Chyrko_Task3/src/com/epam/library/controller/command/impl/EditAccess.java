package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.User;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.UserParam;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class EditAccess implements Command {
	private final static Logger logger = Logger.getLogger(EditAccess.class);
	UtilController uc = new UtilController();	
	User user = User.getInstance();
	
	@Override
	public String execute(String request) {
		String[] param = request.split("&");
		String login = uc.recognizeParam(UserParam.LOGIN, param);	
		String access = uc.recognizeParam(UserParam.ACCESS, param);		
		String response = null;
		System.out.println(access);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {			
			clientService.editAccess(login, access);			
			response = "Access is changed!";
			logger.info(response);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
				
		return response;
	}
}
