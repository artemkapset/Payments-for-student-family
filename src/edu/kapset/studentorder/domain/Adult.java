package edu.kapset.studentorder.domain;

import java.time.LocalDate;

public class Adult extends Person {
    private String passportSeria;       // серия паспорта
    private String passportNumber;      // номер паспорта
    private LocalDate issueDate;        // дата выдачи паспорта
    private String issueDepartment;     // кем выдан паспорт
    private String university;          // учебное заведение
    private String studentId;           // номер студ. билета

    public Adult() {
        System.out.println("Adult is created");
    }

    @Override
    public String getPersonString() {
        return surName + givenName + passportNumber;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
