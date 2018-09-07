package com.ideas2it.employeedetails.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.user.model.User;
import com.ideas2it.employeedetails.user.service.UserService;
import com.ideas2it.employeedetails.user.service.impl.UserServiceImpl;
import com.ideas2it.employeedetails.logger.Logger;
import com.ideas2it.employeedetails.exception.ApplicationException;

/**
 * <p>
 * User Controller is a Controller Class, which is used authorise the user to
 * access the application and allow them to make modifications to the
 * available data. Provides methods to implement basic user operations like
 * Login, Signup and Logout operations.
 * </p>
 * @author    Rahul Ravi
 * @version   1.0
 */
@Controller
public class UserController {

    private UserService userService = null;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return this.userService;
    }

    @RequestMapping(Constants.MAIN_MENU_PATH)
    public String redirectMenu(){
	    return Constants.MAIN_MENU_JSP;
    }

    @RequestMapping("*")
    public String redirectToMenu(){
	    return Constants.ERROR_JSP;
    }


    /**
     * This Method is used to end the session for a user and logout from the
     * application.
     *
     * @param request a HttpServletRequest object which is used to obtain the
     *                parameters of a request from the server.
     *
     * @param response a HttpServletResponse object which is used to redirect
     *                 or send text output.
     */
    @RequestMapping(value=Constants.LOGOUT_PATH,method=RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(Constants.EMAIL)) {
                    loginCookie = cookie;
                    break;
                }
            }
        }
        if(loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        HttpSession session = request.getSession(Boolean.FALSE);
        if (session != null) {
            session.invalidate();
        }
        return new ModelAndView(Constants.INDEX);
    }

    /**
     * This Method is used to obtain the user Credentials during login and
     * create a new Session.
     *
     * @param email a String indicating the email Id entered by the user while
     *              logging in.
     *
     * @param password a String indicating the password entered by the user while
     *                 logging in.
     *
     * @param role a String indicating the role of the user that is logging in.
     */
    @RequestMapping(value=Constants.SIGNUP_PATH,method=RequestMethod.POST)
    private ModelAndView createUser(Model model, @RequestParam(Constants.EMAIL) String email,
                            @RequestParam(Constants.PASSWORD) String password,
                            @RequestParam(Constants.ROLE) String role) {
        try {
            if (null != userService.retrieveUserByEmail(email)) {
                return new ModelAndView(Constants.INDEX, Constants.USER_FAIL, Constants.SIGNIN_USER_FAIL_MESSAGE);
            } else {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);
                if (userService.addUser(user)) {
                    model.addAttribute(Constants.SIGNIN_EMAIL, email);
                    return new ModelAndView(Constants.INDEX, Constants.SIGN_UP_SUCCESS, Constants.SIGN_UP_SUCCESS_MESSAGE);
                } else {
                    return new ModelAndView(Constants.INDEX, Constants.SIGN_UP_FAIL, Constants.SIGN_UP_FAIL_MESSAGE);
                }
            }
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.INDEX_JSP, Constants.LOGIN_FAIL, Constants.USER_ADD_EXCEPTION);
        }
    }

    /**
     * This Method is used to obtain the user Credentials during login and
     * create a new Session.
     *
     * @param email a String indicating the email Id entered by the user while
     *              logging in.
     *
     * @param password a String indicating the password entered by the user while
     *                 logging in.
     *
     * @param request a HttpServletRequest object which is used to obtain the
     *                parameters of a request from the server.
     *
     * @param response a HttpServletResponse object which is used to redirect
     *                 or send text output.
     */
    @RequestMapping(value=Constants.LOGIN_PATH,method=RequestMethod.POST)
    private ModelAndView authenticateUser(@RequestParam(Constants.EMAIL) String email, 
                                          @RequestParam(Constants.PASSWORD) String password,
                                          HttpServletRequest request, HttpServletResponse response) {
        if ((null == email) || null == password) {
            return new ModelAndView(Constants.INDEX_JSP, Constants.LOGIN_FAIL, Constants.LOGIN_FAIL_MESSAGE);
        }
        User user = null;
        try {
            user = userService.retrieveUserByEmail(email);
        } catch (ApplicationException e) {
            Logger.error(e);
            return new ModelAndView(Constants.INDEX_JSP, Constants.LOGIN_FAIL, Constants.LOGIN_FAIL_MESSAGE);
        }
        if ((null != user) && (user.getPassword().equals(password))) {
            Cookie loginCookie = new Cookie(Constants.EMAIL, email);
            loginCookie.setMaxAge(Constants.COOKIE_ACTIVE_INTERVAL);
            response.addCookie(loginCookie);
            HttpSession oldSession = request.getSession(Boolean.FALSE);
            if (null != oldSession) {
                oldSession.invalidate();
            }
            HttpSession session = request.getSession();
            session.setAttribute(Constants.EMAIL, email);
            session.setAttribute(Constants.ROLE, user.getRole());
            session.setMaxInactiveInterval(Constants.SESSION_ACTIVE_INTERVAL);
            return new ModelAndView(Constants.MAIN_MENU_JSP);
        } else {
            return new ModelAndView(Constants.INDEX_JSP, Constants.LOGIN_FAIL, Constants.LOGIN_FAIL_MESSAGE);
        }
    }
}
