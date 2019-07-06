package edu.kapset.studentorder.dao;

import edu.kapset.studentorder.config.Config;
import edu.kapset.studentorder.domain.*;
import edu.kapset.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final String INSERT_CHILD = "INSERT INTO jc_student_child(\n" +
            "\tstudent_order_id,\n" +
            "\tc_sur_name,\n" +
            "\tc_given_name,\n" +
            "\tc_patronymic,\n" +
            "\tc_date_of_birth,\n" +
            "\tc_certificate_number,\n" +
            "\tc_certificate_date,\n" +
            "\tc_register_office_id,\n" +
            "\tc_post_index, c_street_code,\n" +
            "\tc_building,\n" +
            "\tc_extension,\n" +
            "\tc_apartment)\n" +
            "\tVALUES (?, ?, ?,\n" +
            "\t\t\t?, ?, ?, ?,\n" +
            "\t\t\t?, ?, ?, ?,\n" +
            "\t\t\t?, ?);";


    private static final String SELECT_ORDERS =
                    "SELECT so.*, ro.r_office_area_id, ro.r_office_name,\n" +
                    "po_h.p_office_area_id AS h_p_office_area_id, po_h.p_office_name AS h_p_office_name,\n" +
                    "po_w.p_office_area_id AS w_p_office_area_id, po_w.p_office_name AS w_p_office_name\n" +
                    "FROM jc_student_order so\n" +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id\n" +
                    "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id\n" +
                    "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id\n" +
                    "WHERE student_order_status = ? ORDER BY student_order_date LIMIT ?";

    private static final String SELECT_CHILD =
                    "SELECT soc.*, ro.r_office_area_id, ro.r_office_name\n" +
                    "FROM jc_student_child soc\n" +
                    "INNER JOIN jc_register_office ro ON soc.c_register_office_id = ro.r_office_id\n" +
                    "WHERE soc.student_order_id IN";

    private static final String SELECT_ORDERS_FULL =
                    "SELECT so.*, ro.r_office_area_id, ro.r_office_name,\n" +
                    "po_h.p_office_area_id AS h_p_office_area_id, po_h.p_office_name AS h_p_office_name,\n" +
                    "po_w.p_office_area_id AS w_p_office_area_id, po_w.p_office_name AS w_p_office_name,\n" +
                    "soc.*, ro_c.r_office_area_id, ro_c.r_office_name\n" +
                    "FROM jc_student_order so\n" +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id\n" +
                    "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id\n" +
                    "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id\n" +
                    "INNER JOIN jc_student_child soc ON soc.student_order_id = so.student_order_id\n" +
                    "INNER JOIN jc_register_office ro_c ON ro_c.r_office_id = soc.c_register_office_id\n" +
                    "WHERE student_order_status = ? ORDER BY student_order_date LIMIT ?";


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
            con.setAutoCommit(false);   // данное выражение необходимо для запуска транзакции

            try {

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
                gkRs.close();

                saveChildren(con, so, result);

                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    private void saveChildren(Connection con, StudentOrder so, Long soId) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
            for (Child child : so.getChildren()) {
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException {
        setParamsForPerson(stmt, 2, child);
        stmt.setString(6, child.getCertificateNumber());
        stmt.setDate(7, Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, 9, child);
    }

    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        setParamsForPerson(stmt, start, adult);

        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());

        setParamsForAddress(stmt, start + 8, adult);

        stmt.setLong(start + 13, adult.getUniversity().getUniversityId());
        stmt.setString(start + 14, adult.getStudentId());
    }

    private void setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address address = person.getAddress();
        stmt.setString(start, address.getPostCode());
        stmt.setLong(start + 1, address.getStreet().getStreetCode());
        stmt.setString(start + 2, address.getBuilding());
        stmt.setString(start + 3, address.getExtension());
        stmt.setString(start + 4, address.getApartment());
    }

    private void setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start, person.getSurName());
        stmt.setString(start + 1, person.getGivenName());
        stmt.setString(start + 2, person.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(person.getDateOfBirth()));
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {

//        return getStudentOrdersOneSelect();
        return getStudentOrdersTwoSelect();
    }

    private List<StudentOrder> getStudentOrdersOneSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS_FULL)) {

            Map<Long, StudentOrder> maps = new HashMap<>();

            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            int limit = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
            stmt.setInt(2, limit);

            ResultSet rs = stmt.executeQuery();

            int counter = 0;

            while (rs.next()) {
                Long soId = rs.getLong("student_order_id");

                if(!maps.containsKey(soId)) {
                    StudentOrder so = getFullStudentOrder(rs);
                    result.add(so);
                    maps.put(soId, so);
                }

                StudentOrder so = maps.get(soId);
                so.addChild(fillChild(rs));

                counter++;

                if(counter >= limit) {
                    result.remove(result.size() - 1);
                }
            }

            rs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    private List<StudentOrder> getStudentOrdersTwoSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)) {

            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setInt(2, Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentOrder so = getFullStudentOrder(rs);

                result.add(so);
            }

            findChildren(con, result);
            rs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    private void findChildren(Connection con, List<StudentOrder> result) throws SQLException {
        String cl = "(" + result.stream().map(so -> String.valueOf(so.getStudentOrderId())).
                collect(Collectors.joining(",")) + ")";

        Map<Long, StudentOrder> maps = result.stream().
                collect(Collectors.toMap(so -> so.getStudentOrderId(), so -> so));

        try (PreparedStatement stmt = con.prepareStatement(SELECT_CHILD + cl)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Child ch = fillChild(rs);
                StudentOrder so = maps.get(rs.getLong("student_order_id"));
                so.addChild(ch);
            }
        }
    }

    private Child fillChild(ResultSet rs) throws SQLException {
        String surName = rs.getString("c_sur_name");
        String givenName = rs.getString("c_given_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_date_of_birth").toLocalDate();

        Child child = new Child(surName, givenName, patronymic, dateOfBirth);

        Long roId = rs.getLong("c_register_office_id");
        String roArea = rs.getString("r_office_area_id");
        String roName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, roName, roArea);
        child.setIssueDepartment(ro);

        Address adr = new Address();
        Street street = new Street(rs.getLong("c_street_code"), "");
        adr.setStreet(street);
        adr.setPostCode(rs.getString("c_post_index"));
        adr.setBuilding(rs.getString("c_building"));
        adr.setExtension(rs.getString("c_extension"));
        adr.setApartment(rs.getString("c_apartment"));
        child.setAddress(adr);

        return child;
    }

    private Adult fillAdult(ResultSet rs, String pref) throws SQLException {
        Adult adult = new Adult();

        adult.setSurName(rs.getString(pref + "sur_name"));
        adult.setGivenName(rs.getString(pref + "given_name"));
        adult.setPatronymic(rs.getString(pref + "patronymic"));
        adult.setDateOfBirth(rs.getDate(pref + "date_of_birth").toLocalDate());
        adult.setPassportSeria(rs.getString(pref + "passport_seria"));
        adult.setPassportNumber(rs.getString(pref + "passport_number"));
        adult.setIssueDate(rs.getDate(pref + "passport_date").toLocalDate());

        Long poId = rs.getLong(pref + "passport_office_id");
        String poArea = rs.getString(pref + "p_office_area_id");
        String poName = rs.getString(pref + "p_office_name");
        PassportOffice po = new PassportOffice(poId, poArea, poName);
        adult.setIssueDepartment(po);

        Address adr = new Address();
        Street street = new Street(rs.getLong(pref + "street_code"), "");
        adr.setStreet(street);
        adr.setPostCode(rs.getString(pref + "post_index"));
        adr.setBuilding(rs.getString(pref + "building"));
        adr.setExtension(rs.getString(pref + "extension"));
        adr.setApartment(rs.getString(pref + "apartment"));
        adult.setAddress(adr);

        University university = new University(
                rs.getLong(pref + "university_id"), "");
        adult.setUniversity(university);
        adult.setStudentId(rs.getString(pref + "student_number"));

        return adult;
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long roId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, areaId, name);
        so.setMarriageOffice(ro);
    }

    private StudentOrder getFullStudentOrder(ResultSet rs) throws SQLException {
        StudentOrder so = new StudentOrder();

        fillStudentOrder(rs, so);
        fillMarriage(rs, so);

        so.setHusband(fillAdult(rs, "h_"));
        so.setWife(fillAdult(rs, "w_"));
        return so;
    }
}
