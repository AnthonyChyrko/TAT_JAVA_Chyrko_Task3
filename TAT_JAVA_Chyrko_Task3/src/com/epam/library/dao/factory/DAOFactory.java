package com.epam.library.dao.factory;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.UserDAO;
import com.epam.library.dao.impl.SQLBookDAO;
import com.epam.library.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final BookDAO sqlBookImpl = new SQLBookDAO();
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	
	private DAOFactory(){}
	
	public synchronized static DAOFactory getInstance(){
		return instance;
	}
	
	public BookDAO getBookDAO(){
		return sqlBookImpl;
	}
	
	public UserDAO getUserDAO(){
		return sqlUserImpl;
	}
}
