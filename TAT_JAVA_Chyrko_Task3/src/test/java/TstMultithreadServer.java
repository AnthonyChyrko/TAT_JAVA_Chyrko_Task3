package test.java;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.library.controller.Controller;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.pool.ConnectionPool;
import com.epam.library.dao.pool.exception.ConnectionPoolException;
import com.epam.library.server.MultithreadServer;

import test.java.resources.Encodings;
import test.java.resources.PathCommand;

public class TstMultithreadServer {
	List<String> pathFillTestDB = new ArrayList<>();
	List<String> pathCleanTestDB = new ArrayList<>();
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps;	
	ResultSet rs;
	Connection connection;
	StringBuilder sb;	 
	MultithreadServer multithreadServer = MultithreadServer.getInstance();
//	User user = User.getInstance();
    static{
    	
    }
	@Test(dataProvider = "scenario1")
	  public void scenario1(List<String> requestsList, List<String> expectedResponceList) {	
			String[] requestsArray = new String[requestsList.size()];
			requestsArray = requestsList.toArray(requestsArray);
			List<String> actualResponceList = multithreadServer.executeUserCommands(requestsArray);
			for (int i = 0; i < actualResponceList.size(); i++) {
				assertEquals(actualResponceList.get(i), expectedResponceList.get(i));	
			}			  
	  }  
	
		@Test(dataProvider = "scenario2")
		  public void scenario2(List<String> requestsList, List<String> expectedResponceList) {	
				String[] requestsArray = new String[requestsList.size()];
				requestsArray = requestsList.toArray(requestsArray);
				List<String> actualResponceList = multithreadServer.executeUserCommands(requestsArray);
				for (int i = 0; i < actualResponceList.size(); i++) {
					assertEquals(actualResponceList.get(i), expectedResponceList.get(i));	
				}			  
		  }  
		@DataProvider
		public Object[][] scenario2() {
			  List<String> requestsList = Arrays.asList(
					  "command=sign_in&login=Ivan&password=passWord1",
					  "command=edit_Login&login=IvanStoGram",					  
					  "command=sign_out&login=IvanStoGram"
					  );
			  
			  List<String> expectedResponceList = Arrays.asList(
					  "Welcom!",
					  "Login is changed!",					  
					  "Good Bye!"
					  );	  
			  
		  return new Object[][] {
		    new Object[] { requestsList, expectedResponceList },     
		  };
		}  
	
  @DataProvider
  public Object[][] scenario1() {
	  List<String> requestsList = Arrays.asList(
			  "command=sign_in&login=Anton&password=passWord4",
			  "command=ban_user&login=Petr&signIn=BAN",
			  "command=edit_access&login=Sema&access=U",
			  "command=sign_out&login=Anton"
			  );
	  
	  List<String> expectedResponceList = Arrays.asList(
			  "Welcom!",
			  "Profile is changed!",
			  "Access is changed!",
			  "Good Bye!"
			  );	  
	  
    return new Object[][] {
      new Object[] { requestsList, expectedResponceList },     
    };
  }  
  
  @BeforeTest
  public void beforeTest() throws DAOException {
	  pathFillTestDB.add(PathCommand.INSERT_BOOKS);
	  pathFillTestDB.add(PathCommand.INSERT_AUTHORS);
	  pathFillTestDB.add(PathCommand.INSERT_GENRES);
	  pathFillTestDB.add(PathCommand.INSERT_M2M_B_A);
	  pathFillTestDB.add(PathCommand.INSERT_M2M_B_G);
	  pathFillTestDB.add(PathCommand.INSERT_USERS);
	  pathFillTestDB.add(PathCommand.INSERT_SUBSCRIPTIONS);		
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_ADD_B_QUANTITY);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_BOOK_AVAILABLE);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_CREATE_DATE);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_SUBSTRACT_B_QUANTITY);	  
	  
	  try {
		  
		  Controller controller = new Controller();
		  controller.init();
		  ConnectionPool connectionPool = ConnectionPool.getInstance();
		  Connection connection = connectionPool.getConnection();		  
		  for (int i = 0; i < pathFillTestDB.size(); i++) {
			  
			  sb = Encodings.readFileWithCharset(pathFillTestDB.get(i), PathCommand.CHARSET);					
			  ps = connection.prepareStatement(""+sb);
			  ps.executeUpdate();
				
		  }
			
	  } catch (SQLException e) {	
		  e.printStackTrace();
	  } catch (ConnectionPoolException e) {	
		  e.printStackTrace();
	  }
  }

  @AfterTest
  public void afterTest() throws DAOException{
	  
	  pathCleanTestDB.add(PathCommand.DELETE_M2M_B_A);
	  pathCleanTestDB.add(PathCommand.DELETE_M2M_B_G);
	  pathCleanTestDB.add(PathCommand.DELETE_SUBSCRIPTIONS);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_ADD_B_QUANTITY);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_SUBSTRACT_B_QUANTITY);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_CREATE_DATE);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_BOOK_AVAILABLE);
	  pathCleanTestDB.add(PathCommand.DELETE_AUTHORS);
	  pathCleanTestDB.add(PathCommand.DELETE_GENRES);
	  pathCleanTestDB.add(PathCommand.DELETE_USERS);
	  pathCleanTestDB.add(PathCommand.DELETE_BOOKS);
	  try {		  
		  connectionPool = ConnectionPool.getInstance();
		  connection = connectionPool.getConnection();		
		  for (int i = 0; i < pathCleanTestDB.size(); i++) {
			  
			  sb = Encodings.readFileWithCharset(pathCleanTestDB.get(i), PathCommand.CHARSET);		
			  ps = connection.prepareStatement(""+sb);
			  ps.executeUpdate();			
		  }
	  } catch (SQLException e) {				
			e.printStackTrace();
	  } catch (ConnectionPoolException e1) {
		  e1.printStackTrace();
	  }	  	
  }
  
}
