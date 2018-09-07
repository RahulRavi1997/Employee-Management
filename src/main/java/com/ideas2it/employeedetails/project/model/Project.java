package com.ideas2it.employeedetails.project.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.employee.model.Employee;

/**
 * <p>
 * A single project can be assigned to multiple employees but only one client.
 * The Hashcode and Equals Methods are overridden so
 * that two instances are compared with their Id.
 * Soft deletion may be implemented using the active attribute.
 * </p>
 * @author   Rahul Ravi
 * @version  1.0
 */
public class Project {

    private Integer id;
    private String name;
    private Integer numberOfResources;
    private List<Employee> projectMembers = new ArrayList<Employee>();
    private Integer clientId;
    private boolean active;

    public Project() {
    }
    /**
     * Getters and Setters.
     */

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getProjectMembers() {
        return this.projectMembers;
    }

    public void setProjectMembers(List<Employee> projectMembers) {
        this.projectMembers = projectMembers;
    }

    public Integer getNumberOfResources() {
        return this.numberOfResources;
    }

    public void setNumberOfResources(Integer numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

 /**
  * <p>
  * This method is used to override the equals method of Object Project to
  * efficiently compare between 2 project objects. Returns True if the objects
  * have same project Id, else returns false.
  * </p>
  *
  * @param obj an Object to be passed to compare with the current instance of
  * the class
  *
  * @return boolean a boolean data type is returned whether the two objects are
  *                 considered equal.
  */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Project)) {
            return false;
        }
        Project project = (Project) obj;
        return (this.getId() == project.getId());
    }

 /**
  * </p>
  * Method to override the hashcode method of Object Project to efficiently
  * create hashcodes for the objects using the id.
  * </p>
  *
  * @return result an int data type is returned with the modified Id.
  */
    @Override
    public int hashCode() {

        int result = 1;
        result = Constants.PRIME * result + getId();
        return result;
    }
}
