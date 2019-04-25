package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;

public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson (Person person);
}
