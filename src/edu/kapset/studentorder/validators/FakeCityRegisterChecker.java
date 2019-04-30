package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;

/*
класс-заглушка для RealCityRegisterChecker (на случай, если, например, сервис ГРН еще в разработке
или требуются какие-либо особые условия доступа к нему и т.п.)
 */

public class FakeCityRegisterChecker implements CityRegisterChecker {

    @Override
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        return null;
    }
}
