package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.exceptions.ValidationException;

public interface TimeConverter {

    String convertTime(String aTime) throws ValidationException;

}
