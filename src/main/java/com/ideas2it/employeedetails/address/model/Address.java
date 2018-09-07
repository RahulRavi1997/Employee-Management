package com.ideas2it.employeedetails.address.model;

import com.ideas2it.employeedetails.commons.Constants;
/**
 * <p>
 * Address is used to store the address parameters of a person such as door
 * number, Street name, City name, Country name, Pincode. Stores the id of the
 * resident as fields. Overrides HashCode and Equals function.
 * </p>
 *
 * @author   Rahul Ravi
 * @version  1.0
 */
public class Address {

    private Integer id;
    private Integer doorNumber;
    private String street;
    private String city;
    private String country;
    private Long pinCode;
    private String type;
    private Integer clientId;
    private Integer employeeId;

    public Address() {
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

    public Integer getDoorNumber() {
        return this.doorNumber;
    }

    public void setDoorNumber(Integer doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }
 
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getClientId() {
        return this.clientId;
    }
 
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * <p>
     * This method is used to override the equals method of Object Address to
     * efficiently compare between 2 address objects. Returns True if the objects
     * have same address Id, else returns false.
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
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        return (this.id == address.getId());
    }

    /**
     * </p>
     * Method to override the hashcode method of Object Address to efficiently
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
