package models;

import java.util.Date;

class Patient {
    private String name;
    private String surname;
    private Address address;
    private Date birthDate;

    Patient(String name, String surname, Date birthDate, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.birthDate = birthDate;
    }

    Patient() {
        this("", "", new Date(), new Address());
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    String getFullName() {
        return name + " " + surname;
    }

    Address getAddress() {
        return address;
    }

    String getFullAddress() {
        return address.getFullAddress();
    }

    Date getBirthDate() {
        return birthDate;
    }

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setAddress(Address address) {
        this.address = address;
    }

    void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
