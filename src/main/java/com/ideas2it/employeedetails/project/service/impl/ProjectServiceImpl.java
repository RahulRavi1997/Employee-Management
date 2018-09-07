package com.ideas2it.employeedetails.project.service.impl;

import java.util.List;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.employee.service.EmployeeService;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.project.service.ProjectService;
import com.ideas2it.employeedetails.project.dao.ProjectDao;

/**
 * <p>
 * ProjectServiceImpl is a Service-class used to provide logic to Project operations.
 * All the methods in this class throws Application Exception. Exceptions are
 * thrown if the input data is null.
 * Beans are injected during runtime using Spring IOC.
 * </p>
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao = null;
    public EmployeeService employeeService = null;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public ProjectDao getProjectDao() {
        return this.projectDao;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeService getEmployeeService() {
        return this.employeeService;
    }

    /**
     *  {@inheritDoc}
     */
    public Boolean addProject(Project project) throws ApplicationException {

        return projectDao.insertProject(project);

    }

    /**
     *  {@inheritDoc}
     */
    public boolean assignEmployeesToProject(List<Employee> employees,
            Project project)  throws ApplicationException {

        project.getProjectMembers().addAll(employees);
        return projectDao.updateProject(project);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean removeEmployeeFromProject(Employee employee,
            Project project)  throws ApplicationException {

        project.getProjectMembers().remove(employee);
        return projectDao.updateProject(project);

    }

    /**
     *  {@inheritDoc}
     */
    public boolean restoreProject(int id) throws ApplicationException {
        Project project = retrieveProjectById(id);
        if(null == project) {
            return Boolean.FALSE;
        }
        return projectDao.restoreProject(project);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean removeEmployeesFromProject(List<Employee> employees,
            Project project)  throws ApplicationException {

        project.getProjectMembers().removeAll(employees);
        return projectDao.updateProject(project);

    }

    /**
     *  {@inheritDoc}
     */
    public boolean modifyProject(Project project) throws ApplicationException {

        if (null == project) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return projectDao.updateProject(project);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean removeProject(Integer id) throws ApplicationException {

        if (null == id) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        Project project = retrieveProjectById(id);
        if(null == project) {
            return Boolean.FALSE;
        }
        project.getProjectMembers().removeAll(project.getProjectMembers());
        project.setNumberOfResources(0);
        project.setClientId(null);
        return projectDao.deleteProject(project);
    }

    /**
     *  {@inheritDoc}
     */
    public Project retrieveProjectById(Integer id) throws ApplicationException {

        if (null == id) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return projectDao.searchProjectById(id);
    }

    /**
     *  {@inheritDoc}
     */
    public List<Project> retrieveAllProjects() throws ApplicationException {

        return projectDao.getAllProjects();
    }

    /**
     *  {@inheritDoc}
     */
    public List<Project> retrieveUnassignedProjects() throws ApplicationException {

        return projectDao.getUnassignedProjects();
    }
    /**
     *  {@inheritDoc}
     */
    public List<Employee> retrieveAllEmployees() throws ApplicationException {

        return employeeService.retrieveAllEmployees();
    }

    /**
     *  {@inheritDoc}
     */
    public Employee retrieveEmployeeById(Integer id)
            throws ApplicationException {

        return employeeService.retrieveEmployeeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Employee> retrieveEmployeesByIds(Integer[] ids) throws ApplicationException {

        return employeeService.retrieveEmployeesByIds(ids);
    }

    /**
     * {@inheritDoc}
     */
    public List<Project> retrieveProjectsByIds(Integer[] ids) throws ApplicationException {

        return projectDao.getProjectsByIds(ids);
    }
}
