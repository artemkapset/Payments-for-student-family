package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.register.AnswerCityRegister;
import edu.kapset.studentorder.domain.Child;
import edu.kapset.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.StudentOrder;
import edu.kapset.studentorder.exception.CityRegisterException;
import edu.kapset.studentorder.validators.register.CityRegisterChecker;
import edu.kapset.studentorder.validators.register.FakeCityRegisterChecker;

import java.util.List;

// класс, содержащий в себе логику проверки регистрации гражданина в ГРН
public class CityRegisterValidator {
    public String hostName;
    protected int port;
    private String login;
    String password;
    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        try {
            CityRegisterCheckerResponse hans = personChecker.checkPerson(so.getHusband());
            CityRegisterCheckerResponse wans = personChecker.checkPerson(so.getWife());

            List<Child> children = so.getChildren();

            for (Child child : children) {
                CityRegisterCheckerResponse cans = personChecker.checkPerson(child);
            }
        } catch (CityRegisterException e) {
            e.printStackTrace(System.out);
        }


        AnswerCityRegister ans = new AnswerCityRegister();
        return ans;
    }
}
