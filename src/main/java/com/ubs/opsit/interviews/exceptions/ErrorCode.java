/*
 * Name: ErrorCode.java
 *
 * Created by saching on May 6, 2018
 *
 */
package com.ubs.opsit.interviews.exceptions;

/**
 * Error code which are used in {@link ValidationException} to notify
 * API client to show appropriate error messages.
 * @author saching
 *
 */
public enum ErrorCode {

	INVALID_TIME_STRING,
	INVALID_HOUR_VALUE,
	INVALID_MINUTES_VALUE,
	INVALID_SECONDS_VALUE
	
}
