/*
 * Name: BerlinClock.java
 *
 * Created by saching on May 6, 2018
 *
 */
package com.ubs.opsit.interviews.model;

/**
 * @author saching
 *
 */
public class BerlinClock {

	private String newLineSeparator;
	private String seconds;
	private String hoursFirstRow;
	private String hoursSecondRow;
	private String minutesFirstRow;
	private String minutesSecondRow;
	
	/**
	 * Constructs empty berlin clock with given newline separator
	 */
	public BerlinClock(String newLineSeparator) {
		this.newLineSeparator = newLineSeparator;
	}
	
	/**
	 * Constructs a filled berlin clock with given values
	 * @param newLineSeparator
	 * @param seconds
	 * @param hoursFirstRow
	 * @param hoursSecondRow
	 * @param minutesFirstRow
	 * @param minutesSecondRow
	 */
	public BerlinClock(String newLineSeparator, String seconds, String hoursFirstRow, String hoursSecondRow,
			String minutesFirstRow, String minutesSecondRow) {
		super();
		this.newLineSeparator = newLineSeparator;
		this.seconds = seconds;
		this.hoursFirstRow = hoursFirstRow;
		this.hoursSecondRow = hoursSecondRow;
		this.minutesFirstRow = minutesFirstRow;
		this.minutesSecondRow = minutesSecondRow;
	}



	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getHoursFirstRow() {
		return hoursFirstRow;
	}

	public void setHoursFirstRow(String hoursFirstRow) {
		this.hoursFirstRow = hoursFirstRow;
	}

	public String getHoursSecondRow() {
		return hoursSecondRow;
	}

	public void setHoursSecondRow(String hoursSecondRow) {
		this.hoursSecondRow = hoursSecondRow;
	}

	public String getMinutesFirstRow() {
		return minutesFirstRow;
	}

	public void setMinutesFirstRow(String minutesFirstRow) {
		this.minutesFirstRow = minutesFirstRow;
	}

	public String getMinutesSecondRow() {
		return minutesSecondRow;
	}

	public void setMinutesSecondRow(String minutesSecondRow) {
		this.minutesSecondRow = minutesSecondRow;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append(seconds).append(newLineSeparator)
				.append(hoursFirstRow).append(newLineSeparator)
				.append(hoursSecondRow).append(newLineSeparator)
				.append(minutesFirstRow).append(newLineSeparator)
				.append(minutesSecondRow).toString();
	}
	
}
