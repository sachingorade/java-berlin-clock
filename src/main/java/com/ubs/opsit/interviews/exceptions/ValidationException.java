/*
 * Name: ValidationException.java
 *
 * Created by saching on May 6, 2018
 *
 */
package com.ubs.opsit.interviews.exceptions;

/**
 * This exception is used while validating the input given by the users
 * to convert the time into berlin clock time.
 * @author saching
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;

	public ValidationException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidationException other = (ValidationException) obj;
		if (errorCode != other.errorCode)
			return false;
		return true;
	}

}
