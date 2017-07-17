package com.epam.library.controller;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.controller.utils.UserParam;

public class Controller {
	private final static Logger logger = Logger.getLogger(Controller.class);
	UtilController uc = new UtilController();
	private final CommandProvider provider = new CommandProvider();
	
	private final String paramDelimeter = "&";
	
	public String executeTask(String request){
		String commandName;
		Command executionCommand;		
		commandName = uc.recognizeParam(UserParam.COMMAND, request.split(paramDelimeter));
		executionCommand = provider.getCommand(commandName);		
		String response;	
		
		response = executionCommand.execute(request);
		
		logger.info(response);
		return response;
	}
}
