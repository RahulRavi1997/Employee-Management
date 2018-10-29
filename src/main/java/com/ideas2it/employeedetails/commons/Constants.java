package com.ideas2it.employeedetails.commons;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Constants Class is used to store all the constants in other parts of the 
 * application. It is created to reduce repeated Strings and code Size.
 *
 * @author   Rahul Ravi
 * @version  1.0
 */
public final class Constants {

    private Constants() {
    }

    public static final String ID_NOT_NULL = "Enter valid id please!";

    public static final String EMPLOYEE_UNIQUE_KEY_EXCEPTION = 
        "Email-%s already exists! \nError while adding employee\n";
    public static final String CLIENT_UNIQUE_KEY_EXCEPTION = 
        "Email-%s already exists! \nError while adding client\n";

    public static final String EMPLOYEE_ADDITION_EXCEPTION = 
            "Error while adding an employee- Name:%s\n";
    public static final String EMPLOYEE_SEARCH_EXCEPTION = 
            "Error while searching employee id %d\n";
    public static final String EMPLOYEE_DELETE_EXCEPTION = 
            "Error while deleting employee id %d\n";
    public static final String EMPLOYEE_EDIT_EXCEPTION = 
            "Error while modifying employee id %d\n";
    public static final String EMPLOYEE_DISPLAY_EXCEPTION = 
            "Error while displaying employees\n";
    public static final String EMPLOYEE_RESTORE_EXCEPTION = 
            "Error while restoring employee id %d\n";

    public static final String PROJECT_ADD_EXCEPTION = 
            "Error while adding project \n";
    public static final String PROJECT_ADDITION_EXCEPTION = 
            "Error while adding a project- Name:%s \n";
    public static final String PROJECT_ASSIGN_EMPLOYEE_EXCEPTION = 
        "Error while adding employee to project %d\n";
    public static final String PROJECT_REMOVE_EMPLOYEE_EXCEPTION = 
        "Error while removing employee from project %d\n";
    public static final String PROJECT_SEARCH_EXCEPTION = 
            "Error while searching project id %d\n";
    public static final String PROJECT_DELETE_EXCEPTION = 
            "Error while deleting project id %d\n";
    public static final String PROJECT_EDIT_EXCEPTION = 
            "Error while modifying project id %d\n";
    public static final String PROJECT_DISPLAY_EXCEPTION = 
            "Error while displaying projects\n";
    public static final String PROJECT_RESTORE_EXCEPTION = 
            "Error while restoring project id %d\n";

    public static final String CLIENT_ADD_EXCEPTION = 
            "Error while adding client \n";
    public static final String CLIENT_ADDITION_EXCEPTION = 
            "Error while adding a client- Name:%s\n";
    public static final String CLIENT_ASSIGN_PROJECT_EXCEPTION = 
        "Error while adding project to client %d\n";
    public static final String CLIENT_REMOVE_PROJECT_EXCEPTION = 
        "Error while removing project from client %d\n";
    public static final String CLIENT_SEARCH_EXCEPTION = 
            "Error while searching client id %d\n";
    public static final String CLIENT_DELETE_EXCEPTION = 
            "Error while deleting client id %d\n";
    public static final String CLIENT_EDIT_EXCEPTION = 
            "Error while modifying client id %d\n";
    public static final String CLIENT_DISPLAY_EXCEPTION = 
            "Error while displaying clients\n";
    public static final String CLIENT_RESTORE_EXCEPTION = 
            "Error while restoring client id %d\n";

    public static final String USER_ADDITION_EXCEPTION = 
            "Error while adding a user- Name:%s\n";
    public static final String USER_SEARCH_EXCEPTION = 
            "Error while searching user %d\n";
    public static final String USER_DELETE_EXCEPTION = 
            "Error while deleting user id %d\n";
    public static final String USER_EDIT_EXCEPTION = 
            "Error while modifying user id %d\n";
    public static final String USER_DISPLAY_EXCEPTION = 
            "Error while displaying user\n";

    public static final String USER_UNIQUE_KEY_EXCEPTION = 
        "Email-%s already exists! \nError while adding user\n";

    //String to display when adding employee is successful
    public static final String ENTRY_ADDED = "Entry Added";

    //String to display when employee addition is not successful
    public static final String ADDITION_FAILED = "Addtion Failed. Check ID.";

    //String to display when removing employee is successful
    public static final String ENTRY_REMOVED = "Entry Removed";

