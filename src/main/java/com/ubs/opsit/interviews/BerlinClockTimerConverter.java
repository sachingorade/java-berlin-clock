/*
 * Name: BerlinClockTimerConverter.java
 *
 * Created by saching on May 6, 2018
 *
 */
package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.exceptions.ErrorCode;
import com.ubs.opsit.interviews.exceptions.ValidationException;
import com.ubs.opsit.interviews.model.BerlinClock;

/**
 * Berlin clock converter implementation of the {@link TimeConverter} interface.
 * @author saching
 *
 */
public class BerlinClockTimerConverter implements TimeConverter {

	private static final String YELLOW_LIGHT = "Y";
	private static final String RED_LIGHT = "R";
	private static final String OFF = "O";
	private static final int INVALID_SPECIAL_INDEX_VALUE = -1;
	private static final String NEW_LINE_CHAR = System.getProperty("line.separator");
	
	@Override
	public String convertTime(String aTime) throws ValidationException {
		int[] timeValues = validateAndGetSplitTime(aTime);
		return new BerlinClock(NEW_LINE_CHAR, getBerlinClockSecondValue(timeValues[2]), 
				getBerlinClockFirstHoursLine(timeValues[0]), getBerlinClockSecondHoursLine(timeValues[0]), 
				getBerlinClockFirstMinutesLine(timeValues[1]), getBerlinClockSecondMinuteLine(timeValues[1]))
				.toString();
	}
	
	/**
	 * Validates and returns the hour, minute and second values from given input.
	 * @param aTime time to split
	 * @throws ValidationException thrown in case of invalid input 
	 */
	private int[] validateAndGetSplitTime(String aTime) throws ValidationException {
		int[] returnTimeValues = null;
		
		String[] strs = getValidatedInputTime(aTime);

		returnTimeValues = new int[3];
		returnTimeValues[0] = getValidatedTimeRange(strs[0], 0, 24, ErrorCode.INVALID_HOUR_VALUE);
		returnTimeValues[1] = getValidatedTimeRange(strs[1], 0, 59, ErrorCode.INVALID_MINUTES_VALUE);
		returnTimeValues[2] = getValidatedTimeRange(strs[2], 0, 59, ErrorCode.INVALID_SECONDS_VALUE);
		
		return returnTimeValues;
	}

	/**
	 * Validates the given input time format and returns the split string
	 * @param inputTime input time to validate
	 * @return split time string
	 * @throws ValidationException thrown in case of invalid input
	 */
	protected String[] getValidatedInputTime(String inputTime) throws ValidationException {
		if (inputTime == null || inputTime.trim().isEmpty()) {
			throw new ValidationException(ErrorCode.INVALID_TIME_STRING);
		}
		String[] strs = inputTime.split(":");
		if (strs.length != 3) {
			throw new ValidationException(ErrorCode.INVALID_TIME_STRING);
		}
		return strs;
	}
	
	/**
	 * Validates the given time string within in the given range, if not then throws validation
	 * exception with given code.
	 * @param timeStr time string input
	 * @param min range start
	 * @param max range end
	 * @param code code to use if invalid input
	 * @return int converted time string
	 * @throws ValidationException throws if invalid input
	 */
	protected int getValidatedTimeRange(String timeStr, int min, int max, ErrorCode code) throws ValidationException {
		int timeValue;
		try {
			timeValue = Integer.valueOf(timeStr);
		} catch (NumberFormatException e) {
			throw new ValidationException(code);
		}
		if (timeValue < min || timeValue > max) {
			throw new ValidationException(code);
		}
		return timeValue;
	}

	/**
	 * Returns the first line i.e. seconds line value of berlin clock from given seconds
	 * @param seconds input value
	 * @return berlin clock seconds value
	 */
	protected String getBerlinClockSecondValue(int seconds) {
		return seconds % 2 == 0 ? YELLOW_LIGHT : OFF;
	}

	/**
	 * Returns the second line i.e. first line of hour value of berlin clock from given
	 * hours value.
	 * @param hours input hours value.
	 * @return berlin clock first line of hours value
	 */
	protected String getBerlinClockFirstHoursLine(int hours) {
		return getLightOrOffValue(RED_LIGHT, hours, 5, 4);
	}
	
	/**
	 * Returns the third line i.e. second line of the hours value of berlin clock
	 * @param hours hours input value
	 * @return berlin clock second line of hours value
	 */
	protected String getBerlinClockSecondHoursLine(int hours) {
		return getLightOrOffValue(RED_LIGHT, hours % 5, 1, 4);
	}

	/**
	 * Returns the fourth line i.e. first line of minutes value of berlin clock
	 * @param minutes minutes input value
	 * @return berlin clock first line of minutes value
	 */
	protected String getBerlinClockFirstMinutesLine(int minutes) {
		return getLightOrOffValue(YELLOW_LIGHT, minutes, 5, 11, 3, RED_LIGHT);
	}
	
	/**
	 * Returns the fifth line i.e. second line of the minutes value of berlin clock
	 * @param minutes minutes input value
	 * @return berlin clock second line of the minutes value
	 */
	protected String getBerlinClockSecondMinuteLine(int minutes) {
		return getLightOrOffValue(YELLOW_LIGHT, minutes % 5, 1, 4);
	}
	
	/**
	 * Helper function which constructs berlin clock formatted string 
	 * @param lightValue light string to use if the specified index is high
	 * @param value input value
	 * @param modValue limit on the high lights
	 * @param maxLights max lights in the row
	 * @return berlin clock formatted clock string
	 */
	private String getLightOrOffValue(String lightValue, int value, int modValue, int maxLights) {
		return getLightOrOffValue(lightValue, value, modValue, maxLights, INVALID_SPECIAL_INDEX_VALUE, null);
	}

	/**
	 * Helper function which constructs berlin clock formatted string 
	 * @param lightValue light string to use if the specified index is high
	 * @param value input value
	 * @param modValue limit on the high lights
	 * @param maxLights max lights in the row
	 * @param specialIndex special index where special light is to be used
	 * @param specialLight special light value
	 * @return berlin clock formatted clock string
	 */
	private String getLightOrOffValue(String lightValue, int value, int lightLimitValue, int maxLights, 
			int specialIndex, String specialLight) {
		
		StringBuilder builder = new StringBuilder();
		int i = 0, tmp = value / lightLimitValue, indexHandler = 1;
		
		while (i < maxLights) {
			if (i < tmp) {
				if (specialIndex != INVALID_SPECIAL_INDEX_VALUE && indexHandler % specialIndex == 0) {
					builder.append(specialLight);
					indexHandler = 1;
				} else {
					builder.append(lightValue);
					indexHandler++;
				}
			} else {
				builder.append(OFF);
			}
			i++;
		}
		
		return builder.toString();
		
	}

}
