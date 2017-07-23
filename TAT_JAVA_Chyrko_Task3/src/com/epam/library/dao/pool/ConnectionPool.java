package com.epam.library.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.library.dao.constant.DAOConstant;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.pool.exception.ConnectionPoolException;


public class ConnectionPool {

	private static Lock lock = new ReentrantLock();
	private static AtomicBoolean giveConnection = new AtomicBoolean(true);
	private static AtomicBoolean isNull = new AtomicBoolean(true);
	private static ConnectionPool instance;
	
	private ArrayBlockingQueue<Connection> pool;
	 
	public ConnectionPool(){				 
	}
	 
	public void init() throws ConnectionPoolException{
		
		pool = new ArrayBlockingQueue<>(DAOConstant.DEFAULT_POOL_SIZE);
		
		try {
			Class.forName(DAOConstant.LOCATION_OF_JDBC_DRIVER);
			for (int i = 0; i < DAOConstant.DEFAULT_POOL_SIZE; i++) {
				Connection connection = DriverManager.getConnection(DAOConstant.JDBC_URL,
						DAOConstant.DB_USERNAME, DAOConstant.DB_PASSWORD);
				pool.offer(connection);
			}
			
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("SQLException from ConnectionPool!", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("Can't find database driver class!", e);
		}		
	}
	
	public static ConnectionPool getInstance() throws ConnectionPoolException{
		if (isNull.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isNull.set(false);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
	}
	
	public synchronized Connection getConnection() throws DAOException{
		Connection connection = null;
		if(giveConnection.get()){
			connection = pool.element();
		}
		return connection;
	}
	
	public void returnConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
        }
    }
	
	public synchronized void cleanUp() throws ConnectionPoolException{
		giveConnection = new AtomicBoolean(false);		
		
		Iterator<Connection> iter = pool.iterator();
		while(iter.hasNext()){
			 Connection connection = iter.next();
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                throw new ConnectionPoolException
	                ("SQLException occurred during connection pool cleaning",e);
	            }
	            iter.remove();
		}
		
	}
	 
	 
}