    //String to display when employee removal is not successful
    public static final String REMOVAL_FAILED = "Removal Failed. Check ID.";

    //String to display when editing employee is successful
    public static final String ENTRY_MODIFIED = "Entry modified";

    public static final String EDIT_FAILED = "Modification Failed. Check ID.";

    public static final String SEARCH_FAILED = "Search Failed. Check ID.";

    //String to display if search returns null
    public static final String NOT_FOUND = "No such Entry Found";

    public static final String DISPLAY_FAILED = "Display Operation failed";

    public static final String DELETE_FAILED = "DELETE Operation failed. Check ID.";

    //String to display when employee deletion is successful
    public static final String ENTRY_DELETED = "Entry deleted";

    //String to display when employee deletion is not successful
    public static final String DELETION_FAILED = "Deletion Failed. Check id!";

    //String to display when employee search is successful
    public static final String ENTRY_FOUND = "Entry found";

    //String to display when no entries are found
    public static final String EMPTY = "No entries found";

    //String to display if invalid number is given
    public static final String INVALID_INPUT = "Not a Valid Input";

    //String to select id from employees table
    public static final String EMPLOYEE_ID = "employeeid";
    //String to select name from employees table
    public static final String NAME = "name";

    //String to select role from employees table
    public static final String ROLE = "role";

    //String to select email from employees table
    public static final String EMAIL = "email";

    //String to select birth date from employees table
    public static final String BIRTH_DATE = "birthDate";

    //String to select joining date from employees table
    public static final String JOINING_DATE = "joiningDate";

    //String to select salary from employees table
    public static final String SALARY = "salary";

    //String to select id from projects table
    public static final String PROJECT_ID = "id";

    //String to select name from projects table
    public static final String PROJECT_NAME = "name";

    //String to select number of resources from projects table
    public static final String NUMBER_OF_RESOURCES = "numberOfResources";

    //String to display project Role Column
    public static final String PROJECT_ROLE = "projectrole";

    //Prime number to generate hashcode for entities.
    public static final int PRIME = 31;

    //String to specify the required Date Format
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    //String to specify the required Date Format
    public static final String REVERSED_DATE_FORMAT = "yyyy-MM-dd";

    //String to display if invalid date is given
    public static final String INVALID_DATE = "Not a Valid date!Try Again\n";

    public static final String LOGGER_PROPERTIES_LOCATION =
            "log4j.xml";

    public static final String HIBERNATE_CONFIG_FILE =
            "hibernate.cfg.xml";

    public static final String ID = "id";
    public static final String ACTIVE = "active";

    public static final String RESIDENTIAL = "Residential";
    public static final String PERMANENT = "Permanent";

