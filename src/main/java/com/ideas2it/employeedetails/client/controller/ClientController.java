package com.ideas2it.employeedetails.client.controller;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ideas2it.employeedetails.address.model.Address;
import com.ideas2it.employeedetails.client.model.Client;
import com.ideas2it.employeedetails.client.service.ClientService;
import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;
import com.ideas2it.employeedetails.project.model.Project;

/**
 * <p>
 * ClientController is a Controller Class, which is used to implement
 * data storage and retrieval operations on Client Data. A Client object
 * can assigned to many projects at the same time. Uses Spring Mvc to map the
 * methods to URL of requests from the View Layer.
 * </p>
 * Beans are injected during runtime using Spring Dependency Injection.
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
@Controller
public class ClientController {

    private ClientService clientService = null;


    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientService getClientService() {
        return this.clientService;
    }

    /**
     *  This Method is used to redirect user to jsp page with the form used to
     *  create a new Client. Redirects to Error Page if any exception occurs.
     *
     * @param model a Model Object to add a new Client as an attribute.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.CREATE_CLIENT_MAPPING, method=RequestMethod.GET)
    private ModelAndView redirectToCreateClient(Model model) {
        try {
            Client client = new Client();
            List<Address> addresses = new ArrayList<Address>();
            addresses.add(new Address());
            addresses.add(new Address());
            client.setAddresses(addresses);
            client.setProjects(new ArrayList<Project>());
            model.addAttribute(Constants.PROJECTS, clientService.
                    retrieveUnassignedProjects());
            return new ModelAndView(Constants.CREATE_CLIENT_JSP, Constants.
                    CLIENT, client);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.
                    ERROR_MESSAGE, Constants.CLIENT_ADD_EXCEPTION);
        }
    }

    /**
     * This Method is used to create a new Client using the details given by
     * the user. Redirects to error Page if any Exception occurs.
     *
     * @param client a Client object containing the details of the client to
     *                be added.
     *
     * @param idOfSelectedProjects an Array of Integer indicating the id of the
     *                              projects to be added to the employee.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.ADD_CLIENT_MAPPING, method=RequestMethod.POST)
    private ModelAndView addClient(Model model,
            @RequestParam (required=false,value=Constants.ID_OF_PROJECTS) Integer[] idOfSelectedProjects,
            @ModelAttribute Client client) {

        try {
            if (null != idOfSelectedProjects) {
                client.setProjects(clientService.retrieveProjectsByIds(idOfSelectedProjects));
            }
            if (!clientService.addClient(client)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.CLIENT_ADDITION_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.CLIENT_ADD_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_CLIENT_JSP, Constants.
                    CLIENT, client);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_ADDITION_EXCEPTION, client.getName()));
        }
    }

    /**
     *  <p>
     *  Method to modify an existing Client. Fetches the client from and
     *  returns it to the view layer in a ModelAndView Object.
     *  Redirects to Error Page if any exception occurs.
     *  </p>
     *
     * @param id an Integer indicating the id of the client to be updated.
     *
     * @param model a Model Object to add a new Client as an attribute.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.MODIFY_CLIENT_MAPPING, method=RequestMethod.GET)
    private ModelAndView modifyClient(@RequestParam(Constants.ID) int id,
                                      Model model) {
        try {
            Client client = clientService.retrieveClientById(id);
            model.addAttribute(Constants.PROJECTS,
                    clientService.retrieveUnassignedProjects());
            model.addAttribute(Constants.ASSIGNED_PROJECTS, client.getProjects());
            return new ModelAndView(Constants.CREATE_CLIENT_JSP, Constants.
                    CLIENT, client);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_EDIT_EXCEPTION, id));
        }
    }

    /**
     * <p>
     *  This Method is used update the details of an existing client.
     *  Redirects to ErrorPage if any Exception occurs.
     * </p>
     *
     * @param client a Client object containing the details updated by the
     *                user.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.UPDATE_CLIENT_MAPPING, method=RequestMethod.POST)
    private ModelAndView updateClient(Model model,
            @RequestParam (required=false,value=Constants.ID_OF_PROJECTS) Integer[] idOfSelectedProjects,
            @ModelAttribute Client client) {

        try {
            if (null != idOfSelectedProjects) {
                client.setProjects(clientService.retrieveProjectsByIds(idOfSelectedProjects));
            }
            if (!clientService.modifyClient(client)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.CLIENT_EDIT_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.CLIENT_UPDATE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_CLIENT_JSP,Constants.
                    CLIENT, client);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_EDIT_EXCEPTION, client.getId()));
        }
    }

    /**
     * <P>
     * This Method is used to search for a client by the Id given by the user.
     * Redirects to Error Page if any exception occurs.
     *
     * @param id an Integer indicating the id of the client to be retrieved.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.SEARCH_CLIENT_MAPPING, method=RequestMethod.GET)
    private ModelAndView searchClient(@RequestParam(Constants.ID) int id){
        try {
            Client client = clientService.retrieveClientById(id);
            if (null == client) {
                return new ModelAndView(Constants.SEARCH_CLIENT_JSP,Constants.FAIL_MESSAGE,
                        String.format(Constants.CLIENT_SEARCH_FAIL_MESSAGE, id));
            }
            return new ModelAndView(Constants.SEARCH_CLIENT_JSP, Constants.
                   CLIENT, client);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_SEARCH_EXCEPTION, id));
        }
    }

    /**
     * This Method is used to activate deleted clients. Redirects to error Page
     * if any exception occurs.
     *
     * @param id an Integer indicating the id of the client to be restored.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.RESTORE_CLIENT_MAPPING, method=RequestMethod.POST)
    private ModelAndView restoreClient(@RequestParam(Constants.ID) int id, Model model) {
        try {
            if (clientService.restoreClient(id)) {
                model.addAttribute(Constants.MESSAGE, Constants.CLIENT_RESTORE_SUCCESS_MESSAGE);
                return new ModelAndView(Constants.SEARCH_CLIENT_JSP,Constants.
                        CLIENT, clientService.retrieveClientById(id));
            } else {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.CLIENT_EDIT_EXCEPTION);
            }
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.CLIENT_RESTORE_EXCEPTION, id));
        }
    }

    /**
     * This Method is used to remove an existing client by Id given by the
     * user.
     *
     * @param idToDelete an Integer indicating the id of the client to be
     *                   deleted.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.DELETE_CLIENT_MAPPING, method=RequestMethod.POST)
    private ModelAndView deleteClient(@RequestParam(Constants.ID) int idToDelete, Model model) {
        try {
            if (!clientService.deleteClient(idToDelete)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.CLIENT_DELETE_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.CLIENT_DELETE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_CLIENT_JSP, Constants.
                    CLIENT, clientService.retrieveClientById(idToDelete));
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_DELETE_EXCEPTION, idToDelete));
        }
    }

    /**
     * This Method is used to redirect to jsp page to display projects and
     * obtain the projects to be assigned from the user. Redirects to Error Page
     * if any exception occurs.
     *
     * @param clientId an Integer indicating the id of the client to which the
     *                 projects are to be assigned.
     *
     * @param model a Model Object to add the client as an attribute.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.OBTAIN_PROJECTS_MAPPING, method=RequestMethod.GET)
    private ModelAndView obtainProjectsFromUser(
            @RequestParam(Constants.ID) int clientId,
            Model model) {
        try {
            List<Project> projects = clientService.retrieveUnassignedProjects();
            model.addAttribute(Constants.CLIENT_ID, clientId);
            model.addAttribute(Constants.NUMBER_OF_PROJECTS, projects.size());
            return new ModelAndView(Constants.DISPLAY_PROJECT_JSP,Constants.
                    PROJECTS, projects);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_ASSIGN_PROJECT_EXCEPTION, clientId));
        }
    }

    /**
     * <p>
     *  This Method is used to assign projects to a client. Redirects to error
     *  Page if any Exception occurs or operation fails.
     * </p>
     *
     * @param clientId an Integer indicating the id of the client to which
     *                 projects are to be assigned.
     *
     * @param idOfSelectedProjects an Array of Integers indicating the id of
     *                              the projects to be assigned to the client.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.ASSIGN_PROJECTS_MAPPING, method=RequestMethod.POST)
    private ModelAndView assignSelectedProjects(Model model,
            @RequestParam(Constants.CLIENT_ID) int clientId,
            @RequestParam(Constants.ID_OF_PROJECTS) Integer[] idOfSelectedProjects) {
        try {
            Client client = clientService.retrieveClientById(clientId);
            clientService.assignProjectsToClient(clientService.
                    retrieveProjectsByIds(idOfSelectedProjects), client);
            model.addAttribute(Constants.MESSAGE, Constants.PROJECTS_ASSIGN_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_CLIENT_JSP,Constants.
                    CLIENT, client);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.CLIENT_ASSIGN_PROJECT_EXCEPTION, clientId));
        }
    }

    /**
     * <p>
     *  This Method is used to remove project from a client. Redirects to Error
     *  page if operation fails or any exception occurs.
     *
     * @param clientId an Integer indicating the id of the client from which an
     *                 an project is to be removed.
     *
     * @param projectId an Integer indicating the id of the project to be
     *                  unasigned.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.REMOVE_PROJECT_MAPPING, method=RequestMethod.POST)
    private ModelAndView removeProjectFromClient(Model model,
            @RequestParam(Constants.CLIENT_ID) int clientId,
            @RequestParam(Constants.PROJECTID) int projectId) {
        try {
            Client client = clientService.retrieveClientById(clientId);
            Project project = clientService.retrieveProjectById(projectId);
            if (clientService.removeProjectFromClient(project, client)) {
            model.addAttribute(Constants.MESSAGE, Constants.PROJECT_REMOVE_SUCCESS_MESSAGE);
                return new ModelAndView(Constants.SEARCH_CLIENT_JSP, Constants.
                        CLIENT, client);
            }
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_REMOVE_PROJECT_EXCEPTION, clientId));
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                String.format(Constants.CLIENT_REMOVE_PROJECT_EXCEPTION, clientId));
        }
    }

    /**
     * This Method is used to View all added clients of a company.
     * Redirects to Error Page if any error occurs while retrieving all the
     * projects.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.DISPLAY_CLIENT_MAPPING, method=RequestMethod.GET)
    private ModelAndView displayAllClients(Model model) {
        try {
            List<Client> clients = clientService.retrieveAllClients();
            model.addAttribute(Constants.NUMBER_OF_CLIENTS, clients.size());
            return new ModelAndView(Constants.DISPLAY_CLIENT_JSP,Constants.
                    CLIENTS, clients);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    Constants.CLIENT_DISPLAY_EXCEPTION);
        }
    }
}
