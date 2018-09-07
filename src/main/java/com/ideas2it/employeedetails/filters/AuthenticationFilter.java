package com.ideas2it.employeedetails.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.employeedetails.commons.Constants;

/**
 * <p>
 * AuthenticationFilter is a filter to prevent unauthorised requests from entering
 * a servlet. It is used to manage a session and handle unauthorised access from
 * intruders. It redirects to Login page if the user is not authorised or the
 * session is not valid.
 * </p>
 * @author    Rahul Ravi
 * @version   1.0
 */
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log(Constants.AUTHENTICATION_FILTER_INIT);
    }

    /**
     * This Method is used to authenticate and authorise the user, so that the
     * application cannot be accessed by unauthorised personnel. Produces error
     * message if the user does not have the authorisation a servlet.
     *
     * @param request a ServletRequest object with the parameters from the user.
     *
     * @param response a ServletResponse object to be redirected to the required
     *                 webpage.
     *
     * @param chain a FilterChain object that is used to pass the request along
     *              subsequest filters to be filtered.
     */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println(Constants.REQUESTED_RESOURCE + uri);

        HttpSession session = req.getSession(Boolean.FALSE);

        if (uri.contains(Constants.IMAGES) || uri.contains(Constants.STYLES) || uri.contains(Constants.SCRIPT)) {
            chain.doFilter(req, res);
        } else if (null == session || ((null == session.getAttribute(Constants.EMAIL)) && !uri.endsWith(Constants.LOGIN) && !uri.endsWith(Constants.SIGNUP))) {
            req.getRequestDispatcher(Constants.INDEX_PATH).forward(req, res);
        } else if ((null != session) && (uri.equals(Constants.INDEX_JSP_PATH) 
                                          || uri.equals(Constants.PROJECT_FOLDER_PATH)) 
                                     && (null != session.getAttribute(Constants.EMAIL))) {
            res.sendRedirect(Constants.MAIN_MENU_JSP);
        } else if (null == session && !uri.endsWith(Constants.LOGIN) && !uri.endsWith(Constants.SIGNUP) && !uri.endsWith(Constants.INDEX_JSP_PATH)) {
            req.getRequestDispatcher(Constants.INDEX_PATH).forward(req, res);
        } else if ((uri.contains(Constants.PROJECT_PATH) || uri.contains(Constants.REMOVE) || uri.contains(Constants.REMOVE) || uri.contains(Constants.CLIENT_PATH))
                    && (session.getAttribute(Constants.ROLE).equals(Constants.EMPLOYEE_USER_ROLE))) {
            req.setAttribute(Constants.AUTHORISATION_MESSAGE,Constants.EMPLOYEE_AUTHORISATION_MESSAGE);
            req.getRequestDispatcher(Constants.MAIN_MENU_JSP).forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
    public void destroy() {

    }
}