    public static final String EMPLOYEE_INIT = "Initiated Employee Servlet";
    public static final String CREATE_EMPLOYEE_JSP = "create_employee";
    public static final String EMPLOYEES = "employeeList";
    public static final String DISPLAY_EMPLOYEE_JSP = "display_employees";
    public static final String ERROR_JSP = "error";
    public static final String EMPLOYEE = "employee";
    public static final String SEARCH_EMPLOYEE_JSP = "display_employee";
    public static final String FAIL_MESSAGE = "failMessage";
    public static final String MESSAGE = "message";
    public static final String SEARCH_FAIL_MESSAGE = "No Such Employee ID- %s . Try Again";
    public static final String PROJECT_SEARCH_FAIL_MESSAGE = "No Such Project ID- %s . Try Again";
    public static final String CLIENT_SEARCH_FAIL_MESSAGE = "No Such Client ID- %s . Try Again";
    public static final String PROJECT_INIT= "Initiated Project Servlet";
    public static final String CREATE_PROJECT_JSP= "create_project";
    public static final String PROJECTS = "projectList";
    public static final String DISPLAY_PROJECT_JSP = "display_projects";
    public static final String ASSIGN_MEMBERS = "assignMembers";
    public static final String REMOVE_MEMBERS = "removeMembers";
    public static final String ASSIGN_SELECTED = "assignSelected";
    public static final String PROJECT = "project";
    public static final String SEARCH_PROJECT_JSP = "display_project";
    public static final String PROJECTID = "projectid";
    public static final String ID_OF_EMPLOYEES = "idOfEmployees";
    public static final String ID_OF_PROJECTS = "idOfProjects";
    public static final String CLIENT_INIT = "Initiated Client Servlet";
    public static final String CREATE_CLIENT_JSP = "create_client" ;
    public static final String DISPLAY_CLIENT_JSP = "display_clients";
    public static final String CLIENTS = "clientList";
    public static final String COMPANY_NAME = "companyName";
    public static final String CLIENT = "client";
    public static final String CLIENT_ID = "clientid";
    public static final String CLIENTID = "clientId";
    public static final String SEARCH_CLIENT_JSP = "display_client";
    public static final String NUMBER_OF_ADDRESSES = "numberOfAddresses";
    public static final String TYPE = "type";
    public static final String DOOR_NUMBER = "doorNumber";
    public static final String STREET = "street";
    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String PIN_CODE = "pinCode";
    public static final String USER_INIT = "Initiated User Servlet";
    public static final String LOGOUT = "logout";
    public static final String REINSTATE = "reinstate";
    public static final String URL_NOT_FOUND = "404 - No such Url!";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String NO_SUCH_OPERATION = "No such Url! Try again Later";
    public static final String HTML_TYPE = "text/html";
    public static final String PASSWORD = "password";
    public static final String LOGIN_FAIL_MESSAGE = "Invalid Email or Password";
    public static final String LOGIN_FAIL = "loginFail";
    public static final String MAIN_MENU_JSP = "main_menu";
    public static final String AUTHENTICATION_FILTER_INIT = "AuthenticationFilter initialized";
    public static final String REQUESTED_RESOURCE = "Requested Resource::";
    public static final String LOGIN_URL = "/login";
    public static final String INDEX_JSP_PATH = "/EmployeeDetails/index";
    public static final String INDEX_JSP = "index";
    public static final String INDEX = "index";
    public static final String PROJECT_FOLDER_PATH = "/EmployeeDetails/";
    public static final String STYLES_PATH = "/styles";
    public static final String PROJECT_PATH = "project";
    public static final String CLIENT_PATH = "client";
    public static final String REMOVE = "remove";
    public static final String ASSIGN = "assign";
    public static final String EMPLOYEE_USER_ROLE = "Employee";
    public static final String IMAGES_PATH = "/images";
    public static final String AUTHORISATION_MESSAGE = "authorisationMessage";
    public static final String EMPLOYEE_AUTHORISATION_MESSAGE = "You Do Not Have Authorisation to access Projects/Clients. Sorry!";
    public static final Integer COOKIE_ACTIVE_INTERVAL = 10*60;
    public static final Integer SESSION_ACTIVE_INTERVAL = 10*60;
    public static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
    public static final String REQUESTLOGGING_FILTER_INIT = "RequestLoggingFilter initialized";
    public static final String SIGNIN = "signin";
    public static final String SIGNIN_PASSWORD_FAIL_MESSAGE = "Sign-in Failed! Passwords Dont Match";
    public static final String SIGNIN_USER_FAIL_MESSAGE = "Sign-in Failed! User already registered";
    public static final String USER_FAIL = "userFail";
    public static final String SIGNIN_FAIL = "signinFail";
    public static final String SIGN_UP_SUCCESS = "signUpSuccess";
    public static final String SIGN_UP_SUCCESS_MESSAGE = "User successfully Registered!";
    public static final String SIGN_UP_FAIL = "signUpFail";
    public static final String SIGN_UP_FAIL_MESSAGE = "User Registration Failed! Try again Later";
    public static final String MULTIPLE_USERS = "Warning: Another User is logged in with your email Address %s";
    public static final String COMMAND = "command";
    public static final String CREATE_EMPLOYEE_MAPPING = "/create_employee";
    public static final String ADD_EMPLOYEE_MAPPING = "/add_employee";
    public static final String MODIFY_EMPLOYEE_MAPPING = "/modify_employee";
    public static final String UPDATE_EMPLOYEE_MAPPING = "/update_employee";
    public static final String RESTORE_EMPLOYEE_MAPPING = "/restore_employee";
    public static final String DELETE_EMPLOYEE_MAPPING = "/delete_employee";
    public static final String SEARCH_EMPLOYEE_MAPPING = "/search_employee";
    public static final String DISPLAY_EMPLOYEE_MAPPING = "/display_employees";

