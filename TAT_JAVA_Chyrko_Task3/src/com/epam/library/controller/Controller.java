package com.epam.library.controller;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.constant.ControllerConstant;
import com.epam.library.controller.util.UserParam;
import com.epam.library.controller.util.UtilController;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.util.ServiceTool;

public class Controller implements LifeCircle {
	private final static Logger logger = Logger.getLogger(Controller.class);
	UtilController uc = new UtilController();
	private final CommandProvider provider = new CommandProvider();	
	
	public String executeTask(String request){
		String commandName;
		Command executionCommand;		
		commandName = uc.recognizeParam(UserParam.COMMAND, request.split(ControllerConstant.DELIMITER));
		executionCommand = provider.getCommand(commandName);		
		String response;	
		
		response = executionCommand.execute(request);
		
		logger.info(response);
		return response;
	}

	@Override
	public String init() {
		try {
			ServiceTool.init();
		} catch (ServiceException e) {
			return ControllerConstant.UNSUCCESSFUL_INIT;
		}
		return ControllerConstant.START_MESSAGE;
	}

	@Override
	public String destroy() {
		try{
			ServiceTool.destroy();
		} catch (ServiceException e) {
			return ControllerConstant.UNSUCCESSFUL_DESTROY;
		}
	return ControllerConstant.SUCCESSFUL_DESTROY;
	}
}
