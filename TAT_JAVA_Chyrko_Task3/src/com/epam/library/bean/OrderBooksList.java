package com.epam.library.bean;

import java.io.Serializable;
import java.util.List;


public class OrderBooksList implements Serializable{
	
	private static final long serialVersionUID = 1566524680377292759L;
	
	private static OrderBooksList instance;	
	
	private String actionCommand;
	private long bookId;
	private long userId;
	
	private List<Book> orderBooksList;
	public OrderBooksList(){}
	
	public OrderBooksList(String actionCommand, long bookId){
		this.actionCommand = actionCommand;
		this.bookId = bookId;
	}
	
	public static OrderBooksList getInstance(){
		if(instance == null){
			instance = new OrderBooksList();
		}
		return instance;
	}	
	
	public void clearOrderBooksList(){
		this.actionCommand = null;
		this.bookId = 0;
		this.userId = 0;		
	}

	public List<Book> getOrderBooksList() {
		return orderBooksList;		
	}

	public void setOrderBooksList(List<Book> orderBooksList) {
		this.orderBooksList =  orderBooksList;
	}

	public String getActionCommand() {
		return actionCommand;
	}

	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "OrderBooksList [actionCommand=" + actionCommand + ", bookId=" + bookId + ", userId=" + userId
				+ ", orderBooksList=" + orderBooksList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionCommand == null) ? 0 : actionCommand.hashCode());
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		result = prime * result + ((orderBooksList == null) ? 0 : orderBooksList.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderBooksList other = (OrderBooksList) obj;
		if (actionCommand == null) {
			if (other.actionCommand != null)
				return false;
		} else if (!actionCommand.equals(other.actionCommand))
			return false;
		if (bookId != other.bookId)
			return false;
		if (orderBooksList == null) {
			if (other.orderBooksList != null)
				return false;
		} else if (!orderBooksList.equals(other.orderBooksList))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
}
