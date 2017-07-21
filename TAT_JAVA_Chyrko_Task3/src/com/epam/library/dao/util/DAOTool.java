package com.epam.library.dao.util;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.pool.ConnectionPool;
import com.epam.library.dao.pool.exception.ConnectionPoolException;

public class DAOTool {
	
	private DAOTool(){}
	
	public static void init() throws DAOException {
        ConnectionPool pool;
        try {
            pool = ConnectionPool.getInstance();
            pool.init();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    public static void destroy() throws DAOException {
        ConnectionPool pool;
        try {
            pool = ConnectionPool.getInstance();
            pool.cleanUp();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }    
}
