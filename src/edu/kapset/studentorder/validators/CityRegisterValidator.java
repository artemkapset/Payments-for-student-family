package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.AnswerCityRegister;
import edu.kapset.studentorder.domain.StudentOrder;

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
        personChecker.checkPerson(so.getHusband());
        personChecker.checkPerson(so.getWife());
        personChecker.checkPerson(so.getChild());

        AnswerCityRegister ans = new AnswerCityRegister();
        return ans;
    }
}
