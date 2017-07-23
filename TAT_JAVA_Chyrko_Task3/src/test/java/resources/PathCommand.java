package test.java.resources;

import java.util.ArrayList;
import java.util.List;

public class PathCommand {
	public static final String INSERT_BOOKS = ".\\src\\test\\java\\resources\\insertData\\insertBooks.sql";
	public static final String INSERT_AUTHORS = ".\\src\\test\\java\\resources\\insertData\\insertAuthors.sql";
	public static final String INSERT_GENRES = ".\\src\\test\\java\\resources\\insertData\\insertGenres.sql";
	public static final String INSERT_USERS = ".\\src\\test\\java\\resources\\insertData\\insertUsers.sql";
	public static final String INSERT_SUBSCRIPTIONS = ".\\src\\test\\java\\resources\\insertData\\insertSubscriptions.sql";
	public static final String INSERT_M2M_B_A = ".\\src\\test\\java\\resources\\insertData\\insertM2M_B_A.sql";
	public static final String INSERT_M2M_B_G = ".\\src\\test\\java\\resources\\insertData\\insertM2M_B_G.sql";	
	public static final String INSERT_USERS_FOR_SIGN_OUT = ".\\src\\test\\java\\resources\\insertData\\insertUsersForSignOut.sql";
	
	public static final String CREATE_TRIGGER_ADD_B_QUANTITY = ".\\src\\test\\java\\resources\\insertData\\createTriggerAddBQuantity.sql";
	public static final String CREATE_TRIGGER_BOOK_AVAILABLE = ".\\src\\test\\java\\resources\\insertData\\createTriggerBookAvailability.sql";
	public static final String CREATE_TRIGGER_CREATE_DATE = ".\\src\\test\\java\\resources\\insertData\\createTriggerCreateDate.sql";
	public static final String CREATE_TRIGGER_SUBSTRACT_B_QUANTITY = ".\\src\\test\\java\\resources\\insertData\\createTriggerSubtractBQuantity.sql";
	
	public static final String DELETE_BOOKS = ".\\src\\test\\java\\resources\\deleteData\\deleteBooks.sql";
	public static final String DELETE_AUTHORS = ".\\src\\test\\java\\resources\\deleteData\\deleteAuthors.sql";
	public static final String DELETE_GENRES = ".\\src\\test\\java\\resources\\deleteData\\deleteGenres.sql";
	public static final String DELETE_USERS = ".\\src\\test\\java\\resources\\deleteData\\deleteUsers.sql";
	public static final String DELETE_SUBSCRIPTIONS = ".\\src\\test\\java\\resources\\deleteData\\deleteSubscriptions.sql";
	public static final String DELETE_M2M_B_A = ".\\src\\test\\java\\resources\\deleteData\\deleteM2M_B_A.sql";
	public static final String DELETE_M2M_B_G = ".\\src\\test\\java\\resources\\deleteData\\deleteM2M_B_G.sql";	
	public static final String DELETE_TRIGGER_ADD_B_QUANTITY = ".\\src\\test\\java\\resources\\deleteData\\deleteTriggerAddBQuantity.sql";
	public static final String DELETE_TRIGGER_BOOK_AVAILABLE = ".\\src\\test\\java\\resources\\deleteData\\deleteTriggerBookAvailability.sql";
	public static final String DELETE_TRIGGER_CREATE_DATE = ".\\src\\test\\java\\resources\\deleteData\\deleteTriggerCreateDate.sql";
	public static final String DELETE_TRIGGER_SUBSTRACT_B_QUANTITY = ".\\src\\test\\java\\resources\\deleteData\\deleteTriggerSubtractBQuantity.sql";
	
	public static final String CHARSET = "windows-1251";
	
	private static List<String> pathFillTestDB = new ArrayList<>();
	private static List<String> pathCleanTestDB = new ArrayList<>();
	
	static{
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
	}
	
	public static List<String> getPathFillTestDB(){
		return pathFillTestDB;
	}
	public static List<String> getPathCleanTestDB(){
		return pathCleanTestDB;
	}
	
}
