package edu.kapset.studentorder.validators;

import edu.kapset.studentorder.domain.AnswerCityRegister;
import edu.kapset.studentorder.domain.StudentOrder;

// класс, содержащий в себе логику проверки регистрации гражданина в ГРН
public class CityRegisterValidator {
    String hostName;
    String login;
    String password;

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        System.out.println("City register is running: " + hostName + "," + login + "," + password);
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.success = false;
        return ans;
    }
}
