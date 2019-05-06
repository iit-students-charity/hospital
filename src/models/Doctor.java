package models;

class Doctor {
    private String name;
    private String surname;

    Doctor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    Doctor() {
        this("", "");
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

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }
}
