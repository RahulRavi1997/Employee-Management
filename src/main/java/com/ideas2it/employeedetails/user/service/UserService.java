package com.ideas2it.employeedetails.user.service;

import com.ideas2it.employeedetails.user.model.User;
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
public interface UserService {

    /**
     * <p>
     * This Method is used to add a new User.
     * </p>
     *
     * @param user a User object with the email and password to be added.
     *
     * @return boolean a boolean value indicating the successful addition of
     *                 a new User.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving an
     *                              user.
     */
    public Boolean addUser(User user) throws ApplicationException;

    /**
     * <p>
     * This Method is used to search a User Entry and return the User
     * object. It returns null if no match is found.
     * </p>
     *
     * @param email a String indicating the email of the user that is
     *              to be searched an returned.
     *
     * @return user an User object is returned if a valid match is
     *                  found, else returns null.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving an
     *                              user.
     */
    public User retrieveUserByEmail(String email) throws ApplicationException;

}

