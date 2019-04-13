package edu.kapset.studentorder;

import edu.kapset.studentorder.domain.*;
import edu.kapset.studentorder.mail.MailSender;
import edu.kapset.studentorder.validators.ChildrenValidator;
import edu.kapset.studentorder.validators.CityRegisterValidator;
import edu.kapset.studentorder.validators.StudentValidator;
import edu.kapset.studentorder.validators.WeddingValidator;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator() {
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {

    }

    public void checkAll() {
        StudentOrder[] soArray = readStudentOrders(); // "считывание" массива заявок
        for (int c = 0; c < soArray.length; c++) {
            checkOneOrder(soArray[c]);
        }
    }

    public void checkOneOrder(StudentOrder so) {
        /*
        Результаты проверки регистрации в данном городе, регисттрации брака,
        наличия детей и являются ли заявители студентами будут храниться
        в объектах, для которых созданы соответствующие классы.
        Эти объекты создаются вызовом соотв. методов, параметром для которых
        является заявка edu.kapset.studentorder.domain.StudentOrder
         */
        AnswerCityRegister cityRegister = checkCityRegister(so);
        AnswerWedding wedAnswer = checkWedding(so);
        AnswerChildren childAnswer = checkChildren(so);
        AnswerStudent studentAnswer = checkStudent(so);

        // отправка e-mail с результатами проверки и др. информацией
        sendMail(so);
    }

    public StudentOrder[] readStudentOrders() {
        StudentOrder[] soArray = new StudentOrder[3];
        for (int c = 0; c < soArray.length; c++) {
            soArray[c] = SaveStudentOrder.buildStudentOrder(c);
        }
        return soArray;
    }

    // проверка в ГРН
    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        return cityRegisterVal.checkCityRegister(so);
    }

    // проверка регистрации брака
    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingVal.checkWedding(so);
    }

    // проверка наличия детей
    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenVal.checkChildren(so);
    }

    // проверка, что гражданин является студентом
    public AnswerStudent checkStudent(StudentOrder so) {
        return studentVal.checkStudent(so);
    }

    // отправить e-mail
    public void sendMail(StudentOrder so) {
        mailSender.sendMail(so);
    }
}
