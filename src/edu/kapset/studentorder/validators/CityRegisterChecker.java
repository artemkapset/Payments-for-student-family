package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson (Person person) throws CityRegisterException;
}
