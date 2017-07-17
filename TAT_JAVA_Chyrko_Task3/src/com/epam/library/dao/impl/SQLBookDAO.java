package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.epam.library.bean.Book;
import com.epam.library.bean.OrderBooksList;
import com.epam.library.bean.User;
import com.epam.library.controller.utils.OrderBooksListParam;
import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.pool.ConnectionPool;

public class SQLBookDAO implements BookDAO{
	private final static Logger logger = Logger.getLogger(SQLBookDAO.class);
	private static final String GET_BOOK_INFO = "SELECT b_id, b_name, a_name, g_name , b_year, b_quantity, b_available, a_id, g_id FROM library_ver2.books JOIN `m2m_books_authors` USING(`b_id`) JOIN `authors` USING(`a_id`) JOIN `m2m_books_genres` USING(`b_id`) JOIN `genres` USING(`g_id`) WHERE b_id = ?;";
	private static final String GET_BOOKS_INFO = "SELECT b_id, b_name, a_name, g_name , b_year, b_quantity, b_available, a_id, g_id FROM library_ver2.books JOIN `m2m_books_authors` USING(`b_id`) JOIN `authors` USING(`a_id`) JOIN `m2m_books_genres` USING(`b_id`) JOIN `genres` USING(`g_id`);";
	private static final String GET_ALL_SUBSCRIPTIONS = "SELECT sb_id, u_id, b_id, sb_start , sb_finish, sb_is_active FROM library_ver2.subscriptions;";		
	private static final String GET_BOOKS = "SELECT `b_id`, `b_name`, `b_year`, `b_quantity`, `b_available`  FROM `books`;";
		
	private static final String EDIT_BOOK_TITLE = "UPDATE books SET b_name=? WHERE b_id = ?;";
	private static final String EDIT_BOOK_AUTHOR = "UPDATE authors SET a_name=? WHERE a_id = ?;";
	private static final String EDIT_BOOK_YEAR = "UPDATE books SET b_year=? WHERE b_id = ?;";
	private static final String EDIT_BOOK_GENRE = "UPDATE genres SET g_name=? WHERE g_id = ?;";
	private static final String EDIT_BOOK_QUANTITY = "UPDATE books SET b_quantity=? WHERE b_id = ?;";
	private static final String EDIT_BOOK_AVAILABILITY = "UPDATE books SET b_available=? WHERE b_id = ?;";		

	private static final String ADD_SUBSCRIPTION = "INSERT INTO `subscriptions` (`u_id`, `b_id`) VALUES (?, ?);";
	private static final String ADD_NEW_AUTHOR = "INSERT INTO `authors` (`a_name`) VALUES (?);";
	private static final String ADD_NEW_GENRE = "INSERT INTO `genres` (`g_name`) VALUES (?);";
	private static final String ADD_NEW_BOOK = "INSERT INTO `books` (`b_name`, `b_year`, `b_quantity`) VALUES (?, ?, ?);";
	
	private static final String M2M_BOOK_AUTHORS = "INSERT INTO `m2m_books_authors` (`b_id`, `a_id`) VALUES (?, ?)";
	private static final String M2M_BOOK_GENRES = "INSERT INTO `m2m_books_genres` (`b_id`, `g_id`) VALUES (?, ?)";	
	
	private static final String DELETE_BOOK = "DELETE from books where b_id = ?;";
	private static final String REMOVE_SUBSCRIPTION = "DELETE FROM `subscriptions` WHERE `sb_id`=?;";
	
	private static final String LAST_INSERT_ID = "SELECT LAST_INSERT_ID();";	
	
	private static final String BOOK_AVAILABILITY = "UPDATE books SET b_available=? WHERE b_id = ?;";
	
	ConnectionPool connectionPool;
	PreparedStatement ps = null;	
	Connection connection;
	User user = User.getInstance();
	Book book;
	ResultSet rs;

