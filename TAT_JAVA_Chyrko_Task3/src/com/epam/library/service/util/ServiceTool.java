package com.epam.library.service.util;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.util.DAOTool;
import com.epam.library.service.exception.ServiceException;

public class ServiceTool {
	
	private ServiceTool(){}
	
	public static void init() throws ServiceException {
        try {
            DAOTool.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
	
	 public static void destroy() throws ServiceException {
	        try {
	            DAOTool.destroy();
	        } catch (DAOException e) {
	            throw new ServiceException(e);
	        }
	    }
	
}
