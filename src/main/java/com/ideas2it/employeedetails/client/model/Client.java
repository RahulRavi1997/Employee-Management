package com.ideas2it.employeedetails.client.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.address.model.Address;
import com.ideas2it.employeedetails.commons.Constants;

/**
 * Client is a model class which is used to descibe the attributes of the
 * client in a company. The getters and setters are implemented for the purpose
 * of better encapsulation. A client can issue multiple projects at the same
 * time.
 * A single client can be assigned to multiple projects.
 * The Hashcode and Equals Methods are overridden so
 * that two instances are compared with their Id.
 * Soft deletion may be implemented using the active attribute.
 *
 * @author Rahul Ravi
 * @version 1.0
 */

public class Client {

    private Integer id;
    private String name;
    private String email;
    private String companyName;
    private List<Project> projects = new ArrayList<Project>();
    private List<Address> addresses = new ArrayList<Address>();
    private boolean active;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * <p>
     * This method is used to override the equals method of Object Client to
     * efficiently compare between 2 client objects. Returns True if the objects
     * have same client Id, else returns false.
     * </p>
     *
     * @param obj an Object to be passed to compare with the current instance of
     * the class
     *
     * @return boolean a boolean data type is returned whether the two objects
     *                 are considered equal.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Client)) {
            return false;
        }
        Client client = (Client) obj;
        return (this.getId() == client.getId());
    }

    /**
     * </p>
     * Method to override the hashcode method of Object Client to efficiently
     * create hashcodes for the objects using the id.
     * </p>
     *
     * @return result an int data type is returned with the modified Id.
     */
    @Override
    public int hashCode() {

        int result = 1;
        result = Constants.PRIME * result + this.getId();
        return result;
    }
}

