package edu.kapset.studentorder.domain;

import java.time.LocalDate;

public abstract class Person {
    protected String surName;         // фамилия
    protected String givenName;       // имя
    private String patronymic;      // отчество
    private LocalDate dateOfBirth;  // дата рождения
    private Address address;        // адрес

    public Person() {
        System.out.println("Person is created");
    }

    public String getPersonString() {
        return surName + givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}