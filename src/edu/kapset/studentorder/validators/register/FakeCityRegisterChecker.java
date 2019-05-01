package edu.kapset.studentorder.validators.register;

import edu.kapset.studentorder.domain.Adult;
import edu.kapset.studentorder.domain.Child;
import edu.kapset.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.exception.CityRegisterException;

/*
класс-заглушка для RealCityRegisterChecker (на случай, если, например, сервис ГРН еще в разработке
или требуются какие-либо особые условия доступа к нему и т.п.)
 */

public class FakeCityRegisterChecker implements CityRegisterChecker {
    public static final String GOOD_1 = "1000";
    public static final String GOOD_2 = "2000";
    public static final String BAD_1 = "1001";
    public static final String BAD_2 = "2001";
    public static final String ERROR_1 = "1002";
    public static final String ERROR_2 = "2002";

    @Override
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {

        CityRegisterCheckerResponse res = new CityRegisterCheckerResponse();

        if (person instanceof Adult) {
            Adult t = (Adult) person; // выполнятеся приведение типов для доступа к методам класса Adult
            String ps = t.getPassportSeria();
            if (ps.equals(GOOD_1) || ps.equals(GOOD_2)) {
                res.setExisting(true);
                res.setTemporal(false);
            }
            if (ps.equals(BAD_1) || ps.equals(BAD_2)) {
                res.setExisting(false);
            }
            if (ps.equals(ERROR_1) || ps.equals(ERROR_2)) {
                CityRegisterException ex = new CityRegisterException("Fake Error");
                throw ex;
            }
        }
        if (person instanceof Child) {
            res.setExisting(true);
            res.setTemporal(true);
        }
        return res;
    }
}
