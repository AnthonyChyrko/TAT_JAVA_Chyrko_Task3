package com.epam.library.service;

import com.epam.library.service.exception.ServiceException;

public interface ClientService {
	void signIn(String login, String password) throws ServiceException;
	void signOut(String login) throws ServiceException;
	void registration(String login, String password) throws ServiceException;	
	
	void editLogin(String login)throws ServiceException;
	void editPassword(String password)throws ServiceException;
	void editAccess(String targetLogin, String newAccess) throws ServiceException;
	void banUser(String login, String signIn) throws ServiceException;
//	void editAccessByAdmin(String targetLogin, String newAccess) throws ServiceException;
//	void editAccessBySuperAdmin(String targetLogin, String newAccess) throws ServiceException;
}
