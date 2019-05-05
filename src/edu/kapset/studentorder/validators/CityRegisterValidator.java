package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.Person;
import edu.kapset.studentorder.domain.register.AnswerCityRegister;
import edu.kapset.studentorder.domain.Child;
import edu.kapset.studentorder.domain.register.AnswerCityRegisterItem;
import edu.kapset.studentorder.domain.register.CityRegisterResponse;
import edu.kapset.studentorder.domain.StudentOrder;
import edu.kapset.studentorder.exception.CityRegisterException;
import edu.kapset.studentorder.exception.TransportException;
import edu.kapset.studentorder.validators.register.CityRegisterChecker;
import edu.kapset.studentorder.validators.register.FakeCityRegisterChecker;

// класс, содержащий в себе логику проверки регистрации гражданина в ГРН
public class CityRegisterValidator {

    public static final String IN_CODE = "IN_CODE";

//    public String hostName;
//    protected int port;
//    private String login;
//    String password;

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();

        ans.addItem(checkPerson(so.getHusband()));
        ans.addItem(checkPerson(so.getWife()));

        for (Child child : so.getChildren()) {
            ans.addItem(checkPerson(child));
        }

        return ans;
    }

    private AnswerCityRegisterItem checkPerson (Person person) {
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;

        try {
            CityRegisterResponse tmp = personChecker.checkPerson(person);
            status = tmp.isExisting() ?
                    AnswerCityRegisterItem.CityStatus.YES : AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
        } catch (TransportException ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        }

        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(status, person, error);

        return ans;
    }
}
