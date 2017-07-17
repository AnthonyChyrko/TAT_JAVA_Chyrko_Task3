package com.epam.library.server;

import java.util.ArrayList;
import java.util.List;

import com.epam.library.controller.Controller;

public class RequestDispatcher implements Runnable {
	private String [] commands;

	private Controller controller;

	private static final String WRONG_ARG_FORMAT = "Requested operation failed due to wrong arguments format.";

	public List<String> getResponceList() {
		return responceList;
	}

	private List<String> responceList = new ArrayList<>();

	public RequestDispatcher(String [] commands) {
		super();
		this.commands = commands;
	}

	@Override
	public void run() {
		controller = new Controller();
		if (null != commands) {
			for (String command : commands) {
				String responce = controller.executeTask(command);
				responceList.add(responce);
			}
		} else {
			responceList.add(WRONG_ARG_FORMAT);
		}

	}
}
