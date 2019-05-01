package edu.kapset.studentorder.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentOrder {
    private long studentOrderId;    // идентификатор студенческой заявки
    private Adult husband;          // муж
    private Adult wife;             // жена
    private List<Child> children;   // дети
    private String marriageCertificateId; // свидетельство о браке
    private String marriageOffice;  // ЗАГС
    private LocalDate marriageDate; // дата заключения брака

    public long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild (Child child) {    // метод addChild вместо setChild
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public String getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(String marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }
}
