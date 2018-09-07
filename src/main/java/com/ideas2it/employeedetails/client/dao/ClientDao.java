package com.ideas2it.employeedetails.client.dao;

import java.util.List;

import com.ideas2it.employeedetails.client.model.Client;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;

/**
 * <p>
 * ClientDao is a Data-Access-Object interface, which is used to provide
 * data access to implement database manipulation operations on client data.
 * It uses a table from Database to store the client Information.
 * </p>
 * <p>
 * All the data manipulation operations such as creation, removal, updation,
 * and search are done in this layer.
 * </p>
 *
 * All the methods in this class throw a custom Application Exception.
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public interface ClientDao {

    /**
     * <p>
     * This Method is used to add the client details given by the user and
     * adds them to the Database. It returns true if the entry is added
     * successfully, else returns false.
     * </p>
     *
     * @param client an Client object consisting of the details of the
     *                 new client provided by the user.
     *                 
     * @return Boolean a Boolean value is returned. Returns true
     *                 if the addition was successful, else returns False.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while adding an client.
     */
    public Boolean insertClient(Client client) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to update the details of an Client. Returns true
     *  if the entry is modified, else returns false.
     *  <p>
     *
     * @param client an Client object is passed with the details to be
     *                 updated.
     *
     * @return boolean a boolean value is returned whether the operation is
     *                 successful or not.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while updating an client.
     */
    public boolean updateClient(Client client) throws ApplicationException;


    /**
     * <p>
     * This Method is used to restore a deleted Client. Returns true if the entry is
     * removed, else returns false.
     * </p>
     *
     * @param client an Client object that is to be restored.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 remove is successful or not.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while deleting an client.
     */
    public boolean restoreClient(Client client) throws ApplicationException;

    /**
     * <p>
     * This Method is used to delete an Client. Returns true if the entry is
     * removed, else returns false.
     * </p>
     *
     * @param client an Client object that is to be deleted from the
     *                 database table.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 remove is successful or not.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while deleting an client.
     */
    public boolean deleteClient(Client client) throws ApplicationException;

    /**
     * <p>
     * This Method is used to search for an client. Returns the client
     * object if a match is found with same id, else returns null.
     * </p>
     *
     * @param id         an Integer to identify the required Client
     *                   Object by the ID of Client.
     *
     * @return client  an Client object which has same id is returned.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while searching an client.
     */
    public Client searchClientById(Integer id) throws ApplicationException;

    /**
     * <p>
     * This Method is used to obtain an arralylist of all the clients that
     * are currently present.
     * </p>
     *
     * @return clients an Arraylist consisting of all the clients is
     *                   returned.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while displaying all clients.
     */
    public List<Client> getAllClients() throws ApplicationException;
}
