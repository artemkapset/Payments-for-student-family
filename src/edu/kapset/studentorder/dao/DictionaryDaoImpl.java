package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.config.Config;
import edu.kapset.studentorder.domain.CountryArea;
import edu.kapset.studentorder.domain.PassportOffice;
import edu.kapset.studentorder.domain.RegisterOffice;
import edu.kapset.studentorder.domain.Street;
import edu.kapset.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street WHERE UPPER (street_name) LIKE UPPER (?)";
    // знак "?" означает параметризацию,
    // в качестве параметра далее будет использован аргумент String pattern

    private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office WHERE p_office_area_id = ?";

    private static final String GET_REGISTER = "SELECT * FROM jc_register_office WHERE r_office_area_id = ?";

    private static final String GET_AREA = "SELECT * FROM jc_country_struct WHERE area_id LIKE ? AND area_id <> ?";


    private Connection getConnection() throws SQLException {
        /*
        загрузка драйвера - "заставляем" драйвер postgres зарегистрироваться в подсистеме JDBC
        Class.forName("org.postgresql.Driver"); // загрузка класса по имени (...рефлексия)
        */

        // получение соединения с базой данных
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));

        return con;
    }


    @Override
    public List<Street> findStreets(String pattern) throws DaoException {

        List<Street> result = new LinkedList<>();

        try (Connection con = getConnection();
             // создание запроса
             PreparedStatement stmt = con.prepareStatement(GET_STREET))
        // данная конструкция "try-with-resources" позволяет
        // автоматически закрывать соединения с БД после выхода из блока try

        {
            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Street str = new Street(
                        rs.getLong("street_code"),
                        rs.getString("street_name"));
                result.add(str);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffice(String areaId) throws DaoException {

        List<PassportOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT))
        {
            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PassportOffice str = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name"));
                result.add(str);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException {

        List<RegisterOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER))
        {
            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegisterOffice str = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
                result.add(str);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_AREA))
        {
            String param1 = buildParam(areaId);
            String param2 = areaId;

            stmt.setString(1, param1);
            stmt.setString(2, areaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CountryArea str = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name"));
                result.add(str);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

//    метод buildParam формирует параметр для запросов:
//    SELECT * FROM jc_country_struct WHERE area_id LIKE '__0000000000' AND area_id <> ''
//    SELECT * FROM jc_country_struct WHERE area_id LIKE '02___0000000' AND area_id <> '020000000000'
//    SELECT * FROM jc_country_struct WHERE area_id LIKE '02001___0000' AND area_id <> '020010000000'
//    SELECT * FROM jc_country_struct WHERE area_id LIKE '02001001____' AND area_id <> '020010010000'

    private String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0, 2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0, 5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0, 8) + "____";
        }

        throw new SQLException("Invalid areaId: " + areaId);
    }
}
