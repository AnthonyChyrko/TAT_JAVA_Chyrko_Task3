package com.epam.library.service.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;
import com.epam.library.bean.User;
import com.epam.library.controller.session.SessionStorage;
import com.epam.library.controller.util.OrderBooksListParam;
import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService {
	private final static Logger logger = Logger.getLogger(LibraryServiceImpl.class);
	private SessionStorage session = SessionStorage.getInstance();

	@Override
	public void addNewBook(Book book) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(user.getLogin() == null || user.getLogin().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()
						|| user.getAccess() == null || user.getAccess().isEmpty()
							|| user.getSignIn() == null || user.getSignIn().isEmpty()){
					throw new ServiceException("You must be registered or SignIn!");
				}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.addBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());			
		}

	}

	@Override
	public void showAllBooks() throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.showAllBooks();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void editBook(Book book) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(user.getLogin() == null || user.getLogin().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()
						|| user.getAccess() == null || user.getAccess().isEmpty()
							|| user.getSignIn() == null || user.getSignIn().isEmpty()){
					throw new ServiceException("You must be registered or SignIn!");
				}
		
		if(!"A".equals(user.getAccess()) && !"SA".equals(user.getAccess())){
			throw new ServiceException("You do not have access rights!");
		}
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.editBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		
	}

	@Override
	public void bookAvailability(long b_id, String availability) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			throw new ServiceException("You have no permissions to change book availability!");
		}
		if(!user.getSignIn().equals("IN")){
			throw new ServiceException("You may signIn or registrate!");
		}
		if(!"Y".equals(availability)&&!"N".equals(availability)){
			logger.warn("Wrong availability");
			throw new ServiceException("Wrong availability");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.bookAvailability(b_id, availability);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void editOrderBooksList(OrderBooksList orderBooksList) throws ServiceException {
		if(!orderBooksList.getActionCommand().toUpperCase().equals(OrderBooksListParam.ADD_BOOK.toString()) 
				&& !orderBooksList.getActionCommand().toUpperCase().equals(OrderBooksListParam.REMOVE_BOOK.toString())){
			logger.warn("Such command do not exists!");
			throw new ServiceException("Such command do not exists!");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();			
			bookDAO.editOrderBooksList(orderBooksList);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void addSubscription(OrderBooksList orderBooksList) throws ServiceException {	
		User user = session.getUserFromSession(Thread.currentThread().hashCode());		
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			throw new ServiceException("You have no permissions to change book availability!");
		}
		if(!user.getSignIn().equals("IN")){
			throw new ServiceException("You may signIn or registrate!");
		}		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();			
			bookDAO.addSubscription(orderBooksList);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		
	}

	@Override
	public void removeSubscription(long userId, long bookId) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			throw new ServiceException("You have no permissions to change book availability!");
		}
		if(!user.getSignIn().equals("IN")){
			throw new ServiceException("You may signIn or registrate!");
		}		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();			
			bookDAO.removeSubscription(userId, bookId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		
	}

}
