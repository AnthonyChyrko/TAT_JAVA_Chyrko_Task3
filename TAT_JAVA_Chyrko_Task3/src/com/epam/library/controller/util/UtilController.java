package com.epam.library.controller.util;

import org.apache.log4j.Logger;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;

public class UtilController {	
	private final static Logger logger = Logger.getLogger(UtilController.class);
	private OrderBooksList orderBooksList = OrderBooksList.getInstance();
	
	public String recognizeParam(Enum<?> comName, String[] param){	
		
		for (int i = 0; i < param.length; i++) {
			String[] params = param[i].split("=");	
			if(comName.toString().equals(params[0].toUpperCase())){
				try {
					return params[1];
				} catch (ArrayIndexOutOfBoundsException e) {
					logger.error("No such parameter!");
					return null;
				}
				
			}
		}		
		return null;	
	}		

	public Book prepareBook(String[] param) {
		int yearInt;
		int quantityInt;		
		long bookId;
		
		String bookIdStr = recognizeParam(BookParam.BOOK_ID, param);
		String title = recognizeParam(BookParam.TITLE, param);
		String author = recognizeParam(BookParam.AUTHOR, param);
		String genre = recognizeParam(BookParam.GENRE, param);
		String year = recognizeParam(BookParam.YEAR, param);
		String quantity = recognizeParam(BookParam.QUANTITY, param);
		String availability = recognizeParam(BookParam.AVAILABILITY, param);
		if(bookIdStr==null || bookIdStr.isEmpty()){
			bookId = 0;
		}else{
			bookId = Integer.parseInt(bookIdStr);
		}
		if(year==null || year.isEmpty()){
			yearInt = 0;
		}else{
			yearInt = Integer.parseInt(year);
		}
		if(quantity==null || quantity.isEmpty()){
			quantityInt = 0;
		}else{
			quantityInt = Integer.parseInt(quantity);
		}
		return new Book(bookId, title, author, genre, yearInt, quantityInt, availability);		
	}

	public OrderBooksList prepareOrderBooksList(String[] param) {
		long bookId;
		String actionCommand = recognizeParam(OrderBooksListParam.ACTION_COMMAND, param);
		String bookIdStr = recognizeParam(OrderBooksListParam.B_ID, param);
		
		if(bookIdStr==null || bookIdStr.isEmpty()){
			bookId = 0;
		}else{
			bookId = Long.parseLong(bookIdStr);
		}

		orderBooksList.setActionCommand(actionCommand);
		orderBooksList.setBookId(bookId);

		return orderBooksList;
	}
}
