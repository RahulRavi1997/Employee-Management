package com.ideas2it.employeedetails.project.dao;

import java.util.List;

import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;

/**
 * <p>
 * ProjectDao is a Data-Access Object interface, which is used to provide
 * data access classes for the base class to implement database manipulation
 * operations on Project Data.It uses an Database table of Project Information
 * to store the project Information.
 * </p>
 * Each project  can have multiple Employees as members but only a single
 * employee as team leader.
 * All the methods in this class throw a custom Application Exception.
 *
 * @author  : Rahul Ravi
 * @version : 1.0
 */
public interface ProjectDao {

    /**
     * <p>
     * This Method is used to add a new Project entry into the Database. Returns
     * true if the entry is added successfully, else returns false.
     * </p>
     *
     * @param project an Project object containing the details of the project.
     *                Employees are not added during creation.
     *
     * @return Boolean a boolean value containing the id of the added project.
     *                 Returns 0 if addition fails.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while inserting a
     *                              project.
     */
    public Boolean insertProject(Project project) throws ApplicationException ;

    /**
     * <p>
     * This method is used to modify the details of a project and update them
     * in the DataBase. Returns true if the entry is modified, else returns
     * false. Does not affect its employees.
     * </p>
     *
     * @param newProject a Project object is passed with the updated project
     *                   details.
     *                
     * @return boolean a boolean value is returned whether the operation to
     *                  modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while updating a
     *                              project.
     */
    public boolean updateProject(Project newProject)throws ApplicationException;

    /**
     * <p>
     * This method is used to delete a project and delete that entry in the
     * Database. Returns true if the entry is deleted, else returns false.
     * </p>
     *
     * @param project a Project that is to be deleted.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 remove is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while deleting a
     *                              project.
     */
    public boolean deleteProject(Project project) throws ApplicationException ;


    /**
     * <p>
     *  This Method is used to re-activate a project who has been deleted.
     *  Returns true if the operation is successful.
     * </p>
     *
     * @param project a Project Object to be restored.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              project.
     */
    public boolean restoreProject(Project project) throws ApplicationException;


    /**
     * <p>
     * This method is used to search a Project. If the match is found, it
     * returns the project, else returns false.
     * </p>
     *
     * @param id an Integer to identify the required project to be
     *           searched.
     *
     * @return project a Project object is returned if a match is found. An
     *                 empty project is returned if search operation fails.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while searching a
     *                              project.
     */
    public Project searchProjectById(Integer id) throws ApplicationException ;

    /**
     * <p>
     *  This project is used to obtain all the projects that have been created.
     *  Returns empty arraylist if no projects exists.
     * </p>
     *
     * @return list an Arraylist consisting of all the projects.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while obtaining all
     *                              projects.
     */
    public List<Project> getAllProjects() throws ApplicationException ;

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
    public List<Project> getUnassignedProjects() throws ApplicationException ;

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
    public List<Project> getProjectsByIds(Integer[] ids) throws ApplicationException;

}
