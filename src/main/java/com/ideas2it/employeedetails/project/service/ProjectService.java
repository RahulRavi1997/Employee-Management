package com.ideas2it.employeedetails.project.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;

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
public interface ProjectService {

    /**
     * <p>
     * This Method is used to add a new Project. Returns true if the entry
     * is added successfully, else returns false.
     * </p>
     *
     * @param project an Project object containing the details of the project.
     *                Employees are not added during creation.
     *
     * @return Boolean a Boolean value indicating successfull addition of employee.
     *                 Returns false if addition fails.
     * 
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while adding a project.
     */
    public Boolean addProject(Project project) throws ApplicationException ;



    /**
     * <p>
     *  This method is used to assign employees to a Project. The role of the
     *  employee is either Leader or Member. Returns true if the entry is
     *  modified, else returns false.
     * </p>
     *
     * @param employees a list of employee Object to be added to a certain
     *                  project.
     *
     * @param project a project Object containing the project into which the
     *                employee is added.
     *
     * @return boolean a boolean value is returned whether the operation
     *                 to modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while assigning
     *                              employees to a project.
     */
    public boolean assignEmployeesToProject(List<Employee> employees, 
            Project project) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to remove Employees from a Project. Returns true if
     *  the entry is modified, else returns false.
     * </p>
     *
     * @param employees a list of employees to be removed from the project
     *
     * @param project a project Object from which the employees is removed.
     *
     * @return boolean a boolean value is returned whether the operation
     *                 to modify is successful or not
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              to a that occur while removing an employee
     *                              from a project.
     */
    public boolean removeEmployeesFromProject(List<Employee> employees,
            Project project) throws ApplicationException;


    /**
     * <p>
     *  This Method is used to remove Employees from a Project. Returns true if
     *  the entry is modified, else returns false.
     * </p>
     *
     * @param employee an employee to be removed from the project
     *
     * @param project a project Object from which the employees is removed.
     *
     * @return boolean a boolean value is returned whether the operation
     *                 to modify is successful or not
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              to a that occur while removing an employee
     *                              from a project.
     */
    public boolean removeEmployeeFromProject(Employee employee,
            Project project) throws ApplicationException;


    /**
     * <p>
     *  This Method is used to re-activate a project who has been deleted.
     *  Returns true if the operation is successful.
     * </p>
     *
     * @param id an integer indicating the id of the project to be reinstated.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              project.
     */
    public boolean restoreProject(int id) throws ApplicationException;

    /**
     * <p>
     * This method is used to modify the details of a project. Returns true if
     * the entry is modified, else returns false. Does not affect its employees.
     * </p>
     *
     * @param newProject a Project object is passed with the updated project
     *                   details.
     *                
     * @return  boolean a boolean value is returned whether the operation to
     *                  modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying a
     *                              project.
     */
    public boolean modifyProject(Project project) throws ApplicationException;

    /**
     * <p>
     * This method is used to delete a project. Returns true if the entry is
     * deleted, else returns false.
     * </p>
     *
     * @param Integer the id of the Project that is to be deleted.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 remove is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while removing a
     *                              project.
     */
    public boolean removeProject(Integer id) throws ApplicationException;

    /**
     * <p>
     * This method is used to search a Project. If the match is found, it
     * returns the project, else returns false.
     * </p>
     *
     * @param id an Integer to identify the required project to be searched.
     *
     * @return project a Project object is returned if a match is found. An
     *                 empty project is returned if search operation fails.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while searching for
     *                              a project.
     */
    public Project retrieveProjectById(Integer id) throws ApplicationException;

    /**
     * <p>
     *  This project is used to obtain all the projects that have been created.
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
     * This method is used to obtain the List of employees from the Employee 
     * service class for adding them to the Project.
     * </p>
     *
     * @return employees an Arraylist of Employees is returned.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving all
     *                              projects.
     */
    public List<Employee> retrieveAllEmployees() throws ApplicationException;

    /**
     * <p>
     * This Method is used to delete an employee entry. Returns true if the 
     * entry is removed, else returns false if the entry is not found.
     * </p>
     *
     * @param id an Integer containing the id of the Employee object which is
     *           to be deleted.
     *
     * @return boolean a boolean value is returned whether the removal of the
     *                 employee is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while deleting an
     *                              employee.
     */
    public Employee retrieveEmployeeById(Integer id) throws ApplicationException;

    /**
     * <p>
     * This Method is used to retrieve an list of all selected employees.
     * </p>
     *
     * @return ids an Array of integers containing the id of employees that are
     *             to be retrieved.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while displaying all employees.
     */
    public List<Employee> retrieveEmployeesByIds(Integer[] ids) throws ApplicationException;

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

