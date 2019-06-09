package edu.kapset.studentorder.domain;

import java.time.LocalDate;

public class Child extends Person {
    private String certificateNumber;   // номер свидетельства о рождении
    private LocalDate issueDate;        // дата выдачи свидетельства о рождении
    private RegisterOffice issueDepartment;     // кем выдано свидетельство о рождении

    public Child() {
    }

    public Child(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public RegisterOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(RegisterOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
