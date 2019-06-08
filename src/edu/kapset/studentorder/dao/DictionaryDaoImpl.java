package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.domain.Street;
import edu.kapset.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street WHERE UPPER (street_name) LIKE UPPER (?)";
    // знак "?" означает параметризацию,
    // в качестве параметра далее будет использован аргумент String pattern


    private Connection getConnection() throws SQLException {
        /*
        загрузка драйвера - "заставляем" драйвер postgres зарегистрироваться в подсистеме JDBC
        Class.forName("org.postgresql.Driver"); // загрузка класса по имени (...рефлексия)
        */

        // получение соединения с базой данных
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jc_student",
                "postgres",
                "9na7RAVNO63");
        return con;
    }


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
}
