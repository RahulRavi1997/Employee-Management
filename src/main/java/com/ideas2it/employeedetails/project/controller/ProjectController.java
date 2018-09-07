package com.ideas2it.employeedetails.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.project.service.ProjectService;
/**
 * <p>
 * ProjectController is a Controller Class, which is used to implement data
 * storage and retrieval operations on Project Data. Each Project can contain
 * multiple employees working on it. It uses Spring Mvc to map the
 * methods to URL of requests from the View Layer.
 * </p>
 * Beans are injected during runtime using Spring Dependency Injection.
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
@Controller
public class ProjectController {

    private ProjectService projectService = null;

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public ProjectService getProjectService() {
        return this.projectService;
    }

    /**
     *  This Method is used to redirect user to jsp page with the form used to
     *  create a new Project. Redirects to Error Page if any exception occurs.
     *
     * @param model a Model Object to add a new Project as an attribute.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.CREATE_PROJECT_MAPPING, method=RequestMethod.GET)
    private ModelAndView redirectToCreateProject(Model model) {
        try {
            model.addAttribute(Constants.PROJECT, new Project());
            return new ModelAndView(Constants.CREATE_PROJECT_JSP, Constants.
                    EMPLOYEES, projectService.retrieveAllEmployees());
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.
                    ERROR_MESSAGE, Constants.PROJECT_ADD_EXCEPTION);
        }
    }

    /**
     * This Method is used to create a new Project using the details given by
     * the user. Redirects to error Page if any Exception occurs.
     *
     * @param project a Project object containing the details of the project to
     *                be added.
     *
     * @param idOfSelectedEmployees an Array of Integer indicating the id of the
     *                              employees to be added to the project.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.ADD_PROJECT_MAPPING, method=RequestMethod.POST)
    private ModelAndView addProject(@ModelAttribute Project project, Model model, 
        @RequestParam (required=false, value=Constants.ID_OF_EMPLOYEES) Integer[] idOfSelectedEmployees) {

        try {
            List<Employee> employeesToAssign = new ArrayList<Employee>();
            if (null != idOfSelectedEmployees) {
                project.setProjectMembers(projectService.retrieveEmployeesByIds(idOfSelectedEmployees));
            }
            if (!projectService.addProject(project)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.PROJECT_ADDITION_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.PROJECT_ADD_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_PROJECT_JSP, Constants.
                    PROJECT, project);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.
                    ERROR_MESSAGE, String.format(Constants.PROJECT_ADDITION_EXCEPTION, project.getName()));
        }
    }

    /**
     *  <p>
     *  Method to modify an existing Project. Fetches the project from and
     *  returns it to the view layer in a ModelAndView Object.
     *  Redirects to Error Page if any exception occurs.
     *  </p>
     *
     * @param model a Model Object to add a new Project as an attribute.
     *
     * @param id an Integer indicating the id of the project to be updated.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.MODIFY_PROJECT_MAPPING, method=RequestMethod.GET)
    private ModelAndView modifyProject(@RequestParam(Constants.ID) int id, Model model) {
        try {
            Project project = projectService.retrieveProjectById(id);
            List<Employee> employees = projectService.retrieveAllEmployees();
            if (null != project.getProjectMembers()) {
                employees.removeAll(project.getProjectMembers());
            }
            model.addAttribute(Constants.ASSIGNED_EMPLOYEES, project.getProjectMembers());
            model.addAttribute(Constants.PROJECT, project);
            return new ModelAndView(Constants.CREATE_PROJECT_JSP, Constants.
                    EMPLOYEES, employees);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_EDIT_EXCEPTION, id));
        }
    }

    /**
     * <p>
     *  This Method is used update the details of an existing project.
     *  Redirects to ErrorPage if any Exception occurs.
     * </p>
     *
     * @param project a Project object containing the details updated by the
     *                user.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.UPDATE_PROJECT_MAPPING, method=RequestMethod.POST)
    private ModelAndView updateProject(@ModelAttribute Project project, Model model,
            @RequestParam(required=false, value=Constants.ID_OF_EMPLOYEES) Integer[] idOfSelectedEmployees) {

        try {
            project.getProjectMembers().clear();
            if (null != idOfSelectedEmployees) {
                project.getProjectMembers().addAll(projectService.retrieveEmployeesByIds(idOfSelectedEmployees));
            }
            if (!projectService.modifyProject(project)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.PROJECT_EDIT_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.PROJECT_UPDATE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_PROJECT_JSP,Constants.
                    PROJECT, project);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_EDIT_EXCEPTION, project.getId()));
        }
    }

    /**
     * This Method is used to activate deleted projects. Redirects to error Page
     * if any exception occurs.
     *
     * @param id an Integer indicating the id of the project to be restored.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.RESTORE_PROJECT_MAPPING, method=RequestMethod.POST)
    private ModelAndView restoreProject(@RequestParam(Constants.ID) int id, Model model) {
        try {
            if (projectService.restoreProject(id)) {
                model.addAttribute(Constants.MESSAGE, Constants.PROJECT_RESTORE_SUCCESS_MESSAGE);
                return new ModelAndView(Constants.SEARCH_PROJECT_JSP,Constants.
                        PROJECT, projectService.retrieveProjectById(id));
            } else {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.PROJECT_EDIT_EXCEPTION);
            }
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_RESTORE_EXCEPTION, id));
        }
    }

    /**
     * <P>
     * This Method is used to search for a project by the Id given by the user.
     * Redirects to Error Page if any exception occurs.
     *
     * @param id an Integer indicating the id of the project to be retrieved.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.SEARCH_PROJECT_MAPPING, method=RequestMethod.GET)
    private ModelAndView searchProject(@RequestParam(Constants.ID) int id){
        try {
            Project project = projectService.retrieveProjectById(id);
            if (null == project) {
                return new ModelAndView(Constants.SEARCH_PROJECT_JSP, Constants.FAIL_MESSAGE,
                        String.format(Constants.PROJECT_SEARCH_FAIL_MESSAGE, id));
            }
            return new ModelAndView(Constants.SEARCH_PROJECT_JSP, Constants.
                    PROJECT, project);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_SEARCH_EXCEPTION, id));
        }
    }

    /**
     * This Method is used to delete an existing project by Id given by the
     * user. Redirects to Error Page if any Exception occurs and Delete Operation
     * fails.
     *
     * @param idToDelete an Integer indicating the id of the project to be deleted.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.DELETE_PROJECT_MAPPING, method=RequestMethod.POST)
    private ModelAndView deleteProject(@RequestParam(Constants.ID) int idToDelete, Model model) {
        try {
            if (!projectService.removeProject(idToDelete)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.PROJECT_DELETE_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.PROJECT_DELETE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_PROJECT_JSP, Constants.
                    PROJECT, projectService.retrieveProjectById(idToDelete));
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_EDIT_EXCEPTION, idToDelete));
        }
    }

    /**
     * <p>
     *  This Method is used to remove employee from a project. Redirects to Error
     *  page if operation fails or any exception occurs.
     *
     * @param projectId an Integer indicating the id of the project from which an
     *                  an employee is to be removed.
     *
     * @param employeeId an Integer indicating the id of the project to be
     *                   unassigned.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.REMOVE_EMPLOYEE_MAPPING, method=RequestMethod.POST)
    private ModelAndView removeEmployeeFromProject(Model model,
            @RequestParam(Constants.PROJECTID) int projectId,
            @RequestParam(Constants.EMPLOYEE_ID) int employeeId) {
        try {
            Project project = projectService.retrieveProjectById(projectId);
            project.setNumberOfResources(project.getNumberOfResources() - 1);
            projectService.removeEmployeeFromProject(
                    projectService.retrieveEmployeeById(employeeId), project);
            model.addAttribute(Constants.MESSAGE, Constants.EMPLOYEE_REMOVE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_PROJECT_JSP, Constants.
                    PROJECT, project);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_REMOVE_EMPLOYEE_EXCEPTION, projectId));
        }
    }

    /**
     * This Method is used to View all added projects of a company.
     * Redirects to Error Page if any error occurs while retrieving all the
     * projects.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.DISPLAY_PROJECT_MAPPING, method=RequestMethod.GET)
    private ModelAndView displayAllProjects(Model model) {

        try {
            List<Project> projects = projectService.retrieveAllProjects();
            model.addAttribute(Constants.PROJECTS, projects);
            return new ModelAndView(Constants.DISPLAY_PROJECT_JSP,
                Constants.NUMBER_OF_PROJECTS, projects.size());
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.
                    ERROR_MESSAGE, Constants.PROJECT_DISPLAY_EXCEPTION);
        }
    }

    /**
     * This Method is used to redirect to jsp page to display employees and
     * obtain the employees to be assigned from the user. Redirects to Error Page
     * if any exception occurs.
     *
     * @param projectId an Integer indicating the id of the project to which the
     *                  employees are to be assigned.
     *
     * @param model a Model Object to add the project as an attribute.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.OBTAIN_EMPLOYEES_MAPPING, method=RequestMethod.GET)
    private ModelAndView obtainEmployees(@RequestParam(Constants.ID) int projectId,
            Model model) {
        try {
            List<Employee> employees = projectService.retrieveAllEmployees();
            Project project = projectService.retrieveProjectById(projectId);
            if (null != project.getProjectMembers()) {
                employees.removeAll(project.getProjectMembers());
            }
            model.addAttribute(Constants.PROJECT, project);
            model.addAttribute(Constants.NUMBER_OF_EMPLOYEES, employees.size());
            return new ModelAndView(Constants.DISPLAY_EMPLOYEE_JSP, Constants.
                    EMPLOYEES, employees);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.PROJECT_ASSIGN_EMPLOYEE_EXCEPTION, projectId));
        }
    }

    /**
     * <p>
     *  This Method is used to assign employees to a project. Redirects to error
     *  Page if any Exception occurs or operation fails.
     * </p>
     *
     * @param projectId an Integer indicating the id of the project to which
     *                  employees are to be assigned.
     *
     * @param idOfSelectedEmployees an Array of Integers indicating the id of
     *                              the employees to be assigned to the project.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.ASSIGN_EMPLOYEES_MAPPING, method=RequestMethod.POST)
    private ModelAndView assignSelectedEmployees(
            @RequestParam(Constants.PROJECTID) int projectId, Model model,
            @RequestParam(Constants.ID_OF_EMPLOYEES) Integer[] idOfSelectedEmployees) {
        try {
            Project project = projectService.retrieveProjectById(projectId);
            project.setNumberOfResources(project.getNumberOfResources() + idOfSelectedEmployees.length);
            projectService.assignEmployeesToProject(projectService.retrieveEmployeesByIds(idOfSelectedEmployees), project);
            model.addAttribute(Constants.MESSAGE, Constants.EMPLOYEESS_ASSIGN_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_PROJECT_JSP,Constants.
                    PROJECT, project);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                   String.format(Constants.PROJECT_ASSIGN_EMPLOYEE_EXCEPTION, projectId));
        }
    }
}
