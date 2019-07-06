package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.domain.StudentOrder;
import edu.kapset.studentorder.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {
    Long saveStudentOrder (StudentOrder so) throws DaoException;
    List<StudentOrder> getStudentOrders() throws DaoException;
}
