package com.ideas2it.employeedetails.employee.controller;

import java.util.Arrays;
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

import com.ideas2it.employeedetails.address.model.Address;
import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.employee.service.EmployeeService;
import com.ideas2it.employeedetails.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.utils.DateUtil;
/**
 * <p>
 * EmployeeController is a Controller Class, which is used to implement
 * data storage and retrieval operations on Employee Data. The employees
 * can be assigned to many projects at the same time. It uses HttpServlets and
 * Spring MVC to be hosted as a web Application. Service Layer beans are
 * injected during runtime using Spring Dependency injection.
 * </p>
 * @author    Rahul Ravi
 * @version   1.0
 *
 */
@Controller
public class EmployeeController {

    public EmployeeService employeeService = null;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeService getEmployeeService() {
        return this.employeeService;
    }

    /**
     *  This Method is used to redirect user to the webpage with the form used
     *  to create and add a new Employee.
     *
     *  @param model a Model object which is used to add the employee as an
     *               attribute to the view Layer.
     *
     *  @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.CREATE_EMPLOYEE_MAPPING, method=RequestMethod.GET)
    private ModelAndView redirectToCreateEmployee() {

        Employee employee = new Employee();
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address());
        addresses.add(new Address());
        employee.setAddresses(addresses);
        return new ModelAndView(Constants.CREATE_EMPLOYEE_JSP, Constants.EMPLOYEE, employee);
    }


    /**
     *  This Method is used to add a new Employee after obtaining all the
     *  employee details from the user. Redirects to error page if any error
     *  occurs, else redirects to display page for the employee.
     *
     * @param employee an Employee object with all the details of the employee
     *                 to be added.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.ADD_EMPLOYEE_MAPPING, method=RequestMethod.POST)
    private ModelAndView createEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            if (!employeeService.addEmployee(employee)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                    ERROR_MESSAGE, Constants.EMPLOYEE_ADDITION_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.EMPLOYEE_ADD_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_EMPLOYEE_JSP, Constants.
                    EMPLOYEE, employee);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.EMPLOYEE_ADDITION_EXCEPTION, employee.getName()));
        }

    }

    /**
     * This Method is used to restore a deleted employee. Redirects to display
     * all employees on successful restoration.
     *
     * @param id an Integer indicating the id of the employee to be restored or
     *           reactivated.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.RESTORE_EMPLOYEE_MAPPING, method=RequestMethod.POST)
    private ModelAndView restoreEmployee(@RequestParam(Constants.ID) int id, Model model) {
        try {
            if (employeeService.restoreEmployee(id)) {
                model.addAttribute(Constants.MESSAGE, Constants.EMPLOYEE_RESTORE_SUCCESS_MESSAGE);
                return new ModelAndView(Constants.SEARCH_EMPLOYEE_JSP, Constants.
                        EMPLOYEE, employeeService.retrieveEmployeeById(id));
            } else {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.EMPLOYEE_EDIT_EXCEPTION);
            }
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.EMPLOYEE_RESTORE_EXCEPTION, id));
        }
    }

    /**
     *  <p>
     *  Method to update existing Employee Details. Returns true if the entry
     *  is modified successfully, else returns false if the entry is not found.
     *  </p>
     *
     * @param id an Integer indicating the id of the employee to be modified.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.MODIFY_EMPLOYEE_MAPPING, method=RequestMethod.GET)
    private ModelAndView modifyEmployee(@RequestParam(Constants.ID) int id) {
        try {
            return new ModelAndView(Constants.CREATE_EMPLOYEE_JSP,Constants.
                    EMPLOYEE, employeeService.retrieveEmployeeById(id));
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.EMPLOYEE_EDIT_EXCEPTION, id));
        }
    }

    /**
     *  <p>
     *  Method to update existing Employee Details. Returns true if the entry
     *  is modified successfully, else returns false if the entry is not found.
     *  </p>
     *
     * @param employee an Employee object with the updated details of the
     *                 employee.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.UPDATE_EMPLOYEE_MAPPING, method=RequestMethod.POST)
    private ModelAndView updateEmployee(@ModelAttribute Employee employee, Model model) {

        try {
            if (!employeeService.modifyEmployee(employee)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.EDIT_FAILED);
            }
            model.addAttribute(Constants.MESSAGE, Constants.EMPLOYEE_UPDATE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_EMPLOYEE_JSP,Constants.
                    EMPLOYEE, employee);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.EMPLOYEE_EDIT_EXCEPTION, employee.getId()));
        }

    }

    /**
     * This Method is used to remove an existing employee by Id given by the
     * user.
     *
     * @param idToDelete an Integer indicating the id of the employee to be
     *                   deleted.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.DELETE_EMPLOYEE_MAPPING, method=RequestMethod.POST)
    private ModelAndView removeEmployee(@RequestParam(Constants.ID) int idToDelete, Model model) {
        try {
            if (!employeeService.deleteEmployee(idToDelete)) {
                return new ModelAndView(Constants.ERROR_JSP, Constants.
                        ERROR_MESSAGE, Constants.EMPLOYEE_DELETE_EXCEPTION);
            }
            model.addAttribute(Constants.MESSAGE, Constants.EMPLOYEE_DELETE_SUCCESS_MESSAGE);
            return new ModelAndView(Constants.SEARCH_EMPLOYEE_JSP, Constants.
                    EMPLOYEE, employeeService.retrieveEmployeeById(idToDelete));
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.EMPLOYEE_DELETE_EXCEPTION, idToDelete));
        }
    }

    /**
     * This Method is used to search for an employee by the id given by the
     * user. Displays the employee's details if there is a match.
     *
     * @param id an Integer indicating the id of the employee to be retrieved.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.SEARCH_EMPLOYEE_MAPPING, method=RequestMethod.GET)
    private ModelAndView searchEmployee(@RequestParam(Constants.ID) int id) {
        try {
            Employee employee = employeeService.retrieveEmployeeById(id);
            if (null == employee) {
                return new ModelAndView(Constants.SEARCH_EMPLOYEE_JSP, Constants.
                    FAIL_MESSAGE, String.format(Constants.SEARCH_FAIL_MESSAGE, id));
            }
            return new ModelAndView(Constants.SEARCH_EMPLOYEE_JSP, Constants.
                    EMPLOYEE, employee);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    String.format(Constants.EMPLOYEE_SEARCH_EXCEPTION, id));
        }
    }

    /**
     * This Method is used to display all details of the employees.
     *
     * @return response a HttpServletResponse object which is used to redirect
     *                 or send text output.
     *
     * @return modelAndView a ModelAndView object which is used to add
     *                       attributes to a model and redirect it to a view
     *                       such as a jsp page.
     */
    @RequestMapping(value=Constants.DISPLAY_EMPLOYEE_MAPPING, method=RequestMethod.GET)
    private ModelAndView displayAllEmployees(Model model) {
        try {
            List<Employee> employees = employeeService.retrieveAllEmployees();
            model.addAttribute(Constants.EMPLOYEES, employees);
            return new ModelAndView(Constants.DISPLAY_EMPLOYEE_JSP, Constants.NUMBER_OF_EMPLOYEES, employees.size());
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.ERROR_JSP, Constants.ERROR_MESSAGE,
                    Constants.EMPLOYEE_DISPLAY_EXCEPTION);
        }
    }
}
