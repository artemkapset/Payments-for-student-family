package edu.kapset.studentorder;

import edu.kapset.studentorder.dao.DictionaryDaoImpl;
import edu.kapset.studentorder.dao.StudentOrderDao;
import edu.kapset.studentorder.dao.StudentOrderDaoImpl;
import edu.kapset.studentorder.domain.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SaveStudentOrder {

    public static void main(String[] args) throws Exception {

//        List<Street> d = new DictionaryDaoImpl().findStreets("про");
//        for (Street s : d) {
//            System.out.println(s.getStreetName());
//        }
//        List<PassportOffice> p = new DictionaryDaoImpl().findPassportOffice("010010000000");
//        for (PassportOffice po : p) {
//            System.out.println(po.getOfficeName());
//        }
//        List<RegisterOffice> r = new DictionaryDaoImpl().findRegisterOffice("010010000000");
//        for (RegisterOffice ro : r) {
//            System.out.println(ro.getOfficeName());
//        }
//
//        List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
//        for (CountryArea c : ca1) {
//            System.out.println(c.getAreaId() + " : " + c.getAreaName());
//        }
//        System.out.println("----->");
//        List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
//        for (CountryArea c : ca2) {
//            System.out.println(c.getAreaId() + " : " + c.getAreaName());
//        }
//        System.out.println("----->");
//        List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020010000000");
//        for (CountryArea c : ca3) {
//            System.out.println(c.getAreaId() + " : " + c.getAreaName());
//        }
//        System.out.println("----->");
//        List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020010010000");
//        for (CountryArea c : ca4) {
//            System.out.println(c.getAreaId() + " : " + c.getAreaName());
//        }
//        System.out.println("----->");

//        StudentOrder s = buildStudentOrder(10);
        StudentOrderDao dao = new StudentOrderDaoImpl();
//        Long id = dao.saveStudentOrder(s);
//        System.out.println(id);

        List<StudentOrder> soList = dao.getStudentOrders();
        for (StudentOrder so : soList) {
            System.out.println(so.getStudentOrderId());
        }
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
        RegisterOffice ro = new RegisterOffice(1L, "", "");
        so.setMarriageOffice(ro);

        Street street = new Street(1L, "First street");

        Address address = new Address("195000", street, "12", "", "142");

        University university = new University(1L, "Санкт-Петербургский Государственный Университет");

        // Муж
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        PassportOffice po1 = new PassportOffice(1L, "", "");
        husband.setIssueDepartment(po1);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUniversity(university);
        husband.setStudentId("10101010");

        // Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        PassportOffice po2 = new PassportOffice(2L, "", "");
        wife.setIssueDepartment(po2);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUniversity(university);
        wife.setStudentId("20202020");

        // Ребёнок
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice ro2 = new RegisterOffice(2L, "", "");
        child1.setIssueDepartment(ro2);
        child1.setAddress(address);

        // Ребёнок
        Child child2 = new Child("Петров", "Евгений", "Викторович", LocalDate.of(2018, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice ro3 = new RegisterOffice(3L, "", "");
        child2.setIssueDepartment(ro3);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }
}