	@Override
	public void addBook(Book book) throws DAOException{
		
		if(user.getLogin() == null || user.getLogin().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()
						|| user.getAccess() == null || user.getAccess().isEmpty()
							|| user.getSignIn() == null || user.getSignIn().isEmpty()){
					throw new DAOException("You must be registered or SignIn!");
				}
		
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();
		int a_id=0, g_id=0, b_id=0;
		boolean newAuthor = true;
		boolean newGenre = true;
		try {			
			ps = connection.prepareStatement(GET_BOOKS_INFO);
			rs = ps.executeQuery();
			
			//Check the existence of a book
			while(rs.next()){
				if(book.getTitle().equals(rs.getString(2)) && book.getAuthor().equals(rs.getString(3)) 
						&& book.getGenre().equals(rs.getString(4))&& book.getAvailability().equals(rs.getString(7))){
					logger.error("Book already added in library!");
					throw new DAOException("Book already added in library!");
				}
				//check new author
				if(book.getAuthor().equals(rs.getString(3))){					
					a_id = rs.getInt(8);
					logger.warn("Author already is exist!");
					newAuthor = false;
				}
				//check new genre
				if(book.getGenre().equals(rs.getString(4))){
					g_id = rs.getInt(9);			
					logger.warn("Genre already is exist!");
					newGenre = false;
				}				
			}
			
			//if new author, than add to table
			if(newAuthor){
				ps = connection.prepareStatement(ADD_NEW_AUTHOR);
				ps.setString(1, book.getAuthor());
				ps.executeUpdate();
				ResultSet tmp = ps.executeQuery(LAST_INSERT_ID);
				while(tmp.next()){
					a_id = tmp.getInt(1);
				}				
			}
			
			//if new genre, than add to table
			if(newGenre){
				ps = connection.prepareStatement(ADD_NEW_GENRE);
				ps.setString(1, book.getGenre());
				ps.executeUpdate();
				ResultSet tmp = ps.executeQuery(LAST_INSERT_ID);
				while(tmp.next()){
					g_id = tmp.getInt(1);
				}				
			}
			
			//add book
			ps = connection.prepareStatement(ADD_NEW_BOOK);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getYear());
			ps.setInt(3, book.getQuantity());
			ps.executeUpdate();
			ResultSet tmp = ps.executeQuery(LAST_INSERT_ID);
			
			while(tmp.next()){
				b_id = tmp.getInt(1);
			}		
			
			//set bundles
			ps = connection.prepareStatement(M2M_BOOK_AUTHORS);
			ps.setInt(1, b_id);
			ps.setInt(2, a_id);
			ps.executeUpdate();
			
			ps = connection.prepareStatement(M2M_BOOK_GENRES);
			ps.setInt(1, b_id);
			ps.setInt(2, g_id);
			ps.executeUpdate();			
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}		
	}

	@Override
	public void deleteBook(long idBook) throws DAOException{
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();
		try {
			ps = connection.prepareStatement(DELETE_BOOK);
			ps.setLong(1, idBook);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}
		
	}

	@Override
	public void showAllBooks() throws DAOException {
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();
			
		try {
			ps = connection.prepareStatement(GET_BOOKS_INFO);
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)
				+" | "+rs.getString(4)+" | "+rs.getString(5)+" | "+rs.getString(6)	);					
			}
				
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}		
	}

	@Override
	public void editBook(Book book) throws DAOException {
		if(user.getLogin() == null || user.getLogin().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()
						|| user.getAccess() == null || user.getAccess().isEmpty()
							|| user.getSignIn() == null || user.getSignIn().isEmpty()){
					throw new DAOException("You must be registered or SignIn!");
				}
		
		if(!"A".equals(user.getAccess()) && !"SA".equals(user.getAccess())){
			throw new DAOException("You do not have access rights!");
		}
		
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();			
		
		if(book.getTitle()!=null && !book.getTitle().isEmpty()){
			editParam(book.getTitle(), book.getId(), connection, EDIT_BOOK_TITLE);
		}
		if(book.getAuthor()!=null && !book.getAuthor().isEmpty()){
			editParam(book.getAuthor(), book.getId(), connection, EDIT_BOOK_AUTHOR);
		}
		if(book.getYear()!=0){
			editParam(book.getYear(), book.getId(), connection, EDIT_BOOK_YEAR);
		}
		if(book.getQuantity()!=0){
			editParam(book.getQuantity(), book.getId(), connection, EDIT_BOOK_QUANTITY);
		}
		if(book.getGenre()!=null && !book.getGenre().isEmpty()){
			editParam(book.getGenre(), book.getId(), connection, EDIT_BOOK_GENRE);
		}
		if(book.getAvailability()!=null && !book.getAvailability().isEmpty()){
			editParam(book.getAvailability(), book.getId(), connection, EDIT_BOOK_AVAILABILITY);
		}
	}

	private void editParam(String param, long idBook, Connection connection, String query) throws DAOException{
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, param);
			ps.setLong(2, idBook);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}		
	}
	private void editParam(long param, long idBook, Connection connection, String query) throws DAOException{
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, param);
			ps.setLong(2, idBook);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}		
	}

	@Override
	public void bookAvailability(long b_id, String availability) throws DAOException {
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			throw new DAOException("You have no permissions to change book availability!");
		}
		if(!user.getSignIn().equals("IN")){
			throw new DAOException("You may signIn or registrate!");
		}
		try {			
			ps = connection.prepareStatement(BOOK_AVAILABILITY);
			ps.setString(1, availability);
			ps.setLong(2, b_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}
		
	}

	@Override
	public void editOrderBooksList(OrderBooksList orderBooksList) throws DAOException {
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();		
		if(OrderBooksListParam.ADD_BOOK.toString().equals(orderBooksList.getActionCommand().toUpperCase())){			
			try {
				ps = connection.prepareStatement(GET_BOOK_INFO);
				ps.setLong(1, orderBooksList.getBookId());
				rs = ps.executeQuery();//get book			
				while(rs.next()){
					book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
				}
				
				if(orderBooksList.getOrderBooksList()==null){//	create OrderList									
					orderBooksList.setOrderBooksList(new ArrayList<>());					
					orderBooksList.getOrderBooksList().add(book);
					orderBooksList.setUserId(user.getUserId());
					System.out.println("Create new orderBooksList");
				}else{
					orderBooksList.getOrderBooksList().add(book);					
				}				
			} catch (SQLException e) {
				logger.error("SQLException!");
				throw new DAOException("SQLException!");
			}
			
		}else if (OrderBooksListParam.REMOVE_BOOK.toString().equals(orderBooksList.getActionCommand().toUpperCase())){
			Iterator<Book> iter = orderBooksList.getOrderBooksList().iterator();			
			while (iter.hasNext()) {
			        Book book = iter.next();			 
			        if (book.getId() == orderBooksList.getBookId()) {
			                iter.remove();
			        }
			}
		}
		if(orderBooksList.getOrderBooksList().isEmpty()){
			orderBooksList.setOrderBooksList(null);
		}
		
	}

	@Override
	public void addSubscription(OrderBooksList orderBooksList) throws DAOException {
		ResultSet rs;
		System.out.println("\nFROM Subscription\n"+orderBooksList.toString());//TODO
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			throw new DAOException("You have no permissions to change book availability!");
		}
		if(!user.getSignIn().equals("IN")){
			throw new DAOException("You may signIn or registrate!");
		}		
		
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();		
		
		try {			
			for (int i = 0; i < orderBooksList.getOrderBooksList().size(); i++) {
				//get all books				
				ps = connection.prepareStatement(GET_BOOKS);
				rs = ps.executeQuery();
				
				// check availability
				while (rs.next()) {
//					System.out.println(rs.getString(5));
					if(orderBooksList.getOrderBooksList().get(i).getId() == rs.getLong(1) && rs.getString(5).toString().equals("Y")){						
						ps = connection.prepareStatement(ADD_SUBSCRIPTION);
						ps.setLong(1, orderBooksList.getUserId());
						ps.setLong(2, orderBooksList.getOrderBooksList().get(i).getId());				
						ps.executeUpdate(); 
					}					
				}				
			}			
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}		
	}

	@Override
	public void removeSubscription(long userId, long bookId) throws DAOException {
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			throw new DAOException("You have no permissions to change book availability!");
		}
		if(!user.getSignIn().equals("IN")){
			throw new DAOException("You may signIn or registrate!");
		}		
		
		connectionPool = new ConnectionPool();		
		connection = connectionPool.getConnection();	
		
		try {
			ps = connection.prepareStatement(GET_ALL_SUBSCRIPTIONS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getLong(2) == userId && rs.getLong(3) == bookId){				
					ps = connection.prepareStatement(REMOVE_SUBSCRIPTION);
					ps.setInt(1, rs.getInt(1));
					ps.executeUpdate();
				}
			}			
		} catch (SQLException e) {
			logger.error("SQLException!");
			throw new DAOException("SQLException!");
		}
		
	}

}
