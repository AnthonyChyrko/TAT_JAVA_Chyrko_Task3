package com.epam.library.service.factory;

import com.epam.library.service.ClientService;
import com.epam.library.service.LibraryService;
import com.epam.library.service.impl.ClientServiceImpl;
import com.epam.library.service.impl.LibraryServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final ClientService clientService = new ClientServiceImpl();
	private final LibraryService libraryService = new LibraryServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public ClientService getClientService(){
		return clientService;
	}
	
	public LibraryService getLibraryService(){
		return libraryService;
	}
}
