package edu.kapset.studentorder;

import edu.kapset.studentorder.domain.Adult;
import edu.kapset.studentorder.domain.StudentOrder;

public class SaveStudentOrder {

    // сохранение заявки студента
    static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 1000; //  answer должен хранить что-то типа "id"?
        // ...
        return answer;
    }

    // созлание заявки
    static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        return so;
    }
}
