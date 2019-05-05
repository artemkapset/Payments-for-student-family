package edu.kapset.studentorder.validators.register;

import edu.kapset.studentorder.domain.register.CityRegisterResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;
import edu.kapset.studentorder.exception.TransportException;

public interface CityRegisterChecker {
    CityRegisterResponse checkPerson (Person person)
            throws CityRegisterException, TransportException;
}
