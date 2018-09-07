package com.ideas2it.employeedetails.user.model;


import com.ideas2it.employeedetails.commons.Constants;

/**
 * <p>
 * User entity is used authorise users to sign in into the application.
 * A User consists of an id, email, password and role fields.
 * The role attribute can be used to provide authorisation to the user.
 * The active attribute can be used in the soft deletion of a user.
 * </p>
 * <p>
 * The member variables are made private and the methods are public to provide
 * Encapsulation in the program.
 * The hashCode and equals methods are overrided in order to provide comparison
 * of User Objects based on the primary key - Id.
 * </p>
 *
 * @author   Rahul Ravi
 * @version  1.0
 */
public class User {

    private Integer id;
    private String email;
    private String password;
    private boolean active;
    private String role;

    public User() {
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     * <p>
     * This method is used to override the equals method of Object User to
     * efficiently compare between 2 user objects. Returns True if the objects
     * have same user Id, else returns false.
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
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return (this.getId() == user.getId());
    }

    /**
     * </p>
     * Method to override the hashcode method of Object user to efficiently
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
