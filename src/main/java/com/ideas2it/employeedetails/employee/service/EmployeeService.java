package com.ideas2it.employeedetails.employee.service;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;

/**
 * <p>
 * EmployeeServiceImpl is a Service-class used to provide logic to Employee operations.
 * All the methods in this class throws Application Exception. Exceptions are
 * thrown if the input data is null.
 * Beans are injected during runtime using Spring IOC.
 * </p>
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public interface EmployeeService {

    /**
     * <p>
     * This Method is used to add a new employee using the details provided by
     * the user. Returns true if the entry is added successfully, else returns
     * false.
     * </p>
     *
     * @param employee an Employee object containing all the details of the
     *                 employee to be added.
     *
     * @return Boolean a boolean indicating the id of Employee that is added.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while adding an
     *                              employee.
     */
    public Boolean addEmployee(Employee employee) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to modify the details of an existing employee with
     *  updated details provided by the user. Returns true if the employee 
     *  information is updated, else returns false if the operation fails.
     * </p>
     *
     * @param newEmployee an Employee object is passed with the id of the old
     *                    Employee which is used as a reference.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              employee.
     */
    public boolean modifyEmployee(Employee newEmployee)
            throws ApplicationException;

    /**
     * <p>
     *  This Method is used to re-activate an employee who has been deleted.
     *  Returns true if the operation is successful.
     * </p>
     *
     * @param id an integer indicating the id of the employee to be reinstated.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              employee.
     */
    public boolean restoreEmployee(int id) throws ApplicationException;

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
    public boolean deleteEmployee(Integer id) throws ApplicationException;

    /**
     * <p>
     * This Method is used to search an Employee Entry and return the Employee
     * object. It returns null if no match is found.
     * </p>
     *
     * @param id an Integer indicating the id of the employee that is
     *           to be searched an returned.
     *
     * @return employee an Employee object is returned if a valid match is
     *                  found, else returns null.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving an
     *                              employee.
     */
    public Employee retrieveEmployeeById(Integer id) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to obtain all the employee details using an
     *  list. Returns an empty list if no employees are added.
     * </p>
     *
     * @return employees a list consisting of all the employees that are
     *                   added.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while retrieving all
     *                              employees.
     */
    public List<Employee> retrieveAllEmployees() throws ApplicationException;

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

}

