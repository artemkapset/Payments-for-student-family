package edu.kapset.studentorder.validators.register;

import edu.kapset.studentorder.domain.register.CityRegisterResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;
import edu.kapset.studentorder.exception.TransportException;


// класс RealCityRegisterChecker используется для обращения к сервису ГРН
// и получения из него ответа о регистрации посредством метода checkPerson
public class RealCityRegisterChecker implements CityRegisterChecker {

    @Override
    public CityRegisterResponse checkPerson(Person person)
            throws CityRegisterException, TransportException {
        return null;
    }
}
