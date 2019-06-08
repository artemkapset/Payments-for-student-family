package edu.kapset.studentorder;

import edu.kapset.studentorder.dao.DictionaryDaoImpl;
import edu.kapset.studentorder.domain.*;
import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder {

    public static void main(String[] args) throws Exception {
        List<Street> d = new DictionaryDaoImpl().findStreets("sec");
        for (Street s : d) {
            System.out.println(s.getStreetName());
        }

//        StudentOrder s = buildStudentOrder(10);
    }

    // сохранение заявки студента
    static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 199; //  answer должен хранить что-то типа "id"?
        System.out.println("saveStudentOrder");
        // ...
        return answer;
    }

    // созлание заявки
    static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7, 4));
        so.setMarriageOffice("Отдел ЗАГС");

        Street street = new Street(1L, "First street");

        Address address = new Address("195000", street, "12", "", "142");

        // Муж
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment("Отдел милиции №" + id);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);

        // Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        husband.setPassportSeria("" + (2000 + id));
        husband.setPassportNumber("" + (200000 + id));
        husband.setIssueDate(LocalDate.of(2018, 4, 5));
        husband.setIssueDepartment("Отдел милиции №" + id);
        husband.setStudentId("" + (200000 + id));
        husband.setAddress(address);

        // Ребёнок
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 7, 19));
        child1.setIssueDepartment("Отдел ЗАГС №" + id);
        child1.setAddress(address);

        // Ребёнок
        Child child2 = new Child("Петров", "Евгений", "Викторович", LocalDate.of(2018, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 7, 19));
        child2.setIssueDepartment("Отдел ЗАГС №" + id);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }
}
