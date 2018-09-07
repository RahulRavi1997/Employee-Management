package com.ideas2it.employeedetails.logger;

import org.apache.log4j.xml.DOMConfigurator;

import com.ideas2it.employeedetails.commons.Constants;

/**
 * Logger is a custom Logger class that wraps the Imported logger to keep
 * record of all the error messages that arise while the application is
 * running. Has constructors to log both throwable and custom messages or
 * the exception alone.
 *
 * @author Rahul Ravi
 * @version 1.0
 */
public class Logger {

    private static org.apache.log4j.Logger logger;

    static {
        logger = org.apache.log4j.Logger.getLogger(Logger.class.getName());
        DOMConfigurator.configure(Constants.LOGGER_PROPERTIES_LOCATION);
    }

    public static void error(String message, Throwable cause) {
        logger.error(message, cause);
    }
    public static void error(Throwable cause) {
        logger.error(cause);
    }
    public static void debug(String message, Throwable cause) {
        logger.debug(message, cause);
    }
    public static void debug(Throwable cause) {
        logger.debug(cause);
    }
    public static void trace(String message, Throwable cause) {
        logger.trace(message, cause);
    }
    public static void trace(Throwable cause) {
        logger.trace(cause);
    }
    public static void warn(String message, Throwable cause) {
        logger.warn(message, cause);
    }
    public static void warn(Throwable cause) {
        logger.warn(cause);
    }
    public static void info(String message, Throwable cause) {
        logger.info(message, cause);
    }
    public static void info(Throwable cause) {
        logger.info(cause);
    }
}