    public static final String CREATE_PROJECT_MAPPING = "/create_project";
    public static final String ADD_PROJECT_MAPPING = "/add_project";
    public static final String MODIFY_PROJECT_MAPPING = "/modify_project";
    public static final String UPDATE_PROJECT_MAPPING = "/update_project";
    public static final String RESTORE_PROJECT_MAPPING = "/restore_project";
    public static final String DELETE_PROJECT_MAPPING = "/delete_project";
    public static final String SEARCH_PROJECT_MAPPING = "/search_project";
    public static final String DISPLAY_PROJECT_MAPPING = "/display_projects";
    public static final String REMOVE_EMPLOYEE_MAPPING = "/remove_employee";
    public static final String OBTAIN_EMPLOYEES_MAPPING = "/obtain_employees";
    public static final String ASSIGN_EMPLOYEES_MAPPING = "/assign_employees";

    public static final String CREATE_CLIENT_MAPPING = "/create_client";
    public static final String ADD_CLIENT_MAPPING = "/add_client";
    public static final String MODIFY_CLIENT_MAPPING = "/modify_client";
    public static final String UPDATE_CLIENT_MAPPING = "/update_client";
    public static final String RESTORE_CLIENT_MAPPING = "/restore_client";
    public static final String DELETE_CLIENT_MAPPING = "/delete_client";
    public static final String SEARCH_CLIENT_MAPPING = "/search_client";
    public static final String DISPLAY_CLIENT_MAPPING = "/display_clients";    
    public static final String REMOVE_PROJECT_MAPPING = "/remove_project";
    public static final String OBTAIN_PROJECTS_MAPPING = "/obtain_projects";
    public static final String ASSIGN_PROJECTS_MAPPING = "/assign_projects";

    public static final String ASSIGNED_EMPLOYEES = "assignedEmployees";
    public static final String ASSIGNED_PROJECTS = "assignedProjects";

    public static final String STYLES = "styles";
    public static final String IMAGES = "images";
    public static final String SCRIPT = "script";
    public static final String SIGNUP = "signup";
    public static final String LOGIN = "login";
    public static final String LOGIN_PATH = "/login";
    public static final String MAIN_MENU_PATH = "/main_menu";
    public static final String INDEX_PATH = "/WEB-INF/views/index.jsp";
    public static final String ADDRESS_TYPES = "addressTypes";
    public static final String NUMBER_OF_EMPLOYEES = "numberOfEmployees";
    public static final String NUMBER_OF_PROJECTS = "numberOfProjects";
    public static final String NUMBER_OF_CLIENTS = "numberOfClients";

    public static final String USER_ADD_EXCEPTION = "Error while adding User! Try Again later!";
    public static final String SIGNUP_PATH = "/signup";
    public static final String LOGOUT_PATH = "/logout";
    public static final String EMPLOYEE_ADD_SUCCESS_MESSAGE = "Employee Added Successfully";
    public static final String EMPLOYEE_RESTORE_SUCCESS_MESSAGE = "Employee Restored Successfully";
    public static final String EMPLOYEE_DELETE_SUCCESS_MESSAGE = "Employee deleted Successfully";
    public static final String EMPLOYEE_UPDATE_SUCCESS_MESSAGE = "Employee Updated Successfully";

    public static final String CLIENT_ADD_SUCCESS_MESSAGE = "Client added Successfully";
    public static final String CLIENT_UPDATE_SUCCESS_MESSAGE = "Client updated Successfully";
    public static final String CLIENT_DELETE_SUCCESS_MESSAGE = "Client deleted Successfully";
    public static final String CLIENT_RESTORE_SUCCESS_MESSAGE = "Client Restored Successfully";
    public static final String PROJECTS_ASSIGN_SUCCESS_MESSAGE = "Selected Projects Assigned Successfully";
    public static final String PROJECT_REMOVE_SUCCESS_MESSAGE = "Project Unassigned Successfully";

    public static final String PROJECT_ADD_SUCCESS_MESSAGE = "Project added Successfully";
    public static final String PROJECT_UPDATE_SUCCESS_MESSAGE = "Project updated Successfully";
    public static final String PROJECT_DELETE_SUCCESS_MESSAGE = "Project deleted Successfully";
    public static final String PROJECT_RESTORE_SUCCESS_MESSAGE = "Project Restored Successfully";
    public static final String EMPLOYEESS_ASSIGN_SUCCESS_MESSAGE = "Selected Employees Assigned Successfully";
    public static final String EMPLOYEE_REMOVE_SUCCESS_MESSAGE = "Employee Unassigned Successfully";
    public static final String SIGNIN_EMAIL = "signinEmail";
     
    public static final String DATABASE_FAILURE = "Database Connection Error has Occured";
}
