package com.ideas2it.employeedetails;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;

/**
 * <p>
 * GenericDao is a generic class for executing data manipulation operations such
 * as add, update, remove and search. Uses Hibernate to make operations on
 * the database.
 * All the methods in this class throw Application Exception.
 * This class is used by all other Dao classes to execute Hibernate-based
 * operations on a Database server.
 * </p>
 * 
 * @author    Rahul Ravi
 * @version   1.0
 */
public class GenericDao {

    private static SessionFactory factory = null;

    protected GenericDao() {
    }

    /**
     *  <p>
     *  This Method is used to get a sessionFactory for Database Operations.
     *  Checks if the connection is already alive and returns the same
     *  connection instead of creating a new One.
     *  </p>
     *
     *  @return session Returns a Session instance. Checks for null.
     *                  If the SessionFactory is null, opens a new
     *                  SessionFactory to return.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              Hibernate exceptions that occur while
     *                              getting a Session.
     */
    public static Session getSession() throws ApplicationException {
        try {
            if ((null == factory) || (factory.isClosed())) {
                factory = new Configuration().configure(
                    Constants.HIBERNATE_CONFIG_FILE).buildSessionFactory();
            }
        } catch (HibernateException e) {
            Logger.error(Constants.DATABASE_FAILURE, e);
            throw new ApplicationException(e);
        }
        return factory.openSession();
    }

    /**
     * This Method is used to close a Session.
     * Checks if the Session is null before closing.
     *
     * @param session a Session that needs to be checked for null
     *                and closed.
     */
    public static void close(Session session) {
        try {
            if (null != session) {
                session.close();
            }
        } catch (HibernateException e) {
            Logger.error(Constants.DATABASE_FAILURE, e);
        }
    }

    /**
     * This Method is used to insert a new record in the database.
     *
     * @param object an ObjectType to be inserted into the database.
     *
     * @return insertedObject an ObjectType which is obtained after inserting
     *                        into the database.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              Hibernate exceptions that occur while
     *                              saving an entity in the database.
     */
    public static <T> T save(final T object) throws ApplicationException {

        Session session = null;
        Transaction transaction = null;
        T insertedObject = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            insertedObject =  (T) session.save(object);
            transaction.commit();

        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.debug(e);
            throw new ApplicationException(e);
        } catch (PersistenceException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.debug(e);
            throw new ApplicationException(e);
        } finally {
            close(session);
        }
        return insertedObject;
    }

    /**
     * This Method is used to update an existing record in the database.
     *
     * @param object a generic Object which is to be updated in the database
     *               using Hibernate.
     *
     * @return Boolean a boolean is returned. Returns true if the updation
     *                 operation is successful.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              Hibernate exceptions that occur while
     *                              updating an entity in the database.
     */
    public static <T> Boolean update(final T object) throws ApplicationException {

        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();

        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.debug(e);
            throw new ApplicationException(e);
        } finally {
            close(session);
        }
        return Boolean.TRUE;
    }

    /**
     * This Method is used to search and get a record in the database based on a
     * certain property of the class.
     *
     * @param type the Type of class from which the query is made.
     *
     * @param attributeName a String indicating the name of the property to be
     *                      evaluated.
     *
     * @param attributeValue an Object indicating the value to be compared in
     *                       the database.
     *
     * @return T a type which is obtained on successful query in the database.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              Hibernate exceptions that occur while
     *                              updating an entity in the database.
     */
    public <T> T getByAttribute(final Class<T> type, final String attributeName,
            final Object attributeValue) throws ApplicationException {

        Session session = null;
        try {
            session = getSession();
            final Criteria criteria = session.createCriteria(type);
            criteria.add(Restrictions.eq(attributeName, attributeValue));
            return (T) criteria.uniqueResult();
        } catch (HibernateException e) {
            Logger.debug(e);
            throw new ApplicationException(e);
        } finally {
            close(session);
        }
    }

    /**
     * This Method is used to search and get a record in the database based on
     * the primary key.
     *
     * @param type the Type of class from which the query is made.
     *
     * @param id an Integer indicating the id of the object which is to be obtained from the database.
     *
     * @return T a type which is obtained on successful query in the database.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              Hibernate exceptions that occur while
     *                              retrieving an entity from the database.
     */
    public static <T> T get(final Class<T> type, final Integer id) throws ApplicationException {
        
        Session session = null;
        try {
            session = getSession();
            return (T) session.get(type, id);
        } catch (HibernateException e) {
            Logger.debug(e);
            throw new ApplicationException(e);
        } finally {
            close(session);
        }
    }

    /**
     * This Method is used to obtain all objects in a certain class type.
     *
     * @param type the Type of class from which the query is made.
     *
     * @return List<T> a list of objects from the database that are from the
     *                 given class type.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              Hibernate exceptions that occur while
     *                              obtaining a table from the database.
     */
    public static <T> List<T> getAll(final Class<T> type) throws ApplicationException {

        Session session = null;
        try {
            session = getSession();
            return session.createCriteria(type).list();

        } catch (HibernateException e) {
            Logger.debug(e);
            throw new ApplicationException(e);
        } finally {
            close(session);
        }
    }
}
