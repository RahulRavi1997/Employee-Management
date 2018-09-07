package com.ideas2it.employeedetails.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.employee.dao.EmployeeDao;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.GenericDao;
import com.ideas2it.employeedetails.logger.Logger;
/**
 * <p>
 * EmployeeDaoImpl is a Data-Access-Object Class for executing data manipulation
 * operation on employee data such as add, update, remove and search.
 * </p>
 * <p>
 * All the methods in this class throws Application Exception- a custom exception
 * wrapping any exceptions thrown by Hibernate.
 * Soft-delete is done while deleting an employee, setting the status as inactive.
 * </p>
 * 
 * @author    Rahul Ravi
 * @version   1.0
 */

public class EmployeeDaoImpl extends GenericDao implements EmployeeDao {

    String EMPLOYEE_IN_QUERY = "from Employee where id in (:ids)";
    String IDS = "ids";
    public EmployeeDaoImpl() throws ApplicationException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public Boolean insertEmployee(Employee employee) throws ApplicationException {

        employee.setActive(Boolean.TRUE);
        try {
            return (null != super.save(employee));
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.
                    EMPLOYEE_ADDITION_EXCEPTION, employee.getName()), e);
            throw new ApplicationException(String.format(Constants.
                    EMPLOYEE_ADDITION_EXCEPTION, employee.getName()), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployee(Employee employee) throws ApplicationException {

        try {
            return super.update(employee);
        } catch (ApplicationException e) {
            Logger.error(String.format(
                Constants.EMPLOYEE_EDIT_EXCEPTION, employee.getId()), e);
            throw new ApplicationException(String.format(
                Constants.EMPLOYEE_EDIT_EXCEPTION, employee.getId()), e);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public boolean deleteEmployee(Employee employee)
            throws ApplicationException {

        employee.setActive(Boolean.FALSE);
        return updateEmployee(employee);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean restoreEmployee(Employee employee)
            throws ApplicationException {

        employee.setActive(Boolean.TRUE);
        return updateEmployee(employee);
    }


    /**
     *  {@inheritDoc}
     */
    public Employee searchEmployeeById(Integer id) throws ApplicationException {

        try {
            return super.get(Employee.class, id);
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.EMPLOYEE_SEARCH_EXCEPTION, id), e);
            throw new ApplicationException(String.format(Constants.EMPLOYEE_SEARCH_EXCEPTION, id));
        }
    }

    /**
     *  {@inheritDoc}
     */
    public List<Employee> getAllEmployees() throws ApplicationException {

        try {
            return super.getAll(Employee.class);
        } catch (ApplicationException e) {
            Logger.error(Constants.EMPLOYEE_DISPLAY_EXCEPTION, e);
            throw new ApplicationException(Constants.EMPLOYEE_DISPLAY_EXCEPTION, e);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public List<Employee> getEmployeesByIds(Integer[] ids) throws ApplicationException {

        try {
            Session session = super.getSession();
            Query query = session.createQuery(EMPLOYEE_IN_QUERY);  
            query.setParameterList(IDS, ids); 
            return query.list();
        } catch (ApplicationException e) {
            Logger.error(Constants.EMPLOYEE_DISPLAY_EXCEPTION, e);
            throw new ApplicationException(Constants.EMPLOYEE_DISPLAY_EXCEPTION, e);
        }
    }
}
