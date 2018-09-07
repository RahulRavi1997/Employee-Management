package com.ideas2it.employeedetails.client.service.impl;

import java.util.List;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.client.dao.ClientDao;
import com.ideas2it.employeedetails.client.model.Client;
import com.ideas2it.employeedetails.client.service.ClientService;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.project.service.ProjectService;

/**
 * ClientServiceImpl is a Service-class used to provide logic to Client operations.
 * All the methods in this class throws Application Exception. Exceptions are
 * thrown if the input data is null.
 * </p>
 * Beans are injected during runtime using Spring Dependency Injection.
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao = null;
    private ProjectService projectService = null;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDao getClientDao() {
        return this.clientDao;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public ProjectService getProjectService() {
        return this.projectService;
    }

    /**
     *  {@inheritDoc}
     */
    public Boolean addClient(Client client) throws ApplicationException {

        return clientDao.insertClient(client);

    }

    /**
     *  {@inheritDoc}
     */
    public boolean assignProjectsToClient(List<Project> projects,
            Client client)  throws ApplicationException {

        client.getProjects().addAll(projects);
        return clientDao.updateClient(client);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean removeProjectsFromClient(List<Project> projects,
            Client client)  throws ApplicationException {

        client.getProjects().removeAll(projects);
        return clientDao.updateClient(client);

    }


    /**
     *  {@inheritDoc}
     */
    public boolean removeProjectFromClient(Project project, Client client)
            throws ApplicationException {

        client.getProjects().remove(project);
        return clientDao.updateClient(client);

    }

    /**
     *  {@inheritDoc}
     */
    public boolean modifyClient(Client client)
            throws ApplicationException {

        if (null == client) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return clientDao.updateClient(client);
    }


    /**
     *  {@inheritDoc}
     */
    public boolean deleteClient(Integer id)
            throws ApplicationException {

        if (null == id) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        Client client = clientDao.searchClientById(id);
        if (null == client) {
            System.out.println(Constants.NOT_FOUND);
            return Boolean.FALSE;
        }
        client.getProjects().clear();
        return clientDao.deleteClient(client);

    }

    /**
     *  {@inheritDoc}
     */
    public boolean restoreClient(int id) throws ApplicationException {

        Client client = retrieveClientById(id);
        if(null == client) {
            return Boolean.FALSE;
        }
        return clientDao.restoreClient(client);
    }

    /**
     *  {@inheritDoc}
     */
    public Client retrieveClientById(Integer id)
            throws ApplicationException {

        if (null == id) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return clientDao.searchClientById(id);
    }

    /**
     *  {@inheritDoc}
     */
    public List<Client> retrieveAllClients() throws ApplicationException {

        return clientDao.getAllClients();
    }

    /**
     *  {@inheritDoc}
     */
    public List<Project> retrieveAllProjects() throws ApplicationException {

        return projectService.retrieveAllProjects();
    }

    /**
     *  {@inheritDoc}
     */
    public List<Project> retrieveUnassignedProjects() throws ApplicationException {

        return projectService.retrieveUnassignedProjects();
    }

    /**
     *  {@inheritDoc}
     */
    public Project retrieveProjectById(Integer id) throws ApplicationException {

        return projectService.retrieveProjectById(id);
    }

    /**
     *  {@inheritDoc}
     */
    public List<Project> retrieveProjectsByIds(Integer[] ids) throws ApplicationException {

        return projectService.retrieveProjectsByIds(ids);
    }
}

