package com.ideas2it.employeedetails.user.dao;

import java.util.List;

import com.ideas2it.employeedetails.user.model.User;
import com.ideas2it.employeedetails.exception.ApplicationException;

/**
 * <p>
 * UserDao is a Data-Access-Object interface, which is used to provide
 * data access to implement database manipulation operations on User data.
 * It uses a table from Database to store the user Information.
 * </p>
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public interface UserDao {


    /**
     * <p>
     * This Method is used to insert a new User.
     * </p>
     *
     * @param user a User object with the email and password to be inserted.
     *
     * @return boolean a boolean value indicating the successful insertion of
     *                 a new User.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving an
     *                              user.
     */
    public Boolean insertUser(User user) throws ApplicationException;

    /**
     * <p>
     * This Method is used to search for an User. Returns the User
     * object if a match is found with same id, else returns null.
     * </p>
     *
     * @param email a String indicating the email of the user that is
     *              to be searched an returned.
     *
     * @return user  an User object which has same id is returned.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while searching an user.
     */
    public User searchUserByEmail(String email) throws ApplicationException;


}
