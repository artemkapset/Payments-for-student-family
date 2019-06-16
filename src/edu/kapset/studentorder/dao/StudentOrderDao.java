package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.domain.StudentOrder;
import edu.kapset.studentorder.exception.DaoException;

public interface StudentOrderDao {
    Long saveStudentOrder (StudentOrder so) throws DaoException;
}
