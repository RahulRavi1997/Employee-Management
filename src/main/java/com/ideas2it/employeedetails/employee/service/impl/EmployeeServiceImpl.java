package com.ideas2it.employeedetails.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.ideas2it.employeedetails.utils.DateUtil;
import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.dao.EmployeeDao;
import com.ideas2it.employeedetails.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.employee.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    public static EmployeeDao employeeDao = null;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeDao getEmployeeDao() {
        return this.employeeDao;
    }

    /**
     *  {@inheritDoc}
     */
    public Boolean addEmployee(Employee employee) throws ApplicationException {

        return employeeDao.insertEmployee(employee);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean modifyEmployee(Employee employee)
            throws ApplicationException {

        if (null == employee) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return employeeDao.updateEmployee(employee);
    }

    /**
     *  {@inheritDoc}
     */
    public boolean restoreEmployee(int id) throws ApplicationException {
        Employee employee = retrieveEmployeeById(id);
        if(null == employee) {
            return Boolean.FALSE;
        }
        return employeeDao.restoreEmployee(employee);
    }


    /**
     *  {@inheritDoc}
     */
    public boolean deleteEmployee(Integer id)
            throws ApplicationException {

        if (null == id) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        Employee employee = retrieveEmployeeById(id);
        if(null == employee) {
            return Boolean.FALSE;
        }
        employee.getWorkingProjects().clear();
        return employeeDao.deleteEmployee(employee);
    }

    /**
     *  {@inheritDoc}
     */
    public Employee retrieveEmployeeById(Integer id)
            throws ApplicationException {

        if (null == id) {
            throw new ApplicationException(Constants.ID_NOT_NULL);
        }
        return employeeDao.searchEmployeeById(id);
    }

    /**
     *  {@inheritDoc}
     */
    public List<Employee> retrieveAllEmployees() throws ApplicationException {

        return employeeDao.getAllEmployees();
    }

    /**
     *  {@inheritDoc}
     */
    public List<Employee> retrieveEmployeesByIds(Integer[] ids) throws ApplicationException {
        return employeeDao.getEmployeesByIds(ids);
    }
}

