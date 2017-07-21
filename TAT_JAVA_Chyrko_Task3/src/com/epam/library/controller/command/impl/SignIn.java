package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.session.SessionStorage;
import com.epam.library.controller.util.UserParam;
import com.epam.library.controller.util.UtilController;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class SignIn implements Command {
	SessionStorage session = SessionStorage.getInstance();
	private final static Logger logger = Logger.getLogger(SignIn.class);
	UtilController uc = new UtilController();
	@Override
	public String execute(String request) {
		String[] param = request.split("&");
		String login = uc.recognizeParam(UserParam.LOGIN, param);
		String password = uc.recognizeParam(UserParam.PASSWORD, param);
		String response = null;
		logger.info(login + " - " + password);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			clientService.signIn(login, password);
			response = "Welcom!";
			logger.info(response+" "+login);
			System.out.println(session.getUserFromSession(Thread.currentThread().hashCode()).toString() + " FROM SIGN_IN_BEFORE_CONTROLLERR");
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			response = e.getMessage();
		}
				
		return response;
	}

}
