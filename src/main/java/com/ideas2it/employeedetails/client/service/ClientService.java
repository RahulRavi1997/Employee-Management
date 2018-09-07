package com.ideas2it.employeedetails.client.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeedetails.client.model.Client;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;

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
public interface ClientService {

    /**
     * <p>
     * This Method is used to add a new client using the details provided by
     * the user. Returns true if the entry is added successfully, else returns
     * false if addition operation fails.
     * </p>
     *
     * @param client an Client object containing all the details of the
     *                 client to be added.
     *
     * @return Boolean an Boolean value indicating the successful addition of the
     *                 client.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while adding an
     *                              client.
     */
    public Boolean addClient(Client client) throws ApplicationException;


    /**
     * <p>
     *  This method is used to assign projects to a Client. The role of the
     *  project is either Leader or Member. Returns true if the entry is
     *  modified, else returns false.
     * </p>
     *
     * @param projects a list of project Object to be added to a certain
     *                  client.
     *
     * @param client a client Object containing the client into which the
     *                project is added.
     *
     * @return boolean a boolean value is returned whether the operation
     *                 to modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while assigning
     *                              projects to a client.
     */
    public boolean assignProjectsToClient(List<Project> projects, 
            Client client) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to remove Projects that have been assigned to a
     *  Client. Returns true if the entry is modified, else returns false.
     * </p>
     *
     * @param projects a list of projects to be removed from the client
     *
     * @param client a client Object from which the projects is removed.
     *
     * @return boolean a boolean value is returned whether the operation
     *                 to modify is successful or not
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              to a that occur while removing an project
     *                              from a client.
     */
    public boolean removeProjectsFromClient(List<Project> projects,
            Client client) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to remove Project that has been assigned to a
     *  Client. Returns true if the entry is modified, else returns false.
     * </p>
     *
     * @param project a project to be removed from the client.
     *
     * @param client a client Object from which the projects is removed.
     *
     * @return boolean a boolean value is returned whether the operation
     *                 to modify is successful or not
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              to a that occur while removing an project
     *                              from a client.
     */
    public boolean removeProjectFromClient(Project project, Client client)
            throws ApplicationException;


    /**
     * <p>
     *  This Method is used to modify the details of an existing client with
     *  updated details provided by the user. Returns true if the client 
     *  information is updated, else returns false if the operation fails.
     * </p>
     *
     * @param newClient an Client object is passed with the id of the old
     *                    Client which is used as a reference.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              client.
     */
    public boolean modifyClient(Client newClient) throws ApplicationException;

    /**
     * <p>
     * This Method is used to delete an client entry. Returns true if the 
     * entry is removed, else returns false if the entry is not found.
     * </p>
     *
     * @param id an Integer- id of the client which is to be deleted.
     *                 
     *
     * @return boolean a boolean value is returned whether the removal of the
     *                 client is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while deleting an
     *                              client.
     */
    public boolean deleteClient(Integer id) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to re-activate a client who has been deleted.
     *  Returns true if the operation is successful.
     * </p>
     *
     * @param id an integer indicating the id of the client to be reinstated.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              client.
     */
    public boolean restoreClient(int id) throws ApplicationException;


    /**
     * <p>
     * This Method is used to search an Client Entry and return the Client
     * object. It returns null if no match is found.
     * </p>
     *
     * @param id an Integer indicating the id of the client that is
     *           to be searched an returned.
     *
     * @return client a Client object is returned if a valid match is
     *                  found, else returns null.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving an
     *                              client.
     */
    public Client retrieveClientById(Integer id) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to obtain all the client details using an
     *  arraylist. Returns an empty arraylist if no clients are added.
     * </p>
     *
     * @return clients an Arraylist consisting of all the clients that are
     *                   added.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving all
     *                              clients.
     */
    public List<Client> retrieveAllClients() throws ApplicationException;

    /**
     * <p>
     *  This Method is used to obtain all the projects that have been created.
     *  Returns empty arraylist if no projects exists.
     * </p>
     *
     * @return list an Arraylist consisting of all the projects.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving a
     *                              project.
     */
    public List<Project> retrieveAllProjects() throws ApplicationException;

    /**
     * <p>
     *  This Method is used to obtain all the projects that have not been
     *  assigned to any Clients.
     *  Returns empty arraylist if all projects are already assigned.
     * </p>
     *
     * @return list an Arraylist consisting of all the projects.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving a
     *                              project.
     */
    public List<Project> retrieveUnassignedProjects() throws ApplicationException;

    /**
     * <p>
     * This Method is used to search an Project Entry and return the project
     * object. It returns null if no match is found.
     * </p>
     *
     * @param id an Integer indicating the id of the project that is
     *           to be searched an returned.
     *
     * @return project a Project object is returned if a valid match is
     *                  found, else returns null.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving an
     *                              client.
     */
    public Project retrieveProjectById(Integer id) throws ApplicationException;

    /**
     * <p>
     * This Method is used to retrieve an list of all selected projects.
     * </p>
     *
     * @return ids an Array of integers containing the id of projects that are
     *             to be retrieved.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while displaying all employees.
     */
    public List<Project> retrieveProjectsByIds(Integer[] ids) throws ApplicationException;


}
