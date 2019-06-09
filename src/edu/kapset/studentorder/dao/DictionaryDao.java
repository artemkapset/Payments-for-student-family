package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.domain.CountryArea;
import edu.kapset.studentorder.domain.PassportOffice;
import edu.kapset.studentorder.domain.RegisterOffice;
import edu.kapset.studentorder.domain.Street;
import edu.kapset.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    public List<Street> findStreets(String pattern) throws DaoException;
    public List<PassportOffice> findPassportOffice(String areaId) throws DaoException;
    public List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException;
    public List<CountryArea> findAreas(String areaId) throws DaoException;
}
