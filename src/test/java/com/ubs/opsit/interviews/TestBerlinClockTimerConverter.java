/*
 * Name: TestBerlinClockTimerConverter.java
 *
 * Created by saching on May 6, 2018
 *
 */
package com.ubs.opsit.interviews;

import static org.junit.Assert.assertEquals;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.opsit.interviews.exceptions.ErrorCode;
import com.ubs.opsit.interviews.exceptions.ValidationException;

/**
 * @author saching
 *
 */
public class TestBerlinClockTimerConverter {

	private BerlinClockTimerConverter timeConverter = new BerlinClockTimerConverter();
	
	@Rule
	public ExpectedException rule = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		rule = ExpectedException.none();
	}

	/**
	 * This test case validates if the empty string is given as input then the exception is thrown
	 */
	@Test
	public final void testGetValidatedInputTimeEmptyString() throws ValidationException {
		rule.expect(new IsEqual<ValidationException>(new ValidationException(ErrorCode.INVALID_TIME_STRING)));
		timeConverter.getValidatedInputTime("");
	}

	/**
	 * This test case validates if the invalid time format is given as input then the exception is thrown
	 */
	@Test
	public final void testGetValidatedInputTimeInvalidTimeFormat() throws ValidationException {
		rule.expect(new IsEqual<ValidationException>(new ValidationException(ErrorCode.INVALID_TIME_STRING)));
		timeConverter.getValidatedInputTime("00:22:33:22");
	}

	/**
	 * This test case validates if the null string is given as input then the exception is thrown
	 */
	@Test
	public final void testGetValidatedInputTimeNullString() throws ValidationException {
		rule.expect(new IsEqual<ValidationException>(new ValidationException(ErrorCode.INVALID_TIME_STRING)));
		timeConverter.getValidatedInputTime(null);
	}

	/**
	 * This test case validates if the correct string is given as input then no exception is thrown
	 */
	@Test
	public final void testGetValidatedInputTimeValidInput() throws ValidationException {
		timeConverter.getValidatedInputTime("12:33:44");
	}

	/**
	 * This test case validates if the incorrect hour value is given then exception is thrown
	 * @throws ValidationException 
	 */
	@Test
	public final void testGetValidatedTimeRangeNegativeHours() throws ValidationException {
		rule.expect(new IsEqual<ValidationException>(new ValidationException(ErrorCode.INVALID_HOUR_VALUE)));
		timeConverter.getValidatedTimeRange("-4", 0, 23, ErrorCode.INVALID_HOUR_VALUE);
	}

	/**
	 * This test case validates if the incorrect hour value is given then exception is thrown
	 * @throws ValidationException 
	 */
	@Test
	public final void testGetValidatedTimeRangeOutOfRangeHours() throws ValidationException {
		rule.expect(new IsEqual<ValidationException>(new ValidationException(ErrorCode.INVALID_HOUR_VALUE)));
		timeConverter.getValidatedTimeRange("24", 0, 23, ErrorCode.INVALID_HOUR_VALUE);
	}

	/**
	 * This test case validates if the invalid hour value is given then exception is thrown
	 * @throws ValidationException 
	 */
	@Test
	public final void testGetValidatedTimeRangeInvalidHours() throws ValidationException {
		rule.expect(new IsEqual<ValidationException>(new ValidationException(ErrorCode.INVALID_HOUR_VALUE)));
		timeConverter.getValidatedTimeRange("abc", 0, 23, ErrorCode.INVALID_HOUR_VALUE);
	}

	/**
	 * This test case validates if the correct hour value is given then no exception is thrown
	 */
	@Test
	public final void testGetValidatedTimeRangeValidHours() throws ValidationException {
		timeConverter.getValidatedTimeRange("12", 0, 23, ErrorCode.INVALID_HOUR_VALUE);
		timeConverter.getValidatedTimeRange("20", 0, 23, ErrorCode.INVALID_HOUR_VALUE);
	}
	
	/**
	 * This test case validates the seconds to berlin clock seconds light value conversion
	 */
	@Test
	public final void testGetBerlinClockSecondValue() {
		String clockSecondValue = timeConverter.getBerlinClockSecondValue(30);
		assertEquals("Y", clockSecondValue);

		clockSecondValue = timeConverter.getBerlinClockSecondValue(3);
		assertEquals("O", clockSecondValue);
	}
	
	/**
	 * This test case validates the first hour line conversion of the berlin clock
	 */
	@Test
	public final void testGetBerlinClockFirstHoursLine() {
		String firstLine = timeConverter.getBerlinClockFirstHoursLine(3);
		assertEquals("OOOO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstHoursLine(6);
		assertEquals("ROOO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstHoursLine(10);
		assertEquals("RROO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstHoursLine(16);
		assertEquals("RRRO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstHoursLine(22);
		assertEquals("RRRR", firstLine);
	}
	
	/**
	 * This test case validates the second hour line conversion of the berlin clock
	 */
	@Test
	public final void testGetBerlinClockSecondHoursLine() {
		String secondLine = timeConverter.getBerlinClockSecondHoursLine(14);
		assertEquals("RRRR", secondLine);
		
		secondLine = timeConverter.getBerlinClockSecondHoursLine(13);
		assertEquals("RRRO", secondLine);

		secondLine = timeConverter.getBerlinClockSecondHoursLine(12);
		assertEquals("RROO", secondLine);
		
		secondLine = timeConverter.getBerlinClockSecondHoursLine(11);
		assertEquals("ROOO", secondLine);

		secondLine = timeConverter.getBerlinClockSecondHoursLine(10);
		assertEquals("OOOO", secondLine);
	}

	/**
	 * This test case validates the first minute line conversion of the berlin clock
	 */
	@Test
	public final void testGetBerlinClockFirstMinutesLine() {
		String firstLine = timeConverter.getBerlinClockFirstMinutesLine(3);
		assertEquals("OOOOOOOOOOO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstMinutesLine(6);
		assertEquals("YOOOOOOOOOO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstMinutesLine(12);
		assertEquals("YYOOOOOOOOO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstMinutesLine(17);
		assertEquals("YYROOOOOOOO", firstLine);

		firstLine = timeConverter.getBerlinClockFirstMinutesLine(59);
		assertEquals("YYRYYRYYRYY", firstLine);
	}
	
	/**
	 * This test case validates the second minute line conversion of the berlin clock
	 */
	@Test
	public final void testGetBerlinClockSecondMinuteLine() {
		String secondLine = timeConverter.getBerlinClockSecondMinuteLine(5);
		assertEquals("OOOO", secondLine);

		secondLine = timeConverter.getBerlinClockSecondMinuteLine(4);
		assertEquals("YYYY", secondLine);

		secondLine = timeConverter.getBerlinClockSecondMinuteLine(3);
		assertEquals("YYYO", secondLine);

		secondLine = timeConverter.getBerlinClockSecondMinuteLine(2);
		assertEquals("YYOO", secondLine);

		secondLine = timeConverter.getBerlinClockSecondMinuteLine(1);
		assertEquals("YOOO", secondLine);

		secondLine = timeConverter.getBerlinClockSecondMinuteLine(0);
		assertEquals("OOOO", secondLine);
	}
	
}
