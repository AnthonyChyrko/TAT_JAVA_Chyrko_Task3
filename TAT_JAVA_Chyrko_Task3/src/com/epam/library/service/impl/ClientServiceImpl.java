package com.epam.library.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.library.bean.User;
import com.epam.library.constant.RegExp;
import com.epam.library.controller.session.SessionStorage;
import com.epam.library.dao.UserDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {
	private final static Logger logger = Logger.getLogger(ClientServiceImpl.class);
	SessionStorage session = SessionStorage.getInstance();
//	User user = User.getInstance();

	private boolean checkLogin(String login){
		boolean result = false;
		Pattern p = Pattern.compile(RegExp.LOGIN);
		Matcher m = p.matcher(login);
		if(m.matches()){
			result = true;
		}
		return result;
	}
	private boolean checkPassword(String password){
		boolean result = false;
		Pattern p = Pattern.compile(RegExp.PASSWORD);
		Matcher m = p.matcher(password);
		if(m.matches()){
			result = true;
		}
		return result;
	}
	
	@Override
	public void signIn(String login, String password)  throws ServiceException{
		
		if(login == null || login.isEmpty() || password == null || password.isEmpty()){
			logger.warn("Incorrect login or password");
			throw new ServiceException("Incorrect login or password");
		}		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.signIn(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		

	}

	@Override
	public void signOut(String login) throws ServiceException{
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		try {
//			logger.info(user.toString());
			if(user.getLogin()==null && user.getLogin().isEmpty()){
				logger.warn("There is no user in the system!");
				throw new ServiceException("There is no user in the system!");
			}else if(!user.getLogin().equals(login)){
				logger.warn("Only the user can do SingOut!");
				throw new ServiceException("Only the user can do SingOut!");
			}
			if(!"IN".equals(user.getSignIn())){
				logger.warn("You must be SignIn!");
				throw new ServiceException("You must be SignIn!");
			}
		
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.signOut(login);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}	catch (NullPointerException npe) {
			logger.warn("No one user in system!");
			throw new ServiceException("No one user in system!");
		}
	}

	@Override
	public void registration(String login, String password) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(user.getSignIn() != null){
			logger.warn("Someone from the user already in the system!");
			throw new ServiceException("Someone from the user already in the system!");
		}
		
		if(login == null || password== null || login.isEmpty() || password.isEmpty()){	
			logger.warn("Field login or password can not be empty!");
			throw new ServiceException("Field login or password can not be empty!");
		}		
		
		if(!checkLogin(login)){
			logger.warn("Incorrect LOGIN!");
			throw new ServiceException("Incorrect LOGIN!");
		}
		if(!checkPassword(password)){
			logger.warn("Incorrect PASSWORD!");
			throw new ServiceException("Incorrect PASSWORD!");
		}
		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.registration(login, password);			
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		

	}	

	@Override
	public void editLogin(String login) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(user.getLogin() == null || user.getLogin().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()
						|| user.getAccess() == null || user.getAccess().isEmpty()
							|| user.getSignIn() == null || user.getSignIn().isEmpty()){
			logger.error("You must be registered or SignIn!");
			throw new ServiceException("You must be registered or SignIn!");
		}		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.editLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void editPassword(String password) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.editPassword(password);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void editAccess(String targetLogin, String newAccess) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.editAccess(targetLogin, newAccess);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void banUser(String targetlogin, String signIn) throws ServiceException {
		User user = session.getUserFromSession(Thread.currentThread().hashCode());
		if(user.getLogin() == null || user.getLogin().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty()
						|| user.getAccess() == null || user.getAccess().isEmpty()
							|| user.getSignIn() == null || user.getSignIn().isEmpty()){
				logger.warn("You must be registered or SignIn!");
					throw new ServiceException("You must be registered or SignIn!");
				}

		if(!signIn.equals("OUT")&&!signIn.equals("BAN")){
			logger.warn("You can not change a parameter to this value!");
			throw new ServiceException("You can not change a parameter to this value!");
		}
		
		if(!user.getAccess().equals("A")&&!user.getAccess().equals("SA")){
			logger.warn("You do not have permission to BLOCK/UNLOCK user!");
			throw new ServiceException("You do not have permission to BLOCK/UNLOCK user!");
		}
		
		if(targetlogin.equals(user.getLogin())){
			logger.warn("You can not ban yourself!");
			throw new ServiceException("You can not ban yourself!");
		}
			
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.banUser(targetlogin, signIn);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

}
