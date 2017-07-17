package com.epam.library.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandName;
import com.epam.library.controller.command.impl.AddBook;
import com.epam.library.controller.command.impl.AddSubscription;
import com.epam.library.controller.command.impl.BanUser;
import com.epam.library.controller.command.impl.BookAvailability;
import com.epam.library.controller.command.impl.EditAccess;
import com.epam.library.controller.command.impl.EditBook;
import com.epam.library.controller.command.impl.EditLogin;
import com.epam.library.controller.command.impl.EditOrderBooksList;
import com.epam.library.controller.command.impl.EditPassword;
import com.epam.library.controller.command.impl.Register;
import com.epam.library.controller.command.impl.RemoveSubscription;
import com.epam.library.controller.command.impl.ShowAllBooks;
import com.epam.library.controller.command.impl.SignIn;
import com.epam.library.controller.command.impl.SignOut;
import com.epam.library.controller.command.impl.WrongRequest;

public class CommandProvider {
	private final static Logger logger = Logger.getLogger(CommandProvider.class);
	private final Map<CommandName, Command> repository = new HashMap<>();
	
	CommandProvider() {
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.SIGN_OUT, new SignOut());
		repository.put(CommandName.REGISTRATION, new Register());
		repository.put(CommandName.ADD_BOOK, new AddBook());
		repository.put(CommandName.EDIT_BOOK, new EditBook());
		repository.put(CommandName.SHOW_ALL_BOOKS, new ShowAllBooks());
		repository.put(CommandName.BOOK_AVAILABILITY, new BookAvailability());
		
		repository.put(CommandName.ADD_SUBSCRIPTION, new AddSubscription());
		repository.put(CommandName.REMOVE_SUBSCRIPTION, new RemoveSubscription());
		
		
		repository.put(CommandName.EDIT_ORDER_BOOKS_LIST, new EditOrderBooksList());
		repository.put(CommandName.EDIT_LOGIN, new EditLogin());
		repository.put(CommandName.EDIT_PASSWORD, new EditPassword());
		repository.put(CommandName.EDIT_ACCESS, new EditAccess());
		repository.put(CommandName.BAN_USER, new BanUser());		
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
	}
	
	Command getCommand(String name){
		CommandName commandName = null;
		Command command = null;
		try{
			commandName = CommandName.valueOf(name.toUpperCase());			
			command = repository.get(commandName);
			logger.info(""+commandName);
		}catch (IllegalArgumentException | NullPointerException e) {
			
			logger.info(""+commandName);
			command = repository.get(CommandName.WRONG_REQUEST);			
		}
		
		return command;		
	}
}
