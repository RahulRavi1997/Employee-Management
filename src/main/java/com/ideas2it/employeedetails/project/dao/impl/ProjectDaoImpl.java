 package com.ideas2it.employeedetails.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.model.Employee;
import com.ideas2it.employeedetails.exception.ApplicationException;
import com.ideas2it.employeedetails.logger.Logger;
import com.ideas2it.employeedetails.project.dao.ProjectDao;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.GenericDao;
/**
 * <p>
 * ProjectDaoImpl is a Data-Access Object Class, which is used to provide 
 * data access facilites for the base class to implement database manipulation
 * operations on Project Data.It uses an Database of Project Objects to
 * store the project Information.
 * </p>
 * <p>
 * All the methods in this class throw a custom Application Exception.
 * </p>
 *
 * @author    Rahul Ravi
 * @version   1.0
 */
public class ProjectDaoImpl extends GenericDao implements ProjectDao {

    String PROJECT_IN_QUERY = "from Project where id in (:ids)";
    String IDS = "ids";

    public ProjectDaoImpl() throws ApplicationException {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public Boolean insertProject(Project project) throws ApplicationException {

        project.setActive(Boolean.TRUE);
        try {
            return (null != super.save(project));
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.PROJECT_ADDITION_EXCEPTION, project.getName()), e);
            throw new ApplicationException(String.format(Constants.PROJECT_ADDITION_EXCEPTION, project.getName()), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateProject(Project project) throws ApplicationException {

        try {
            return super.update(project);
        } catch (ApplicationException e) {
            Logger.error(String.format(
                Constants.PROJECT_EDIT_EXCEPTION, project.getId()), e);
            throw new ApplicationException(String.format(
                Constants.PROJECT_EDIT_EXCEPTION, project.getId()), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteProject(Project project) throws ApplicationException {

        project.setActive(Boolean.FALSE);
        return updateProject(project);
    }

    /**
     * {@inheritDoc}
     */
    public boolean restoreProject(Project project) throws ApplicationException {

        project.setActive(Boolean.TRUE);
        return updateProject(project);
    }


    /**
     * {@inheritDoc}
     */
    public Project searchProjectById(Integer id) throws ApplicationException {

        try {
            return super.get(Project.class, id);
        } catch (ApplicationException e) {
            Logger.error(String.format(Constants.PROJECT_SEARCH_EXCEPTION, id), e);
            throw new ApplicationException(String.format(Constants.PROJECT_SEARCH_EXCEPTION, id), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Project> getAllProjects() throws ApplicationException {

        try {
            return super.getAll(Project.class);
        } catch (ApplicationException e) {
            Logger.error(Constants.PROJECT_DISPLAY_EXCEPTION, e);
            throw new ApplicationException(Constants.PROJECT_DISPLAY_EXCEPTION, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Project> getUnassignedProjects() throws ApplicationException {

        Session session = null;
        List<Project> projects = new ArrayList<Project>();
        try {
            session = super.getSession();
            Criteria criteria = session.createCriteria(Project.class)
                                   .add(Restrictions.isNull(Constants.CLIENTID));
            projects = criteria.list();
        } catch (HibernateException e) {
            Logger.error(e);
            throw new ApplicationException(String.format(
                Constants.PROJECT_DISPLAY_EXCEPTION), e);
        } finally {
            super.close(session);
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     */
    public List<Project> getProjectsByIds(Integer[] ids) throws ApplicationException {

        try {
            Session session = super.getSession();
            Query query = session.createQuery(PROJECT_IN_QUERY);  
            query.setParameterList(IDS, ids); 
            return query.list();
        } catch (ApplicationException e) {
            Logger.error(Constants.PROJECT_DISPLAY_EXCEPTION, e);
            throw new ApplicationException(Constants.PROJECT_DISPLAY_EXCEPTION, e);
        }

    }
}
