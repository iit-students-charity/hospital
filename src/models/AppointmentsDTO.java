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

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public String getPatientStreet() {
        return patientStreet;
    }

    public String getPatientBuildingNumber() {
        return patientBuildingNumber;
    }

    public String getPatientBirthDate() {
        return patientBirthDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public String getDate() {
        return date;
    }

    public String getDiagnosis() {
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
