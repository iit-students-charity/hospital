package models;

public class AppointmentsDTO {
    private String patientName;
    private String patientSurname;
    private String patientCity;
    private String patientStreet;
    private String patientBuildingNumber;
    private String patientBirthDate;
    private String doctorName;
    private String doctorSurname;
    private String date;
    private String diagnosis;

    public AppointmentsDTO() {
    }

    public AppointmentsDTO(String patientName, String patientSurname, String patientCity, String patientStreet, String patientBuildingNumber, String patientBirthDate, String doctorName, String doctorSurname, String date, String diagnosis) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.patientCity = patientCity;
        this.patientStreet = patientStreet;
        this.patientBuildingNumber = patientBuildingNumber;
        this.patientBirthDate = patientBirthDate;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.date = date;
        this.diagnosis = diagnosis;
    }

    String getPatientName() {
        return patientName;
    }

    String getPatientSurname() {
        return patientSurname;
    }

    String getPatientCity() {
        return patientCity;
    }

    String getPatientStreet() {
        return patientStreet;
    }

    String getPatientBuildingNumber() {
        return patientBuildingNumber;
    }

    String getPatientBirthDate() {
        return patientBirthDate;
    }

    String getDoctorName() {
        return doctorName;
    }

    String getDoctorSurname() {
        return doctorSurname;
    }

    String getDate() {
        return date;
    }

    String getDiagnosis() {
        return diagnosis;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public void setPatientStreet(String patientStreet) {
        this.patientStreet = patientStreet;
    }

    public void setPatientBuildingNumber(String patientBuildingNumber) {
        this.patientBuildingNumber = patientBuildingNumber;
    }

    public void setPatientBirthDate(String patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
