package com.ideas2it.employeedetails.user.service.impl;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.user.dao.UserDao;
import com.ideas2it.employeedetails.user.model.User;
import com.ideas2it.employeedetails.user.service.UserService;
import com.ideas2it.employeedetails.exception.ApplicationException;

/**
 * </p>
 * UserServiceImpl is a Service-class used to provide logic to User operations.
 * All the methods in this class throws Application Exception. Exceptions are
 * thrown if the input data is null.
 * Beans are injected during runtime using Spring IOC.
 * </p>
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public class UserServiceImpl implements UserService {

    public static UserDao userDao = null;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }


    /**
     *  {@inheritDoc}
     */
    public Boolean addUser(User user) throws ApplicationException {

        if (null == user) {
            throw new ApplicationException(Constants.INVALID_INPUT);
        }
        return userDao.insertUser(user);
    }

    /**
     *  {@inheritDoc}
     */
    public User retrieveUserByEmail(String email)
            throws ApplicationException {

        if (null == email) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return userDao.searchUserByEmail(email);
    }

}

