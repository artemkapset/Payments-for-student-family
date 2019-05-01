package edu.kapset.studentorder.validators.register;

import edu.kapset.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson (Person person) throws CityRegisterException;
}
