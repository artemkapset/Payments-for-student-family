package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.config.Config;
import edu.kapset.studentorder.domain.*;
import edu.kapset.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER = "INSERT INTO jc_student_order(\n" +
            "\tstudent_order_status,\n" +
            "\tstudent_order_date,\n" +
            "\th_sur_name,\n" +
            "\th_given_name,\n" +
            "\th_patronymic,\n" +
            "\th_date_of_birth,\n" +
            "\th_passport_seria,\n" +
            "\th_passport_number,\n" +
            "\th_passport_date,\n" +
            "\th_passport_office_id,\n" +
            "\th_post_index,\n" +
            "\th_street_code,\n" +
            "\th_building,\n" +
            "\th_extension,\n" +
            "\th_apartment,\n" +
            "\th_university_id,\n" +
            "\th_student_number,\n" +
            "\tw_sur_name,\n" +
            "\tw_given_name,\n" +
            "\tw_patronymic,\n" +
            "\tw_date_of_birth,\n" +
            "\tw_passport_seria,\n" +
            "\tw_passport_number,\n" +
            "\tw_passport_date,\n" +
            "\tw_passport_office_id,\n" +
            "\tw_post_index,\n" +
            "\tw_street_code,\n" +
            "\tw_building,\n" +
            "\tw_extension,\n" +
            "\tw_apartment,\n" +
            "\tw_university_id,\n" +
            "\tw_student_number,\n" +
            "\tcertificate_id,\n" +
            "\tregister_office_id,\n" +
            "\tmarriage_date)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?,\n" +
            "\t\t\t?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
            "\t\t\t?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
            "\t\t\t?, ?, ?, ?, ?, ?, ?, ?, ?);";

    // TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));

        return con;
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {

        Long result = -1L;  // идентификатор заявки

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     INSERT_ORDER,
                     new String[] {"student_order_id"}))
        {
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            setParamsForAdult(stmt, 3, so.getHusband());
            setParamsForAdult(stmt, 18, so.getWife());

            stmt.setString(33, so.getMarriageCertificateId());
            stmt.setLong(34, so.getMarriageOffice().getOfficeId());
            stmt.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

            stmt.executeUpdate();

            ResultSet gkRs = stmt.getGeneratedKeys();

            if (gkRs.next()) {
                result = gkRs.getLong(1);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        stmt.setString(start, adult.getSurName());
        stmt.setString(start + 1, adult.getGivenName());
        stmt.setString(start + 2, adult.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        Address address = adult.getAddress();
        stmt.setString(start + 8, address.getPostCode());
        stmt.setLong(start + 9, address.getStreet().getStreetCode());
        stmt.setString(start + 10, address.getBuilding());
        stmt.setString(start + 11, address.getExtension());
        stmt.setString(start + 12, address.getApartment());
        stmt.setLong(start + 13, adult.getUniversity().getUniversityId());
        stmt.setString(start + 14, adult.getStudentId());
    }
}
