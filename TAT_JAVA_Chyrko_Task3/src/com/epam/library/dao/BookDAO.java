package com.epam.library.dao;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;
import com.epam.library.dao.exception.DAOException;

public interface BookDAO {
	void addBook(Book book) throws DAOException;
	void deleteBook(long idBook) throws DAOException;	
	void showAllBooks() throws DAOException;
	void editBook(Book book) throws DAOException;
	void bookAvailability(long b_id, String availability) throws DAOException;
	void editOrderBooksList(OrderBooksList orderBooksList) throws DAOException;
	void addSubscription(OrderBooksList orderBooksList) throws DAOException;
	void removeSubscription(long userId, long bookId) throws DAOException;
}
