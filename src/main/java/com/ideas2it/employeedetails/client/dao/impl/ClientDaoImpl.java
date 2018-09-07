package com.ideas2it.employeedetails.client.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.client.model.Client;
import com.ideas2it.employeedetails.client.dao.ClientDao;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.GenericDao;
/**
 * <p>
 * ClientDaoImpl is a Data-Access-Object Class for executing data manipulation
 * operation on client data such as add, update, remove and search.
 * </p>
 * <p>
 * ClientDaoImpl uses Database tables to store Client Information and
 * implement other operations.
 * </p>
 * All the methods in this class throw a custom Application Exception.
 * 
 * @author    Rahul Ravi
 * @version   1.0
 */

public class ClientDaoImpl extends GenericDao implements ClientDao {


    public ClientDaoImpl() throws ApplicationException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public Boolean insertClient(Client client) throws ApplicationException {

        client.setActive(Boolean.TRUE);
        try {
            return (null != super.save(client));
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.CLIENT_ADDITION_EXCEPTION, client.getName()), e);
            throw new ApplicationException(String.format(Constants.CLIENT_ADDITION_EXCEPTION, client.getName()), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateClient(Client client) throws ApplicationException {

        try {
            return super.update(client);
        } catch (ApplicationException e) {
            Logger.error(String.format(
                Constants.CLIENT_EDIT_EXCEPTION, client.getId()), e);
            throw new ApplicationException(String.format(
                Constants.CLIENT_EDIT_EXCEPTION, client.getId()), e);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public boolean deleteClient(Client client)
            throws ApplicationException {

        client.setActive(Boolean.FALSE);
        return updateClient(client);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean restoreClient(Client client)
            throws ApplicationException {

        client.setActive(Boolean.TRUE);
        return updateClient(client);
    }


    /**
     *  {@inheritDoc}
     */
    public Client searchClientById(Integer id) throws ApplicationException {

        try {
            return super.get(Client.class, id);
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.CLIENT_SEARCH_EXCEPTION, id), e);
            throw new ApplicationException(String.format(Constants.CLIENT_SEARCH_EXCEPTION, id), e);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public List<Client> getAllClients() throws ApplicationException {

        try {
            return super.getAll(Client.class);
        } catch (ApplicationException e) {
            Logger.error(Constants.CLIENT_DISPLAY_EXCEPTION, e);
            throw new ApplicationException(Constants.CLIENT_DISPLAY_EXCEPTION, e);
        }
    }
}
