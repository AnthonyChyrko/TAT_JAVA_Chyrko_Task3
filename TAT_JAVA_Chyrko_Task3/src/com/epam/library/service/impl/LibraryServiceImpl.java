package com.epam.library.service.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;
import com.epam.library.controller.utils.OrderBooksListParam;
import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService {
	private final static Logger logger = Logger.getLogger(LibraryServiceImpl.class);

	@Override
	public void addNewBook(Book book) throws ServiceException {
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
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();			
			bookDAO.removeSubscription(userId, bookId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		
	}

}
