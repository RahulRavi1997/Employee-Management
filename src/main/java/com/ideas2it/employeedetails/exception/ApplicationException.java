package com.ideas2it.employeedetails.exception;

/**
 * ApplicationException is a custom user-defined Exception class that is
 * defined specifically for the application designed. It can be used to handle
 * multiple exceptions at the same time and avoid application being terminated.
 *
 * Provides Multiple contructors to throw exceptions along with errorCode and
 * customised Error message.
 *
 * @author Rahul Ravi
 * @version 1.0
 */
public class ApplicationException extends Throwable {

    private int errorCode;
    private String message;

    public ApplicationException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

   public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public ApplicationException(String message, int errorCode, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable cause, int errorCode) {
        super(cause);
        this.message = message;
        this.errorCode = errorCode;
    }

    public int geterrorCode() {
        return this.errorCode;
    }
    public String getMessage() {
        return this.message;
    }
}

