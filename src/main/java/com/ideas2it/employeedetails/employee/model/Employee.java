package com.ideas2it.employeedetails.employee.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ideas2it.employeedetails.commons.Constants;
import com.ideas2it.employeedetails.utils.DateUtil;
import com.ideas2it.employeedetails.project.model.Project;
import com.ideas2it.employeedetails.address.model.Address;


/**
 * <p>
 * A single Employee Object can be assigned to multiple projects and can have
 * multiple addresses. The Hashcode and Equals Methods are overridden so
 * that two instances are compared with their Id.
 * Soft deletion may be implemented using the active attribute.
 * </p>
 *
 * @author   Rahul Ravi
 * @version  1.0
 */
public class Employee {

    private Integer id;
    private String email;
    private String name;
    private List<Address> addresses = new ArrayList<Address>();
    private Date birthDate;
    private String role;
    private Long salary;
    private Date joiningDate;
    private List<Project> workingProjects = new ArrayList<Project>();
    private boolean active;

    public Employee() {
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSalary() {
        return this.salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getJoiningDate() {
        return this.joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public List<Project> getWorkingProjects() {
        return this.workingProjects;
    }

    public void setWorkingProjects(List<Project> workingProjects) {
        this.workingProjects = workingProjects;
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
     * This Method is used to calculate the age of an Employee.
     *
     * @return int an Int indicating the age of an employee is returned.
     */
    public Integer getAge() {
        return DateUtil.getYearDifference(this.birthDate);
    }

    /**
     * <p>
     * This method is used to override the equals method of Object Employee to
     * efficiently compare between 2 employee objects. Returns True if the objects
     * have same employee Id, else returns false.
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
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) obj;
        return (this.getId() == employee.getId());
    }

    /**
     * </p>
     * Method to override the hashcode method of Object Employee to efficiently
     * create hashcodes for the objects using the id.
     * </p>
     *
     * @return result an int data type is returned with the modified Id.
     */
    @Override
    public int hashCode() {

        int result = 1;
        result = Constants.PRIME * result + this.id;
        return result;
    }
}
