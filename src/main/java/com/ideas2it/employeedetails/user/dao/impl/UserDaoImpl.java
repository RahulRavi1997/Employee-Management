package com.ideas2it.employeedetails.user.dao.impl;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.GenericDao;
import com.ideas2it.employeedetails.user.model.User;
import com.ideas2it.employeedetails.user.dao.UserDao;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;
/**
 * <p>
 * UserDaoImpl is a Data-Access-Object Class for executing data manipulation
 * operation on user data such as add, update, remove and search. 
 * All the methods in this class throw Application Exception.
 * </p>
 * 
 * @author    Rahul Ravi
 * @version   1.0
 */
public class UserDaoImpl extends GenericDao implements UserDao {

    public  UserDaoImpl() throws ApplicationException {
        super();
    }

    /**
     *  {@inheritDoc}
     */
    public Boolean insertUser(User user) throws ApplicationException {

        user.setActive(Boolean.TRUE);
        try {
            return (null != super.save(user));
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.USER_ADDITION_EXCEPTION, user.getEmail()), e);
            throw new ApplicationException(String.format(Constants.USER_ADDITION_EXCEPTION, user.getEmail()), e);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public User searchUserByEmail(String email) throws ApplicationException {

        try {
            return super.getByAttribute(User.class, Constants.EMAIL, email);
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.USER_SEARCH_EXCEPTION, email), e);
            throw new ApplicationException(String.format(Constants.USER_SEARCH_EXCEPTION, email), e);
        }
    }
}
