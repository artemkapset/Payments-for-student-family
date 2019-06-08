package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.domain.Street;
import edu.kapset.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    public List<Street> findStreets(String pattern) throws DaoException;
}
