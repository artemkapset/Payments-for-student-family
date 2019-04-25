package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;

/*
класс-заглушка для RealCityRegisterChecker (на случай, если, например, сервис ГРН еще в разработке
или требуются какие-либо особые условия доступа к нему и т.п.)
 */

public class FakeCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson (Person person) {


        return null;
    }
}
