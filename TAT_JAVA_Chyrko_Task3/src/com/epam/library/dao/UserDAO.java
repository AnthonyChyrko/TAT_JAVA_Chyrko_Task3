package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;

public interface UserDAO {
	void signIn(String login, String password) throws DAOException;
	void signOut(String login) throws DAOException;
	void registration(String login, String password) throws DAOException;

	void editLogin(String login) throws DAOException;
	void editPassword(String password) throws DAOException;
	void editAccess(String targetLogin, String newAccess) throws DAOException;
	void banUser(String targetlogin,String signIn) throws DAOException;
//	void editAccessByAdmin(String targetLogin, String newAccess) throws DAOException;
//	void editAccessBySuperAdmin(String targetLogin, String newAccess) throws DAOException;
	
}
