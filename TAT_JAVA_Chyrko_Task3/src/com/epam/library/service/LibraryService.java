package com.epam.library.service;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;
import com.epam.library.service.exception.ServiceException;

public interface LibraryService {
	void addNewBook(Book book) throws ServiceException;	
	void showAllBooks()throws ServiceException;
	void editBook(Book book) throws ServiceException;
	void bookAvailability(long b_id, String availability) throws ServiceException;
	void editOrderBooksList(OrderBooksList orderBooksList) throws ServiceException;
	void addSubscription(OrderBooksList orderBooksList) throws ServiceException;
	void removeSubscription(long userId, long bookId) throws ServiceException;
}
