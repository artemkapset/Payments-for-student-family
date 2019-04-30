package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.AnswerCityRegister;
import edu.kapset.studentorder.domain.CityRegisterCheckerResponse;
import edu.kapset.studentorder.domain.StudentOrder;
import edu.kapset.studentorder.exception.CityRegisterException;

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
            CityRegisterCheckerResponse cans = personChecker.checkPerson(so.getChild());
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }


        AnswerCityRegister ans = new AnswerCityRegister();
        return ans;
    }
}
