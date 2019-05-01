package edu.kapset.studentorder.validators.register;

import edu.kapset.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;


// класс RealCityRegisterChecker используется для обращения к сервису ГРН
// и получения из него ответа о регистрации посредством метода checkPerson
public class RealCityRegisterChecker implements CityRegisterChecker {

    @Override
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        return null;
    }
}
