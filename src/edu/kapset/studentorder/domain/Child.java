package edu.kapset.studentorder.domain;

import java.time.LocalDate;

public class Child extends Person {
    private String certificateNumber;   // номер свидетельства о рождении
    private LocalDate issueDate;        // дата выдачи свидетельства о рождении
    private String issueDepartment;     // кем выдано свидетельство о рождении

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

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
