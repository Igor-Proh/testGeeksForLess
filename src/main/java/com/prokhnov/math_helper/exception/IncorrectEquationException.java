package com.prokhnov.math_helper.exception;

/**
 * The {@code IncorrectEquationException} class.<br/>
 * Exception class for incorrect equation
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class IncorrectEquationException extends RuntimeException {

    public IncorrectEquationException() {
        super();
    }

    public IncorrectEquationException(String message) {
        super(message);
    }
}
