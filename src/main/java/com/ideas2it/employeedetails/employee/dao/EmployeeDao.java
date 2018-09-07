package com.ideas2it.employeedetails.employee.dao;

import java.util.List;

import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.project.model.Project;

/**
 * <p>
 * EmployeeDao is a Data-Access-Object interface, which is used to provide
 * data access to implement database manipulation operations on employee data.
 * It uses a table from Database to store the employee Information.
 * </p>
 * <p>
 * All the data manipulation operations such as creation, removal, updation,
 * and search are done in this layer.
 * </p>
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public interface EmployeeDao {

    /**
     * <p>
     * This Method is used to add the employee details given by the user and
     * adds them to the Database. It returns true if the entry is added
     * successfully, else returns false.
     * </p>
     *
     * @param employee an Employee object consisting of the details of the
     *                 new employee provided by the user.
     *                 
     * @return Boolean a boolean indicating the id of the Employee that is inserted.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while adding an employee.
     */
    public Boolean insertEmployee(Employee employee) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to update the details of an Employee. Returns true
     *  if the entry is modified, else returns false.
     *  <p>
     *
     * @param employee an Employee object is passed with the details to be
     *                 updated.
     *
     * @return boolean a boolean value is returned whether the operation is
     *                 successful or not.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while updating an employee.
     */
    public boolean updateEmployee(Employee employee) throws ApplicationException;

    /**
     * <p>
     * This Method is used to delete an Employee. Returns true if the entry is
     * removed, else returns false.
     * </p>
     *
     * @param employee an Employee object that is to be deleted from the
     *                 database table.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 remove is successful or not.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while deleting an employee.
     */
    public boolean deleteEmployee(Employee employee) throws ApplicationException;

    /**
     * <p>
     * This Method is used to search for an employee. Returns the employee
     * object if a match is found with same id, else returns null.
     * </p>
     *
     * @param id         an Integer to identify the required Employee
     *                   Object by the ID of Employee.
     *
     * @return employee  an Employee object which has same id is returned.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while searching an employee.
     */
    public Employee searchEmployeeById(Integer id) throws ApplicationException;

    /**
     * <p>
     *  This Method is used to re-activate an employee who has been deleted.
     *  Returns true if the operation is successful.
     * </p>
     *
     * @param employee an Employee object to be restored.
     *
     * @return boolean a boolean value is returned whether the operation to
     *                 modify is successful or not.
     *
     * @throws ApplicationException A Custom Exception created for catching
     *                              exceptions that occur while modifying an
     *                              employee.
     */
    public boolean restoreEmployee(Employee employee) throws ApplicationException;

    /**
     * <p>
     * This Method is used to obtain an arraylist of all the employees that
     * are currently present.
     * </p>
     *
     * @return employees an Arraylist consisting of all the employees is
     *                   returned.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while displaying all employees.
     */
    public List<Employee> getAllEmployees() throws ApplicationException;

    /**
     * <p>
     * This Method is used to obtain an arraylist of all selected employees.
     * </p>
     *
     * @return ids an Array of integers containing the id of employees that are
     *             to be retrieved.
     *
     * @throws ApplicationException An exception created for catching exceptions
     *                              that occur while displaying all employees.
     */
    public List<Employee> getEmployeesByIds(Integer[] ids) throws ApplicationException;
}
